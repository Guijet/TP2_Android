package com.example.guijet.tp2_android.Activity2;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guijet.tp2_android.Classes.ChatAdapter;
import com.example.guijet.tp2_android.Classes.Message;
import com.example.guijet.tp2_android.Classes.UdpObject;
import com.example.guijet.tp2_android.Classes.User;
import com.example.guijet.tp2_android.Communication.Emetteur;
import com.example.guijet.tp2_android.Communication.Recepteur;
import com.example.guijet.tp2_android.R;
import com.example.guijet.tp2_android.Tools.Fonts.ModifyFonts;
import com.example.guijet.tp2_android.Tools.ScreenTools.Focus;
import com.example.guijet.tp2_android.Tools.ScreenTools.ManualUI;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    Boolean isUsername = true;
    TextView Username;
    EditText TB_SendMsg;
    String adresseName,port,username,adresseMulticast;
    public static RecyclerView recyclerView;
    public static List<Message> messages;
    public static ChatAdapter adapter;
    List<Message> listMessage;
    private UdpObject udpObject;
    private Recepteur recepteur;
    private Emetteur emetteur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Get info last Activity
        //fillFakeMessages();
        getInfoFromLastActivity();

        //Seting Up Maunal UI
        ManualUI ui = new ManualUI(this);
        ui.setDesignSize(375,667);
        ui.removeTopBar();
        ui.setManualView((int)ui.rw(375),(int)ui.rh(667) - ui.getStatusBarHeight());

        //Page Set Ups
        setUpHeader(ui);
        setRecyclerView(ui);
        setUpBottom(ui);

        messages = new ArrayList<>();
        adapter = new ChatAdapter(this,messages);
        udpObject = new UdpObject(adresseName,username,port,adresseMulticast);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);

        recepteur = new Recepteur(this,udpObject);
        recepteur.execute();
    }

    private void fillFakeMessages() {
        listMessage = new ArrayList<Message>();
        //listMessage.add(new Message("Ce message est un test pour recycler view",new User("Jonathan","124.123.123"),0));
        //listMessage.add(new Message("Deuxieme message de Jonathan",new User("Guillaume","123.453.1234"),0));
        //listMessage.add(new Message("Ma reponse a mousieur bouchard",new User("Charles","234.234.216"),1));
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

    private void setRecyclerView(ManualUI ui){
        recyclerView = new RecyclerView(this);
        recyclerView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        ui.setPosition(recyclerView,0,ui.rh(106),ui.rw(375),ui.rh(667) - (ui.rh(106) + ui.rh(80)));
        ui.addView(recyclerView);
    }

    private void setUpBottom(ManualUI ui){
        View bottomView = new View(this);
        bottomView.setBackgroundColor(Color.parseColor("#E5E5E5"));
        bottomView.setId(R.id.BottomView);
        ui.setPosition(bottomView,0,ui.rh(587),ui.rw(375),ui.rh(80));
        ui.addView(bottomView);

        TB_SendMsg = new EditText(this);
        TB_SendMsg.setFocusable(true);
        TB_SendMsg.setId(R.id.TB_SendMessage);
        TB_SendMsg.setHint("Enter message here...");
        TB_SendMsg.setHintTextColor(Color.parseColor("#000000"));
        TB_SendMsg.setTextColor(Color.parseColor("#000000"));
        TB_SendMsg.setAllCaps(false);
        TB_SendMsg.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
        TB_SendMsg.setBackgroundColor(Color.TRANSPARENT);
        TB_SendMsg.setShowSoftInputOnFocus(true);
        TB_SendMsg.setFocusableInTouchMode(true);
        TB_SendMsg.requestFocus();
        Focus.request(this,TB_SendMsg);
        //TB_SendMsg.getBackground().mutate().setColorFilter(Color.TRANSPARENT, PorterDuff.Mode.SRC_ATOP);
        ui.setPosition(TB_SendMsg,ui.rw(15),ui.rh(593),ui.rw(280),ui.rh(45));
        ui.addView(TB_SendMsg);

        ImageButton sendButton = new ImageButton(this);
        sendButton.setImageResource(R.drawable.send);
        sendButton.setOnClickListener(this);
        sendButton.setBackgroundColor(Color.TRANSPARENT);
        sendButton.setId(R.id.BTN_Send);
        sendButton.setScaleType(ImageView.ScaleType.FIT_CENTER);
        ui.setPosition(sendButton,ui.rw(326),ui.rh(600),ui.rw(40),ui.rh(40));
        ui.addView(sendButton);
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

    private void changeIpFromUsername() {
        if(isUsername) {
            //Mettre les ips adresse
            isUsername = false;
        }
        else {
            //Mettre les usernames
            isUsername = true;
        }
        recepteur.setShowIp(!isUsername);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.SwitchButton){
            //Changer les noms des user par le ip
            changeIpFromUsername();
        }

        if(view.getId() == R.id.BTN_Send) {
            //Verify characters limits
            if(TB_SendMsg.getText().toString().length() > 1 || TB_SendMsg.getText().toString().length() < 60){
                //SEND MESSAGE
                //EMPTY EditText
                //Add message in recycler view
                new Emetteur(this,udpObject,new Message(udpObject.getUsername() + ":" + TB_SendMsg.getText().toString(),new User(udpObject.getUsername(),false),1)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                TB_SendMsg.setText("");
            }
            else {
                Toast.makeText(this, "Message mut be between 1 and 60 characters", Toast.LENGTH_SHORT).show();
            }
        }
    }
}