package com.example.asus.quiztioner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class AplikasiActivity extends AppCompatActivity {
    public Button NextBut;
    public TextView From, Times;
    public EditText AppName;

    Firebase sbmits;
    Firebase urlA1;

    public int sendingData;
    public String sendData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aplikasi);
        Firebase.setAndroidContext(this);

        NextBut = (Button)findViewById(R.id.NexusButton);

        From = (TextView)findViewById(R.id.welcome);
        From.setText(getIntent().getExtras().getString("Email"));

        sbmits = new Firebase("https://login-f2aaf.firebaseio.com/Jawaban/" + From.getText().toString().replace('.',',') + "/Upload/Times");
        urlA1 = new Firebase("https://login-f2aaf.firebaseio.com/Aplikasi");

        Times = (TextView)findViewById(R.id.sbmit);
        AppName = (EditText)findViewById(R.id.FirstAnswer);

        sbmits.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String Value = dataSnapshot.getValue(String.class);
                if(!dataSnapshot.exists()){
                    sendingData = 1;
                    sendData = String.valueOf(sendingData);
                    Times.setText(sendData);

                }
                else {
                    sendingData = Integer.valueOf(Value) + 1;
                    Times.setText(Value);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        NextBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Firebase firebaseA1 = urlA1.child(From.getText().toString().replace('.',','));
                firebaseA1.child(Times.getText().toString()).child("Nama").setValue(AppName.getText().toString());
                firebaseA1.child(Times.getText().toString()).child("sid").setValue(Times.getText().toString());
                firebaseA1.child(Times.getText().toString()).child("uid").setValue(From.getText().toString());

                Intent i = new Intent(AplikasiActivity.this, BiodataActivity.class);
                i.putExtra("Email", From.getText().toString());
                startActivity(i);
            }
        });
    }
}
