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
    LiveData<List<Note>> getNotes();

    @Insert
    Completable add(Note note);

    @Query("delete from notes where id = :id")
    Completable remove(int id);
}
