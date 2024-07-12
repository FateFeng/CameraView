@file:Suppress("NOTHING_TO_INLINE")

package com.otaliastudios.opengl.internal

import android.opengl.*

// Not using the native types directly (as typealias) for visibility - android.opengl.* stuff
// is not internal, so we would not be able to make our typealiases internal as well.
// Use data class to have equals().
internal data class EglSurface(val native: EGLSurface?)
internal data class EglDisplay(val native: EGLDisplay?)
internal data class EglContext(val native: EGLContext?)
internal data class EglConfig(val native: EGLConfig)

internal val EGL_NO_CONTEXT = EglContext(EGL14.EGL_NO_CONTEXT)
internal val EGL_NO_DISPLAY = EglDisplay(EGL14.EGL_NO_DISPLAY)
internal val EGL_NO_SURFACE = EglSurface(EGL14.EGL_NO_SURFACE)
internal val EGL_SUCCESS = EGL14.EGL_SUCCESS
internal val EGL_NONE = EGL14.EGL_NONE
internal val EGL_WIDTH = EGL14.EGL_WIDTH
internal val EGL_HEIGHT = EGL14.EGL_HEIGHT
internal val EGL_READ = EGL14.EGL_READ
internal val EGL_DRAW = EGL14.EGL_DRAW
internal val EGL_CONTEXT_CLIENT_VERSION = EGL14.EGL_CONTEXT_CLIENT_VERSION
internal val EGL_OPENGL_ES2_BIT = EGL14.EGL_OPENGL_ES2_BIT
internal val EGL_OPENGL_ES3_BIT_KHR = EGLExt.EGL_OPENGL_ES3_BIT_KHR
internal val EGL_RED_SIZE = EGL14.EGL_RED_SIZE
internal val EGL_GREEN_SIZE = EGL14.EGL_GREEN_SIZE
internal val EGL_BLUE_SIZE = EGL14.EGL_BLUE_SIZE
internal val EGL_ALPHA_SIZE = EGL14.EGL_ALPHA_SIZE
internal val EGL_SURFACE_TYPE = EGL14.EGL_SURFACE_TYPE
internal val EGL_WINDOW_BIT = EGL14.EGL_WINDOW_BIT
internal val EGL_PBUFFER_BIT = EGL14.EGL_PBUFFER_BIT
internal val EGL_RENDERABLE_TYPE = EGL14.EGL_RENDERABLE_TYPE

internal inline fun eglChooseConfig(display: EglDisplay, attributes: IntArray, configs: Array<EglConfig?>, configsSize: Int, numConfigs: IntArray): Boolean {
    val nativeConfigs = arrayOfNulls<EGLConfig>(configs.size)
    val result = EGL14.eglChooseConfig(display.native, attributes, 0, nativeConfigs, 0, configsSize, numConfigs, 0)
    if (result) configs.indices.forEach { configs[it] = nativeConfigs[it]?.let { EglConfig(it) } }
    return result
}
internal inline fun eglInitialize(display: EglDisplay, major: IntArray, minor: IntArray)
        = EGL14.eglInitialize(display.native, major, 0, minor, 0)
internal inline fun eglCreateContext(display: EglDisplay, config: EglConfig, sharedContext: EglContext, attributes: IntArray)
        = EglContext(EGL14.eglCreateContext(display.native, config.native, sharedContext.native, attributes, 0))
internal inline fun eglGetDefaultDisplay() = EglDisplay(EGL14.eglGetDisplay(EGL14.EGL_DEFAULT_DISPLAY))
internal inline fun eglGetCurrentContext() = EglContext(EGL14.eglGetCurrentContext())
internal inline fun eglGetCurrentDisplay() = EglDisplay(EGL14.eglGetCurrentDisplay())
internal inline fun eglGetCurrentSurface(which: Int) = EglSurface(EGL14.eglGetCurrentSurface(which))
internal inline fun eglQuerySurface(display: EglDisplay, surface: EglSurface, attribute: Int, out: IntArray)
        = EGL14.eglQuerySurface(display.native, surface.native, attribute, out, 0)
internal inline fun eglCreateWindowSurface(display: EglDisplay, config: EglConfig, surface: Any, attributes: IntArray)
        = com.otaliastudios.opengl.internal.EglSurface(EGL14.eglCreateWindowSurface(display.native, config.native, surface, attributes, 0))
internal inline fun eglCreatePbufferSurface(display: EglDisplay, config: EglConfig, attributes: IntArray)
        = com.otaliastudios.opengl.internal.EglSurface(EGL14.eglCreatePbufferSurface(display.native, config.native, attributes, 0))
internal inline fun eglMakeCurrent(display: EglDisplay, draw: EglSurface, read: EglSurface, context: EglContext)
        = EGL14.eglMakeCurrent(display.native, draw.native, read.native, context.native)
internal inline fun eglSwapBuffers(display: EglDisplay, surface: EglSurface)
        = EGL14.eglSwapBuffers(display.native, surface.native)
internal inline fun eglPresentationTime(display: EglDisplay, surface: EglSurface, nanoseconds: Long)
        = EGLExt.eglPresentationTimeANDROID(display.native, surface.native, nanoseconds)
internal inline fun eglDestroyContext(display: EglDisplay, context: EglContext)
        = EGL14.eglDestroyContext(display.native, context.native)
internal inline fun eglDestroySurface(display: EglDisplay, surface: EglSurface)
        = EGL14.eglDestroySurface(display.native, surface.native)
internal inline fun eglReleaseThread() = EGL14.eglReleaseThread()
internal inline fun eglTerminate(display: EglDisplay) = EGL14.eglTerminate(display.native)
internal inline fun eglGetError() = EGL14.eglGetError()