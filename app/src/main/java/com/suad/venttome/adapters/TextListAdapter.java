package com.suad.venttome.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.card.MaterialCardView;
import com.suad.venttome.R;
import com.suad.venttome.TimeAgo;

import java.io.File;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TextListAdapter extends RecyclerView.Adapter<TextListAdapter.TextViewHolder>{

    private File[] allTextFiles;
    private TimeAgo timeAgo;

    private onTextItemListClick onTextItemListClick;



    public TextListAdapter(File[] allTextFiles, onTextItemListClick onTextItemListClick) {
        this.allTextFiles = allTextFiles;
        this.onTextItemListClick = onTextItemListClick;
    }

    @NonNull
    @Override
    public TextViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_text_item, parent, false);
        timeAgo = new TimeAgo();
        return new TextViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TextListAdapter.TextViewHolder holder, int position) {
        holder.cardText.setText(allTextFiles[position].getName());
        holder.textCardTitle.setText(timeAgo.getTimeAgo(allTextFiles[position].lastModified()));
    }

    @Override
    public int getItemCount() {
        return allTextFiles.length;
    }

    //Inner Class

    public class TextViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final Context context;
        private final View linearLayout;
        //Adding components
        private MaterialCardView TextCard;
        private TextView textCardTitle;
        private Button cardText;
        private String recordFile;
        private ImageButton deleteText;


        public TextViewHolder(@NonNull View itemView) {
            super(itemView);

            TextCard = itemView.findViewById(R.id.textCard);
            textCardTitle = itemView.findViewById(R.id.text_card_title);
            cardText = itemView.findViewById(R.id.card_text);
            context = itemView.getContext();
            deleteText = itemView.findViewById(R.id.delete_text_entry);
            linearLayout = itemView.findViewById(R.id.text_item_layout);

            TextCard.setOnClickListener(this);
            deleteText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    SharedPreferences prefs = context.getSharedPreferences("REMOVING", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putBoolean("Delete",true);
                    editor.putInt("What to Remove", position); // save updated value
                    editor.apply();
                    notifyItemRemoved(position);
                }
            });

        }




        @Override
        public void onClick(View v) {
            onTextItemListClick.onClickListener(allTextFiles[getAdapterPosition()], getAdapterPosition());
        }
        

    }




    //Interface
    public interface onTextItemListClick{
        void onClickListener(File file, int position);
    }
    
    
}
