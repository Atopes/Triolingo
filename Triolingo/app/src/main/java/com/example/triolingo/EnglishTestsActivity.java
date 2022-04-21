package com.example.triolingo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import org.w3c.dom.Text;

public class EnglishTestsActivity extends AppCompatActivity {

    private RadioButton option1, option2, option3, option4;
    private Button buttonNext;
    private TextView question;
    public int[] UserAnswers = new int[10];
    public int[] CorrectAnswers = new int[10]; //pocet otazok
    private String[] questions = new String[10];
    private String[][] DefinedAnswers = new String[4][10];
    private int activeQuestion = 0, score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_english_tests);

        Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
        intent.putExtra("score", score);
        startActivity(intent);

        question = (TextView) findViewById(R.id.question);
        option1 = (RadioButton) findViewById(R.id.option1);
        option2 = (RadioButton) findViewById(R.id.option2);
        option3 = (RadioButton) findViewById(R.id.option3);
        option4 = (RadioButton) findViewById(R.id.option4);
        buttonNext = (Button) findViewById(R.id.buttonNext);

        CorrectAnswers = new int[]{2, 2, 2, 2, 2, 0, 0, 0, 0, 0};
        questions = new String[]{"","ahaha","bebebe","cecece","","","","","",""};
        DefinedAnswers = new String[][]{
                {"1d","2d","3b","4c","","","","","",""},
                {"2a","3b","4c","1a","","","","","",""},
                {"3b","4c","1a","2d","","","","","",""},
                {"4c","1a","2d","3b","","","","","",""}};
    }

    public void onComplete(){
        for (int i = 0; i < questions.length; i++){
            if (CorrectAnswers[i] == UserAnswers[i]){
                score++;
            }
            if(score == 10){
                score += 5;
            }
        }
        System.out.println(score);

        Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
        intent.putExtra("score", score);
        startActivity(intent);
    }

    public void onNext(View view){
        if(activeQuestion == 9){
            onComplete();
            Intent i = new Intent(this, ProfileActivity.class);
            i.putExtra("score",score);
            startActivity(i);

        }else {
            activeQuestion++;
            loadQuestion();
            if (activeQuestion ==8){
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
        }
    }

    public void loadQuestion(){
        question.setText(questions[activeQuestion]);
        option1.setText(DefinedAnswers[0][activeQuestion]);
        option2.setText(DefinedAnswers[1][activeQuestion]);
        option3.setText(DefinedAnswers[2][activeQuestion]);
        option4.setText(DefinedAnswers[3][activeQuestion]);
    }

    public void onRadioButtonClicked(View view){
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()){
            case R.id.option1:
                if (checked) {
                    UserAnswers[activeQuestion] = 1;
                }
                break;
            case R.id.option2:
                if (checked) {
                    UserAnswers[activeQuestion] = 2;
                }
                break;
            case R.id.option3:
                if (checked) {
                    UserAnswers[activeQuestion] = 3;
                }
                break;
            case R.id.option4:
                if (checked) {
                    UserAnswers[activeQuestion] = 4;
                }
                break;
        }
    }
}