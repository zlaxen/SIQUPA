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

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    Firebase check;

    private EditText txtEmail;
    private EditText txtpassword;
    private FirebaseAuth firebaseAuth;
    public Button LoginButton;
    Firebase placeurl;
    BiodataActivity biodataActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtEmail = findViewById(R.id.name);
        txtpassword = findViewById(R.id.pass);
        firebaseAuth = FirebaseAuth.getInstance();
        LoginButton = findViewById(R.id.LoginButton);
        Firebase.setAndroidContext(this);

        check = new Firebase("https://login-f2aaf.firebaseio.com/Biodata/");

        //placeurl = new Firebase("https://login-f2aaf.firebaseio.com/Jawaban/" + biodataActivity.From.getText().toString().replace('.',','));

        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog progressDialog = ProgressDialog.show(LoginActivity.this, "Please Wait...", "Processing...", true);
                (firebaseAuth.signInWithEmailAndPassword(txtEmail.getText().toString(), txtpassword.getText().toString())).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();

                        if(task.isSuccessful()){
                            Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
                            check.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    if(!dataSnapshot.child(txtEmail.getText().toString().replace('.',',')).exists()){
                                        Intent i = new Intent(LoginActivity.this, InformationActivity.class);
                                        i.putExtra("Email", firebaseAuth.getCurrentUser().getEmail());
                                        startActivity(i);
                                    }
                                    else if(dataSnapshot.child(txtEmail.getText().toString().replace('.',',')).exists()){
                                        Intent i = new Intent(LoginActivity.this, AplikasiActivity.class);
                                        i.putExtra("Email", firebaseAuth.getCurrentUser().getEmail());
                                        startActivity(i);
                                    }
                                }

                                @Override
                                public void onCancelled(FirebaseError firebaseError) {

                                }
                            });
                            //Firebase firebase = placeurl.child(biodataActivity.From.getText().toString().replace('.',','));
                            //firebase.child("Times").setValue(numbers);
                        }

                        else{
                            Log.e("Error", task.getException().toString());
                            Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }

                    }
                });
            }
        });
    }
}

