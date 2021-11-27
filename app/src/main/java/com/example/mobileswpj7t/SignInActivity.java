package com.example.mobileswpj7t;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity {
    EditText editTextUserName, editTextPassword;
    TextView textViewForgotPassword, textViewRegister;
    ProgressBar progressBar;

    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_activity);

        editTextUserName = (EditText) findViewById(R.id.editTextUserName);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);

        textViewForgotPassword = (TextView) findViewById(R.id.txtSignInForgotPassword);
        textViewRegister = (TextView) findViewById(R.id.txtSignInRegister);
        progressBar = (ProgressBar) findViewById(R.id.progressBarSignIn);

        mAuth = FirebaseAuth.getInstance();

    }

    public void txtSignInForgotPasswordClicked(View v){
        Intent intent = new Intent(this,ForgotPasswordActivity.class);
        startActivity(intent);
    }

    public void txtSignInRegisterClicked(View v){
        Intent intent = new Intent(this,SignUpActivity.class);
        startActivity(intent);
    }

    public void buttonSignInScrSignInClicked(View v){
        String userName = editTextUserName.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        //이메일이 다르다면
        if(!Patterns.EMAIL_ADDRESS.matcher(userName).matches() || editTextPassword.length() < 6){
            if(!Patterns.EMAIL_ADDRESS.matcher(userName).matches()){
                editTextUserName.setError("정확한 이메일 입력바랍니다.");
                editTextUserName.requestFocus();
            }//비밀번호가 이상하다면
            if(editTextPassword.length() < 6){
                editTextPassword.setError("비밀번호는 6자 이상이어야 합니다.");
                editTextPassword.requestFocus();
            }
        }else{
            progressBar.setVisibility(View.VISIBLE);

            mAuth.signInWithEmailAndPassword(userName, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(SignInActivity.this,"로그인에 성공 했습니다.", Toast.LENGTH_LONG).show();
                    }else{
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(SignInActivity.this,"로그인에 실패 했습니다.", Toast.LENGTH_LONG).show();
                    }

                }
            });
        }
    }
}