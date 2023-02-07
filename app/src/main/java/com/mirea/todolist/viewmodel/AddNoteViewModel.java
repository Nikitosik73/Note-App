package com.mirea.todolist.viewmodel;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import androidx.lifecycle.MutableLiveData;

import com.mirea.todolist.data.Note;
import com.mirea.todolist.data.NotesDatabase;

public class AddNoteViewModel extends AndroidViewModel {

    private NotesDatabase notesDatabase;

    private MutableLiveData<Boolean> shouldCloseScreen = new MutableLiveData<>();

    private Handler handler = new Handler(Looper.getMainLooper());

    public AddNoteViewModel(@NonNull Application application) {
        super(application);
        notesDatabase = NotesDatabase.getInstance(application);
    }

    public LiveData<Boolean> getShouldCloseScreen() {
        return shouldCloseScreen;
    }

    public void saveNote(Note note) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                notesDatabase.notesDao().add(note);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        shouldCloseScreen.setValue(true);
                    }
                });
            }
        });
        thread.start();
    }
}
