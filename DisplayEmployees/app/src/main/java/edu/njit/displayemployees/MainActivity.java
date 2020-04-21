package edu.njit.displayemployees;

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

import java.net.URL;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
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

    public void displayEmployees(View view) throws java.io.IOException {

        TextView tv = findViewById(R.id.text_main);
        EditText et = findViewById(R.id.edit_file);
        double averageSalary, averageYearsOfService;
        int i = 0;
        String[] dataArray = new String[18];
        String filename = et.getText().toString();
        URL file_url = new URL(filename);
        Scanner fsc = new Scanner(file_url.openStream());

        // create three Employee objects
        Employee emp1 = new Employee();
        Employee emp2 = new Employee();
        Employee emp3 = new Employee();

        while (fsc.hasNext()) {
            dataArray[i] = fsc.nextLine();
            i++;
        }

        //read the data from the online file into each Employee object
        emp1.setName(dataArray[0]);
        emp1.setID(dataArray[1]);
        emp1.setSalary(Double.parseDouble(dataArray[2]));
        emp1.setOffice(dataArray[3]);
        emp1.setExtension(dataArray[4]);
        emp1.setYearsOfService(Integer.parseInt(dataArray[5]));

        emp2.setName(dataArray[6]);
        emp2.setID(dataArray[7]);
        emp2.setSalary(Double.parseDouble(dataArray[8]));
        emp2.setOffice(dataArray[9]);
        emp2.setExtension(dataArray[10]);
        emp2.setYearsOfService(Integer.parseInt(dataArray[11]));

        emp3.setName(dataArray[12]);
        emp3.setID(dataArray[13]);
        emp3.setSalary(Double.parseDouble(dataArray[14]));
        emp3.setOffice(dataArray[15]);
        emp3.setExtension(dataArray[16]);
        emp3.setYearsOfService(Integer.parseInt(dataArray[17]));

        tv.setText("Read employee data from online file successfully.\n\n");

        //display each employee's information through TextView
        tv.append("Name: " + emp1.getName() + "\n");
        tv.append("ID: " + emp1.getID() + "\n");
        tv.append("Salary: $" + String.format("%.2f", emp1.getSalary()) + "\n");
        tv.append("Office: " + emp1.getOffice() + "\n");
        tv.append("Extension Number: " + emp1.getExtension() + "\n");
        tv.append("Years of Service: " + emp1.getYearsOfService() + "\n\n");

        tv.append("Name: " + emp2.getName() + "\n");
        tv.append("ID: " + emp2.getID() + "\n");
        tv.append("Salary: $" + String.format("%.2f", emp2.getSalary()) + "\n");
        tv.append("Office: " + emp2.getOffice() + "\n");
        tv.append("Extension Number: " + emp2.getExtension() + "\n");
        tv.append("Years of Service: " + emp2.getYearsOfService() + "\n\n");

        tv.append("Name: " + emp3.getName() + "\n");
        tv.append("ID: " + emp3.getID() + "\n");
        tv.append("Salary: $" + String.format("%.2f", emp3.getSalary()) + "\n");
        tv.append("Office: " + emp3.getOffice() + "\n");
        tv.append("Extension Number: " + emp3.getExtension() + "\n");
        tv.append("Years of Service: " + emp3.getYearsOfService() + "\n\n");

        //calculate average number of service years and display through TextView
        averageYearsOfService = (emp1.getYearsOfService() + emp2.getYearsOfService() + emp3.getYearsOfService()) / 3.0;
        tv.append("Average Years of Service: " + String.format("%.1f", averageYearsOfService));

        //calculate geometric average of salaries and output through TextView
        averageSalary = Math.cbrt(emp1.getSalary() * emp2.getSalary() * emp3.getSalary());
        tv.append("\n\nAverage Employee Salary: $" + String.format("%.2f", averageSalary));
    }
}