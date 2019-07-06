package com.example.asus.quiztioner;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    private EditText txtEmail;
    private EditText txtpassword;
    private FirebaseAuth firebaseAuth;
    public Button RegisButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        txtEmail = findViewById(R.id.name);
        txtpassword = findViewById(R.id.pass);
        firebaseAuth = FirebaseAuth.getInstance();
        RegisButton = findViewById(R.id.RegisButton);

        RegisButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog progressDialog = ProgressDialog.show(RegisterActivity.this, "Please Wait...", "Processing...", true);
                (firebaseAuth.createUserWithEmailAndPassword(txtEmail.getText().toString(), txtpassword.getText().toString())).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();

                        if(task.isSuccessful()){
                            Toast.makeText(RegisterActivity.this, "Register successfull", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(i);
                        }

                        else{
                            Log.e("Error", task.getException().toString());
                            Toast.makeText(RegisterActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }
}
