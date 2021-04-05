package cristiados.triviaquiz;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.List;

import cristiados.triviaquiz.data.model.MySpinnerAdapter;


public class SelectNum extends AppCompatActivity {

    Button play;
    Spinner num_questions;
    List<String> numbers =Arrays.asList("5","10", "20","100", "Infinito");
    Typeface tb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_num);
        //Initialize
        play = (Button) findViewById(R.id.playButton);
        num_questions = (Spinner) findViewById(R.id.spinner);
        TextView textv = (TextView) findViewById(R.id.text_num_question);
        tb = Typeface.createFromAsset(getAssets(), "fonts/TitilliumWeb-Bold.ttf");
        textv.setTypeface(tb);
        //play again button onclick listener
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectNum.this, MainGameActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // Create the instance of ArrayAdapter
        // having the list of courses
        MySpinnerAdapter adapter = new MySpinnerAdapter(
                this,
                android.R.layout.simple_spinner_item,
                numbers);
        //ArrayAdapter ad= new ArrayAdapter(this,android.R.layout.simple_spinner_item,numbers);

        // set simple layout resource file
        // for each item of spinner
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        num_questions.setAdapter(adapter);

        num_questions.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0,
                                       View arg1,
                                       int position,
                                       long id)
            {
                int q = 0;
                switch (position){
                    case 0: MainGameActivity.correct2Won = 5;
                        break;
                    case 1: MainGameActivity.correct2Won = 10;
                        break;
                    case 2:  MainGameActivity.correct2Won  = 20;
                        break;
                    case 3:  MainGameActivity.correct2Won  = 100;
                        break;
                    case 4:  MainGameActivity.correct2Won  = 1000000;
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0)
            {
                // Auto-generated method stub
            }
        });

        //Setting typefaces for textview and button - this will give stylish fonts on textview and button
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/shablagooital.ttf");
        play.setTypeface(typeface);

    }
    public void onBackPressed() {
        finish();
    }
}