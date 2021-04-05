package cristiados.triviaquiz;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class PlayAgain extends AppCompatActivity {

    Button playAgain;
    TextView wrongAnsText;
    static int correctANS;
    private SimpleDateFormat mDateFormat;
    UserInfoHelper uif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_again);
        //Initialize
        playAgain = (Button) findViewById(R.id.playAgainButton);
        wrongAnsText = (TextView) findViewById(R.id.wrongAns);

        uif = new UserInfoHelper(this);
        if (mDateFormat == null) {
            mDateFormat = new SimpleDateFormat("dd/MM/yyyy-HH:ss", Locale.US);
        }
        uif.addScore(new Pair<Integer, String>(correctANS, mDateFormat.format(Calendar.getInstance().getTime())));

        //play again button onclick listener
        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PlayAgain.this, SelectNum.class);
                startActivity(intent);
                finish();
            }
        });

        //Setting typefaces for textview and button - this will give stylish fonts on textview and button
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/shablagooital.ttf");
        playAgain.setTypeface(typeface);
        wrongAnsText.setTypeface(typeface);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
