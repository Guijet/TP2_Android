package com.example.guijet.tp2_android.Tools.ScreenTools;

import android.app.Activity;
import android.content.Context;

public class Density extends Activity
{
    public static float get(Context context)
    {
        return context.getResources().getDisplayMetrics().density;
    }
}
