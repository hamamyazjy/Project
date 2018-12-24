package com.example.hamam.project;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hamam.project.Model.Note;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NoteActivity extends AppCompatActivity {

    private DatabaseReference getUserDataReference;
    private RecyclerView noteList;
    String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);


        user_id =getIntent().getStringExtra( "user_id");


        //Firebase
        FirebaseDatabase database= FirebaseDatabase.getInstance();
        getUserDataReference =database.getReference().child("Task");



        noteList =findViewById(R.id.notelist);
        noteList.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        noteList.setLayoutManager(linearLayoutManager);



      DisplayAllUsersPosts();


    }

    private void DisplayAllUsersPosts() {

        FirebaseRecyclerAdapter<Note,NotesViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Note, NotesViewHolder>(
                Note.class,
                R.layout.note_item,
                NotesViewHolder.class,
                getUserDataReference.child(user_id)
        ) {
            @Override
            protected void populateViewHolder(NotesViewHolder viewHolder, Note model, int position) {

//                final  String postKey =getRef(position).getKey();

                viewHolder.setName(model.getName());
                viewHolder.setTime(model.getTime());
                viewHolder.setDate(model.getDate());
                viewHolder.setDescription(model.getDescription());

//
//                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//
//                        Intent i =new Intent(MainActivity.this,ClickPostActivity.class);
//                        i.putExtra("postKey",postKey);
//                        startActivity(i);
//                    }
//                });

            }
        };
        noteList.setAdapter(firebaseRecyclerAdapter);
    }


    public static class NotesViewHolder extends RecyclerView.ViewHolder {
        View mView;

        public NotesViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }


        public void setName(String name) {
            TextView notename = mView.findViewById(R.id.name);
            notename.setText(name);

         }

        public void setDate(String date){
            TextView notedate = mView.findViewById(R.id.date);
            notedate.setText(date);
        }

        public void setTime(String time){
            TextView noteTime = mView.findViewById(R.id.time);
            noteTime.setText(time);
        }

        public void setDescription(String description){
            TextView noteDescription = mView.findViewById(R.id.description);
            noteDescription.setText(description);
        }


    }

}
