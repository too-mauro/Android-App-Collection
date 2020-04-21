package edu.njit.etox;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.StrictMode;
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
import java.text.Format;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
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

    public void etoxAndDisplay(View view) throws java.io.IOException {
        TextView tv = findViewById(R.id.text_main);
        double some_nums[] = new double[50];
        int n = 0;
        int o = 0;
        double[] answer;
        EditText et1 = findViewById(R.id.edit_infile);
        EditText et2 = findViewById(R.id.edit_outfile);
        String infilename = et1.getText().toString();
        String outfilename = et2.getText().toString();

        AssetManager assetManager = getAssets();
        Scanner fsc = new Scanner(assetManager.open(infilename));

        while (fsc.hasNext()) {
            some_nums[n] = fsc.nextDouble();
            n++;
        }

        answer = etox_it(some_nums, n);
        tv.setText("Your value(s) have been calculated to:\n");
        while (o < n) {
            tv.append(Double.toString(answer[o]) + "\n");
            o++;
        }
        File outfile = new File(getExternalFilesDir(null), outfilename);
        if (outfile.exists()) {
            tv.append("\n\nThis file already exists. Either manually delete it or choose another filename before continuing.");}
        else {
            int i;
            FileWriter fw = new FileWriter(outfile);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            for (i = 0; i < n; i++) {
                pw.println(String.format("%.4f", some_nums[i]));
            }
            pw.close();
            tv.append("\n\nThe data has been written to the specified output file successfully.");
        }
    }

    public double[] etox_it(double[] a, int num_items) {
        int i;

        for (i = 0; i < num_items; i++) {
            a[i] = Math.exp(a[i]);
        }

        return a;
    }
}
