package com.example.halper.stringlistlab;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
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

    // add the item to the list

    public void addItem(View view) {

        EditText et1;
        EditText et2;
        String new_item;
        int pos;
        StringList the_list;

        et1 = (EditText) findViewById(R.id.edit_item);
        new_item = et1.getText().toString();

        et2 = (EditText) findViewById(R.id.edit_position);
        pos = Integer.parseInt(et2.getText().toString());

        // Get the string list instance

        the_list = StringList.getInstance();

        // try to put the new item on the list

        try
        {
            the_list.add(pos, new_item);

            Toast.makeText(AddItemActivity.this,
                    new_item + " added to the list.",
                    Toast.LENGTH_SHORT).show();
        }
        catch(IndexOutOfBoundsException e)
        {
            Toast.makeText(AddItemActivity.this,
                    "Failed to add item to the list.",
                    Toast.LENGTH_SHORT).show();

        }

    } // end addItem

} // end AddItemActivity

