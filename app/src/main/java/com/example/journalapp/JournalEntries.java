package com.example.journalapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class JournalEntries extends AppCompatActivity implements View.OnClickListener {

    FirebaseAuth firebaseAuth;
    TextView startRegistration;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal_entries);

        firebaseAuth = FirebaseAuth.getInstance();


        startRegistration = (TextView)findViewById(R.id.tv_start_registration_activity);





    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;

        switch (item.getItemId()){
            case R.id.menuNewEntry:
                /*This is where I will add a new entry*/
                //newEntry();
                intent = new Intent(getApplicationContext(),NewJournalEntry.class);
                startActivity(intent);
                return true;

            case R.id.menuSignOut:
                firebaseAuth.signOut();
                intent = new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
                return true;

                default:
                    return super.onOptionsItemSelected(item);
        }


    }


    private void initialize() {



    }

    @Override
    public void onClick(View v) {
        if(v == startRegistration){
            Intent intent = new Intent(getApplicationContext(),Registration.class);
            startActivity(intent);
        }
    }
}
