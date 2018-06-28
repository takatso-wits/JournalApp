package com.example.journalapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Registration extends AppCompatActivity implements View.OnClickListener {
    EditText username, password, email;
    Button btnRegistration;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        /*Call to the method that initializes all the views in the .xml file*/
        initialize();

        /*Check whether the user is already logged in*/
        if(firebaseAuth.getCurrentUser() != null){
            finish();
            /*Start the Journal activity*/
        }

        btnRegistration.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        if(v == btnRegistration){
            startRegistration();
        }

    }

    private void initialize() {
        username = (EditText)findViewById(R.id.et_user_name);
        password = (EditText)findViewById(R.id.et_user_password);
        email = (EditText)findViewById(R.id.et_user_email);
        btnRegistration = (Button)findViewById(R.id.btn_register);
        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();
    }

    private void startRegistration() {
         String user_email = email.getText().toString().trim();
         String user_password = password.getText().toString().trim();
         String user_name = username.getText().toString().trim();

         String nameError = "Name field cannot be empty";
         String emailError = "Email field cannot be empty";
         String passwordError = "Password field cannot be empty";
         String progressMessage = "Registration underway...";
         Context context = getApplicationContext();
         /*Check if the fields are not empty are not empty*/
         if(TextUtils.isEmpty(user_name)){
             Toast.makeText(context,nameError,Toast.LENGTH_SHORT).show();
             return;
         }
        if(TextUtils.isEmpty(user_email)){
            Toast.makeText(context,emailError,Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(user_password)){
            Toast.makeText(context,passwordError,Toast.LENGTH_SHORT).show();
            return;
        }

        /*Registering the user into Firebase*/
        progressDialog.setMessage(progressMessage);
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(user_email,user_password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();

                        if(task.isSuccessful()){
//                            finish();
                            /*Start the Journal Activity*/
                            Toast.makeText(getApplicationContext(),
                                    "Successfully Registered",
                                    Toast.LENGTH_SHORT).show();

                        }else{
                            Toast.makeText(getApplicationContext(),
                                    "Could not register, Please try again",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }
}
