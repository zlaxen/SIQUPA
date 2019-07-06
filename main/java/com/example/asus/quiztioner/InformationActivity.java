package com.example.asus.quiztioner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.client.Firebase;

public class InformationActivity extends AppCompatActivity {
    public Button sendButton;
    Firebase infoUrl;

    TextView From;
    TextView NameQ;
    EditText NameA;
    TextView InstansiQ;
    EditText InstansiA;
    TextView NoTelpQ;
    EditText NoTelpA;
    TextView EmailQ;
    EditText EmailA;
    TextView WebsiteQ;
    EditText WebsiteA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        Firebase.setAndroidContext(this);

        sendButton = (Button)findViewById(R.id.sendIn);
        From = (TextView)findViewById(R.id.welcome);
        From.setText(getIntent().getExtras().getString("Email"));

        NameQ = (TextView)findViewById(R.id.Name);
        NameA = (EditText)findViewById(R.id.NameAnswer);
        InstansiQ = (TextView)findViewById(R.id.Instansi);
        InstansiA = (EditText)findViewById(R.id.InstansiAnswer);
        NoTelpQ = (TextView)findViewById(R.id.Telp);
        NoTelpA = (EditText)findViewById(R.id.TelpAnswer);
        EmailQ = (TextView)findViewById(R.id.Email);
        EmailA = (EditText)findViewById(R.id.EmailAnswer);
        WebsiteQ = (TextView)findViewById(R.id.Website);
        WebsiteA = (EditText)findViewById(R.id.WebsiteAnswer);

        infoUrl = new Firebase("https://login-f2aaf.firebaseio.com/Biodata");

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Firebase fireBio = infoUrl.child(From.getText().toString().replace('.',','));
                fireBio.child(NameQ.getText().toString()).setValue(NameA.getText().toString());
                fireBio.child(InstansiQ.getText().toString()).setValue(InstansiA.getText().toString());
                fireBio.child(NoTelpQ.getText().toString()).setValue(NoTelpA.getText().toString());
                fireBio.child(EmailQ.getText().toString()).setValue(EmailA.getText().toString());
                fireBio.child(WebsiteQ.getText().toString()).setValue(WebsiteA.getText().toString());

                Intent i = new Intent(InformationActivity.this, AplikasiActivity.class);
                i.putExtra("Email", From.getText().toString());
                startActivity(i);
            }
        });
    }
}
