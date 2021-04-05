package cristiados.triviaquiz;

import android.app.ActionBar;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.util.Pair;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //private DashboardView1 mDashboardView1;
    private DashboardView2 mDashboardView2;
    private TableLayout t1;
    //private DashboardView3 mDashboardView3;
    //private DashboardView4 mDashboardView4;
    UserInfoHelper uif;

    private boolean isAnimFinished = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        toolbar.setTitle("Inicio > Mi Progreso");
        uif = new UserInfoHelper(this);
        toolbar.setTitleTextAppearance(this, R.style.RobotoBoldTextAppearance);

        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, HomeScreen.class);
                startActivity(intent);
                finish();
            }
        });

        mDashboardView2 = (DashboardView2) findViewById(R.id.dashboard_view_2);

        mDashboardView2.setOnClickListener(this);
        List<Pair<Integer,String> > scores = uif.getAllScores();


        TableLayout tl = (TableLayout) findViewById(R.id.table);
        Typeface tb;
        tb = Typeface.createFromAsset(getAssets(), "fonts/TitilliumWeb-Bold.ttf");

        TextView textOrigin = (TextView)findViewById(R.id.simpleTextView);
        TextView textColum2 = (TextView)findViewById(R.id.simpleTextView2);
        Pair<Integer,String>  aa =scores.remove(0);
        mDashboardView2.setCreditValue(aa.first);
        mDashboardView2.setBest_date(aa.second);
        mDashboardView2.setTypeFont(tb);

        textOrigin.setTypeface(tb);
        textColum2.setTypeface(tb);
        for(Pair<Integer,String> ps : scores)
        {
            TableRow row = new TableRow(this);
            TextView tv  = new TextView(this);
            TextView tv2 = new TextView(this);

            tv.setText(""+ps.first);
            tv2.setText(ps.second);
            tv.setLayoutParams(textOrigin.getLayoutParams());
            tv2.setLayoutParams(textColum2.getLayoutParams());
            tv.setPadding(textOrigin.getPaddingLeft(),textOrigin.getPaddingTop(),textOrigin.getPaddingRight(),textOrigin.getPaddingBottom());
            tv2.setPadding(textColum2.getPaddingLeft(),textColum2.getPaddingTop(),textColum2.getPaddingRight(),textColum2.getPaddingBottom());
            tv.setCompoundDrawablePadding(textOrigin.getCompoundDrawablePadding());
            tv2.setCompoundDrawablePadding(textColum2.getCompoundDrawablePadding());
            tv.setBackground(textOrigin.getBackground());
            tv2.setBackground(textColum2.getBackground());
            tv.setTypeface(tb);
            tv2.setTypeface(tb);
            tv.setTextColor(textOrigin.getTextColors());
            tv2.setTextColor(textColum2.getTextColors());
            tv.setTextSize(textOrigin.getTextSize());
            tv2.setTextSize(textColum2.getTextSize());
            row.addView(tv);
            row.addView(tv2);
            tl.addView(row);
        }

        //mDashboardView2.setCreditValueWithAnim(new Random().nextInt(1000) );
    }
    public void onClick(View v) {
    }
    /*
    @Override
    public void onClick(View v) {

        mDashboardView2.setCreditValueWithAnim(new Random().nextInt(1000));

    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    void shareImageWithText(){
        Uri contentUri = Uri.parse("android.resource://" + getPackageName() + "/drawable/" + "ic_launcher");

        StringBuilder msg = new StringBuilder();
        msg.append("Hola, te invito a descargar esta app para poner a prueba tu conocimiento!");
        msg.append("\n");
        msg.append("https://play.google.com/store/apps/details?id=Your_Package_Name"); //example :com.package.name

        if (contentUri != null) {
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); // temp permission for receiving app to read this file
            shareIntent.setType("*/*");
            shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, msg.toString());
            shareIntent.putExtra(Intent.EXTRA_STREAM, contentUri);
            try {
                startActivity(Intent.createChooser(shareIntent, "Share via"));
            } catch (ActivityNotFoundException e) {
                Toast.makeText(getApplicationContext(), "No App Available", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void onBackPressed() {
        finish();
    }

}
