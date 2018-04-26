package com.daydev.projectqr;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    public EditText txt_email, txt_password;
    public FirebaseAuth auth;
    public ProgressBar progressBar;
    public Button login_action, register_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt_email = (EditText) findViewById(R.id.txt_email);
        txt_password = (EditText) findViewById(R.id.txt_password);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        login_action = (Button) findViewById(R.id.login_action);
        register_button = (Button) findViewById(R.id.register_button);

        auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            Toast.makeText(getApplicationContext(), "OK, you already logged in!", Toast.LENGTH_SHORT).show();
            //Intent for check Session
            //finish();
            Intent changeToScan = new Intent(MainActivity.this,ScanActivity.class);
            startActivity(changeToScan);

        }

        login_action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = txt_email.getText().toString();
                final String password = txt_password.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                //authenticate user
                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (!task.isSuccessful()) {
                                    if (password.length() < 6) {
                                        txt_password.setError(getString(R.string.minimum_password));
                                    } else {
                                        Toast.makeText(MainActivity.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    Toast.makeText(getApplicationContext(), "OK, you already logged in!", Toast.LENGTH_SHORT).show();
                                    //finish();
                                    Intent changeToScan = new Intent(MainActivity.this,ScanActivity.class);
                                    startActivity(changeToScan);
                                }
                            }
                        });
            }
        });

        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Todo ไปทำหน้าสมัครสมาชิกเองนะจ๊ะ
                Toast.makeText(getApplicationContext(), "ไปทำหน้าสมัครสมาชิกเองนะ! โอเค๊", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
