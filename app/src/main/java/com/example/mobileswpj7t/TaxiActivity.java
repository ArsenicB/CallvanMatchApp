package com.example.mobileswpj7t;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.mobileswpj7t.databinding.ActivityBoardBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TaxiActivity extends DrawerBaseActivity implements View.OnClickListener {

    private Spinner startspinner, endspinner;
    private String startT="",endT="";
    private TextView mtext;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore mStore = FirebaseFirestore.getInstance();
    private String phonenum,nicname;

    private boolean startB=false,endB=false;
    ActivityBoardBinding activityBoardBinding;

    private ArrayList<Taxi> arrayList;
    private TaxiAdapter taxiAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityBoardBinding = ActivityBoardBinding.inflate(getLayoutInflater());
        setContentView(activityBoardBinding.getRoot());
        allocateActivityTitle("콜밴 합승");

        mtext=findViewById(R.id.text);

        findViewById(R.id.new_btn).setOnClickListener(this);//새로만들기 버튼 눌렸을 시
        Button btn =(Button) findViewById(R.id.join_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                taxiAdapter.notifyDataSetChanged();
            }
        });
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

        startspinner = (Spinner) findViewById(R.id.Start_spin);
        endspinner = (Spinner) findViewById(R.id.End_spin);
        startspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                startT = adapterView.getItemAtPosition(i).toString();
                startB=true;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                startB=false;
            }
        });

        endspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                endT = adapterView.getItemAtPosition(i).toString();
                endB=true;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                endB=false;
            }
        });

        //리사이클러뷰
        recyclerView = (RecyclerView)findViewById(R.id.taxi_recyclerview);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);;

        arrayList = new ArrayList<>();
        taxiAdapter = new TaxiAdapter(arrayList);
        recyclerView.setAdapter(taxiAdapter);


    }

    @Override//새로만들기 버튼 눌렸을 시
    public void onClick(View view) {
        mtext.setText("버튼 클릭 정상"+startB+"/"+endB+"/"+phonenum + "/");
        if(startB==false || endB ==false){
            //하나라도 선택이 제대로 안되었을 경우
            mtext.setText("제대로 클릭");
        }
        if(mAuth.getCurrentUser()!=null){
            String taxiId = mStore.collection(FirebaseID.taxi).document().getId();
            Map<String, Object> data = new HashMap<>();
            data.put(FirebaseID.documentId, taxiId);
            data.put(FirebaseID.startpoint,startT);
            data.put(FirebaseID.endpoint,endT);
            data.put(FirebaseID.people,1);
            data.put(FirebaseID.phoneNo_1, "닉네임 : "+nicname+" / 전화번호 : "+phonenum);
            data.put(FirebaseID.phoneNo_2, "");
            data.put(FirebaseID.phoneNo_3, "");
            data.put(FirebaseID.phoneNo_4, "");
            mStore.collection(FirebaseID.taxi).document(taxiId).set(data, SetOptions.merge());
        }
    }
}