package com.example.mobileswpj7t;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.mobileswpj7t.Adapters.Board;
import com.example.mobileswpj7t.Adapters.PostAdapter;
import com.example.mobileswpj7t.databinding.ActivityBoardBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaxiActivity extends DrawerBaseActivity implements RecyclerViewItemClickListener.OnItemClickListener, View.OnClickListener {

    private String startT="",endT="";
    private TextView mtext;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore mStore = FirebaseFirestore.getInstance();
    private String phonenum,nicname;

    private boolean startB=false,endB=false;
    ActivityBoardBinding activityBoardBinding;

    private RecyclerView recyclerView;
    private TaxiAdapter taxiAdapter;
    private List<Taxi> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityBoardBinding = ActivityBoardBinding.inflate(getLayoutInflater());
        setContentView(activityBoardBinding.getRoot());
        allocateActivityTitle("콜밴 합승");

        findViewById(R.id.new_btn).setOnClickListener(this);

        recyclerView = findViewById(R.id.taxi_recyclerview);

        recyclerView.addOnItemTouchListener(new RecyclerViewItemClickListener(this,recyclerView,this));
    }

    @Override
    protected void onStart() {
        super.onStart();
        mDatas = new ArrayList<>();
        mStore.collection(FirebaseID.taxi).orderBy(FirebaseID.timestamp, Query.Direction.DESCENDING)
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    mDatas.clear();
                    for(DocumentSnapshot snap : task.getResult()){
                        Map<String, Object> shot = snap.getData();
                        String documentId = String.valueOf(shot.get(FirebaseID.documentId));
                        String startP = String.valueOf(shot.get(FirebaseID.startpoint));
                        String endP = String.valueOf(shot.get(FirebaseID.endpoint));
                        String people = String.valueOf(shot.get(FirebaseID.people));
                        Taxi data = new Taxi(documentId, startP, endP, people);
                        //Board data = new Board(documentId, title, contents);
                        mDatas.add(data);
                    }
                    taxiAdapter = new TaxiAdapter(mDatas);
                    recyclerView.setAdapter(taxiAdapter);
                }
            }
        });
    }

    @Override
    public void onItemClick(View view, int position) {

    }

    @Override
    public void onItemLongClick(View view, int position) {
        Intent intent = new Intent(this,JoinTaxiActivity.class);
        intent.putExtra(FirebaseID.documentId, mDatas.get(position).getDocumentId());
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(TaxiActivity.this,NewTaxiActivity.class);
        startActivity(intent);
    }
}