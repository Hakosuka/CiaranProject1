package com.touchtechpayments.developer.ciaranproject1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

/**
 * A simple Java class designed to show an alert dialog.
 * Author: Ciaran Cumiskey
 * Version: 1.0 16 Mar 2015 - Shows an alert dialog when the button is pressed.
 * Version: 1.1 18 Mar 2015 - Upgraded to play a sound when the button is pressed.
 * Version: 1.1.1 18 Mar 2015 - Plays a sound when the "No" button in the dialog box is selected.
 * Version: 1.2 23 Mar 2015 - Shows a "splash screen" upon starting up
 */
public class MainActivity extends ActionBarActivity {
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // performs onCreate() on an ActionBarActivity object
        setContentView(R.layout.activity_main); // sets the view
        button = (Button)findViewById(R.id.button);
        // add button click listener
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // create a MediaPlayer to play the audio file
                MediaPlayer mPlr1 = MediaPlayer.create(getApplicationContext(), R.raw.dialog_alert);
                // M_PLR2 needs to be a final variable as onClick is an anonymous class
                final MediaPlayer M_PLR2 = MediaPlayer.create(getApplicationContext(), R.raw.cancel_noise);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getApplicationContext());
                alertDialogBuilder.setTitle("Alert"); // set title
                mPlr1.start(); // plays alert sound
                alertDialogBuilder
                        .setMessage("Press yes to exit this app, and no to close this alert.")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int id){
                                // if this button is clicked, close the current activity
                                MainActivity.this.finish();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int id){
                                // if this button is clicked, close the dialog box and do nothing else
                                M_PLR2.start();
                                dialog.cancel();
                                M_PLR2.release();
                            }
                        });
                        // Create alert dialog
                        AlertDialog aDialog = alertDialogBuilder.create();
                        // Show alert dialog
                        aDialog.show();
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
}