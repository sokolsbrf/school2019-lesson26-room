package ru.dimasokol.school.newnotebook.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "Tags",
        foreignKeys = @ForeignKey(entity = Note.class,
            parentColumns = "id", childColumns = "note_id",
        onDelete = ForeignKey.CASCADE))
public class Tag {

    @PrimaryKey
    @ColumnInfo(name = "id")
    private long mId;

    @ColumnInfo(name = "name")
    private String mName;

    @ColumnInfo(name = "note_id", index = true)
    private long mNoteId;

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public long getNoteId() {
        return mNoteId;
    }

    public void setNoteId(long noteId) {
        mNoteId = noteId;
    }
}
