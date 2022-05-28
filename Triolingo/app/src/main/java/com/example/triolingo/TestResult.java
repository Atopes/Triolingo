package com.example.triolingo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.Objects;

public class TestResult extends AppCompatActivity {
    private TextView result1,result2,result3,result4,result5,result6,result7,result8,result9,result10;
    private TextView[] res=new TextView[10];
    private TextView testName,score,performance;
    private int scoreInt=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_result);

        result1 =(TextView) findViewById(R.id.result1);
        result2 =(TextView) findViewById(R.id.result2);
        result3 =(TextView) findViewById(R.id.result3);
        result4 =(TextView) findViewById(R.id.result4);
        result5 =(TextView) findViewById(R.id.result5);
        result6 =(TextView) findViewById(R.id.result6);
        result7 =(TextView) findViewById(R.id.result7);
        result8 =(TextView) findViewById(R.id.result8);
        result9 =(TextView) findViewById(R.id.result9);
        result10 =(TextView) findViewById(R.id.result10);

        testName =(TextView) findViewById(R.id.testName);
        score=(TextView) findViewById(R.id.testScore);
        performance =(TextView) findViewById(R.id.performance);

        res[0] = result1;
        res[1] = result2;
        res[2] = result3;
        res[3] = result4;
        res[4] = result5;
        res[5] = result6;
        res[6] = result7;
        res[7] = result8;
        res[8] = result9;
        res[9] = result10;

        setTestResults();
    }
    public void setTestResults(){
        testName.setText(TestsMenu.testNames[TestsMenu.testID]);
        for (int i = 0; i < EnglishTestsActivity.UserAnswers.length;i++){
            System.out.print("\nUser answer:" + i + " - " + EnglishTestsActivity.UserAnswers[i]);
            if (Objects.equals(TestsMenu.correctAnswers.get(EnglishTestsActivity.usedQuestions[i]), EnglishTestsActivity.UserAnswers[i])){
                scoreInt++;
                res[i].setTextColor(Color.GREEN);
                res[i].setText("O");
            }else{
                res[i].setTextColor(Color.RED);
                res[i].setText("X");
            }
        }
        int perf = scoreInt*10;
        performance.setText("Performance: "+perf+"%");

        if (scoreInt==10){
            score.setText("Score:  "+scoreInt+"/10 +5 Bonus" );
            scoreInt+=5;
        }else{
            score.setText("Score:  "+scoreInt+"/10" );
        }
    }

    public void onOK(View v){
        Intent i = new Intent(this, ProfileActivity.class);
        i.putExtra("score", scoreInt);
        startActivity(i);
        finish();
    }
}