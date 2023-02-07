package com.mirea.todolist.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.mirea.todolist.data.Note;
import com.mirea.todolist.databinding.ActivityAddNoteBinding;
import com.mirea.todolist.viewmodel.AddNoteViewModel;

public class AddNoteActivity extends AppCompatActivity {

    private ActivityAddNoteBinding binding;

    private String text;
    private int priority;

    private AddNoteViewModel addNoteViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddNoteBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        addNoteViewModel = new ViewModelProvider(this).get(AddNoteViewModel.class);

        addNoteViewModel.getShouldCloseScreen().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean shouldClose) {
                if (shouldClose){
                    finish();
                }
            }
        });

        binding.buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNote();
            }
        });
    }

    private void saveNote() {
        text = binding.editTextAddNote.getText().toString().trim();
        priority = getPriority();
        Note note = new Note(text, priority);
        addNoteViewModel.saveNote(note);



        if (text.isEmpty()) {
            Toast.makeText(
                    this,
                    "Поле должно быть заполненным",
                    Toast.LENGTH_SHORT
            ).show();
        }
    }

    private int getPriority() {

        if (binding.radioButtonLowPriority.isChecked()) {
            priority = 0;
        } else if (binding.radioButtonMediumPriority.isChecked()) {
            priority = 1;
        } else {
            priority = 2;
        }
        return priority;
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, AddNoteActivity.class);
    }
}