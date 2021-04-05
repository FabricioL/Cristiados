package cristiados.triviaquiz;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import info.hoang8f.widget.FButton;

public class HomeScreen extends AppCompatActivity {
    FButton playGame, addQuest, login, quit;
    TextView tQ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        //the below method will initialize views
        initViews();

        //PlayGame button - it will take you to the MainGameActivity
        playGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeScreen.this, SelectNum.class);
                startActivity(intent);
                finish();
            }
        });

        //Invite a friend
        addQuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //See my progress
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeScreen.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //Quit button - This will quit the game
        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initViews() {
        //initialize views here
        playGame = (FButton) findViewById(R.id.playGame);
        addQuest = (FButton) findViewById(R.id.Invite);
        login= (FButton) findViewById(R.id.login);
        quit = (FButton) findViewById(R.id.quit);
        tQ = (TextView) findViewById(R.id.tQ);

        //Typeface - this is for fonts style
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/shablagooital.ttf");
        playGame.setTypeface(typeface);
        addQuest.setTypeface(typeface);
        login.setTypeface(typeface);
        quit.setTypeface(typeface);
        tQ.setTypeface(typeface);
    }
}
