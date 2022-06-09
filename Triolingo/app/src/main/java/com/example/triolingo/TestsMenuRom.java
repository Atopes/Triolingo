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
import java.util.LinkedList;

public class TestsMenuRom extends AppCompatActivity {

    public static final String[] testNamesRom=new String[]{"Frázy","Zámená","Číslovky","Prídavné mená","Rodina","Jedlo","Časti tela","Zvieratá","Povolania","Domov","Zdravie" };
    private int internalCounter=0,scoreToNextTest=30,id;
    public static int testID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tests_menu_rom);
    }

    public void lockTest(Button b, int idTestu) {
        if (ProfileActivity.score < scoreToNextTest * idTestu){
            b.getBackground().setAlpha(64);
        }
    }

    public void startTest0Rom(View view) {
        String nazov= ((Button) view).getText().toString();
        for (int i=0;i<testNamesRom.length;i++){
            if (nazov.equals(testNamesRom[i])){
                id=i;
                testID=i;
                break;
            }
        }
        if (ProfileActivity.score >=scoreToNextTest*id){
            loadTest(id);
            startActivity(new Intent(TestsMenuRom.this, EnglishTestsActivity.class));
        }else{
            Toast.makeText(getApplicationContext(), "For this test you need at least " +scoreToNextTest*id +" score.", Toast.LENGTH_SHORT).show();
        }
    }

    public void back(View view) {
        startActivity(new Intent(TestsMenuRom.this, ProfileActivity.class));
    }

    public void loadTest(int id){
        TestsMenu.correctAnswers.clear();
        TestsMenu.questions.clear();
        TestsMenu.definedAnswers1.clear();
        TestsMenu.definedAnswers2.clear();
        TestsMenu.definedAnswers3.clear();
        TestsMenu.definedAnswers4.clear();
        internalCounter=0;
        String filename = ""+id+"R";
        BufferedReader reader;
        try{
            final InputStream file = getAssets().open(filename);
            reader = new BufferedReader(new InputStreamReader(file));
            String strLine = reader.readLine();
            while(strLine != null){
                 if(internalCounter==0) {
                     TestsMenu.questions.add(strLine.trim());
                    internalCounter++;
                }else if(internalCounter==5){
                     TestsMenu.correctAnswers.add(strLine.trim());
                    internalCounter=0;
                }else if(internalCounter==1) {
                     TestsMenu.definedAnswers1.add(strLine.trim());
                    internalCounter++;
                }else if(internalCounter==2) {
                     TestsMenu.definedAnswers2.add(strLine.trim());
                    internalCounter++;
                }else if(internalCounter==3) {
                     TestsMenu.definedAnswers3.add(strLine.trim());
                    internalCounter++;
                }else{
                     TestsMenu.definedAnswers4.add(strLine.trim());
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