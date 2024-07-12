package com.otaliastudios.cameraview.filters;

import androidx.annotation.NonNull;

import com.otaliastudios.cameraview.filter.BaseFilter;


/**
 * Tints the frames with specified color.
 */
public class MirroFilter extends BaseFilter {

    @NonNull
    private static String FRAGMENT_SHADER = "#extension GL_OES_EGL_image_external : require\n"
            + "precision mediump float;\n"
            + "varying vec2 " + DEFAULT_FRAGMENT_TEXTURE_COORDINATE_NAME + ";\n"
            + "uniform samplerExternalOES sTexture;\n"
            + "void main() {\n"
            + "  gl_FragColor = texture2D(sTexture, vec2("
            + DEFAULT_FRAGMENT_TEXTURE_COORDINATE_NAME + ".x,"
            + "1.0-" + DEFAULT_FRAGMENT_TEXTURE_COORDINATE_NAME + ".y));\n"
            + "}\n";

    public MirroFilter() {
    }

    @NonNull
    @Override
    public String getFragmentShader() {
        return FRAGMENT_SHADER;
    }

    @Override
    public void onCreate(int programHandle) {
        super.onCreate(programHandle);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPreDraw(long timestampUs, @NonNull float[] transformMatrix) {
        super.onPreDraw(timestampUs, transformMatrix);
    }
}
