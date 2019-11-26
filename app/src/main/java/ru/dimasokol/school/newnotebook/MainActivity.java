package ru.dimasokol.school.newnotebook;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.Date;
import java.util.List;

import ru.dimasokol.school.newnotebook.room.Note;
import ru.dimasokol.school.newnotebook.room.NotesDatabase;

public class MainActivity extends AppCompatActivity {

    private View mAddButton;
    private NotesDatabase mDb;
    private LiveData<List<Note>> mLiveData;
    private RecyclerView mRecyclerView;
    private NotesAdapter mAdapter;
    private View mDeleteAllButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RoomDatabase.Builder<NotesDatabase> builder = Room.databaseBuilder(this, NotesDatabase.class, "notes");
        builder.addMigrations(new Migration(1, 2) {
            @Override
            public void migrate(@NonNull SupportSQLiteDatabase database) {
                database.execSQL("CREATE TABLE IF NOT EXISTS `Tags` (`id` INTEGER NOT NULL, `name` TEXT, `note_id` INTEGER NOT NULL, PRIMARY KEY(`id`), FOREIGN KEY(`note_id`) REFERENCES `Note`(`id`) ON UPDATE NO ACTION ON DELETE NO ACTION )");
                database.execSQL("CREATE  INDEX `index_Tags_note_id` ON `Tags` (`note_id`)");
            }
        }, new Migration(2, 3) {
            @Override
            public void migrate(@NonNull SupportSQLiteDatabase database) {
                database.execSQL("CREATE  INDEX `index_Note_last_modified` ON `Note` (`last_modified`)");
            }
        });
        mDb = builder.build();

        mRecyclerView = findViewById(R.id.recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new NotesAdapter();
        mRecyclerView.setAdapter(mAdapter);

        mLiveData = mDb.getNotesDao().getAllNotes();
        mLiveData.observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                mAdapter.setNotes(notes);
            }
        });

        mAddButton = findViewById(R.id.button_add);
        mDeleteAllButton = findViewById(R.id.button_delete);

        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Note test = new Note();
                test.setContent("Test test test");
                test.setTitle("Test");
                test.setLastModified(new Date());

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        mDb.getNotesDao().addNote(test);
                    }
                }).start();

            }
        });

        mDeleteAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        mDb.getNotesDao().removeAll();
                    }
                }).start();
            }
        });
    }
}
