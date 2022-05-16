package com.example.triolingo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TestsMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tests_menu);
    }

    public void startTest(View view) {
        startActivity(new Intent(TestsMenu.this, EnglishTestsActivity.class));
    }

    public void back(View view) {
        startActivity(new Intent(TestsMenu.this, ProfileActivity.class));
    }
}