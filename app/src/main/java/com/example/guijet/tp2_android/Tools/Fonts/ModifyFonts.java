package com.example.guijet.tp2_android.Tools.Fonts;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import org.w3c.dom.Text;

/**
 * Created by Charles on 2017-06-19.
 */

public class ModifyFonts {

    public static void SetRegularToTextViews(final Context context, final TextView... textViews) {
        for (TextView tv : textViews)
            tv.setTypeface(Fonts.MontserratRegular(context));
    }

    public static void SetRegularToEditText(final Context context, final EditText... textViews) {
        for (EditText tv : textViews)
            tv.setTypeface(Fonts.MontserratRegular(context));
    }

    public static void SetRegularToTextView(final Context context, final TextView... textViews) {
        for (TextView tv : textViews)
            tv.setTypeface(Fonts.MontserratRegular(context));
    }

    public static void SetBoldToTextViews(final Context context, final TextView... textViews) {
        for (TextView tv : textViews)
            tv.setTypeface(Fonts.MontserratBold(context));
    }

    public static void SetBoldToEditText(final Context context, final EditText... editTexts) {
        for (EditText editText : editTexts)
            editText.setTypeface(Fonts.MontserratBold(context));
    }

    public static void SetBoldToTextView(final Context context, final TextView... textViews) {
        for (TextView textView : textViews)
            textView.setTypeface(Fonts.MontserratBold(context));
    }

    public static void SetBoldToButtons(final Context context, final Button... buttons) {
        for (Button btn : buttons)
            btn.setTypeface(Fonts.MontserratBold(context));
    }

    public static void SetLightToTextViews(final Context context, final TextView... textViews) {
        for (TextView tv : textViews)
            tv.setTypeface(Fonts.MontserratLight(context));
    }

    public static void SetLightToButtons(final Context context, final Button... buttons) {
        for (Button btn : buttons)
            btn.setTypeface(Fonts.MontserratLight(context));
    }

    public static void SetLightToEditText(final Context context, final EditText... editTexts) {
        for (EditText editText : editTexts)
            editText.setTypeface(Fonts.MontserratLight(context));
    }
}