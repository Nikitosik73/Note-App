package com.mirea.todolist.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NotesDao {

    @Query("select * from notes")
    LiveData<List<Note>> getNotes();

    @Insert
    void add(Note note);

    @Query("delete from notes where id = :id")
    void remove(int id);
}
