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

public class EnglishTestsActivity extends AppCompatActivity {

    private RadioButton option1, option2, option3, option4;
    private Button buttonNext;
    private TextView question;
    private RadioGroup radioGroup;
    public int[] UserAnswers = new int[10];
    public int[] CorrectAnswers = new int[10]; //pocet otazok
    private String[] questions = new String[10];
    private String[][] DefinedAnswers = new String[4][10];
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

        CorrectAnswers = new int[]{2, 1, 1, 1, 1, 1, 1, 1, 1, 4};
        questions = new String[]{"How are you?","Have a nice day!","I'm sorry, I don't understand.","The information is true.","See you later.","That’s so kind of you.","Could you repeat that, please?","Where do you work?","Let me to introduce myself.","What time is it?"};
        DefinedAnswers = new String[][]{
                {"Koľko máš rokov?","Želám ti / vám pekný deň.","Mrzí ma to, ale nerozumiem vám.","Informácia je pravdivá.","Uvidíme sa neskôr.","To je od teba / vás také milé.","Mohli by ste mi to zopakovať, prosím?","Kde pracuješ / pracujete?","Dovoľte, aby som sa predstavil.","Čo je to?"},
                {"Ako sa máš?","Mám sa dobre.","Mrzí ma to, ale niesom hladný.","Informácie je nepravdivá.","Uvidíme sa zajtra.","Som skutočne vďačný/á.","Mohol by si prosím hovoriť pomalšie?","Kde bývaš / bývate?","Moje meno je ...","Aké je počasie?"},
                {"Odkiaľ si?","Dlho sme sa nevideli.","Mrzí ma to, ale už musím ísť.","Chýba nám správna informácia.","Dlho sme sa nevideli.","Ďakujem za všetko","Čo tým chceš povedať?","Ako dlho si / ste tu?","Teší ma, že vás poznávam.","Čo je dnes za deň?"},
                {"Koľko je hodín?","Dnes mám dobrý deň","Mrzí ma to, ale nepáči sa mi to.","Nesprávna informácia.","Dlho sa neuvidíme.","Naozaj si to vážim.","Čo to znamená?","Odkiaľ si / ste?","Bolo mi veľkým potešením.","Aký je čas?"}};
    }

    public void onComplete(){
        for (int i = 0; i < questions.length; i++){
            System.out.print("\nUser answer:" + i + " - " + UserAnswers[i]);
            if (CorrectAnswers[i] == UserAnswers[i]){
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
                    System.out.println(UserAnswers[activeQuestion]);
                }
                break;
            case R.id.option2:
                if (checked) {
                    UserAnswers[activeQuestion] = 2;
                    System.out.println(UserAnswers[activeQuestion]);
                }
                break;
            case R.id.option3:
                if (checked) {
                    UserAnswers[activeQuestion] = 3;
                    System.out.println(UserAnswers[activeQuestion]);
                }
                break;
            case R.id.option4:
                if (checked) {
                    UserAnswers[activeQuestion] = 4;
                    System.out.println(UserAnswers[activeQuestion]);
                }
                break;
        }
    }
}