package com.example.bit_by_bit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class chatLayoutAdaptor extends RecyclerView.Adapter<chatLayoutAdaptor.myviewholder> {
    ArrayList<com.example.whatsapp_c.chatLayoutModal> dataholder;
    Context context;
    Activity activity;
    private SharedPreferences sharedPreferences;

    public chatLayoutAdaptor(ArrayList<com.example.whatsapp_c.chatLayoutModal> dataholder, Context context, Activity activity) {
        this.dataholder = dataholder;
        this.context = context;
        this.activity = activity;
    }


    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_layout, parent, false);
        return new myviewholder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        holder.img_profile.setImageResource(dataholder.get(position).getImage());
        holder.tv_fullname.setText(dataholder.get(position).getFullname());
        holder.tv_last_message.setText(dataholder.get(position).getLast_message());
        holder.tv_last_time.setText(dataholder.get(position).getLast_time());

        holder.ll_chatLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, com.example.whatsapp_c.chatActivity.class);
                intent.putExtra("fullname",dataholder.get(position).getFullname());
                activity.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataholder.size();
    }

    public static class myviewholder extends RecyclerView.ViewHolder {

        ImageView img_profile;
        TextView tv_fullname, tv_last_message, tv_last_time;
        LinearLayout ll_chatLayout;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            img_profile = itemView.findViewById(R.id.img_profile);
            tv_fullname = itemView.findViewById(R.id.tv_fullname);
            tv_last_message = itemView.findViewById(R.id.tv_last_message);
            tv_last_time = itemView.findViewById(R.id.tv_last_time);
            ll_chatLayout = itemView.findViewById(R.id.ll_chatLayout);
        }

    }
}
