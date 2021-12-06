package com.example.mobileswpj7t;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class JoinTaxiActivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore mStore = FirebaseFirestore.getInstance();

    private TextView txtS,txtE,txt01,txt02,txt03,txt04;
    private Button btnJ,btnB;
    private String id;
    private String phonenum,nicname,people;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_taxi);

        txtS=findViewById(R.id.text_starting2);
        txtE=findViewById(R.id.text_ending2);
        txt01=findViewById(R.id.text_person01_2);
        txt02=findViewById(R.id.text_person02_2);
        txt03=findViewById(R.id.text_person03_2);
        txt04=findViewById(R.id.text_person04_2);
        btnB=findViewById(R.id.btn_join);
        btnJ=findViewById(R.id.btn_back);


        Intent getIntent = getIntent();
        id = getIntent.getStringExtra(FirebaseID.documentId);

        findViewById(R.id.btn_join).setOnClickListener(this);

        if(mAuth.getCurrentUser()!=null){
            mStore.collection(FirebaseID.user).document(mAuth.getCurrentUser().getUid()).get()
                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if(task.getResult()!=null){
                                phonenum = (String)task.getResult().getData().get(FirebaseID.phoneNo);
                                nicname = (String)task.getResult().getData().get(FirebaseID.nicname);
                                people = (String)task.getResult().getData().get(FirebaseID.people);
                            }
                        }
                    });
        }


        mStore.collection(FirebaseID.taxi).document(id).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            if(task.getResult().exists()){
                                if(task.getResult().exists()){
                                    if(task.getResult()!=null){
                                        Map<String,Object> snap = task.getResult().getData();
                                        String S = String.valueOf(snap.get(FirebaseID.startpoint));
                                        String E = String.valueOf(snap.get(FirebaseID.endpoint));
                                        String U01 = String.valueOf(snap.get(FirebaseID.phoneNo_1));
                                        String U02 = String.valueOf(snap.get(FirebaseID.phoneNo_2));
                                        String U03 = String.valueOf(snap.get(FirebaseID.phoneNo_3));
                                        String U04 = String.valueOf(snap.get(FirebaseID.phoneNo_4));
                                        txtS.setText(S);
                                        txtE.setText(E);
                                        txt01.setText(U01);
                                        txt02.setText(U02);
                                        txt03.setText(U03);
                                        txt04.setText(U04);
                                    }
                                }
                            }
                            else{
                                Toast.makeText(JoinTaxiActivity.this,"알수없는 에러",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }
    public void btnBack(View v){
        finish();
    }

    @Override
    public void onClick(View v) {
        if(mAuth.getCurrentUser()!=null){
                if(txt02.getText().toString().equals("")){
                    //2번이 비어있음
                    String taxiId = mStore.collection(FirebaseID.taxi).document().getId();
                    Map<String, Object> data = new HashMap<>();
                    data.put(FirebaseID.documentId, taxiId);
                    data.put(FirebaseID.startpoint,txtS.getText().toString());
                    data.put(FirebaseID.endpoint,txtE.getText().toString());
                    data.put(FirebaseID.people,"2명");
                    data.put(FirebaseID.phoneNo_1, txt01.getText().toString());
                    data.put(FirebaseID.phoneNo_2, "닉네임 : "+nicname+" / 전화번호 : "+phonenum);
                    data.put(FirebaseID.phoneNo_3, "");
                    data.put(FirebaseID.phoneNo_4, "");
                    data.put(FirebaseID.timestamp, FieldValue.serverTimestamp());
                    mStore.collection(FirebaseID.taxi).document(taxiId).set(data, SetOptions.merge());
                    mStore.collection(FirebaseID.taxi).document(id).delete();
                    finish();
                }
                else if(txt03.getText().toString().equals("")){
                    //3번이 비어있음
                    String taxiId = mStore.collection(FirebaseID.taxi).document().getId();
                    Map<String, Object> data = new HashMap<>();
                    data.put(FirebaseID.documentId, taxiId);
                    data.put(FirebaseID.startpoint,txtS.getText().toString());
                    data.put(FirebaseID.endpoint,txtE.getText().toString());
                    data.put(FirebaseID.people,"3명");
                    data.put(FirebaseID.phoneNo_1, txt01.getText().toString());
                    data.put(FirebaseID.phoneNo_2, txt02.getText().toString());
                    data.put(FirebaseID.phoneNo_3, "닉네임 : "+nicname+" / 전화번호 : "+phonenum);
                    data.put(FirebaseID.phoneNo_4, "");
                    data.put(FirebaseID.timestamp, FieldValue.serverTimestamp());
                    mStore.collection(FirebaseID.taxi).document(taxiId).set(data, SetOptions.merge());
                    mStore.collection(FirebaseID.taxi).document(id).delete();
                    finish();
                }
                else if(txt04.getText().toString().equals("")){
                    //4번이 비어있음
                    String taxiId = mStore.collection(FirebaseID.taxi).document().getId();
                    Map<String, Object> data = new HashMap<>();
                    data.put(FirebaseID.documentId, taxiId);
                    data.put(FirebaseID.startpoint,txtS.getText().toString());
                    data.put(FirebaseID.endpoint,txtE.getText().toString());
                    data.put(FirebaseID.people,"4명");
                    data.put(FirebaseID.phoneNo_1, txt01.getText().toString());
                    data.put(FirebaseID.phoneNo_2, txt02.getText().toString());
                    data.put(FirebaseID.phoneNo_3, txt03.getText().toString());
                    data.put(FirebaseID.phoneNo_4, "닉네임 : "+nicname+" / 전화번호 : "+phonenum);
                    data.put(FirebaseID.timestamp, FieldValue.serverTimestamp());
                    mStore.collection(FirebaseID.taxi).document(taxiId).set(data, SetOptions.merge());
                    mStore.collection(FirebaseID.taxi).document(id).delete();
                    finish();
                }
                else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("인원이 꽉 찼습니다.");
                    builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getApplicationContext(), "확인 하였습니다.", Toast.LENGTH_SHORT).show();
                        }
                    });
                    AlertDialog alertDialog =builder.create();
                    alertDialog.show();
                    finish();
                }
        }
    }
}