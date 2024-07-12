package com.otaliastudios.opengl.types

import com.otaliastudios.opengl.core.Egloo
import java.nio.ByteOrder


public typealias Buffer = java.nio.Buffer
public typealias FloatBuffer = java.nio.FloatBuffer
public typealias ByteBuffer = java.nio.ByteBuffer
public typealias ShortBuffer = java.nio.ShortBuffer
public typealias IntBuffer = java.nio.IntBuffer

public fun byteBuffer(size: Int): ByteBuffer = ByteBuffer
    .allocateDirect(size * Egloo.SIZE_OF_BYTE)
    .order(ByteOrder.nativeOrder())
    .also { it.limit(it.capacity()) }

public fun shortBuffer(size: Int): ShortBuffer = byteBuffer(size * Egloo.SIZE_OF_SHORT).asShortBuffer()
public fun floatBuffer(size: Int): FloatBuffer = byteBuffer(size * Egloo.SIZE_OF_FLOAT).asFloatBuffer()
public fun intBuffer(size: Int): IntBuffer = byteBuffer(size * Egloo.SIZE_OF_INT).asIntBuffer()

// TODO should this be public and be applied to more structures?
internal interface Disposable {
    fun dispose()
}

public fun Buffer.dispose() {
    if (this is Disposable) this.dispose()
}