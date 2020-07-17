package iss.workshop.sa4108memorygame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ResultActivity extends AppCompatActivity {

    private Button start;
    private Button scoreboard;
    private Button submit;
    private String name;
    private int score;

    EditText nameInput;
    TextView playerScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

       /* Intent intent = getIntent();
        int time = intent.getIntExtra("time");*/

        nameInput = (EditText) findViewById(R.id.nameInput);
        playerScore = (TextView) findViewById(R.id.playerScore);

        submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitScore();
            }
        });


        scoreboard = (Button) findViewById(R.id.scoreboard);
        scoreboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openScoreboard();
            }
        });

        start = (Button) findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMainActivity();
            }
        });


    }

    public void openScoreboard() {
        Intent scoreIntent = new Intent(ResultActivity.this, ScoreActivity.class);
        scoreIntent.putExtra("name", "Team 3");
        startActivity(scoreIntent);
    }

    public void openMainActivity() {
        Intent mainIntent = new Intent(ResultActivity.this, MainActivity.class);
        startActivity(mainIntent);
    }

    public void submitScore() {

        String filePath = "ScoreBoard";
        String fileName = "ScoreBoard.txt";
        File mTargetFile = new File(this.getFilesDir(), filePath + "/" +fileName);
        String fileContent = "User name1" + "," + "time1";

        try{

            File parent = mTargetFile.getParentFile();

            if(!parent.exists() && !parent.mkdirs()) {
                throw new IllegalStateException("Couldn't create directory: " + parent);
            }
            FileOutputStream fos = new FileOutputStream(mTargetFile);
            fos.write(fileContent.getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        Intent mainIntent = new Intent(ResultActivity.this, ScoreActivity.class);

        startActivity(mainIntent);
    }

}