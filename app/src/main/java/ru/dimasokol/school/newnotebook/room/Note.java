package ru.dimasokol.school.newnotebook.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class Note {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long mId;

    @ColumnInfo(name = "title")
    private String mTitle;

    @ColumnInfo(name = "content")
    private String mContent;

    @ColumnInfo(name = "last_modified", index = true)
    private Date mLastModified;

    @Ignore
    private String mTest;

    public String getTest() {
        return mTest;
    }

    public void setTest(String test) {
        mTest = test;
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }

    public Date getLastModified() {
        return mLastModified;
    }

    public void setLastModified(Date lastModified) {
        mLastModified = lastModified;
    }
}
