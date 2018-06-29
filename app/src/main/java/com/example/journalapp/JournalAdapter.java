package com.example.journalapp;


import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class JournalAdapter extends RecyclerView.Adapter<JournalAdapter.EntryViewHolder> {

    /*I need to specify how many views the adapter will hold.*/
    private int numberItems;

    public JournalAdapter(int numberOfItems){
        numberItems = numberOfItems;
    }

    @NonNull
    @Override
    public EntryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.journal_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, parent,shouldAttachToParentImmediately);
        EntryViewHolder viewHolder = new EntryViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull EntryViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return numberItems;
    }

    class EntryViewHolder extends RecyclerView.ViewHolder{

        /*Elements in the ViewHolder*/
        TextView entryName, entryDate;
        ImageView editEntry, deleteEntry;

        /*Constructor*/
        public EntryViewHolder(View itemView){
            super(itemView);
            entryName = (TextView)itemView.findViewById(R.id.tv_entry_name);
            entryDate = (TextView) itemView.findViewById(R.id.tv_entry_date);
            editEntry = (ImageView)itemView.findViewById(R.id.img_edit_entry);
            deleteEntry = (ImageView)itemView.findViewById(R.id.img_delete_entry);
        }

        /*Conviniece method bind()*/
        void bind(int listIndex){

            entryName.setText(String.valueOf(listIndex));
            entryDate.setText(String.valueOf(listIndex));

        }

    }
}
