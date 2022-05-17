package com.example.triolingo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TestsMenu extends AppCompatActivity {
    public static String[] correctAnswers = new String[10];
    public static String[] questions = new String[10];
    public static String[][] definedAnswers = new String[4][10];
    private final String[] testNames=new String[]{"Úvodný test","Frázy","Predložky","Vety","Zámená 1","Zámená 2", "Zámená 3"};
    private Button test0,test1,test2,test3,test4,test5,test6;
    private int internalCounter=0,externalCounter=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tests_menu);
        test0 = (Button) findViewById(R.id.test0);
        test1 = (Button) findViewById(R.id.test1);
        test2 = (Button) findViewById(R.id.test2);
        test3 = (Button) findViewById(R.id.test3);
        test4 = (Button) findViewById(R.id.test4);
        test5 = (Button) findViewById(R.id.test5);
        test6 = (Button) findViewById(R.id.test6);

        test0.setText(testNames[0]);
        test1.setText(testNames[1]);
        test2.setText(testNames[2]);
        test3.setText(testNames[3]);
        test4.setText(testNames[4]);
        test5.setText(testNames[5]);
        test6.setText(testNames[6]);
    }

    public void startTest0(View view) {
       loadTest(0);
        startActivity(new Intent(TestsMenu.this, EnglishTestsActivity.class));
    }
    public void startTest1(View view) {
        if (ProfileActivity.score >=40*1){
            loadTest(1);
            startActivity(new Intent(TestsMenu.this, EnglishTestsActivity.class));
        }else{
            Toast.makeText(getApplicationContext(), "For this test you need at least " +40*1 +" score.", Toast.LENGTH_SHORT).show();
        }
    }
    public void startTest2(View view) {
        if (ProfileActivity.score >=40*2){
            loadTest(2);
            startActivity(new Intent(TestsMenu.this, EnglishTestsActivity.class));
        }else{
            Toast.makeText(getApplicationContext(), "For this test you need at least " +40*2 +" score.", Toast.LENGTH_SHORT).show();
        }
    }
    public void startTest3(View view) {
        if (ProfileActivity.score >=40*3){
            loadTest(3);
            startActivity(new Intent(TestsMenu.this, EnglishTestsActivity.class));
        }else{
            Toast.makeText(getApplicationContext(), "For this test you need at least " +40*3 +" score.", Toast.LENGTH_SHORT).show();
        }
    }
    public void startTest4(View view) {
        if (ProfileActivity.score >=40*4){
            loadTest(4);
            startActivity(new Intent(TestsMenu.this, EnglishTestsActivity.class));
        }else{
            Toast.makeText(getApplicationContext(), "For this test you need at least " +40*4 +" score.", Toast.LENGTH_SHORT).show();
        }
    }
    public void startTest5(View view) {
        if (ProfileActivity.score >=40*5){
            loadTest(5);
            startActivity(new Intent(TestsMenu.this, EnglishTestsActivity.class));
        }else{
            Toast.makeText(getApplicationContext(), "For this test you need at least " +40*5 +" score.", Toast.LENGTH_SHORT).show();
        }
    }
    public void startTest6(View view) {
        if (ProfileActivity.score >=40*6){
            loadTest(6);
            startActivity(new Intent(TestsMenu.this, EnglishTestsActivity.class));
        }else{
            Toast.makeText(getApplicationContext(), "For this test you need at least " +40*6 +" score.", Toast.LENGTH_SHORT).show();
        }
    }
    public void back(View view) {
        startActivity(new Intent(TestsMenu.this, ProfileActivity.class));
    }

    public void loadTest(int id){
        internalCounter=0;
        externalCounter=0;
        String filename = ""+id;
        BufferedReader reader;
        try{
            final InputStream file = getAssets().open(filename);
            reader = new BufferedReader(new InputStreamReader(file));
            String strLine = reader.readLine();
            while(strLine != null){
                 if(internalCounter==0) {
                    questions[externalCounter] = strLine.trim();
                    System.out.println(questions[externalCounter]);
                    internalCounter++;
                }else if(internalCounter==5){
                    correctAnswers[externalCounter] = strLine.trim();
                    System.out.println(correctAnswers[externalCounter]);
                    internalCounter=0;
                    externalCounter++;
                }else {
                    definedAnswers[internalCounter-1][externalCounter]=strLine.trim();
                    System.out.println(definedAnswers[internalCounter-1][externalCounter]);
                    internalCounter++;
                }
                strLine = reader.readLine();
            }
            file.close();
            reader.close();
        } catch(IOException ioe){
            ioe.printStackTrace();
        }
    }
}