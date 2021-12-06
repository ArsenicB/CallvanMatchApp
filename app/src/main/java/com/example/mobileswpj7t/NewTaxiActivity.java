package com.example.mobileswpj7t;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class NewTaxiActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore mStore = FirebaseFirestore.getInstance();
    private EditText mstart, mend;
    private String phonenum,nicname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_taxi);

        mstart=findViewById(R.id.taxi_start);
        mend=findViewById(R.id.taxi_end);

        findViewById(R.id.taxi_save_btn).setOnClickListener(this);//새로만들기 버튼 눌렸을 시
        if(mAuth.getCurrentUser()!=null){
            mStore.collection(FirebaseID.user).document(mAuth.getCurrentUser().getUid()).get()
                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if(task.getResult()!=null){
                                phonenum = (String)task.getResult().getData().get(FirebaseID.phoneNo);
                                nicname = (String)task.getResult().getData().get(FirebaseID.nicname);
                            }
                        }
                    });
        }

    }

    @Override
    public void onClick(View v) {
        if(mAuth.getCurrentUser()!=null){
            String taxiId = mStore.collection(FirebaseID.taxi).document().getId();
            Map<String, Object> data = new HashMap<>();
            data.put(FirebaseID.documentId, taxiId);
            data.put(FirebaseID.startpoint,mstart.getText().toString());
            data.put(FirebaseID.endpoint,mend.getText().toString());
            data.put(FirebaseID.people,"1명");
            data.put(FirebaseID.phoneNo_1, "닉네임 : "+nicname+" / 전화번호 : "+phonenum);
            data.put(FirebaseID.phoneNo_2, "");
            data.put(FirebaseID.phoneNo_3, "");
            data.put(FirebaseID.phoneNo_4, "");
            data.put(FirebaseID.timestamp, FieldValue.serverTimestamp());
            mStore.collection(FirebaseID.taxi).document(taxiId).set(data, SetOptions.merge());
            finish();
        }

    }
}