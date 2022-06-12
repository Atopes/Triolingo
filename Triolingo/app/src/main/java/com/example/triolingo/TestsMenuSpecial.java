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

public class TestsMenuSpecial extends AppCompatActivity {

    public static final String[] testNamesSpecial = new String[]{"Nárečia 1","Nárečia 2","Nárečia 3","Nárečia 4","Nárečia 5","Nárečia 6", "Nárečia 7" };
    private Button test0,test1,test2,test3,test4,test5,test6;
    private int internalCounter = 0, scoreToNextTest = 30, id;
    public static int testID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tests_menu_special);

        test0 = (Button) findViewById(R.id.test0special);
        test1 = (Button) findViewById(R.id.test1special);
        test2 = (Button) findViewById(R.id.test2special);
        test3 = (Button) findViewById(R.id.test3special);
        test4 = (Button) findViewById(R.id.test4special);
        test5 = (Button) findViewById(R.id.test5special);
        test6 = (Button) findViewById(R.id.test6special);

        test0.setText(testNamesSpecial[0]);
        lockTest(test0, 0);
        test1.setText(testNamesSpecial[1]);
        lockTest(test1, 1);
        test2.setText(testNamesSpecial[2]);
        lockTest(test2, 2);
        test3.setText(testNamesSpecial[3]);
        lockTest(test3, 3);
        test4.setText(testNamesSpecial[4]);
        lockTest(test4, 4);
        test5.setText(testNamesSpecial[5]);
        lockTest(test5, 5);
        test6.setText(testNamesSpecial[6]);
        lockTest(test6, 6);
    }

    public void lockTest(Button b, int idTestu) {
        if (ProfileActivity.score < scoreToNextTest * idTestu){
            b.getBackground().setAlpha(64);
        }
    }

    public void startTestSpecial(View view) {
        String nazov = ((Button) view).getText().toString();
        for (int i = 0; i < testNamesSpecial.length; i++){
            if (nazov.equals(testNamesSpecial[i])){
                id = i;
                testID = i;
                break;
            }
        }
        if (ProfileActivity.score >= scoreToNextTest * id){
            loadTest(id);
            startActivity(new Intent(com.example.triolingo.TestsMenuSpecial.this, TestsActivity.class));
        }else{
            Toast.makeText(getApplicationContext(), "For this test you need at least " + scoreToNextTest * id +" score.", Toast.LENGTH_SHORT).show();
        }
    }

    public void back(View view) {
        startActivity(new Intent(com.example.triolingo.TestsMenuSpecial.this, ProfileActivity.class));
    }

    public void loadTest(int id){
        TestsMenuEng.correctAnswers.clear();
        TestsMenuEng.questions.clear();
        TestsMenuEng.definedAnswers1.clear();
        TestsMenuEng.definedAnswers2.clear();
        TestsMenuEng.definedAnswers3.clear();
        TestsMenuEng.definedAnswers4.clear();
        internalCounter = 0;
        String filename = ""+ id +"N";
        BufferedReader reader;
        try{
            final InputStream file = getAssets().open(filename);
            reader = new BufferedReader(new InputStreamReader(file));
            String strLine = reader.readLine();
            while(strLine != null){
                if(internalCounter == 0) {
                    TestsMenuEng.questions.add(strLine.trim());
                    internalCounter++;
                }else if(internalCounter == 5){
                    TestsMenuEng.correctAnswers.add(strLine.trim());
                    internalCounter = 0;
                }else if(internalCounter == 1) {
                    TestsMenuEng.definedAnswers1.add(strLine.trim());
                    internalCounter++;
                }else if(internalCounter == 2) {
                    TestsMenuEng.definedAnswers2.add(strLine.trim());
                    internalCounter++;
                }else if(internalCounter == 3) {
                    TestsMenuEng.definedAnswers3.add(strLine.trim());
                    internalCounter++;
                }else{
                    TestsMenuEng.definedAnswers4.add(strLine.trim());
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