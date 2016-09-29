package test.shakespereaninsults;

import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.AdapterView;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    String one;
    String two;
    String three;


    @Override
    protected void onCreate(Bundle savedInstanceState) {




        setContentView(R.layout.activity_main);
        final Spinner spinnerOne = (Spinner)findViewById(R.id.selectOne);
        final Spinner spinnerTwo = (Spinner)findViewById(R.id.selectTwo);
        final Spinner spinnerThree = (Spinner)findViewById(R.id.selectThree);
        super.onCreate(savedInstanceState);


        spinnerOne.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                TextView insultText = (TextView)findViewById(R.id.textView);
                one = spinnerOne.getSelectedItem().toString();
                two = spinnerTwo.getSelectedItem().toString();
                three = spinnerThree.getSelectedItem().toString();
                one = one == "" ? "No" : one;
                String insult = (one+" " + two+ " "+three);
                insultText.setText("Thou " +one+" " + two+ " "+three);

            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });

        spinnerTwo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                TextView insultText = (TextView)findViewById(R.id.textView);
                one = spinnerOne.getSelectedItem().toString();
                two = spinnerTwo.getSelectedItem().toString();
                three = spinnerThree.getSelectedItem().toString();
                one = one == "" ? "No" : one;
                String insult = (one+" " + two+ " "+three);
                insultText.setText("Thou " +one+" " + two+ " "+three);
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });

        spinnerThree.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {



            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });



        final Button randomButton = (Button) findViewById(R.id.randomButton);
        randomButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String[] arrayOne = getResources().getStringArray(R.array.boxOne);
                String one = arrayOne[new Random().nextInt(arrayOne.length - 1)];
                String[] arrayTwo = getResources().getStringArray(R.array.boxOne);
                String two = arrayTwo[new Random().nextInt(arrayTwo.length - 1)];
                String[] arrayThree = getResources().getStringArray(R.array.boxOne);
                String three = arrayThree[new Random().nextInt(arrayThree.length - 1)];

                TextView insultText = (TextView)findViewById(R.id.textView);
                insultText.setText("Thou " +one+" " + two+ " "+three);

            }
        });




        final Button shareButton = (Button) findViewById(R.id.shareButton);
        shareButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {



                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(Intent.EXTRA_TEXT, "http://www.google.com");
                startActivity(Intent.createChooser(sharingIntent, "Share via"));

            }
        });

    }

    public void setText(){

    }

}
