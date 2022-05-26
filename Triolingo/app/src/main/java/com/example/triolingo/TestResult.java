package com.example.triolingo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class TestResult extends AppCompatActivity {
    private TextView correctAnswer1,correctAnswer2,correctAnswer3,correctAnswer4,correctAnswer5,correctAnswer6,correctAnswer7,correctAnswer8,correctAnswer9,correctAnswer10;
    private TextView userAnswer1,userAnswer2,userAnswer3,userAnswer4,userAnswer5,userAnswer6,userAnswer7,userAnswer8,userAnswer9,userAnswer10;
    private TextView result1,result2,result3,result4,result5,result6,result7,result8,result9,result10;
    private LinkedList<TextView> results;
    private TextView[] res=new TextView[10];
    private TextView testName,score,performance;
    private int scoreInt=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_result);

        correctAnswer1=(TextView) findViewById(R.id.correctAnswer);
        correctAnswer2=(TextView) findViewById(R.id.correctAnswer1);
        correctAnswer3=(TextView) findViewById(R.id.correctAnswer2);
        correctAnswer4=(TextView) findViewById(R.id.correctAnswer3);
        correctAnswer5=(TextView) findViewById(R.id.correctAnswer4);
        correctAnswer6=(TextView) findViewById(R.id.correctAnswer5);
        correctAnswer7=(TextView) findViewById(R.id.correctAnswer6);
        correctAnswer8=(TextView) findViewById(R.id.correctAnswer7);
        correctAnswer9=(TextView) findViewById(R.id.correctAnswer8);
        correctAnswer10=(TextView) findViewById(R.id.correctAnswer9);

        userAnswer1=(TextView) findViewById(R.id.yourAnswer);
        userAnswer2=(TextView) findViewById(R.id.yourAnswer1);
        userAnswer3=(TextView) findViewById(R.id.yourAnswer2);
        userAnswer4=(TextView) findViewById(R.id.yourAnswer3);
        userAnswer5=(TextView) findViewById(R.id.yourAnswer4);
        userAnswer6=(TextView) findViewById(R.id.yourAnswer5);
        userAnswer7=(TextView) findViewById(R.id.yourAnswer6);
        userAnswer8=(TextView) findViewById(R.id.yourAnswer7);
        userAnswer9=(TextView) findViewById(R.id.yourAnswer8);
        userAnswer10=(TextView) findViewById(R.id.yourAnswer9);

        result1 =(TextView) findViewById(R.id.result);
        result2 =(TextView) findViewById(R.id.result1);
        result3 =(TextView) findViewById(R.id.result2);
        result4 =(TextView) findViewById(R.id.result3);
        result5 =(TextView) findViewById(R.id.result4);
        result6 =(TextView) findViewById(R.id.result5);
        result7 =(TextView) findViewById(R.id.result6);
        result8 =(TextView) findViewById(R.id.result7);
        result9 =(TextView) findViewById(R.id.result8);
        result10 =(TextView) findViewById(R.id.result9);

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

        correctAnswer1.setText(TestsMenu.correctAnswers.get(EnglishTestsActivity.usedQuestions[0]));
        correctAnswer2.setText(TestsMenu.correctAnswers.get(EnglishTestsActivity.usedQuestions[1]));
        correctAnswer3.setText(TestsMenu.correctAnswers.get(EnglishTestsActivity.usedQuestions[2]));
        correctAnswer4.setText(TestsMenu.correctAnswers.get(EnglishTestsActivity.usedQuestions[3]));
        correctAnswer5.setText(TestsMenu.correctAnswers.get(EnglishTestsActivity.usedQuestions[4]));
        correctAnswer6.setText(TestsMenu.correctAnswers.get(EnglishTestsActivity.usedQuestions[5]));
        correctAnswer7.setText(TestsMenu.correctAnswers.get(EnglishTestsActivity.usedQuestions[6]));
        correctAnswer8.setText(TestsMenu.correctAnswers.get(EnglishTestsActivity.usedQuestions[7]));
        correctAnswer9.setText(TestsMenu.correctAnswers.get(EnglishTestsActivity.usedQuestions[8]));
        correctAnswer10.setText(TestsMenu.correctAnswers.get(EnglishTestsActivity.usedQuestions[9]));

        userAnswer1.setText(EnglishTestsActivity.UserAnswers[0]);
        userAnswer2.setText(EnglishTestsActivity.UserAnswers[1]);
        userAnswer3.setText(EnglishTestsActivity.UserAnswers[2]);
        userAnswer4.setText(EnglishTestsActivity.UserAnswers[3]);
        userAnswer5.setText(EnglishTestsActivity.UserAnswers[4]);
        userAnswer6.setText(EnglishTestsActivity.UserAnswers[5]);
        userAnswer7.setText(EnglishTestsActivity.UserAnswers[6]);
        userAnswer8.setText(EnglishTestsActivity.UserAnswers[7]);
        userAnswer9.setText(EnglishTestsActivity.UserAnswers[8]);
        userAnswer10.setText(EnglishTestsActivity.UserAnswers[9]);
    }

    public void onOK(View v){
        Intent i = new Intent(this, ProfileActivity.class);
        i.putExtra("score", scoreInt);
        startActivity(i);
        finish();
    }
}