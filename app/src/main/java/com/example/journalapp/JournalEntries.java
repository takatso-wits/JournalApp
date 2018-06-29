package com.example.journalapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class JournalEntries extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    private JournalAdapter journalAdapter;
    private RecyclerView entry;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal_entries);

        firebaseAuth = FirebaseAuth.getInstance();

        entry = (RecyclerView)findViewById(R.id.rv_journal_entries);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        entry.setLayoutManager(layoutManager);

        journalAdapter = new JournalAdapter(1);
        entry.setAdapter(journalAdapter);


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
                Toast.makeText(this,
                        "Come back in a very short while..",
                        Toast.LENGTH_SHORT).show();
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
}
