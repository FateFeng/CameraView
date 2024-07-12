@file:Suppress("NOTHING_TO_INLINE")

package com.otaliastudios.opengl.internal

import android.opengl.GLES11Ext
import android.opengl.GLES20
import android.opengl.GLES30
import android.opengl.GLES31
import android.os.Build
import androidx.annotation.RequiresApi
import com.otaliastudios.opengl.types.FloatBuffer
import java.nio.Buffer

internal val GL_TRUE = GLES20.GL_TRUE
@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
internal val GL_SHADER_STORAGE_BUFFER = GLES31.GL_SHADER_STORAGE_BUFFER.toUInt()
internal val GL_VIEWPORT = GLES20.GL_VIEWPORT
internal val GL_NO_ERROR = GLES20.GL_NO_ERROR
internal val GL_UNSIGNED_BYTE = GLES20.GL_UNSIGNED_BYTE.toUInt()
internal val GL_FLOAT = GLES20.GL_FLOAT.toUInt()
internal val GL_RGBA = GLES20.GL_RGBA.toUInt()
internal val GL_TRIANGLES = GLES20.GL_TRIANGLES.toUInt()
internal val GL_TRIANGLE_FAN = GLES20.GL_TRIANGLE_FAN.toUInt()
internal val GL_TRIANGLE_STRIP = GLES20.GL_TRIANGLE_STRIP.toUInt()
internal val GL_TEXTURE0 = GLES20.GL_TEXTURE0.toUInt()
internal val GL_TEXTURE_EXTERNAL_OES = GLES11Ext.GL_TEXTURE_EXTERNAL_OES.toUInt()
internal val GL_TEXTURE_MIN_FILTER = GLES20.GL_TEXTURE_MIN_FILTER.toUInt()
internal val GL_TEXTURE_MAG_FILTER = GLES20.GL_TEXTURE_MAG_FILTER.toUInt()
internal val GL_TEXTURE_WRAP_S = GLES20.GL_TEXTURE_WRAP_S.toUInt()
internal val GL_TEXTURE_WRAP_T = GLES20.GL_TEXTURE_WRAP_T.toUInt()
internal val GL_CLAMP_TO_EDGE = GLES20.GL_CLAMP_TO_EDGE
internal val GL_NEAREST = GLES20.GL_NEAREST.toFloat()
internal val GL_LINEAR = GLES20.GL_LINEAR.toFloat()
internal val GL_FRAMEBUFFER = GLES20.GL_FRAMEBUFFER.toUInt()
internal val GL_FRAMEBUFFER_COMPLETE = GLES20.GL_FRAMEBUFFER_COMPLETE.toUInt()
internal val GL_COLOR_ATTACHMENT0 = GLES20.GL_COLOR_ATTACHMENT0.toUInt()
internal val GL_COMPILE_STATUS = GLES20.GL_COMPILE_STATUS.toUInt()
internal val GL_LINK_STATUS = GLES20.GL_LINK_STATUS.toUInt()
internal val GL_VERTEX_SHADER = GLES20.GL_VERTEX_SHADER.toUInt()
internal val GL_FRAGMENT_SHADER = GLES20.GL_FRAGMENT_SHADER.toUInt()

internal inline fun glGenTextures(count: Int, array: UIntArray) = withSignedArray(array, count = count) {
    GLES20.glGenTextures(count, it, 0)
}
internal inline fun glDeleteTextures(count: Int, array: UIntArray) = withSignedArray(array, count = count) {
    GLES20.glDeleteTextures(count, it, 0)
}
internal inline fun glActiveTexture(unit: UInt) = GLES20.glActiveTexture(unit.toInt())
internal inline fun glBindTexture(target: UInt, texture: UInt) = GLES20.glBindTexture(target.toInt(), texture.toInt())
internal inline fun glTexParameteri(target: UInt, parameter: UInt, value: Int) = GLES20.glTexParameteri(target.toInt(), parameter.toInt(), value)
internal inline fun glTexParameterf(target: UInt, parameter: UInt, value: Float) = GLES20.glTexParameterf(target.toInt(), parameter.toInt(), value)
internal inline fun glTexImage2D(target: UInt, level: Int, internalFormat: Int, width: Int, height: Int, border: Int, format: UInt, type: UInt, pixels: Buffer?)
        = GLES20.glTexImage2D(target.toInt(), level, internalFormat, width, height, border, format.toInt(), type.toInt(), pixels)

internal inline fun glGenFramebuffers(count: Int, array: UIntArray) = withSignedArray(array, count = count) {
    GLES20.glGenFramebuffers(count, it, 0)
}
internal inline fun glDeleteFramebuffers(count: Int, array: UIntArray) = withSignedArray(array, count = count) {
    GLES20.glDeleteFramebuffers(count, it, 0)
}
internal inline fun glBindFramebuffer(target: UInt, framebuffer: UInt) = GLES20.glBindFramebuffer(target.toInt(), framebuffer.toInt())
internal inline fun glCheckFramebufferStatus(target: UInt) = GLES20.glCheckFramebufferStatus(target.toInt()).toUInt()
internal inline fun glFramebufferTexture2D(target: UInt, attachment: UInt, textureTarget: UInt, texture: UInt, level: Int)
        = GLES20.glFramebufferTexture2D(target.toInt(), attachment.toInt(), textureTarget.toInt(), texture.toInt(), level)

