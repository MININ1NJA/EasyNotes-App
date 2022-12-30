package com.example.easynotes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;


public class NoteDetailsActivity extends AppCompatActivity {

    EditText titleEditText, contentEditText;
    ImageButton saveNotebtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_details);

        titleEditText = findViewById(R.id.notes_title_text);
        contentEditText = findViewById(R.id.notes_content_text);
        saveNotebtn = findViewById(R.id.save_note_btn);

        saveNotebtn.setOnClickListener((v)->{});

        Button submitNote = (Button) findViewById(R.id.submit_note);
        submitNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(NoteDetailsActivity.this,"Notes Added", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(NoteDetailsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    void saveNote(){
        String noteTitle = titleEditText.getText().toString();
        String noteContent = contentEditText.getText().toString();
        if (noteTitle == null || noteTitle.isEmpty()){
            titleEditText.setError("Title is required");
            return;
        }

        Note note= new Note();
        note.setTitle(noteTitle);
        note.setContent(noteContent);
        saveNoteToFirebase(note);

    }

    void saveNoteToFirebase(Note note){
        DocumentReference documentReference;
        documentReference = Utility.getCollectionReferenceForNotes().document();

        documentReference.set(note).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                //note is added
                if (task.isSuccessful()) {
                    Utility.ShowToast(NoteDetailsActivity.this,"Noted Added");
                    finish();
                } else {
                    Utility.ShowToast(NoteDetailsActivity.this,"Failed to add note");


                }
            }

        });




    }
}