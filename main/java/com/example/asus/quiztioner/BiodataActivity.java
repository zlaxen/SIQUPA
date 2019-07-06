package com.example.asus.quiztioner;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SectionIndexer;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.GenericTypeIndicator;
import com.firebase.client.ValueEventListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.UploadTask;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;

public class BiodataActivity extends AppCompatActivity {
    Button SendButton;
    //Firebase url;
    Firebase urlFire, urlA1, name, sbmits;
    TextView From, Submit, FirstQ, SecondQ, ThirdQ, FourthQ, FifthQ, SixthQ, SeventhQ, EighthQ, NinthQ, TenthQ, EleventhQ, TwelvethQ, ThirteenthQ, FourteenthQ, FifteenthQ, SixteenthQ, SeventeenthQ, EighteenthQ, NinteenthQ, TwentiethQ;
    EditText FirstA, SecondA, ThirdA, FourthA, FifthA, SixthA, SeventhA, EighthA, NinthA, TenthA, EleventhA, TwelvethA, ThirteenthA, FourteenthA, FifteenthA, SixteenthA, SeventeenthA, EighteenthA, NinteenthA, TwentiethA;

    TextView Twenty1Q;
    EditText Twenty1A;
    TextView Twenty2Q;
    EditText Twenty2A;
    TextView Twenty3Q;
    EditText Twenty3A;
    TextView Twenty4Q;
    EditText Twenty4A;
    TextView Twenty5Q;
    EditText Twenty5A;