internal inline fun glGenBuffers(count: Int, array: UIntArray) = withSignedArray(array, count = count) {
    GLES20.glGenBuffers(count, it, 0)
}
internal inline fun glDeleteBuffers(count: Int, array: UIntArray) = withSignedArray(array, count = count) {
    GLES20.glDeleteBuffers(count, it, 0)
}
internal inline fun glBindBuffer(target: UInt, id: UInt) = GLES20.glBindBuffer(target.toInt(), id.toInt())
internal inline fun glBufferData(target: UInt, size: Int, usage: UInt) = GLES20.glBufferData(target.toInt(), size, null, usage.toInt())
internal inline fun glBindBufferBase(target: UInt, index: UInt, id: UInt) = GLES30.glBindBufferBase(target.toInt(), index.toInt(), id.toInt())

internal inline fun glCreateShader(type: UInt) = GLES20.glCreateShader(type.toInt()).toUInt()
internal inline fun glShaderSource(shader: UInt, source: String) = GLES20.glShaderSource(shader.toInt(), source)
internal inline fun glCompileShader(shader: UInt) = GLES20.glCompileShader(shader.toInt())
internal inline fun glDeleteShader(shader: UInt) = GLES20.glDeleteShader(shader.toInt())
internal inline fun glGetShaderInfoLog(shader: UInt) = GLES20.glGetShaderInfoLog(shader.toInt())
internal inline fun glGetShaderiv(shader: UInt, parameter: UInt, result: IntArray)
        = GLES20.glGetShaderiv(shader.toInt(), parameter.toInt(), result, 0)

internal inline fun glCreateProgram() = GLES20.glCreateProgram().toUInt()
internal inline fun glAttachShader(program: UInt, shader: UInt) = GLES20.glAttachShader(program.toInt(), shader.toInt())
internal inline fun glLinkProgram(program: UInt) = GLES20.glLinkProgram(program.toInt())
internal inline fun glUseProgram(program: UInt) = GLES20.glUseProgram(program.toInt())
internal inline fun glDeleteProgram(program: UInt) = GLES20.glDeleteProgram(program.toInt())
internal inline fun glGetProgramInfoLog(program: UInt) = GLES20.glGetProgramInfoLog(program.toInt())
internal inline fun glGetProgramiv(program: UInt, parameter: UInt, result: IntArray)
        = GLES20.glGetProgramiv(program.toInt(), parameter.toInt(), result, 0)

internal inline fun glEnableVertexAttribArray(array: UInt) = GLES20.glEnableVertexAttribArray(array.toInt())
internal inline fun glDisableVertexAttribArray(array: UInt) = GLES20.glDisableVertexAttribArray(array.toInt())
internal inline fun glGetAttribLocation(program: UInt, name: String) = GLES20.glGetAttribLocation(program.toInt(), name)
internal inline fun glGetUniformLocation(program: UInt, name: String) = GLES20.glGetUniformLocation(program.toInt(), name)
internal inline fun glVertexAttribPointer(index: UInt, size: Int, type: UInt, normalized: Boolean, stride: Int, pointer: Buffer)
        = GLES20.glVertexAttribPointer(index.toInt(), size, type.toInt(), normalized, stride, pointer)
internal inline fun glUniformMatrix4fv(location: Int, count: Int, transpose: Boolean, value: FloatBuffer)
        = GLES20.glUniformMatrix4fv(location, count, transpose, value)
internal inline fun glUniformMatrix4fv(location: Int, count: Int, transpose: Boolean, value: FloatArray)
        = GLES20.glUniformMatrix4fv(location, count, transpose, value, 0)
internal inline fun glUniform4fv(location: Int, count: Int, value: FloatBuffer)
        = GLES20.glUniform4fv(location, count, value)
internal inline fun glUniform4fv(location: Int, count: Int, value: FloatArray)
        = GLES20.glUniform4fv(location, count, value, 0)

internal inline fun glGetIntegerv(parameter: UInt, array: IntArray) = GLES20.glGetIntegerv(parameter.toInt(), array, 0)
internal inline fun glGetError() = GLES20.glGetError().toUInt()

internal inline fun glDrawArrays(mode: UInt, first: Int, count: Int) = GLES20.glDrawArrays(mode.toInt(), first, count)
internal inline fun glDrawElements(mode: UInt, count: Int, type: UInt, indices: Buffer) = GLES20.glDrawElements(mode.toInt(), count, type.toInt(), indices)

private inline fun <T> withSignedArray(source: UIntArray, pos: Int = 0, count: Int = source.size, block: (IntArray) -> T): T {
    val signed = IntArray(source.size) { source[it].toInt() }
    val result = block(signed)
    for (i in pos until pos + count) {
        source[i] = signed[i].toUInt()
    }
    return result
}
