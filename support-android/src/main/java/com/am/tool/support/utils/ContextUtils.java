/*
 * Copyright (C) 2022 AlexMofer
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.am.tool.support.utils;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.util.DisplayMetrics;
import android.util.TypedValue;

import androidx.annotation.Nullable;

/**
 * Context工具
 * Created by Alex on 2022/11/30.
 */
public class ContextUtils {

    private ContextUtils() {
        //no instance
    }

    /**
     * Returns the {@link Activity} given a {@link Context} or null if there is no {@link Activity},
     * taking into account the potential hierarchy of {@link ContextWrapper ContextWrappers}.
     */
    @Nullable
    public static Activity getActivity(Context context) {
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
        return null;
    }

    /**
     * Converts an dip data value holding a dimension to its final floating
     * point value. The parameter <var>value</var> are as in {@link TypedValue#COMPLEX_UNIT_DIP}.
     *
     * @param metrics Current display metrics to use in the conversion --
     *                supplies display density and scaling information.
     * @param value   A dip data value.
     * @return The complex floating point value multiplied by the appropriate
     * metrics depending on its unit.
     */
    public static float getDimension(DisplayMetrics metrics, float value) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, metrics);
    }

    /**
     * This is the same as {@link #getDimension(DisplayMetrics, float)}
     */
    public static float getDimension(Context context, float value) {
        return getDimension(context.getResources().getDisplayMetrics(), value);
    }


    /**
     * Converts a dip data value holding a dimension to its final value
     * as an integer pixel size.  This is the same as
     * {@link #getDimension}, except the raw floating point value is
     * converted to an integer (pixel) value for use as a size.  A size
     * conversion involves rounding the base value, and ensuring that a
     * non-zero base value is at least one pixel in size.
     * The given <var>value</var> must be structured as a
     * {@link TypedValue#COMPLEX_UNIT_DIP}.
     *
     * @param metrics Current display metrics to use in the conversion --
     *                supplies display density and scaling information.
     * @param value   A dip data value.
     * @return The number of pixels specified by the data and its desired
     * multiplier and units.
     */
    public static int getDimensionPixelOffset(DisplayMetrics metrics, float value) {
        final float f = getDimension(metrics, value);
        final int res = (int) ((f >= 0) ? (f + 0.5f) : (f - 0.5f));
        if (res != 0) return res;
        if (value == 0) return 0;
        if (value > 0) return 1;
        return -1;
    }

    /**
     * This is the same as {@link #getDimensionPixelOffset(DisplayMetrics, float)}
     */
    public static int getDimensionPixelOffset(Context context, float value) {
        return getDimensionPixelOffset(context.getResources().getDisplayMetrics(), value);
    }

    /**
     * Converts a dip data value holding a dimension to its final value
     * as an integer pixel offset.  This is the same as
     * {@link #getDimension}, except the raw floating point value is
     * truncated to an integer (pixel) value.
     * The given <var>value</var> must be structured as a
     * {@link TypedValue#COMPLEX_UNIT_DIP}.
     *
     * @param metrics Current display metrics to use in the conversion --
     *                supplies display density and scaling information.
     * @param value   A dip data value.
     * @return The number of pixels specified by the data and its desired
     * multiplier and units.
     */
    public static int getDimensionPixelSize(DisplayMetrics metrics, float value) {
        return (int) getDimension(metrics, value);
    }

    /**
     * This is the same as {@link #getDimensionPixelSize(DisplayMetrics, float)}
     */
    public static int getDimensionPixelSize(Context context, float value) {
        return getDimensionPixelSize(context.getResources().getDisplayMetrics(), value);
    }
}
