package com.shivam.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    String id_str;
    String pass_str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Sign In");

        Button buttonAdmin = findViewById(R.id.adminButton);
        Button buttonLogin = findViewById(R.id.buttonLogin);
        final TextView id = findViewById(R.id.editTextRollno);
        final TextView pass = findViewById(R.id.editTextPassword);


        buttonAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String str = "Admin";
                final String password = "12345";

                id_str = id.getText().toString();
                pass_str = pass.getText().toString();

                Log.d("ADebugTag", "Id: " + id_str);
                Log.d("ADebugTag", "Password: " + pass_str);

                if (id_str.isEmpty())
                {
                    id.setError("field required");
                    id.requestFocus();
                    return;
                }

                if (pass_str.isEmpty())
                {
                    pass.setError("field required");
                    pass.requestFocus();
                    return;
                }

                if (str.equalsIgnoreCase(id_str) && password.equalsIgnoreCase(pass_str)){

                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    Log.d("ADebugTag", "Success");
                }
                else {
                    Toast.makeText(LoginActivity.this, "Incorrect Id/Password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String str = "HRG";
                final String password = "12345";

                id_str = id.getText().toString();
                pass_str = pass.getText().toString();

                Log.d("ADebugTag", "Id: " + id_str);
                Log.d("ADebugTag", "Password: " + pass_str);

                if (id_str.isEmpty())
                {
                    id.setError("field required");
                    id.requestFocus();
                    return;
                }

                if (pass_str.isEmpty())
                {
                    pass.setError("field required");
                    pass.requestFocus();
                    return;
                }

                if (str.equalsIgnoreCase(id_str) && password.equalsIgnoreCase(pass_str)){

                    Intent intent = new Intent(LoginActivity.this, UserActivity.class);
                    startActivity(intent);
                    Log.d("ADebugTag", "Success");
                }
                else {
                    Toast.makeText(LoginActivity.this, "Incorrect Id/Password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
