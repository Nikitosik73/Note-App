package com.mirea.todolist.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.mirea.todolist.R;
import com.mirea.todolist.data.Note;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {

    private List<Note> notes = new ArrayList<>();
    private onNoteClickListener onNoteClickListener;

    public void setOnNoteClickListener(NotesAdapter.onNoteClickListener onNoteClickListener) {
        this.onNoteClickListener = onNoteClickListener;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }

    public List<Note> getNotes() {
        return new ArrayList<>(notes);
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.note_item,
                parent,
                false
        );
        return new NotesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {

        Note note = notes.get(position);
        holder.textViewNotes.setText(note.getText());

        int colorResultId;
        switch (note.getPriority()) {
            case 0:
                colorResultId = R.color.green;
                break;
            case 1:
                colorResultId = R.color.orange;
                break;
            default:
                colorResultId = R.color.red;
        }
        holder.textViewNotes.setBackgroundColor(
                ContextCompat.getColor(holder.itemView.getContext(), colorResultId)
        );

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onNoteClickListener != null){
                    onNoteClickListener.onNoteClick(note);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public static class NotesViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewNotes;

        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNotes = itemView.findViewById(R.id.textViewNotes);
        }
    }

    interface onNoteClickListener {
        void onNoteClick(Note note);
    }
}
