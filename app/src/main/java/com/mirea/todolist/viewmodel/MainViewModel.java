package com.mirea.todolist.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mirea.todolist.data.Note;
import com.mirea.todolist.data.NotesDatabase;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private NotesDatabase notesDatabase;

    public MainViewModel(@NonNull Application application) {
        super(application);
        notesDatabase = NotesDatabase.getInstance(application);
    }

    public LiveData<List<Note>> getNotes(){
        return notesDatabase.notesDao().getNotes();
    }

    public void remove(Note note){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                notesDatabase.notesDao().remove(note.getId());
            }
        });
        thread.start();
    }
}
