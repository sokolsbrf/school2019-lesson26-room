package ru.dimasokol.school.newnotebook.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NotesDao {

    @Query("SELECT * FROM Note")
    LiveData<List<Note>> getAllNotes();

    @Query("SELECT * FROM Note WHERE id = :noteId")
    LiveData<Note> getNoteById(long noteId);

    @Insert
    long addNote(Note note);

    @Delete
    void removeNotes(Note... notes);

    @Query("DELETE FROM Note WHERE 1")
    void removeAll();

    @Update
    void updateNote(Note note);
}
