package ma.alaram.mosque.mosquealaram;

import android.*;
import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import ma.alaram.mosque.mosquealaram.COMMON.UnitOfwork;

public class SplashScreen extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

     findViewById(R.id.splash).setOnClickListener(new View.OnClickListener()
     {
         @Override
         public void onClick(View v)
         {
            // Toast.makeText(SplashScreen.this, "clicked", Toast.LENGTH_SHORT).show();
             Intent intent=new Intent(getApplicationContext(),MapsActivity.class);
             startActivity(intent);
            //SettingBox();
         }
     });

    }
    private void SettingBox()
    {
        final android.support.v7.app.AlertDialog.Builder alert = new android.support.v7.app.AlertDialog.Builder(SplashScreen.this);
        alert.setTitle("       Namaz Timing");
        LayoutInflater infulater = this.getLayoutInflater();
        View view = infulater.inflate(R.layout.namaz_timing,null);
        alert.setView(view);;
        alert.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).show();

    }
}
