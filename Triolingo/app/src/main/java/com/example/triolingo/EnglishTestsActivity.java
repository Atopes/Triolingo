package com.example.triolingo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class EnglishTestsActivity extends AppCompatActivity {

    private RadioButton option1, option2, option3, option4;
    private Button buttonNext;
    private TextView question;
    private RadioGroup radioGroup;
    public String[] UserAnswers = new String[10];
    private int activeQuestion = 0, score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_english_tests);

        question = (TextView) findViewById(R.id.question);
        option1 = (RadioButton) findViewById(R.id.option1);
        option2 = (RadioButton) findViewById(R.id.option2);
        option3 = (RadioButton) findViewById(R.id.option3);
        option4 = (RadioButton) findViewById(R.id.option4);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        buttonNext = (Button) findViewById(R.id.buttonNext);

        loadQuestion();
    }

    public void onComplete(){
        for (int i = 0; i < TestsMenu.questions.length; i++){
            System.out.print("\nUser answer:" + i + " - " + UserAnswers[i]);
            if (Objects.equals(TestsMenu.correctAnswers[i], UserAnswers[i])){
                score++;
                System.out.print(" => Correct!");
                if(score == 10){
                    score += 5;
                }
            }
        }
        System.out.println("\nScore: " + score);
    }

    public void onNext(View view){
        radioGroup.clearCheck();
        if(activeQuestion == 9){
            onComplete();

            Intent i = new Intent(this, ProfileActivity.class);
            i.putExtra("score", score);
            startActivity(i);
            finish();

        }else {
            activeQuestion++;
            loadQuestion();
            if (activeQuestion == 9){
                buttonNext.setText("Finish");
            }else {
                buttonNext.setText("Next");
            }
        }
    }

    public void onPrevious(View view){
        if(activeQuestion != 0) {
            activeQuestion--;
            loadQuestion();
            radioGroup.clearCheck();
        }
    }

    public void loadQuestion(){
        int[] a=new int[]{0,1,2,3};
        shuffleArray(a);
        question.setText(TestsMenu.questions[activeQuestion]);
        option1.setText(TestsMenu.definedAnswers[a[0]][activeQuestion]);
        option2.setText(TestsMenu.definedAnswers[a[1]][activeQuestion]);
        option3.setText(TestsMenu.definedAnswers[a[2]][activeQuestion]);
        option4.setText(TestsMenu.definedAnswers[a[3]][activeQuestion]);
    }
    static void shuffleArray(int[] ar)
    {
        // If running on Java 6 or older, use `new Random()` on RHS here
        Random rnd = ThreadLocalRandom.current();
        for (int i = ar.length - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            int a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }

    public void onRadioButtonClicked(View view){
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()){
            case R.id.option1:
                if (checked) {
                    UserAnswers[activeQuestion] =(String) option1.getText();
                    System.out.println(UserAnswers[activeQuestion]);
                }
                break;
            case R.id.option2:
                if (checked) {
                    UserAnswers[activeQuestion] = (String) option2.getText();
                    System.out.println(UserAnswers[activeQuestion]);
                }
                break;
            case R.id.option3:
                if (checked) {
                    UserAnswers[activeQuestion] = (String) option3.getText();
                    System.out.println(UserAnswers[activeQuestion]);
                }
                break;
            case R.id.option4:
                if (checked) {
                    UserAnswers[activeQuestion] = (String) option4.getText();
                    System.out.println(UserAnswers[activeQuestion]);
                }
                break;
        }
    }
}