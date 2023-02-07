package com.mirea.todolist.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface NotesDao {

    @Query("select * from notes")
    List<Note> getNotes();

    @Insert
    void add(Note note);

    @Query("delete from notes where id = :id")
    void remove(int id);
}
