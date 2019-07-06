package com.example.asus.quiztioner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    public Button Loginbutton;
    public Button Regisbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Loginbutton = (Button)findViewById(R.id.MainLoginButton);
        Regisbutton = (Button)findViewById(R.id.MainRegisButton);

        Loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });

        Regisbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });
    }
}

