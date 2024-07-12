@file:Suppress("NOTHING_TO_INLINE")

package com.otaliastudios.opengl.internal

import android.opengl.GLU
import android.opengl.Matrix
import android.util.Log
import androidx.annotation.RequiresApi

internal inline fun logv(tag: String, message: String) { Log.v(tag, message) }
internal inline fun logi(tag: String, message: String) { Log.i(tag, message) }
internal inline fun logw(tag: String, message: String) { Log.w(tag, message) }
internal inline fun loge(tag: String, message: String) { Log.e(tag, message) }

internal fun intToHexString(value: Int): String = Integer.toHexString(value)
internal fun gluErrorString(value: Int): String = GLU.gluErrorString(value)

internal fun matrixMakeIdentity(matrix: FloatArray) = Matrix.setIdentityM(matrix, 0)
internal fun matrixTranslate(matrix: FloatArray, x: Float, y: Float, z: Float) = Matrix.translateM(matrix, 0, x, y, z)
internal fun matrixScale(matrix: FloatArray, x: Float, y: Float, z: Float) = Matrix.scaleM(matrix, 0, x, y, z)
internal fun matrixRotate(matrix: FloatArray, angle: Float, x: Float, y: Float, z: Float) = Matrix.rotateM(matrix, 0, angle, x, y, z)
internal fun matrixClone(matrix: FloatArray) = matrix.clone()
internal fun matrixMultiply(result: FloatArray, left: FloatArray, right: FloatArray) = Matrix.multiplyMM(result, 0, left, 0 , right, 0)