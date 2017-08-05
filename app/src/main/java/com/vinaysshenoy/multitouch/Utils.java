package com.vinaysshenoy.multitouch;

import android.content.res.Resources;

/**
 * Created by vinaysshenoy on 05/08/17.
 */

public final class Utils {

    private Utils() {}

    public static float dpToPx(float dp) {
        return dp * Resources.getSystem().getDisplayMetrics().density;
    }
}
