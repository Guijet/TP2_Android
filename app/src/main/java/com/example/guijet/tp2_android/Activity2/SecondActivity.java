package com.example.guijet.tp2_android.Activity2;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guijet.tp2_android.Classes.Message;
import com.example.guijet.tp2_android.R;
import com.example.guijet.tp2_android.Tools.Fonts.ModifyFonts;
import com.example.guijet.tp2_android.Tools.ScreenTools.ManualUI;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    Boolean isUsername = true;
    TextView Username;
    String adresseName,port,username,adresseMulticast;
    RecyclerView recyclerView;
    List<Message> listMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Get info last Activity
        fillFakeMessages();
        getInfoFromLastActivity();

        //Seting Up Maunal UI
        ManualUI ui = new ManualUI(this);
        ui.setDesignSize(375,667);
        ui.removeTopBar();
        ui.setManualView((int)ui.rw(375),(int)ui.rh(667) - ui.getStatusBarHeight());

        //Page Set Ups
        setUpHeader(ui);

        Toast.makeText(this,adresseName + ": " + adresseMulticast,Toast.LENGTH_SHORT).show();
    }

    private void fillFakeMessages(){
        listMessage = new ArrayList<Message>();
        listMessage.add(new Message("Ce message est un test pour recycler view","Jonathan Bouchard",false));
        listMessage.add(new Message("Deuxieme message de Jonathan","Jonathan Bouchard",false));
        listMessage.add(new Message("Ma reponse a mousieur bouchard","Guillaume Jetté",true));
    }

    private void setUpHeader(ManualUI ui){
        View containerHeader = new View(this);
        containerHeader.setBackgroundColor(Color.BLACK);
        ui.setPosition(containerHeader,0,0,ui.rw(667),ui.rh(106));
        ui.addView(containerHeader);

        Username = new TextView(this);
        Username.setText(adresseName);
        Username.setId(R.id.Username);
        Username.setTextColor(Color.parseColor("#FFFFFF"));
        Username.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        Username.setTextSize(TypedValue.COMPLEX_UNIT_PX,ui.rw(24));
        ModifyFonts.SetBoldToTextView(this,Username);
        ui.setPosition(Username,ui.rw(73),ui.rh(37),ui.rw(229),ui.rh(40));
        ui.addView(Username);

        ImageButton switchUsername = new ImageButton(this);
        switchUsername.setImageResource(R.drawable.switchusername);
        switchUsername.setOnClickListener(this);
        switchUsername.setBackgroundColor(Color.TRANSPARENT);
        switchUsername.setId(R.id.SwitchButton);
        switchUsername.setScaleType(ImageView.ScaleType.FIT_CENTER);
        ui.setPosition(switchUsername,ui.rw(310),ui.rh(35),ui.rw(50),ui.rh(50));
        ui.addView(switchUsername);
    }

    private void setRecyclerView(){

    }

    private void getInfoFromLastActivity(){
        Intent intent = getIntent();
        adresseName = intent.getStringExtra("AdresseName");
        username = intent.getStringExtra("Username");
        port = intent.getStringExtra("Port");
        adresseMulticast = intent.getStringExtra("AdresseMulticast");
    }

    private void changeTopText(){
        if(isUsername) {
            Username.setText(adresseMulticast);
            isUsername = false;
        }
        else{
            Username.setText(adresseName);
            isUsername = true;
        }


    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.SwitchButton){
            changeTopText();
        }
    }
}
