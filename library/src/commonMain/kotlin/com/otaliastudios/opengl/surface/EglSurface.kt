package com.otaliastudios.opengl.surface

import android.graphics.Bitmap
import android.opengl.EGLSurface
import android.opengl.GLES20
import com.otaliastudios.opengl.core.EglCore
import com.otaliastudios.opengl.core.Egloo
import com.otaliastudios.opengl.internal.EGL_HEIGHT
import com.otaliastudios.opengl.internal.EGL_NO_SURFACE
import com.otaliastudios.opengl.internal.EGL_WIDTH
import com.otaliastudios.opengl.internal.EglSurface
import java.io.*
import java.nio.ByteBuffer
import java.nio.ByteOrder
import kotlin.jvm.Throws

public abstract class EglSurface internal constructor(
    eglCore: EglCore,
    eglSurface: EglSurface
) : EglNativeSurface(eglCore, eglSurface) {

    @Suppress("unused")
    protected constructor(eglCore: EglCore, eglSurface: EGLSurface)
            : this(eglCore, EglSurface(eglSurface))

    /**
     * Saves the EGL surface to the given output stream.
     * Expects that this object's EGL surface is current.
     */
    @Suppress("MemberVisibilityCanBePrivate")
    public fun toOutputStream(stream: OutputStream, format: Bitmap.CompressFormat = Bitmap.CompressFormat.PNG) {
        if (!isCurrent()) throw RuntimeException("Expected EGL context/surface is not current")
        // glReadPixels fills in a "direct" ByteBuffer with what is essentially big-endian RGBA
        // data (i.e. a byte of red, followed by a byte of green...).  While the Bitmap
        // constructor that takes an int[] wants little-endian ARGB (blue/red swapped), the
        // Bitmap "copy pixels" method wants the same format GL provides.
        //
        // Making this even more interesting is the upside-down nature of GL, which means
        // our output will look upside down relative to what appears on screen if the
        // typical GL conventions are used.
        val width = getWidth()
        val height = getHeight()
        val buf = ByteBuffer.allocateDirect(width * height * 4)
        buf.order(ByteOrder.LITTLE_ENDIAN)
        GLES20.glReadPixels(0, 0, width, height, GLES20.GL_RGBA, GLES20.GL_UNSIGNED_BYTE, buf)
        Egloo.checkGlError("glReadPixels")
        buf.rewind()
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        bitmap.copyPixelsFromBuffer(buf)
        bitmap.compress(format, 90, stream)
        bitmap.recycle()
    }

    /**
     * Saves the EGL surface to a file.
     * Expects that this object's EGL surface is current.
     */
    @Suppress("unused")
    @Throws(IOException::class)
    public fun toFile(file: File, format: Bitmap.CompressFormat = Bitmap.CompressFormat.PNG) {
        var stream: BufferedOutputStream? = null
        try {
            stream = BufferedOutputStream(FileOutputStream(file.toString()))
            toOutputStream(stream, format)
        } finally {
            stream?.close()
        }
    }

    /**
     * Saves the EGL surface to given format.
     * Expects that this object's EGL surface is current.
     */
    @Suppress("unused")
    public fun toByteArray(format: Bitmap.CompressFormat = Bitmap.CompressFormat.PNG): ByteArray {
        val stream = ByteArrayOutputStream()
        stream.use {
            toOutputStream(it, format)
            return it.toByteArray()
        }
    }

    public companion object {
        @Suppress("HasPlatformType", "unused")
        protected val TAG: String = EglSurface::class.java.simpleName
    }
}

public open class EglNativeSurface internal constructor(
        internal var eglCore: EglCore,
        internal var eglSurface: EglSurface) {

    private var width = -1
    private var height = -1

    /**
     * Can be called by subclasses whose width is guaranteed to never change,
     * so we can cache this value. For window surfaces, this should not be called.
     */
    @Suppress("unused")
    protected fun setWidth(width: Int) {
        this.width = width
    }

    /**
     * Can be called by subclasses whose height is guaranteed to never change,
     * so we can cache this value. For window surfaces, this should not be called.
     */
    @Suppress("unused")
    protected fun setHeight(height: Int) {
        this.height = height
    }

    /**
     * Returns the surface's width, in pixels.
     *
     * If this is called on a window surface, and the underlying surface is in the process
     * of changing size, we may not see the new size right away (e.g. in the "surfaceChanged"
     * callback).  The size should match after the next buffer swap.
     */
    @Suppress("MemberVisibilityCanBePrivate")
    public fun getWidth(): Int {
        return if (width < 0) {
            eglCore.querySurface(eglSurface, EGL_WIDTH)
        } else {
            width
        }
    }

    /**
     * Returns the surface's height, in pixels.
     */
    @Suppress("MemberVisibilityCanBePrivate")
    public fun getHeight(): Int {
        return if (height < 0) {
            eglCore.querySurface(eglSurface, EGL_HEIGHT)
        } else {
            height
        }
    }

    /**
     * Release the EGL surface.
     */
    public open fun release() {
        eglCore.releaseSurface(eglSurface)
        eglSurface = EGL_NO_SURFACE
        height = -1
        width = -1
    }

    /**
     * Whether this surface is current on the
     * attached [EglCore].
     */
    @Suppress("MemberVisibilityCanBePrivate")
    public fun isCurrent(): Boolean {
        return eglCore.isSurfaceCurrent(eglSurface)
    }

    /**
     * Makes our EGL context and surface current.
     */
    @Suppress("unused")
    public fun makeCurrent() {
        eglCore.makeSurfaceCurrent(eglSurface)
    }

    /**
     * Makes no surface current for the attached [eglCore].
     */
    @Suppress("unused")
    public fun makeNothingCurrent() {
        eglCore.makeCurrent()
    }

    /**
     * Sends the presentation time stamp to EGL.
     * [nsecs] is the timestamp in nanoseconds.
     */
    @Suppress("unused")
    public fun setPresentationTime(nsecs: Long) {
        eglCore.setSurfacePresentationTime(eglSurface, nsecs)
    }
}