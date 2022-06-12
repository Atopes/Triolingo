package com.example.triolingo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Objects;
import java.util.Random;

public class TestResult extends AppCompatActivity {
    private TextView result1,result2,result3,result4,result5,result6,result7,result8,result9,result10;
    private TextView[] res=new TextView[10];
    private TextView testName,score,performance,motivationalQuote;
    private int scoreInt=0;
    private String[] quotes= new String[]{"Live as if you were to die tomorrow. Learn as if you were to live forever.","Wisdom is not a product of schooling but of the lifelong attempt to acquire it.",
            "The beautiful thing about learning is nobody can take it away from you.","You don’t learn to walk by following rules. You learn by doing, and by falling over.",
            "A man who asks is a fool for five minutes. A man who never asks is a fool for life.","A moment’s insight is sometimes worth a life’s experience.",
            "The expert in anything was once a beginner himself.","Never stop learning, because life never stops teaching.","Believe you can and you are halfway there."
            ,"A little progress each day adds up to a big results"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_result);

        result1 = (TextView) findViewById(R.id.result1);
        result2 = (TextView) findViewById(R.id.result2);
        result3 = (TextView) findViewById(R.id.result3);
        result4 = (TextView) findViewById(R.id.result4);
        result5 = (TextView) findViewById(R.id.result5);
        result6 = (TextView) findViewById(R.id.result6);
        result7 = (TextView) findViewById(R.id.result7);
        result8 = (TextView) findViewById(R.id.result8);
        result9 = (TextView) findViewById(R.id.result9);
        result10 = (TextView) findViewById(R.id.result10);

        testName = (TextView) findViewById(R.id.testName);
        score = (TextView) findViewById(R.id.testScore);
        performance =(TextView) findViewById(R.id.performance);
        motivationalQuote=(TextView) findViewById(R.id.motivation);
        Random rd = new Random();

        motivationalQuote.setText(quotes[rd.nextInt(quotes.length)]);
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
        testName.setText(TestsMenuEng.testNames[TestsMenuEng.testID]);
        for (int i = 0; i < TestsActivity.UserAnswers.length; i++){
            System.out.print("\nUser answer:" + i + " - " + TestsActivity.UserAnswers[i]);
            if (Objects.equals(TestsMenuEng.correctAnswers.get(TestsActivity.usedQuestions[i]), TestsActivity.UserAnswers[i])){
                scoreInt++;
                res[i].setTextColor(Color.GREEN);
                res[i].setText("O");
            }else{
                res[i].setTextColor(Color.RED);
                res[i].setText("X");
            }
        }
        int perf = scoreInt * 10;
        performance.setText("Úspešnosť: "+ perf +"%");

        if (scoreInt == 10){
            score.setText("Skóre:  "+ scoreInt +"/10 +5 Bonus" );
            scoreInt+=5;
        }else{
            score.setText("Skóre:  "+ scoreInt +"/10" );
        }
    }

    public void onOK(View v){
        Intent i = new Intent(this, ProfileActivity.class);
        i.putExtra("score", scoreInt);
        startActivity(i);
        finish();
    }
}