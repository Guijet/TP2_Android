package com.example.guijet.tp2_android.Activity1;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.PathShape;
import android.graphics.drawable.shapes.RectShape;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aigestudio.wheelpicker.WheelPicker;
import com.example.guijet.tp2_android.Activity2.SecondActivity;
import com.example.guijet.tp2_android.Classes.AdresseMultiCast;
import com.example.guijet.tp2_android.R;
import com.example.guijet.tp2_android.Tools.DialogHelper.DialogHelper;
import com.example.guijet.tp2_android.Tools.Fonts.ModifyFonts;
import com.example.guijet.tp2_android.Tools.LogicalCode.Command;
import com.example.guijet.tp2_android.Tools.ScreenTools.ManualUI;
import com.example.guijet.tp2_android.Tools.ScreenTools.PageStarter;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class FirstActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText TB_Adresse,TB_PortUDP,TB_Pseudonyme;
    private List<AdresseMultiCast> listAdresse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        //Array adresses
        instantiateArrayAdress();

        //Seting Up Maunal UI
        ManualUI ui = new ManualUI(this);
        ui.setDesignSize(375,667);
        ui.removeTopBar();
        ui.setManualView((int)ui.rw(375),(int)ui.rh(667) - ui.getStatusBarHeight());

        setBackground(ui);
        setTitle(ui);
        setEditText(ui);
        setButton(ui);
    }

    private void instantiateArrayAdress(){
        listAdresse = new ArrayList<>();
        listAdresse.add(new AdresseMultiCast("La Comté","230.0.0.1"));
        listAdresse.add(new AdresseMultiCast("Mordor","230.0.0.2"));
        listAdresse.add(new AdresseMultiCast("Isengard","230.0.0.3"));
    }

    private void setBackground(ManualUI ui){
        View bgView = new View(this);
        bgView.setBackgroundColor(Color.parseColor("#000000"));
        ui.setPosition(bgView,ui.rw(0),ui.rh(0),ui.rw(375),ui.rh(667));
        ui.addView(bgView);
    }

    private void setTitle(ManualUI ui){
        TextView title = new TextView(this);
        title.setText("Hobbit Chat");
        title.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        title.setTextColor(Color.parseColor("#FFFFFF"));
        title.setTextSize(TypedValue.COMPLEX_UNIT_PX,ui.rw(46));
        ModifyFonts.SetLightToTextViews(this,title);

        ui.setPosition(title,ui.rw(25),ui.rh(81),ui.rw(325),ui.rh(57));
        ui.addView(title);
    }

    private void setEditText(ManualUI ui){

        //PICKER VIEW CHARLES
        TB_Adresse = new EditText(this);
        TB_Adresse.setHint("Adresse Multicast");
        TB_Adresse.setId(R.id.TB_Adresse);
        TB_Adresse.setOnClickListener(this);
        TB_Adresse.setHintTextColor(Color.parseColor("#FFFFFF"));
        TB_Adresse.setTextColor(Color.parseColor("#FFFFFF"));
        TB_Adresse.setAllCaps(false);
        TB_Adresse.setFocusable(false);
        TB_Adresse.setFocusableInTouchMode(false);
        TB_Adresse.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        TB_Adresse.getBackground().mutate().setColorFilter(Color.parseColor("#FFFFFF"), PorterDuff.Mode.SRC_ATOP);
        ui.setPosition(TB_Adresse,ui.rw(53),ui.rh(232),ui.rw(263),ui.rh(45));
        ui.addView(TB_Adresse);

        TB_PortUDP = new EditText(this);
        TB_PortUDP.setHint("Port UDP");
        TB_PortUDP.setInputType(InputType.TYPE_CLASS_NUMBER);
        TB_PortUDP.setId(R.id.TB_Port);
        TB_PortUDP.setText("6000");
        TB_PortUDP.setHintTextColor(Color.parseColor("#FFFFFF"));
        TB_PortUDP.setTextColor(Color.parseColor("#FFFFFF"));
        TB_PortUDP.setAllCaps(false);
        TB_PortUDP.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        TB_PortUDP.getBackground().mutate().setColorFilter(Color.parseColor("#FFFFFF"), PorterDuff.Mode.SRC_ATOP);
        ui.setPosition(TB_PortUDP,ui.rw(53),ui.rh(298),ui.rw(263),ui.rh(45));
        ui.addView(TB_PortUDP);


        TB_Pseudonyme = new EditText(this);
        TB_Pseudonyme.setId(R.id.TB_Username);
        TB_Pseudonyme.setHint("Pseudonyme");
        TB_Pseudonyme.setHintTextColor(Color.parseColor("#FFFFFF"));
        TB_Pseudonyme.setTextColor(Color.parseColor("#FFFFFF"));
        TB_Pseudonyme.setAllCaps(false);
        TB_Pseudonyme.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        TB_Pseudonyme.getBackground().mutate().setColorFilter(Color.parseColor("#FFFFFF"), PorterDuff.Mode.SRC_ATOP);
        ui.setPosition(TB_Pseudonyme,ui.rw(53),ui.rh(362),ui.rw(263),ui.rh(45));
        ui.addView(TB_Pseudonyme);
    }

    private void setUpWheelPicker(){
        final DialogHelper myDialogHelper = new DialogHelper(getStringFromList());
        myDialogHelper.buildWheelPicker(this, "Adresse multicast", new Command() {
            @Override
            public void execute() {
                TB_Adresse.setText(myDialogHelper.getSelectedItem());
            }
        });
    }

    private String getAdresseByName(String name){
        String adresseMulti = "";
        for (AdresseMultiCast adresse: listAdresse) {
            if(name.equals(adresse.getName())) {
                adresseMulti = adresse.getAdresse();
                break;
            }
        }
        return adresseMulti;
    }

    private List<String> getStringFromList(){
        List<String> listName = new ArrayList<String>();
        for (AdresseMultiCast adresse: listAdresse) {
            listName.add(adresse.getName());
        }
        return listName;
    }

    private void setButton(ManualUI ui) {
        GradientDrawable gdDefault = new GradientDrawable();
        gdDefault.setColor(Color.parseColor("#FFFFFF"));
        gdDefault.setCornerRadius(ui.rw(157)/2);
        gdDefault.setStroke(1, Color.parseColor("#FFFFFF"));

        Button startButton = new Button(this);
        startButton.setId(R.id.ButtonStart);
        startButton.setText("Start");
        startButton.setOnClickListener(this);
        startButton.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        startButton.setAllCaps(false);
        startButton.setBackgroundColor(Color.parseColor("#FFFFFF"));
        startButton.setTextColor(Color.parseColor("#000000"));
        startButton.setTextSize(TypedValue.COMPLEX_UNIT_PX,ui.rw(46));
        startButton.setBackgroundDrawable(gdDefault);

        ModifyFonts.SetLightToButtons(this,startButton);
        ui.setPosition(startButton,ui.rw(109),ui.rh(478),ui.rw(157),ui.rh(157));
        ui.addView(startButton);
    }

    private void goToNextActivity(){
        if(!TB_Adresse.getText().toString().isEmpty() && !TB_PortUDP.getText().toString().isEmpty() && !TB_Pseudonyme.getText().toString().isEmpty()) {
            if(!(TB_Pseudonyme.getText().toString().length() < 2) && !(TB_Pseudonyme.getText().toString().length() > 8)){
                //Passer a l'autre page
                Intent myIntent = new Intent(FirstActivity.this, SecondActivity.class);
                myIntent.putExtra("Username", TB_Pseudonyme.getText().toString());
                myIntent.putExtra("Port", TB_PortUDP.getText().toString());
                myIntent.putExtra("AdresseName", TB_Adresse.getText().toString());
                myIntent.putExtra("AdresseMulticast", getAdresseByName(TB_Adresse.getText().toString()));
                PageStarter.startActivityEnter(this,myIntent);
            }
            else
                Toast.makeText(this,"Username must be beetween 2 and 8 characters",Toast.LENGTH_LONG).show();
        }
        else
            Toast.makeText(this,"You need to fill all fields",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ButtonStart:
                goToNextActivity();
                break;
            case R.id.TB_Adresse:
                setUpWheelPicker();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        PageStarter.finish(this);
    }
}
