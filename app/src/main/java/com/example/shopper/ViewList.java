package com.example.shopper;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class ViewList extends AppCompatActivity {

    Intent intent;

    // fields used to get shopping list id passed from Main Activity
    long id;
    Bundle bundle;

    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // get the id
        bundle = this.getIntent().getExtras();
        id = bundle.getLong("_id");

        dbHandler = new DBHandler(this, null);

        String shoppingListName = dbHandler.getShoppingListName((int)id);

        this.setTitle(shoppingListName);
    }

    /**
     * This method further initializes the action bar of the main activity
     * It gets the code we wrote in the menu main and incorporates it into the
     * action bar.
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_list, menu);
        return true;
    }

    /**
     * This method gets called when an item in the overflow menu is selected and it
     * controls what happens when the item is selected
     * @param item item is selected in the overflow menu
     * @return true if an item was selected; else return false
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // get the id of the item that was selected
        switch(item.getItemId()){
            case R.id.action_home :
                // initializing an intent for the Create List activity, starting it
                // and returning home
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_create_list:
                // initializing an intent for the Main activity, starting it
                // and returning home
                intent = new Intent(this, CreateList.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public void openAddItem(View view) {
    }
}
