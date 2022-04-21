package com.example.triolingo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {
    private ImageView englishTest;

    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;
    private Button logOut;
    private int scorebuffer = 0, score = 0, level = 1;
    private TextView levelTxt, scoreTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        logOut = (Button) findViewById(R.id.signOut);
        englishTest = (ImageView) findViewById(R.id.englishTest);
        levelTxt = (TextView) findViewById(R.id.level);
        scoreTxt = (TextView) findViewById(R.id.score);

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(ProfileActivity.this,MainActivity.class));
            }
        });
        englishTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this,EnglishTestsActivity.class));
            }
        });

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();
        UpdateStats();
        final TextView textViewWelcomeClient = (TextView) findViewById(R.id.userName);

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if (userProfile != null) {

                    String fullName = userProfile.name;

                    String[] editFullName = fullName.split(" ");
                    String firstName = editFullName[0];

                    textViewWelcomeClient.setText(firstName);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ProfileActivity.this, "Something went wrong!", Toast.LENGTH_LONG).show();
            }
        });
    }
    public void UpdateStats(){
        try {
            Intent intent = getIntent();
            scorebuffer = intent.getIntExtra("score", 0);
        } catch (Exception e) {
            System.out.println("Extras empty..."); }

        score += scorebuffer;
        scoreTxt.setText("Your score: " + String.valueOf(score));
        levelTxt.setText("Your level: " + String.valueOf(1 + (score/100)));
    }
}