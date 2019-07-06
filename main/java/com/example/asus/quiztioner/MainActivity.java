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

import java.util.jar.Attributes;

public class MainActivity extends AppCompatActivity {
    Button SendsButton;
    Firebase url;
    Firebase urlA1;

    TextView From;
    TextView NameQ;
    TextView TTLQ;
    TextView JabatanQ;
    TextView InstansiQ;
    TextView NoTelpQ;

    EditText NameA;
    EditText TTLA;
    EditText JabatanA;
    EditText InstansiA;
    EditText NoTelpA;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);

        NameQ = (TextView)findViewById(R.id.Name);
        NameA = (EditText)findViewById(R.id.NameAnswer);
        JabatanQ = (TextView)findViewById(R.id.Jabatan);
        JabatanA = (EditText)findViewById(R.id.JabatanAnswer);
        InstansiQ = (TextView)findViewById(R.id.Instansi);
        InstansiA = (EditText)findViewById(R.id.InstansiAnswer);
        NoTelpQ = (TextView)findViewById(R.id.Telp);
        NoTelpA = (EditText)findViewById(R.id.TelpAnswer);
        TTLQ = (TextView)findViewById(R.id.TTL);
        TTLA = (EditText)findViewById(R.id.TTLAnswer);

        From = (TextView)findViewById(R.id.welcome);
        From.setText(getIntent().getExtras().getString("Email"));

        url = new Firebase("https://login-f2aaf.firebaseio.com/Biodata");
        SendsButton = (Button)findViewById(R.id.Sndsbutton);

        SendsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Firebase firebase = url.child(From.getText().toString().replace('.',','));

                firebase.child(NameQ.getText().toString()).setValue(NameA.getText().toString());
                firebase.child(JabatanQ.getText().toString()).setValue(JabatanA.getText().toString());
                firebase.child(InstansiQ.getText().toString()).setValue(InstansiA.getText().toString());
                firebase.child(TTLQ.getText().toString()).setValue(TTLA.getText().toString());
                firebase.child(NoTelpQ.getText().toString()).setValue(NoTelpA.getText().toString());
            }
        });
    }
}
