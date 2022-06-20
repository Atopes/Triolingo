package com.example.triolingo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.*;


import java.util.HashMap;
import java.util.Map;

public class Ladder extends AppCompatActivity {
    private String[] names = new String[10];
    private long[] scores = new long[10];

    private FirebaseFirestore databaseReferenceRanking;
    private boolean inLadder=false;
    private TextView level1,level2,level3,level4,level5,level6,level7,level8,level9,level10;
    private TextView name1,name2,name3,name4,name5,name6,name7,name8,name9,name10;
    private TextView score1,score2,score3,score4,score5,score6,score7,score8,score9,score10;
    private TextView userScore,userLevel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ladder);

        databaseReferenceRanking = FirebaseFirestore.getInstance();

        userLevel=(TextView) findViewById(R.id.LyourLevel);
        userScore=(TextView) findViewById(R.id.LyourScore);
        userLevel.setText("Level: "+((ProfileActivity.score/100)+1));
        userScore.setText("Skóre: "+(ProfileActivity.score));

        level1 = (TextView) findViewById(R.id.ladderLevel);
        level2 = (TextView) findViewById(R.id.ladderLevel2);
        level3 = (TextView) findViewById(R.id.ladderLevel3);
        level4 = (TextView) findViewById(R.id.ladderLevel4);
        level5 = (TextView) findViewById(R.id.ladderLevel5);
        level6 = (TextView) findViewById(R.id.ladderLevel6);
        level7 = (TextView) findViewById(R.id.ladderLevel7);
        level8 = (TextView) findViewById(R.id.ladderLevel8);
        level9 = (TextView) findViewById(R.id.ladderLevel9);
        level10 = (TextView) findViewById(R.id.ladderLevel10);

        name1 = (TextView) findViewById(R.id.ladderName);
        name2 = (TextView) findViewById(R.id.ladderName2);
        name3 = (TextView) findViewById(R.id.ladderName3);
        name4 = (TextView) findViewById(R.id.ladderName4);
        name5 = (TextView) findViewById(R.id.ladderName5);
        name6 = (TextView) findViewById(R.id.ladderName6);
        name7 = (TextView) findViewById(R.id.ladderName7);
        name8 = (TextView) findViewById(R.id.ladderName8);
        name9 = (TextView) findViewById(R.id.ladderName9);
        name10 = (TextView) findViewById(R.id.ladderName10);

        score1 =(TextView) findViewById(R.id.ladderScore);
        score2 =(TextView) findViewById(R.id.ladderScore2);
        score3 =(TextView) findViewById(R.id.ladderScore3);
        score4 =(TextView) findViewById(R.id.ladderScore4);
        score5 =(TextView) findViewById(R.id.ladderScore5);
        score6 =(TextView) findViewById(R.id.ladderScore6);
        score7 =(TextView) findViewById(R.id.ladderScore7);
        score8 =(TextView) findViewById(R.id.ladderScore8);
        score9 =(TextView) findViewById(R.id.ladderScore9);
        score10 =(TextView) findViewById(R.id.ladderScore10);

        getData();
    }
    public void allTheStuff(){
        if (!inLadder) {
            if (ProfileActivity.score > scores[9]) {
                scores[9]=ProfileActivity.score;
                names[9]=ProfileActivity.fullName;
            }
        }

        sortArrays(scores,names);
        showData();

        for (int i = 0; i < 10; i++) {
            saveData(i);
        }

    }
    public void showData(){
        level1.setText(String.valueOf(1+scores[9]/100));
        level2.setText(String.valueOf(1+scores[8]/100));
        level3.setText(String.valueOf(1+scores[7]/100));
        level4.setText(String.valueOf(1+scores[6]/100));
        level5.setText(String.valueOf(1+scores[5]/100));
        level6.setText(String.valueOf(1+scores[4]/100));
        level7.setText(String.valueOf(1+scores[3]/100));
        level8.setText(String.valueOf(1+scores[2]/100));
        level9.setText(String.valueOf(1+scores[1]/100));
        level10.setText(String.valueOf(1+scores[0]/100));

        name1.setText(names[9]);
        name2.setText(names[8]);
        name3.setText(names[7]);
        name4.setText(names[6]);
        name5.setText(names[5]);
        name6.setText(names[4]);
        name7.setText(names[3]);
        name8.setText(names[2]);
        name9.setText(names[1]);
        name10.setText(names[0]);

        score1.setText(String.valueOf(scores[9]));
        score2.setText(String.valueOf(scores[8]));
        score3.setText(String.valueOf(scores[7]));
        score4.setText(String.valueOf(scores[6]));
        score5.setText(String.valueOf(scores[5]));
        score6.setText(String.valueOf(scores[4]));
        score7.setText(String.valueOf(scores[3]));
        score8.setText(String.valueOf(scores[2]));
        score9.setText(String.valueOf(scores[1]));
        score10.setText(String.valueOf(scores[0]));
    }

    public void saveData(int rank){
        Map<String, Object> user = new HashMap<>();
        user.put("Name", names[9-rank]);
        user.put("Score", scores[9-rank]);
        String documentPath ="Rank"+(rank+1) ;
        databaseReferenceRanking.collection("Ranking").document(documentPath)
                .set(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        System.out.println("Pridane");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        System.out.println("Nepridane");
                    }
                });
    }
    public void getData(){

        databaseReferenceRanking.collection("Ranking")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            int rank= 0;
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                scores[rank] = (Long) document.getData().get("Score");
                                names[rank] = (String) document.getData().get("Name");
                                if (ProfileActivity.fullName.equals(names[rank])){
                                    inLadder=true;
                                    scores[rank]= ProfileActivity.score;
                                }
                                rank++;
                                if (rank==9){
                                    allTheStuff();
                                    break;
                                }
                            }
                        } else {
                            System.out.println("Didnt get data");
                        }
                    }
                });
    }

    public void sortArrays(long[] scores,String[] names){
        int small = -1;
        for (int i = 0; i <scores.length; i++) {
            small = i;
            for (int j = i ; j <= scores.length-1; j++) {
                if (scores[j] < scores[small]) {
                    small = j;
                }
            }
            long tempScore = scores[i];
            String tempName =names[i];
            scores[i] = scores[small];
            names[i]= names[small];
            scores[small] = tempScore;
            names[small] = tempName;
        }
    }

    public void onBack(View v){
        Intent i = new Intent(this, ProfileActivity.class);
        startActivity(i);
        finish();
    }
}