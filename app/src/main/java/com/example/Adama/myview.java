package com.example.Adama;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class myview extends RecyclerView.ViewHolder{

    public TextView text;
    public TextView artist;
    public Button cancel;
     public myview(@NonNull View itemView) {
        super(itemView);
        text = itemView.findViewById(R.id.titleitem);
        artist = itemView.findViewById(R.id.artistitem);
        cancel = itemView.findViewById(R.id.cancelitem);

    }
}