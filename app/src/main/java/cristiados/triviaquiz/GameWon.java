package cristiados.triviaquiz;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import info.hoang8f.widget.FButton;

public class GameWon extends AppCompatActivity {

    FButton playAgain;
    FButton shareButton;
    FButton progressButton;
    static int correctANS;
    private TextView tx;
    private File imagePath;
    private static int count = 0;
    private SimpleDateFormat mDateFormat;
    UserInfoHelper uif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_won);

        //Initialize
        playAgain = (FButton) findViewById(R.id.playAgainButton);
        shareButton = (FButton) findViewById(R.id.sharebutton);
        tx = (TextView) findViewById(R.id.textView2);
        progressButton = (FButton) findViewById(R.id.progressbutton);
        tx.setText(correctANS+"  respuestas Correctas");
        uif = new UserInfoHelper(this);
        if (mDateFormat == null) {
            mDateFormat = new SimpleDateFormat("dd/MM/yyyy-HH:ss", Locale.US);
        }
        uif.addScore(new Pair<Integer, String>(correctANS, mDateFormat.format(Calendar.getInstance().getTime())));
        //play again button onclick listener
        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GameWon.this, SelectNum.class);
                startActivity(intent);
            }
        });
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bitmap = takeScreenshot();
                saveBitmap(bitmap);
                shareIt();
            }
        });
        progressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GameWon.this, MainActivity.class);
                startActivity(intent);
            }
        });
        //Setting typefaces for textview and button - this will give stylish fonts on textview and button
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/shablagooital.ttf");
        playAgain.setTypeface(typeface);
        shareButton.setTypeface(typeface);
        progressButton.setTypeface(typeface);
    }
    public Bitmap takeScreenshot() {
        View rootView = findViewById(android.R.id.content).getRootView();
        rootView.setDrawingCacheEnabled(true);
        return rootView.getDrawingCache();
    }

    private void saveBitmap(Bitmap bitmap) {
        imagePath = new File(Environment.getExternalStorageDirectory() + "/scrnshot"+count+".png"); ////File imagePath
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(imagePath);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            Log.e("GREC", e.getMessage(), e);
        } catch (IOException e) {
            Log.e("GREC", e.getMessage(), e);
        }
    }
    private void shareIt() {
        Uri uri = Uri.fromFile(imagePath);
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("image/*");
        String shareBody = "My highest score with screen shot";
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "My Catch score");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        sharingIntent.putExtra(Intent.EXTRA_STREAM, uri);

        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }


}