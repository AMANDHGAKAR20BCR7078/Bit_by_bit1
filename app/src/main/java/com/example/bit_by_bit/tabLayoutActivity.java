package com.example.bit_by_bit;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class tabLayoutActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout);

        tabLayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewpager);

        tabLayout.setupWithViewPager(viewPager);


        viewPagerAdaptor viewPagerAdaptor = new viewPagerAdaptor(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPagerAdaptor.addFragment(new com.example.whatsapp_c.chatListFragment(), "CAMERA");
        viewPagerAdaptor.addFragment(new com.example.whatsapp_c.chatListFragment(), "CHATS");
        viewPagerAdaptor.addFragment(new com.example.whatsapp_c.statusFragment(), "STATUS");
        viewPagerAdaptor.addFragment(new com.example.whatsapp_c.callsFragment(), "CALLS");
        viewPager.setAdapter(viewPagerAdaptor);

    }


}