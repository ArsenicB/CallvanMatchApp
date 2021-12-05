package com.example.mobileswpj7t;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

public class Post2Activity extends AppCompatActivity {
    private FirebaseFirestore mStore = FirebaseFirestore.getInstance();

    private TextView mtitle, mcontent;

    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post2);

        mtitle = findViewById(R.id.post2_title);
        mcontent = findViewById(R.id.post2_contents);

        Intent getIntent = getIntent();
        id = getIntent.getStringExtra(FirebaseID.documentId);
        Log.e("ItemPost2",id);

        mStore.collection(FirebaseID.post).document(id).get()
            .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if(task.isSuccessful()){
                        if(task.getResult().exists()){
                            if(task.getResult().exists()){
                                if(task.getResult()!=null){
                                    Map<String,Object> snap = task.getResult().getData();
                                    String title = String.valueOf(snap.get(FirebaseID.title));
                                    String contents = String.valueOf(snap.get(FirebaseID.contents));
                                    mtitle.setText(title);
                                    mcontent.setText(contents);
                                }
                            }
                        }
                        else{
                            Toast.makeText(Post2Activity.this,"삭제된 문성비니다.",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
    }
}