package com.example.halper.stringlistlab;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RemoveItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_item);
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

    public void removeItem(View view) {

        EditText et;
        int pos;
        StringList the_list;

        et = findViewById(R.id.remove_position);
        pos = Integer.parseInt(et.getText().toString());

        // Get the string list instance
        the_list = StringList.getInstance();

        // try to remove the item from the list
        try
        {
            the_list.remove(pos);

            Toast.makeText(RemoveItemActivity.this,
                    "Item at position " + pos + " removed from the list.",
                    Toast.LENGTH_SHORT).show();
        }
        catch(IndexOutOfBoundsException e)
        {
            Toast.makeText(RemoveItemActivity.this,
                    "Failed to remove item from the list.",
                    Toast.LENGTH_SHORT).show();

        }

    } // end addItem

} // end AddItemActivity
