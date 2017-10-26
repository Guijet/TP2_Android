package com.example.guijet.tp2_android.Classes;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.guijet.tp2_android.Classes.Message;
import com.example.guijet.tp2_android.R;
import com.example.guijet.tp2_android.Tools.Fonts.ModifyFonts;

import java.util.List;

/**
 * Created by Guijet on 2017-10-26.
 */

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Message> messages;
    private Context ctx;

    class RightViewHolder extends RecyclerView.ViewHolder {

        TextView message_tv;

        public RightViewHolder(View view) {
            super(view);
            //message_tv = (TextView) view.findViewById(R.id.;
        }
    }

    class LeftViewHolder extends RecyclerView.ViewHolder {

        TextView message_tv;

        public LeftViewHolder(View view) {
            super(view);
            //message_tv = (TextView) view.findViewById(R.id.message_tv);
        }
    }

    public ChatAdapter(List<Message> messages, Activity ctx_) {
        this.messages = messages;
        this.ctx = ctx_;
    }

    @Override
    public int getItemViewType(int position) {
        return messages.get(position).getSender();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == 0) {
            // Create view for sent message
            return new RightViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.sent_message_layout, parent, false));
        }
        else if (viewType == 1) {
            // Create view for received message
            return new LeftViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.receiver_message_layout, parent, false));
        }
        else
            return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case 0:
                RightViewHolder rightViewHolder = (RightViewHolder)holder;
                ModifyFonts.SetRegularToTextView(ctx, rightViewHolder.message_tv);
                rightViewHolder.message_tv.setText(messages.get(position).getMessage());
                break;
            case 1:
                LeftViewHolder leftViewHolder = (LeftViewHolder)holder;
                ModifyFonts.SetRegularToTextView(ctx, leftViewHolder.message_tv);
                leftViewHolder.message_tv.setText(messages.get(position).getMessage());
                break;
        }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }
}