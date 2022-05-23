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
    private final String[] testNames=new String[]{"Úvodný test","Frázy","Predložky","Vety","Zámená 1","Zámená 2", "Zámená 3","Časti tela","Pritomný a priebehový čas","Veľa,mnoho,veľmi" ,"Členy", "Modálne slovesá" , "Say, Tell, Speak, Talk" , "Vzťažné zámená" ,"Past tenses","Present Perfect 1", "Present Perfect 2","Used to","Present Perfect/Past","Pre-Intermediate test 1", "Pre-Intermediate test 2"};
    private Button test0,test1,test2,test3,test4,test5,test6,test7,test8,test9,test10,test11,test12,test13,test14,test15,test16,test17,test18,test19,test20;
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
        test7 = (Button) findViewById(R.id.test7);
        test8 = (Button) findViewById(R.id.test8);
        test9 = (Button) findViewById(R.id.test9);
        test10 = (Button) findViewById(R.id.test10);
        test11 = (Button) findViewById(R.id.test11);
        test12 = (Button) findViewById(R.id.test12);
        test13 = (Button) findViewById(R.id.test13);
        test14 = (Button) findViewById(R.id.test14);
        test15 = (Button) findViewById(R.id.test15);
        test16 = (Button) findViewById(R.id.test16);
        test17 = (Button) findViewById(R.id.test17);
        test18 = (Button) findViewById(R.id.test18);
        test19 = (Button) findViewById(R.id.test19);
        test20 = (Button) findViewById(R.id.test20);


        test0.setText(testNames[0]);
        test1.setText(testNames[1]);
        test2.setText(testNames[2]);
        test3.setText(testNames[3]);
        test4.setText(testNames[4]);
        test5.setText(testNames[5]);
        test6.setText(testNames[6]);
        test7.setText(testNames[7]);
        test8.setText(testNames[8]);
        test9.setText(testNames[9]);
        test10.setText(testNames[10]);
        test11.setText(testNames[11]);
        test12.setText(testNames[12]);
        test13.setText(testNames[13]);
        test14.setText(testNames[14]);
        test15.setText(testNames[15]);
        test16.setText(testNames[16]);
        test17.setText(testNames[17]);
        test18.setText(testNames[18]);
        test19.setText(testNames[19]);
        test20.setText(testNames[20]);
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