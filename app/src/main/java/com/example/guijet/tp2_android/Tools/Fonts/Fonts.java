package com.example.guijet.tp2_android.Tools.Fonts;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by Charles on 2017-06-16.
 */

public class Fonts {
    public static Typeface MontserratBold(Context context) {
        return Typeface.createFromAsset(context.getAssets(),"Montserrat-Bold.otf");
    }

    public static Typeface MontserratExtraLight(Context context) {
        return Typeface.createFromAsset(context.getAssets(),"Montserrat-ExtraLight.otf");
    }

    public static Typeface MontserratLight(Context context) {
        return Typeface.createFromAsset(context.getAssets(),"Montserrat-Light.otf");
    }

    public static Typeface MontserratRegular(Context context) {
        return Typeface.createFromAsset(context.getAssets(),"Montserrat-Regular.otf");
    }
}
