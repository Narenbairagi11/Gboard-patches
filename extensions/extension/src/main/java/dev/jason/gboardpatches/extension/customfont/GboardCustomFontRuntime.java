package dev.jason.gboardpatches.extension.customfont;

import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public final class GboardCustomFontRuntime {
    private static final String FONT_ASSET = "gboard-custom-fonts/CustomFont.ttf";
    private static volatile Typeface cachedTypeface;

    private GboardCustomFontRuntime() {}

    public static void applyToSoftKey(Object softKeyObject) {
        if (!(softKeyObject instanceof View)) return;
        View softKeyView = (View) softKeyObject;
        Typeface typeface = getTypeface(softKeyView);
        if (typeface != null) applyRecursive(softKeyView, typeface);
    }

    private static Typeface getTypeface(View view) {
        Typeface typeface = cachedTypeface;
        if (typeface != null) return typeface;
        synchronized (GboardCustomFontRuntime.class) {
            typeface = cachedTypeface;
            if (typeface != null) return typeface;
            try {
                typeface = Typeface.createFromAsset(view.getContext().getAssets(), FONT_ASSET);
                cachedTypeface = typeface;
                return typeface;
            } catch (RuntimeException ignored) {
                return null;
            }
        }
    }

    private static void applyRecursive(View view, Typeface typeface) {
        if (view instanceof TextView) ((TextView) view).setTypeface(typeface);
        if (view instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) view;
            for (int i = 0; i < group.getChildCount(); i++) {
                applyRecursive(group.getChildAt(i), typeface);
            }
        }
    }
}
