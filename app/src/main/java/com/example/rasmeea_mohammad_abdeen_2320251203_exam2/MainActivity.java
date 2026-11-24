package com.example.rasmeea_mohammad_abdeen_2320251203_exam2;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    TextInputEditText etEmail, etPassword;
    TextInputLayout emailLayout, passwordLayout;
    Button btnLogin;
    long lastBackPress = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        emailLayout = findViewById(R.id.emailLayout);
        passwordLayout = findViewById(R.id.passwordLayout);
        btnLogin = findViewById(R.id.btnLogin);

        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                validate();
            }
        };

        etEmail.addTextChangedListener(watcher);
        etPassword.addTextChangedListener(watcher);

        btnLogin.setOnClickListener(v -> {
            Intent intent = new Intent(this, ModeActivity.class);
            startActivity(intent);
        });

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                long now = System.currentTimeMillis();
                if (now - lastBackPress < 2000) {
                    finish();
                } else {
                    lastBackPress = now;
                    View root = findViewById(R.id.root);
                    Snackbar.make(root, "Press back again to exit", 2000).show();
                }
            }
        });
    }

    private void validate() {
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString();

        boolean emailValid = Patterns.EMAIL_ADDRESS.matcher(email).matches();
        boolean passwordValid = password.length() >= 6;

        if (!email.isEmpty() && !emailValid) {
            emailLayout.setError("Invalid email");
        } else {
            emailLayout.setError(null);
        }

        if (!password.isEmpty() && !passwordValid) {
            passwordLayout.setError("At least 6 characters");
        } else {
            passwordLayout.setError(null);
        }

        btnLogin.setEnabled(emailValid && passwordValid);
    }
}
