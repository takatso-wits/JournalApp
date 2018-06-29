package com.example.journalapp;

import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NewJournalEntry extends AppCompatActivity  implements View.OnClickListener {

    EditText edEntryName, edThoughts;
    /*CalendarView calendarView;*/
    Button btnAddEntry;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_journal_entry);
        initialize();

        /*calendarView.setOnClickListener(this);*/
        btnAddEntry.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if(v == btnAddEntry){
            startNewEntry();
        }

    }
    private void initialize() {

        edEntryName = (EditText)findViewById(R.id.et_entry_name);
        edThoughts = (EditText)findViewById(R.id.et_entry);
        /*calendarView = (CalendarView)findViewById(R.id.calDate);*/
        btnAddEntry = (Button)findViewById(R.id.btnAdd);
        databaseReference = FirebaseDatabase.getInstance().getReference("Entries");
    }


    private void startNewEntry() {
        String entryName = edEntryName.getText().toString().trim();
        String thoughts = edThoughts.getText().toString().trim();

        if(!TextUtils.isEmpty(entryName)){
            /*This will hel us in getting the Primary Key*/
            String entry_id = databaseReference.push().getKey();

            Entries entries = new Entries(entry_id,entryName,thoughts);

            databaseReference.setValue(entries);
            Toast.makeText(getApplicationContext(),"Entry created",Toast.LENGTH_LONG).show();



        }else{
            Toast.makeText(getApplicationContext(),"Entry name cannot be empty",Toast.LENGTH_LONG).show();
        }


        if(!TextUtils.isEmpty(thoughts)){

        }else{
            Toast.makeText(getApplicationContext(),"Thoughts cannot be empty",Toast.LENGTH_LONG).show();
        }


    }
}
