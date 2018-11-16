package com.example.marvellous.mealorderingapprestaurants;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.marvellous.mealorderingapprestaurants.Common.Common;
import com.example.marvellous.mealorderingapprestaurants.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignInActivity extends AppCompatActivity {
    EditText userPhone, userPassword;
    Button btnSignIn;
    FirebaseDatabase database;
    DatabaseReference users;
    LayoutInflater inflater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        userPhone = findViewById(R.id.User_Phone_No);
        userPassword = findViewById(R.id.User_Password);
        btnSignIn = findViewById(R.id.btn_signin);

        //Init FireBase
        database = FirebaseDatabase.getInstance();
        users = database.getReference("User");

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signInUser(userPhone.getText().toString(),userPassword.getText().toString());
            }
        });
    }

    private void signInUser(final String phone, String password) {
        final ProgressDialog progressDialog = new ProgressDialog(SignInActivity.this);
        progressDialog.setMessage("Please wait");
        progressDialog.show();
        final String localPhone = phone;
        final String localPassword = password;
        users.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(localPhone).exists())
                {
                    progressDialog.dismiss();
                    User user = dataSnapshot.child(localPhone).getValue(User.class);
                    user.setPhone(localPhone);
                    if (Boolean.parseBoolean(user.getIsStaff()))//if is staff == true
                    {
                        if (user.getPassword().equals(localPassword))
                        {
                            Intent login = new Intent(SignInActivity.this,HomeActivity.class);
                            Common.currentUser = user;
                            startActivity(login);
                            finish();
                        }
                        else
                       Toast.makeText(SignInActivity.this,"Wrong Password!",Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(SignInActivity.this,"Login with staff account!",Toast.LENGTH_SHORT).show();

                }
                else {
                    progressDialog.dismiss();

                    Toast.makeText(SignInActivity.this,"User does not exist",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
