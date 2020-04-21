package com.example.halper.towersofhanoi;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static int sourceTowerIndex;
    private static int destTowerIndex;
    private static TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // get a handle on the TextView for displaying the moves

        tv = (TextView) findViewById(R.id.text_main);

        // by default: source tower is A, and dest tower is C

        sourceTowerIndex = 0;
        destTowerIndex = 2;

    }

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

    /////////////////////////////////////////////////////////////////////////////////////

    public void onRadioSourceClicked(View view)
    {
        //
        // This method responds to a radio button click to
        // set the source tower
        //

        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_source1:
                if (checked)
                    sourceTowerIndex = 0;
                break;

            case R.id.radio_source2:
                if (checked)
                    sourceTowerIndex = 1;
                break;

            case R.id.radio_source3:
                if (checked)
                    sourceTowerIndex = 2;
                break;
        }

    } // end onRadioSourceClicked

/////////////////////////////////////////////////////////////////////////////////////

    public void onRadioDestClicked(View view)
    {
        //
        // This method responds to a radio button click to
        // set the destination tower
        //

        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_dest1:
                if (checked)
                    destTowerIndex = 0;
                break;

            case R.id.radio_dest2:
                if (checked)
                    destTowerIndex = 1;
                break;

            case R.id.radio_dest3:
                if (checked)
                    destTowerIndex = 2;
                break;
        }
    } // end onRadioDestClicked

/////////////////////////////////////////////////////////////////////////////////////

    public void showMoves(View view) {
        // This method responds to the button press and displays the
        // required moves for the requested Towers of Hanoi puzzle.
        // It calls on the recursive method "towers" to display the
        // actual moves

        EditText editText;
        int num_rings;
        String source;
        String spare;
        String dest;
        int spareTowerIndex;

        // get the names of the towers from the resources
        String tower[] = getResources().getStringArray(R.array.tower);

        // make sure the source and dest towers are different
        if (sourceTowerIndex == destTowerIndex) {
            tv.setText("The source and destination towers must be different.");
        }
        else {
            // determine the spare tower index
            spareTowerIndex = 3 - (sourceTowerIndex + destTowerIndex);

            // get the number of rings.  If bad number, then set value to 1 by default
            editText = (EditText) findViewById(R.id.edit_rings);

            try {
                num_rings = Integer.parseInt(editText.getText().toString());
            }
            catch (NumberFormatException e) {
                num_rings = 1;
            }

            // get the tower names
            source = tower[sourceTowerIndex];
            dest = tower[destTowerIndex];
            spare = tower[spareTowerIndex];

            tv.setText("");

            towers(num_rings, source, dest, spare);

            tv.append("\nNumber of rings: " + num_rings + "\n");
            tv.append("Source Tower: " + source + "\n");
            tv.append("Destination Tower: " + dest + "\n");
            tv.append("Spare Tower: " + spare + "\n");

        }

    } // end showMoves

/////////////////////////////////////////////////////////////////////////////////////

    public static void towers(int num_rings, String source, String dest, String spare) {
        if (num_rings == 1) {
            tv.append("Move ring from Tower " + source + " to Tower " + dest + ".\n");
        }
        else {
            towers(num_rings - 1, source, spare, dest);
            tv.append("Move ring from Tower " + source + " to Tower " + dest + ".\n");
            towers(num_rings - 1, spare, dest, source);
        }
    }  // end towers

} // end MainActivity

