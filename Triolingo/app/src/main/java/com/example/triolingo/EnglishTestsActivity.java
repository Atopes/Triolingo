package com.example.triolingo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

public class EnglishTestsActivity extends AppCompatActivity {

    private RadioButton option1,option2,option3,option4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_english_tests);

        option1 = (RadioButton) findViewById(R.id.option1);
        option2 = (RadioButton) findViewById(R.id.option2);
        option3 = (RadioButton) findViewById(R.id.option3);
        option4 = (RadioButton) findViewById(R.id.option4);




        }
    public void onRadioButtonClicked(View view ){
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()){
            case R.id.option1:
                if (checked)
                    break;
            case R.id.option2:
                if (checked)
                    break;
            case R.id.option3:
                if (checked)
                    break;
            case R.id.option4:
                if (checked)
                    break;
        }

}}