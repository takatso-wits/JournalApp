package com.example.journalapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity implements View.OnClickListener {
    Button btnLogin;
    EditText email, password;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;
    TextView startRegistration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initialize();

        /*Check whether the user is already logged in*/
        if(firebaseAuth.getCurrentUser() != null){
            finish();
            /*Start the Journal activity*/
            Intent intent = new Intent(getApplicationContext(), JournalEntries.class);
            startActivity(intent);
        }

        btnLogin.setOnClickListener(this);


}

    private void initialize() {
        email = (EditText) findViewById(R.id.et_user_email);
        password = (EditText)findViewById(R.id.et_user_password);
        btnLogin = (Button) findViewById(R.id.btn_login);
        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();
        startRegistration =(TextView)findViewById(R.id.tv_start_registration_activity);

    }

    @Override
    public void onClick(View v) {
        if(v == btnLogin){
            statLogin();
        }

        if(v == startRegistration){
            finish();
            Intent intent = new Intent(getApplicationContext(), Registration.class);
            startActivity(intent);
        }
    }

    private void statLogin() {

        String user_email = email.getText().toString().trim();
        String user_password = password.getText().toString().trim();
        String emailError = "Email field cannot be empty";
        String passwordError = "Password field cannot be empty";
        Context context = getApplicationContext();
        String  message = "Getting started...";

        if(TextUtils.isEmpty(user_email)){
            Toast.makeText(context,emailError,Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(user_password)){
            Toast.makeText(context,passwordError,Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage(message);
        progressDialog.show();

        /*Login Authentication using Firebase*/
        firebaseAuth.signInWithEmailAndPassword(user_email,user_password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if(task.isSuccessful()){
                            /*Start Journal Activity*/
                            finish();
                            Intent intent = new Intent(getApplicationContext(),
                                    JournalEntries.class);
                            startActivity(intent);

                        }else{
                            Toast.makeText(getApplicationContext(),
                                    "Could not Login...",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
