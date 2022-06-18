package com.example.bit_by_bit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class chatListFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<com.example.whatsapp_c.chatLayoutModal> dataholder;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_chat_list, container, false);
        recyclerView = view.findViewById(R.id.rev_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        dataholder = new ArrayList<>();

        com.example.whatsapp_c.chatLayoutModal chatLayoutModal = new com.example.whatsapp_c.chatLayoutModal(R.drawable.ic_baseline_person_24,"Aman Dhakar", "Hello","12:00");
        dataholder.add(chatLayoutModal);

        com.example.whatsapp_c.chatLayoutModal chatLayoutModal1 = new com.example.whatsapp_c.chatLayoutModal(R.drawable.ic_baseline_person_24,"Aman Dhakar", "Hello","12:00");
        dataholder.add(chatLayoutModal1);

        com.example.whatsapp_c.chatLayoutModal chatLayoutModal2 = new com.example.whatsapp_c.chatLayoutModal(R.drawable.ic_baseline_person_24,"Aman Dhakar", "Hello","12:00");
        dataholder.add(chatLayoutModal2);

        recyclerView.setAdapter(new chatLayoutAdaptor(dataholder,getContext(),getActivity()));

        return view;
    }
}