package com.example.triolingo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TestsMenuRom extends AppCompatActivity {

    public static final String[] testNamesRom = new String[]{"Frázy","Zámená","Číslovky","Prídavné mená","Rodina","Jedlo","Časti tela","Zvieratá","Povolania","Domov","Zdravie" };
    private Button test0,test1,test2,test3,test4,test5,test6,test7,test8,test9,test10;
    private int internalCounter=0,scoreToNextTest = 30,id;
    public static int testID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tests_menu_rom);

        test0 = (Button) findViewById(R.id.test0rom);
        test1 = (Button) findViewById(R.id.test1rom);
        test2 = (Button) findViewById(R.id.test2rom);
        test3 = (Button) findViewById(R.id.test3rom);
        test4 = (Button) findViewById(R.id.test4rom);
        test5 = (Button) findViewById(R.id.test5rom);
        test6 = (Button) findViewById(R.id.test6rom);
        test7 = (Button) findViewById(R.id.test7rom);
        test8 = (Button) findViewById(R.id.test8rom);
        test9 = (Button) findViewById(R.id.test9rom);
        test10 = (Button) findViewById(R.id.test10rom);

        test0.setText(testNamesRom[0]);
        lockTest(test0, 0);
        test1.setText(testNamesRom[1]);
        lockTest(test1, 1);
        test2.setText(testNamesRom[2]);
        lockTest(test2, 2);
        test3.setText(testNamesRom[3]);
        lockTest(test3, 3);
        test4.setText(testNamesRom[4]);
        lockTest(test4, 4);
        test5.setText(testNamesRom[5]);
        lockTest(test5, 5);
        test6.setText(testNamesRom[6]);
        lockTest(test6, 6);
        test7.setText(testNamesRom[7]);
        lockTest(test7, 7);
        test8.setText(testNamesRom[8]);
        lockTest(test8, 8);
        test9.setText(testNamesRom[9]);
        lockTest(test9, 9);
        test10.setText(testNamesRom[10]);
        lockTest(test10, 10);

    }

    public void lockTest(Button b, int idTestu) {
        if (ProfileActivity.score < scoreToNextTest * idTestu){
            b.getBackground().setAlpha(64);
        }
    }

    public void startTestRom(View view) {
        String nazov = ((Button) view).getText().toString();
        for (int i = 0; i < testNamesRom.length; i++){
            if (nazov.equals(testNamesRom[i])){
                id = i;
                testID = i;
                break;
            }
        }
        if (ProfileActivity.score >= scoreToNextTest * id){
            loadTest(id);
            startActivity(new Intent(TestsMenuRom.this, TestsActivity.class));
        }else{
            Toast.makeText(getApplicationContext(), "Na odomknutie tohto testu potrebujete aspoň " + scoreToNextTest * id +" skóre.", Toast.LENGTH_SHORT).show();
        }
    }

    public void back(View view) {
        startActivity(new Intent(TestsMenuRom.this, ProfileActivity.class));
    }

    public void loadTest(int id){
        TestsMenuEng.correctAnswers.clear();
        TestsMenuEng.questions.clear();
        TestsMenuEng.definedAnswers1.clear();
        TestsMenuEng.definedAnswers2.clear();
        TestsMenuEng.definedAnswers3.clear();
        TestsMenuEng.definedAnswers4.clear();
        internalCounter=0;
        String filename = ""+ id +"R";
        BufferedReader reader;
        try{
            final InputStream file = getAssets().open(filename);
            reader = new BufferedReader(new InputStreamReader(file));
            String strLine = reader.readLine();
            while(strLine != null) {
                 if(internalCounter == 0) {
                     TestsMenuEng.questions.add(strLine.trim());
                    internalCounter++;
                }else if(internalCounter == 5){
                     TestsMenuEng.correctAnswers.add(strLine.trim());
                    internalCounter=0;
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
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }
}