    public int sendingData;
    public String sendData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biodata);
        Firebase.setAndroidContext(this);

        FirstQ = (TextView)findViewById(R.id.First);
        FirstA = (EditText)findViewById(R.id.FirstAnswer);
        SecondQ = (TextView)findViewById(R.id.Second);
        SecondA = (EditText)findViewById(R.id.SecondAnswer);
        ThirdQ = (TextView)findViewById(R.id.Third);
        ThirdA = (EditText)findViewById(R.id.ThirdAnswer);
        FourthQ = (TextView)findViewById(R.id.Fourth);
        FourthA = (EditText)findViewById(R.id.FourthAnswer);
        FifthQ = (TextView)findViewById(R.id.Fifth);
        FifthA = (EditText) findViewById(R.id.FifthAnswer);

        SixthQ = (TextView)findViewById(R.id.Sixth);
        SixthA = (EditText) findViewById(R.id.SixthAnswer);
        SeventhQ = (TextView)findViewById(R.id.Seventh);
        SeventhA = (EditText) findViewById(R.id.SeventhAnswer);
        EighthQ = (TextView)findViewById(R.id.Eighth);
        EighthA = (EditText) findViewById(R.id.EighthAnswer);
        NinthQ = (TextView)findViewById(R.id.Ninth);
        NinthA = (EditText) findViewById(R.id.NinthAnswer);
        TenthQ = (TextView)findViewById(R.id.Tenth);
        TenthA = (EditText) findViewById(R.id.TenthAnswer);

        EleventhQ = (TextView)findViewById(R.id.Eleven);
        EleventhA = (EditText) findViewById(R.id.ElevenAnswer);
        TwelvethQ = (TextView)findViewById(R.id.Twelveth);
        TwelvethA = (EditText) findViewById(R.id.TwelvethAnswer);
        ThirteenthQ = (TextView)findViewById(R.id.Thirteenth);
        ThirteenthA = (EditText) findViewById(R.id.ThirteenthAnswer);
        FourteenthQ = (TextView)findViewById(R.id.Fourteenth);
        FourteenthA = (EditText) findViewById(R.id.FourteenthAnswer);
        FifteenthQ = (TextView)findViewById(R.id.Fifteenth);
        FifteenthA = (EditText) findViewById(R.id.FifteenthAnswer);

        SixteenthQ = (TextView)findViewById(R.id.Sixteenth);
        SixteenthA = (EditText) findViewById(R.id.SixteenthAnswer);
        SeventeenthQ = (TextView)findViewById(R.id.Seventeenth);
        SeventeenthA = (EditText) findViewById(R.id.SeventeenthAnswer);
        EighteenthQ = (TextView)findViewById(R.id.Eighteenth);
        EighteenthA = (EditText) findViewById(R.id.EighteenthAnswer);
        NinteenthQ = (TextView)findViewById(R.id.Ninteenth);
        NinteenthA = (EditText) findViewById(R.id.NinteenthAnswer);
        TwentiethQ = (TextView)findViewById(R.id.Twentieth);
        TwentiethA = (EditText) findViewById(R.id.TwentiethAnswer);

        Twenty1Q = (TextView)findViewById(R.id.Twenty1) ;
        Twenty1A = (EditText) findViewById(R.id.Twenty1Answer);
        Twenty2Q = (TextView)findViewById(R.id.Twenty2);
        Twenty2A = (EditText) findViewById(R.id.Twenty2Answer);
        Twenty3Q = (TextView)findViewById(R.id.Twenty3);
        Twenty3A = (EditText) findViewById(R.id.Twenty3Answer);
        Twenty4Q = (TextView)findViewById(R.id.Twenty4);
        Twenty4A = (EditText) findViewById(R.id.Twenty4Answer);
        Twenty5Q = (TextView)findViewById(R.id.Twenty5);
        Twenty5A = (EditText) findViewById(R.id.Twenty5Answer);

        Submit = (TextView)findViewById(R.id.sbmit);
        From = (TextView)findViewById(R.id.welcome);
        From.setText(getIntent().getExtras().getString("Email"));

        sbmits = new Firebase("https://login-f2aaf.firebaseio.com/Jawaban/" + From.getText().toString().replace('.',',') + "/Upload/Times");

        urlFire = new Firebase("https://login-f2aaf.firebaseio.com");

        urlA1 = new Firebase("https://login-f2aaf.firebaseio.com/Jawaban");
        SendButton = (Button)findViewById(R.id.Sndbutton);

        sbmits.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String Value = dataSnapshot.getValue(String.class);
                if(!dataSnapshot.exists()){
                    sendingData = 1;
                    sendData = String.valueOf(sendingData);
                    Submit.setText(sendData);

                }
                else {
                    sendingData = Integer.valueOf(Value) + 1;
                    Submit.setText(Value);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        ValueEventListener soal = urlFire.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                DataSnapshot soalSnapshot = dataSnapshot.child("Soal");
                Iterable<DataSnapshot> soalChildren = soalSnapshot.getChildren();
                ArrayList<String> StringNumber = new ArrayList<String>(5);
                boolean baka = false;
                int num = 0;
                while (soalChildren.iterator().hasNext()) {
                    DataSnapshot soal = soalChildren.iterator().next();
                    if(!baka){
                        if (soalSnapshot.getChildrenCount() >= 20) {
                            String data = soal.getKey();
                            String forCheck = "Soal 01";
                            if (data.equals(forCheck)) {
                                baka = true;
                                Twenty1Q.setText("Lower class");
                                Twenty2Q.setText(null);
                                Twenty3Q.setText(null);
                                Twenty4Q.setText(null);
                                Twenty5Q.setText(null);

                            } else {
                                String s = soal.getValue(String.class);
                                StringNumber.add(s);
                                num++;
                            }
                        }
                    }
                }
                if(num > 0){
                    Twenty1Q.setText(StringNumber.get(0));
                    num--;
                }
                if(num > 0){
                    Twenty2Q.setText(StringNumber.get(1));
                    num--;
                }
                if (num > 0){
                    Twenty3Q.setText(StringNumber.get(2));
                    num--;
                }
                if (num > 0){
                    Twenty4Q.setText(StringNumber.get(3));
                    num--;
                }
                if (num > 0){
                    Twenty4Q.setText(StringNumber.get(4));
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        urlFire.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String Value = dataSnapshot.child("Soal").child("Soal 01").getValue(String.class);
                String Value2 = dataSnapshot.child("Soal").child("Soal 02").getValue(String.class);
                String Value3 = dataSnapshot.child("Soal").child("Soal 03").getValue(String.class);
                String Value4 = dataSnapshot.child("Soal").child("Soal 04").getValue(String.class);
                String Value5 = dataSnapshot.child("Soal").child("Soal 05").getValue(String.class);

                String Value6 = dataSnapshot.child("Soal").child("Soal 06").getValue(String.class);
                String Value7 = dataSnapshot.child("Soal").child("Soal 07").getValue(String.class);
                String Value8 = dataSnapshot.child("Soal").child("Soal 08").getValue(String.class);
                String Value9 = dataSnapshot.child("Soal").child("Soal 09").getValue(String.class);
                String Value10 = dataSnapshot.child("Soal").child("Soal 10").getValue(String.class);

                String Value11 = dataSnapshot.child("Soal").child("Soal 11").getValue(String.class);
                String Value12 = dataSnapshot.child("Soal").child("Soal 12").getValue(String.class);
                String Value13 = dataSnapshot.child("Soal").child("Soal 13").getValue(String.class);
                String Value14 = dataSnapshot.child("Soal").child("Soal 14").getValue(String.class);
                String Value15 = dataSnapshot.child("Soal").child("Soal 15").getValue(String.class);

                String Value16 = dataSnapshot.child("Soal").child("Soal 16").getValue(String.class);
                String Value17 = dataSnapshot.child("Soal").child("Soal 17").getValue(String.class);
                String Value18 = dataSnapshot.child("Soal").child("Soal 18").getValue(String.class);
                String Value19 = dataSnapshot.child("Soal").child("Soal 19").getValue(String.class);
                String Value20 = dataSnapshot.child("Soal").child("Soal 20").getValue(String.class);

                FirstQ.setText(Value);
                SecondQ.setText(Value2);
                ThirdQ.setText(Value3);
                FourthQ.setText(Value4);
                FifthQ.setText(Value5);

                SixthQ.setText(Value6);
                SeventhQ.setText(Value7);
                EighthQ.setText(Value8);
                NinthQ.setText(Value9);
                TenthQ.setText(Value10);

                EleventhQ.setText(Value11);
                TwelvethQ.setText(Value12);
                ThirteenthQ.setText(Value13);
                FourteenthQ.setText(Value14);
                FifteenthQ.setText(Value15);

                SixteenthQ.setText(Value16);
                SeventeenthQ.setText(Value17);
                EighteenthQ.setText(Value18);
                NinteenthQ.setText(Value19);
                TwentiethQ.setText(Value20);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        SendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Firebase firebaseA1 = urlA1.child(From.getText().toString().replace('.',','));
                firebaseA1.child(Submit.getText().toString()).child(FirstQ.getText().toString()).setValue(FirstA.getText().toString());
                firebaseA1.child(Submit.getText().toString()).child(SecondQ.getText().toString()).setValue(SecondA.getText().toString());
                firebaseA1.child(Submit.getText().toString()).child(ThirdQ.getText().toString()).setValue(ThirdA.getText().toString());
                firebaseA1.child(Submit.getText().toString()).child(FourthQ.getText().toString()).setValue(FourthA.getText().toString());
                firebaseA1.child(Submit.getText().toString()).child(FifthQ.getText().toString()).setValue(FifthA.getText().toString());

                firebaseA1.child(Submit.getText().toString()).child(SixthQ.getText().toString()).setValue(SixthA.getText().toString());
                firebaseA1.child(Submit.getText().toString()).child(SeventhQ.getText().toString()).setValue(SeventhA.getText().toString());
                firebaseA1.child(Submit.getText().toString()).child(EighthQ.getText().toString()).setValue(EighthA.getText().toString());
                firebaseA1.child(Submit.getText().toString()).child(NinthQ.getText().toString()).setValue(NinthA.getText().toString());
                firebaseA1.child(Submit.getText().toString()).child(TenthQ.getText().toString()).setValue(TenthA.getText().toString());

                firebaseA1.child(Submit.getText().toString()).child(EleventhQ.getText().toString()).setValue(EleventhA.getText().toString());
                firebaseA1.child(Submit.getText().toString()).child(TwelvethQ.getText().toString()).setValue(TwelvethA.getText().toString());
                firebaseA1.child(Submit.getText().toString()).child(ThirteenthQ.getText().toString()).setValue(ThirteenthA.getText().toString());
                firebaseA1.child(Submit.getText().toString()).child(FourteenthQ.getText().toString()).setValue(FourteenthA.getText().toString());
                firebaseA1.child(Submit.getText().toString()).child(FifteenthQ.getText().toString()).setValue(FifteenthA.getText().toString());

                firebaseA1.child(Submit.getText().toString()).child(SixteenthQ.getText().toString()).setValue(SixteenthA.getText().toString());
                firebaseA1.child(Submit.getText().toString()).child(SeventeenthQ.getText().toString()).setValue(SeventeenthA.getText().toString());
                firebaseA1.child(Submit.getText().toString()).child(EighteenthQ.getText().toString()).setValue(EighteenthA.getText().toString());
                firebaseA1.child(Submit.getText().toString()).child(NinteenthQ.getText().toString()).setValue(NinteenthA.getText().toString());
                firebaseA1.child(Submit.getText().toString()).child(TwentiethQ.getText().toString()).setValue(TwentiethA.getText().toString());

                if(!TextUtils.isEmpty(Twenty1Q.getText().toString())) {
                    firebaseA1.child(Submit.getText().toString()).child(Twenty1Q.getText().toString()).setValue(Twenty1A.getText().toString());
                }
                if(!TextUtils.isEmpty(Twenty2Q.getText().toString())) {
                    firebaseA1.child(Submit.getText().toString()).child(Twenty2Q.getText().toString()).setValue(Twenty2A.getText().toString());
                }
                if(!TextUtils.isEmpty(Twenty3Q.getText().toString())) {
                    firebaseA1.child(Submit.getText().toString()).child(Twenty3Q.getText().toString()).setValue(Twenty3A.getText().toString());
                }
                if(!TextUtils.isEmpty(Twenty4Q.getText().toString())) {
                    firebaseA1.child(Submit.getText().toString()).child(Twenty4Q.getText().toString()).setValue(Twenty4A.getText().toString());
                }
                if(!TextUtils.isEmpty(Twenty5Q.getText().toString())) {
                    firebaseA1.child(Submit.getText().toString()).child(Twenty5Q.getText().toString()).setValue(Twenty5A.getText().toString());
                }

                if(Submit.getText().toString().equals("1")){
                    firebaseA1.child("Upload").child("Times").setValue(sendingData + 1);
                }
                else {
                    firebaseA1.child("Upload").child("Times").setValue(sendingData);
                }
                sendingData ++;

                Toast.makeText(BiodataActivity.this, "Send data success", Toast.LENGTH_LONG).show();

                Intent i = new Intent(BiodataActivity.this, AplikasiActivity.class);
                i.putExtra("Email", From.getText().toString());
                startActivity(i);
            }
        });
    }
}
