package com.example.triolingo;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class TestsActivity extends AppCompatActivity {

    private RadioButton option1, option2, option3, option4;
    private Button buttonNext;
    private TextView question, questionNumber;
    private RadioGroup radioGroup;
    public static String[] UserAnswers = new String[10];
    public static int[] usedQuestions = new int[10];
    private int activeQuestion = 0, score = 0, questionCounter = 0;
    private boolean resultShown = false;
    private LinkedList<RadioButton> options = new LinkedList<RadioButton>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tests);

        question = (TextView) findViewById(R.id.question);
        questionNumber = (TextView) findViewById(R.id.questionNuber);
        option1 = (RadioButton) findViewById(R.id.option1);
        option2 = (RadioButton) findViewById(R.id.option2);
        option3 = (RadioButton) findViewById(R.id.option3);
        option4 = (RadioButton) findViewById(R.id.option4);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        buttonNext = (Button) findViewById(R.id.buttonNext);
        options.add(option1);
        options.add(option2);
        options.add(option3);
        options.add(option4);

        ShuffleQuestions();
        loadQuestion();
    }
    public void ShuffleQuestions(){
        Random rd = new Random();
        int loadedQuestionCounter = 0;
        Arrays.fill(usedQuestions, -5);
        boolean foundDuplicate = false;
        boolean loadedFirst = false;
        while(loadedQuestionCounter < 10) {
            int a = rd.nextInt(TestsMenuEng.questions.size() + 1) - 1;
            System.out.println("Generated "+ a);
            for (int i = 0; i < 10; i++) {
                if (usedQuestions[i] == a) {
                    foundDuplicate = true;
                }
            }
            if (!foundDuplicate) {
                if(a == -1 && !loadedFirst || a == 0 && !loadedFirst){
                    a = 0;
                    loadedFirst = true;
                }
                if (a != -1) {
                    usedQuestions[loadedQuestionCounter] = a;
                    System.out.println("Used Question" + loadedQuestionCounter + "- " + usedQuestions[loadedQuestionCounter]);
                    loadedQuestionCounter++;
                }
            } else {
                foundDuplicate = false;
            }
        }
    }

    public void onComplete(){
        for (int i = 0; i < usedQuestions.length;i++){
            System.out.print("\nUser answer:" + i + " - " + UserAnswers[i]);
            if (Objects.equals(TestsMenuEng.correctAnswers.get(usedQuestions[i]), UserAnswers[i])){
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
        if (!resultShown) {
            if (!option1.isChecked() && !option2.isChecked() && !option3.isChecked() && !option4.isChecked()) {
                    Toast.makeText(TestsActivity.this,"Nevybrali ste ??iadnu z mo??nost??!",Toast.LENGTH_LONG).show();
                return;
            }
            if (UserAnswers[activeQuestion].equals(TestsMenuEng.correctAnswers.get(usedQuestions[activeQuestion]))){
                for (int i = 0; i < options.size(); i++) {
                    if (options.get(i).getText().toString().equals(UserAnswers[activeQuestion])){
                        options.get(i).setTextColor(Color.GREEN);
                    }
                }
            }else {
                for (int i = 0; i < options.size(); i++) {
                    if (options.get(i).getText().toString().equals(UserAnswers[activeQuestion])){
                        options.get(i).setTextColor(Color.RED);
                    }else if(options.get(i).getText().toString().equals(TestsMenuEng.correctAnswers.get(usedQuestions[activeQuestion]))){
                        options.get(i).setTextColor(Color.GREEN);
                    }
                }
            }
            resultShown = true;
        }else {
            for (int i = 0; i < options.size(); i++) {
                options.get(i).setTextColor(Color.BLACK);
            }
            radioGroup.clearCheck();
            if (activeQuestion == 9) {
                //onComplete();
                Intent i = new Intent(this, TestResult.class);
                // i.putExtra("score", score);
                startActivity(i);
                finish();
            } else {
                activeQuestion++;
                loadQuestion();
                if (activeQuestion == 9) {
                    buttonNext.setText("VYHODNOTI??");
                } else {
                    buttonNext.setText("??ALEJ");
                }
                resultShown = false;
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
        questionCounter++;
        String[] a = new String[]{TestsMenuEng.definedAnswers1.get(usedQuestions[activeQuestion]), TestsMenuEng.definedAnswers2.get(usedQuestions[activeQuestion]), TestsMenuEng.definedAnswers3.get(usedQuestions[activeQuestion]), TestsMenuEng.definedAnswers4.get(usedQuestions[activeQuestion])};
        shuffleArray(a);
        question.setText(TestsMenuEng.questions.get(usedQuestions[activeQuestion]));
        questionNumber.setText("OT??ZKA ??. "+ questionCounter);
        option1.setText(a[0]);
        option2.setText(a[1]);
        option3.setText(a[2]);
        option4.setText(a[3]);
    }
    static void shuffleArray(String[] ar)
    {
        // If running on Java 6 or older, use `new Random()` on RHS here
        Random rnd = ThreadLocalRandom.current();
        for (int i = ar.length - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            String a = ar[index];
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