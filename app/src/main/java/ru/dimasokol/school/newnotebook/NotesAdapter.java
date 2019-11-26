package ru.dimasokol.school.newnotebook;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

import ru.dimasokol.school.newnotebook.room.Note;

public class NotesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Note> mNotes = Collections.emptyList();

    public NotesAdapter() {
        setHasStableIds(true);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NoteHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(android.R.layout.simple_list_item_2, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((NoteHolder) holder).bind(mNotes.get(position));
    }

    @Override
    public int getItemCount() {
        return mNotes.size();
    }

    @Override
    public long getItemId(int position) {
        return mNotes.get(position).getId();
    }

    public void setNotes(List<Note> notes) {
        mNotes = notes;
        notifyDataSetChanged();
    }

    private class NoteHolder extends RecyclerView.ViewHolder {

        private TextView mTitle;
        private TextView mContent;

        public NoteHolder(@NonNull View itemView) {
            super(itemView);
            mTitle = itemView.findViewById(android.R.id.text1);
            mContent = itemView.findViewById(android.R.id.text2);
        }

        public void bind(Note note) {
            mTitle.setText(note.getTitle());
            mContent.setText(note.getContent());
        }
    }
}
