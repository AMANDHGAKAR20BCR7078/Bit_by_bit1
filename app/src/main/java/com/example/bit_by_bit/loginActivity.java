package com.example.bit_by_bit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.whatsapp_c.tabLayoutActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;
import java.util.concurrent.TimeUnit;


public class loginActivity extends AppCompatActivity {

    TextInputLayout til_phoneno, til_otp, til_profilename, til_about;
    TextInputEditText tiet_otp;
    Button btn_signup, btn_logout, btn_verify, btn_save;
    LinearLayout ll_logindata, ll_profiledata;
//    TextView tv_error;

    private String phone, otp;
    private String verificationCodeBySystem;
    private PrograceBar prograce_bar;
    SharedPreferences sharedPreferences;
    String uid;

    public static final String SHARED_PREF_ALL_DATA = "All data";
    public static String ISLOGIN;

    @Override
    protected void onStart() {
        super.onStart();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        sharedPreferences = getSharedPreferences(SHARED_PREF_ALL_DATA, MODE_PRIVATE);

        //Hooks
        til_phoneno = findViewById(R.id.til_phoneno);
        til_otp = findViewById(R.id.til_otp);
        btn_signup = findViewById(R.id.btn_sign);
        btn_logout = findViewById(R.id.btn_logout);
        btn_verify = findViewById(R.id.btn_otp_verify);
        ll_logindata = findViewById(R.id.ll_login_data);
        ll_profiledata = findViewById(R.id.ll_profile_data);
        til_profilename = findViewById(R.id.til_profile_name);
        til_about = findViewById(R.id.til_about);
        btn_save = findViewById(R.id.btn_save);

        String islogin = sharedPreferences.getString(ISLOGIN, null);
        System.out.println(islogin);
        if (islogin.equals("true")) {
            Intent intent = new Intent(loginActivity.this, tabLayoutActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }


        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressbar();
                phone = til_phoneno.getEditText().getText().toString();
                if (!phone.equals("")) {
                    til_otp.setVisibility(View.VISIBLE);
                    btn_verify.setVisibility(View.VISIBLE);
                    til_phoneno.setEnabled(false);
                    sendVerificationCode(phone);
                    prograce_bar.dismissbar();
                }
            }
        });
        btn_verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressbar();
                otp = til_otp.getEditText().getText().toString();
                if (!otp.equals("")) {
                    verifyCode(otp);
                    btn_signup.setVisibility(View.INVISIBLE);
                    btn_logout.setVisibility(View.VISIBLE);
                    prograce_bar.dismissbar();
                }
            }
        });
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String profile_name = til_profilename.getEditText().getText().toString();
                String about = til_about.getEditText().getText().toString();
                System.out.println("UID: " + uid);
                createEntryonDatabse(profile_name,about,uid);

//                Intent intent = new Intent(loginActivity.this, tabLayoutActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                startActivity(intent);
            }
        });

    }

    private void sendVerificationCode(String phone) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91" + phone,
                60,
                TimeUnit.SECONDS,
                this,
                mCallbacks);
    }

    private final PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            System.out.println("Check1: onCodeSent");
            verificationCodeBySystem = s;
        }

        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
            String code = phoneAuthCredential.getSmsCode();
            System.out.println("Check2: onVerificationCompleted");
            if (code != null) {
                progressbar();
                tiet_otp.setText(code);
                verifyCode(code);
            }
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            System.out.println("Check3: onVerificationFailed");
            System.out.println(e.getMessage());
            Toast.makeText(loginActivity.this, "Failed!! /n" + e.getMessage(), Toast.LENGTH_SHORT).show();
//            tv_error.setVisibility(View.VISIBLE);
//            tv_error.setText(e.getMessage());
        }
    };

    private void verifyCode(String codebyUser) {
        System.out.println("Check4: verifyCode");
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationCodeBySystem, codebyUser);
        signInTheUserByCredsntial(credential);
    }

    private void signInTheUserByCredsntial(PhoneAuthCredential credential) {

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(loginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            System.out.println("Check5: onComplete");
                            //tv_error.setText("completed!! ");
                            uid = firebaseAuth.getUid();
                            String display_name = firebaseAuth.getCurrentUser().getDisplayName();
                            String email = firebaseAuth.getCurrentUser().getEmail();
                            System.out.println("UID: " + uid + "NAME: " + display_name + "EMAIL: " + email);

                            SharedPreferences.Editor editor1 = sharedPreferences.edit();
                            editor1.putString(ISLOGIN, "true");
                            editor1.apply();
                            ll_logindata.setVisibility(View.GONE);
//                            ll_profiledata.setVisibility(View.VISIABLE);


                            Toast.makeText(loginActivity.this, "Completed", Toast.LENGTH_SHORT).show();
                        } else {
                            System.out.println("Check5: onFailed");
                            Toast.makeText(loginActivity.this, Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    public void createEntryonDatabse(String profile_name, String about, String uid) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference("user");

        userModalClass userModalClass = new userModalClass(profile_name,about,uid);


        myRef.child(uid).setValue(userModalClass);
    }

    public void progressbar() {
        prograce_bar = new PrograceBar(loginActivity.this);
        prograce_bar.showDialog();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        }, 500);
    }


}