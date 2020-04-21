package edu.njit.gradesorter;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

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

    public void sorter(View view) throws java.io.IOException {
        int numArray[] = new int[200];
        TextView tv = findViewById(R.id.text_main);
        EditText et1 = findViewById(R.id.edit_infile);
        EditText et2 = findViewById(R.id.edit_outfile);
        int fromIndex = 0;
        int toIndex = 0;
        String infilename = et1.getText().toString();
        String outfilename = et2.getText().toString();
        double median;

        AssetManager assetManager = getAssets();
        Scanner fsc = new Scanner(assetManager.open(infilename));

        while (fsc.hasNext()) {
            numArray[toIndex] = fsc.nextInt();
            toIndex++;
        }

        Arrays.sort(numArray, fromIndex, toIndex);

        File outfile = new File(getExternalFilesDir(null), outfilename);
        if (outfile.exists()) {
            tv.setText("File already exists; delete it or choose another filename.");
        } else {
            int i;
            FileWriter fw = new FileWriter(outfile);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            for (i = 0; i < toIndex; i++) {
                pw.print(numArray[i] + " ");
            }
            pw.close();
            tv.setText("Sorted array has been written to specified file successfully.");
        }

        tv.append("\n\nThere are " + toIndex + " integers in the array.");

        int spread = numArray[toIndex - 1] - numArray[fromIndex];
        tv.append("\nThe spread is " + spread + ".");

        double rms_sum = 0.0;
        for (double num : numArray) {
            rms_sum += Math.pow(num, 2);
        }
        double rms = Math.sqrt(rms_sum / toIndex);
        tv.append("\nThe root mean square is " + String.format("%.4f", rms) + ".");

        if (toIndex % 2 == 0) {
            median = (numArray[toIndex / 2] + numArray[(toIndex / 2) - 1]) / 2.0;}
        else {median = numArray[(toIndex - 1) / 2];}
        tv.append("\nThe median value is " + median + ".");
    }
}