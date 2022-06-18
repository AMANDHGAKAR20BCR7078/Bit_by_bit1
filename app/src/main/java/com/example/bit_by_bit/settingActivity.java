package com.example.bit_by_bit;

import static com.example.whatsapp_c.login.loginActivity.SHARED_PREF_ALL_DATA;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class settingActivity extends AppCompatActivity {

    private Button btn_logout;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        sharedPreferences = getSharedPreferences(SHARED_PREF_ALL_DATA, MODE_PRIVATE);

        btn_logout = findViewById(R.id.btn_logout);

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor1 = sharedPreferences.edit();
                editor1.putString(loginActivity.ISLOGIN, "false");
                editor1.apply();
                System.out.println(sharedPreferences.getString(loginActivity.ISLOGIN,null));
                startActivity(new Intent(settingActivity.this,loginActivity.class));
                finish();
            }
        });
    }
}