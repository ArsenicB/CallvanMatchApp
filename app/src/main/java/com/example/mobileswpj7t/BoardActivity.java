package com.example.mobileswpj7t;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.mobileswpj7t.Adapters.PostAdapter;
import com.example.mobileswpj7t.databinding.ActivityTaxiBinding;
import com.example.mobileswpj7t.Adapters.Board;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BoardActivity extends DrawerBaseActivity implements View.OnClickListener, RecyclerViewItemClickListener.OnItemClickListener {
    private FirebaseFirestore mStore = FirebaseFirestore.getInstance();
    private RecyclerView mPostRecyclerview;

    private PostAdapter mAdapter;
    private List<Board> mDatas;

    ActivityTaxiBinding activityTaxiBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityTaxiBinding = ActivityTaxiBinding.inflate(getLayoutInflater());
        setContentView(activityTaxiBinding.getRoot());
        allocateActivityTitle("게시판");

        mPostRecyclerview = findViewById(R.id.main_recyclerview);

        findViewById(R.id.main_post_edit).setOnClickListener(this);

        mPostRecyclerview.addOnItemTouchListener(new RecyclerViewItemClickListener(this,mPostRecyclerview, this));
    }

    @Override
    protected void onStart() {
        super.onStart();
        mDatas = new ArrayList<>();
        mStore.collection(FirebaseID.post).orderBy(FirebaseID.timestamp, Query.Direction.DESCENDING)
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    mDatas.clear();
                    for(DocumentSnapshot snap : task.getResult()){
                        Map<String, Object> shot = snap.getData();
                        String documentId = String.valueOf(shot.get(FirebaseID.documentId));
                        String nicname = String.valueOf(shot.get(FirebaseID.nicname));
                        String title = String.valueOf(snap.get(FirebaseID.title));
                        String contents = String.valueOf(shot.get(FirebaseID.contents));
                        Board data = new Board(documentId, nicname, title,contents);
                        //Board data = new Board(documentId, title, contents);
                        mDatas.add(data);
                    }

                    mAdapter = new PostAdapter(mDatas);
                    mPostRecyclerview.setAdapter(mAdapter);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(this, PostActivity.class));
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(this,Post2Activity.class);
        intent.putExtra(FirebaseID.documentId, mDatas.get(position).getDocumentId());
        startActivity(intent);

    }

    @Override
    public void onItemLongClick(View view, final int position) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setMessage("삭제 하시겠습니까?");
        dialog.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mStore.collection(FirebaseID.post).document(mDatas.get(position).getDocumentId()).delete();
                Toast.makeText(BoardActivity.this,"삭제 됨",Toast.LENGTH_SHORT).show();
            }
        }).setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(BoardActivity.this,"취소 됨",Toast.LENGTH_SHORT).show();
            }
        });
        dialog.setTitle("삭제 알림");
        dialog.show();
    }

}