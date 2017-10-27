package com.example.guijet.tp2_android.Classes;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.guijet.tp2_android.R;
import com.example.guijet.tp2_android.Tools.Fonts.ModifyFonts;

import java.util.List;

/**
 * Created by Charles on 2017-10-26.
 */

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Message> messages;
    private Context ctx;

    public ChatAdapter(Activity ctx_,List<Message> messages) {
        this.messages = messages;
        this.ctx = ctx_;
    }

    private class RightViewHolder extends RecyclerView.ViewHolder {
        public TextView message_content;
        public TextView sender_name;

        public RightViewHolder(View view) {
            super(view);
            sender_name = view.findViewById(R.id.sender_name);
            message_content = view.findViewById(R.id.message_content);
        }
    }

    private class LeftViewHolder extends RecyclerView.ViewHolder {
        public TextView message_content;
        public TextView sender_name;

        public LeftViewHolder(View view) {
            super(view);
            sender_name = view.findViewById(R.id.sender_name);
            message_content = view.findViewById(R.id.message_content);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return messages.get(position).getSender();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == 1)
            return new RightViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.sender_view, parent, false));
        else if (viewType == 0)
            return new LeftViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.receiver_view, parent, false));
        else
            return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case 1:
                RightViewHolder rightViewHolder = (RightViewHolder)holder;
                rightViewHolder.message_content.setText(messages.get(position).getContent());
                rightViewHolder.sender_name.setText(messages.get(position).getUser().getUsername());
                break;
            case 0:
                LeftViewHolder leftViewHolder = (LeftViewHolder)holder;
                leftViewHolder.message_content.setText(messages.get(position).getContent());
                leftViewHolder.sender_name.setText(messages.get(position).getUser().getUsername());
                break;
        }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }
}