package ma.alaram.mosque.mosquealaram;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ma.alaram.mosque.mosquealaram.COMMON.UnitOfwork;

import static android.R.attr.data;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    Context context = this;
    UnitOfwork uow = new UnitOfwork(context);
    Marker marker;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        findViewById(R.id.zone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MosqueZone.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.addlocation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetRadiusAndPlaceName();

            }
        });
        findViewById(R.id.setting).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SettingBox();
            }
        });


        if (ContextCompat.checkSelfPermission(MapsActivity.this,
                android.Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(MapsActivity.this,
                Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED)
        {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.READ_SMS)
                    && ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.SEND_SMS)
                    && ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_PHONE_STATE)) {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.READ_SMS, Manifest.permission.SEND_SMS, Manifest.permission.READ_PHONE_STATE}, 1);

            }
            else
            {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.READ_SMS, Manifest.permission.SEND_SMS, Manifest.permission.READ_PHONE_STATE}, 1);
            }

        }
        else
        {
            //do nothing
        }
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.INTERNET}, 1);
        LocationManager locationManger = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        // Toast.makeText(context, "Running", Toast.LENGTH_SHORT).show();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
       // Toast.makeText(context, "Running", Toast.LENGTH_SHORT).show();
        if (locationManger.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {

            locationManger.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,10000,0, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
            //        Toast.makeText(context, "Network Provider", Toast.LENGTH_SHORT).show();

                    // Toast.makeText(context, "Changed", Toast.LENGTH_SHORT).show();
                    mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(MapsActivity.this, R.raw.googlemap));
                    uow.mapData.setLatitude(location.getLatitude());
                    uow.mapData.setLongitude(location.getLongitude());


                    //  Toast.makeText(context,list1.get(0), Toast.LENGTH_SHORT).show();
                    LatLng currentLocation = new LatLng(location.getLatitude(), location.getLongitude());
                    if (uow.prefrencesMemory.getPrefrenceData("LastCircle", "ExistCircle", context).equals("yes")) {

                        ArrayList<String> list1 = uow.mapData.ListOfPlaces(context);
                        for (int i = 0; i < list1.size(); i++) {
                            ArrayList<String> data = uow.mapData.getMapData(context, list1.get(i));

                            float[] distance = new float[2];
                            ////////////////////////////////////////multiTime////////////////
                            Location.distanceBetween(location.getLatitude(), location.getLongitude(),
                                    Double.parseDouble(data.get(1)),
                                    Double.parseDouble(data.get(2)), distance);
                            if (distance[0] <= uow.mapData.getRadius()) {
                                // inside circle
                                //   Toast.makeText(context, "Inside The circle", Toast.LENGTH_SHORT).show();
                                AudioManager am = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
                                if (uow.prefrencesMemory.getPrefrenceData("setting", "ring", context).equals("silent")) {
                                    am.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                                } else if (uow.prefrencesMemory.getPrefrenceData("setting", "ring", context).equals("vibration")) {
                                    am.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
                                } else if (uow.prefrencesMemory.getPrefrenceData("setting", "ring", context).equals("normal")) {
                                    am.setRingerMode(AudioManager.MODE_NORMAL);

                                }

                                uow.prefrencesMemory.SaveData("insideCircle", "checkLocationIsInsideTheCircle", "LastCircle", context);
                            } else {
                                /// outSide The circle
                                //  Toast.makeText(context, "OutSideTheCircle", Toast.LENGTH_S
                                uow.prefrencesMemory.SaveData("OutSideCircle", "checkLocationIsInsideTheCircle", "LastCircle", context);

                                AudioManager am = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
                                am.setRingerMode(AudioManager.RINGER_MODE_NORMAL);

                            }
                        }
                    } else {
                    }
                    if (marker != null) {
                        marker.remove();
                    }
                    String list = "";

                    MarkerOptions markeOption = new MarkerOptions()
                            // .icon(BitmapDescriptorFactory.fromResource(R.drawable.custome_google_map_marker))
                            .title("lahore pakistan")
                            // .icon(BitmapDescriptorFactory.fromResource(R.drawable.addlocation))
                            .position(new LatLng(location.getLatitude(),location.getLongitude()));

                    marker = mMap.addMarker(markeOption);
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation,17.2f));
                    // mMap.animateCamera(CameraUpdateFactory.zoomIn());
                    // Zoom out to zoom level 10, animating with a duration of 2 seconds.
                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {

                }

                @Override
                public void onProviderEnabled(String provider) {

                }

                @Override
                public void onProviderDisabled(String provider) {

                }
            });
        } else if (locationManger.isProviderEnabled(LocationManager.GPS_PROVIDER)) {

            locationManger.requestLocationUpdates(LocationManager.GPS_PROVIDER,10000,0, new LocationListener()
            {
                @Override
                public void onLocationChanged(Location location)
                {
                    //Toast.makeText(context, "GPS Provider", Toast.LENGTH_SHORT).show();

                    // Toast.makeText(context, "Changed", Toast.LENGTH_SHORT).show();
                    uow.mapData.setLatitude(location.getLatitude());
                    uow.mapData.setLongitude(location.getLongitude());


                    //  Toast.makeText(context,list1.get(0), Toast.LENGTH_SHORT).show();
                    LatLng currentLocation = new LatLng(location.getLatitude(), location.getLongitude());
                    if (uow.prefrencesMemory.getPrefrenceData("LastCircle", "ExistCircle", context).equals("yes")) {

                        ArrayList<String> list1 = uow.mapData.ListOfPlaces(context);
                        for (int i = 0; i < list1.size(); i++) {
                            ArrayList<String> data = uow.mapData.getMapData(context, list1.get(i));

                            float[] distance = new float[2];
                            ////////////////////////////////////////multiTime////////////////
                            Location.distanceBetween(location.getLatitude(), location.getLongitude(),
                                    Double.parseDouble(data.get(1)),
                                    Double.parseDouble(data.get(2)), distance);
                            if (distance[0] <= uow.mapData.getRadius()) {
                                // inside circle
                                //   Toast.makeText(context, "Inside The circle", Toast.LENGTH_SHORT).show();
                                AudioManager am = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
                                if (uow.prefrencesMemory.getPrefrenceData("setting", "ring", context).equals("silent")) {
                                    am.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                                } else if (uow.prefrencesMemory.getPrefrenceData("setting", "ring", context).equals("vibration")) {
                                    am.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
                                } else if (uow.prefrencesMemory.getPrefrenceData("setting", "ring", context).equals("normal")) {
                                    am.setRingerMode(AudioManager.MODE_NORMAL);

                                }

                                uow.prefrencesMemory.SaveData("insideCircle", "checkLocationIsInsideTheCircle", "LastCircle", context);
                            } else {
                                /// outSide The circle
                                //  Toast.makeText(context, "OutSideTheCircle", Toast.LENGTH_S
                                uow.prefrencesMemory.SaveData("OutSideCircle", "checkLocationIsInsideTheCircle", "LastCircle", context);

                                AudioManager am = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
                                am.setRingerMode(AudioManager.RINGER_MODE_NORMAL);

                            }
                        }
                    } else {
                    }
                    if (marker != null) {
                        marker.remove();
                    }
                    String list = "";

                    MarkerOptions markeOption = new MarkerOptions()
                            .title("lahore pakistan")
                           // .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_add_location_black_24dp))
                            .position(new LatLng(location.getLatitude(), location.getLongitude()));

                    marker = mMap.addMarker(markeOption);
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation,17.2f));
                    // mMap.animateCamera(CameraUpdateFactory.zoomIn());
                    // Zoom out to zoom level 10, animating with a duration of 2 seconds.

                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {

                }

                @Override
                public void onProviderEnabled(String provider) {

                }

                @Override
                public void onProviderDisabled(String provider) {

                }
            });

        }


    }

    @Override
    public void onMapReady(final GoogleMap googleMap)

    {
        mMap = googleMap;

       // Toast.makeText(context, "mapReady", Toast.LENGTH_SHORT).show();
        //mMap.setMyLocationEnabled(true);
        if(uow.prefrencesMemory.getPrefrenceData("LastCircle","ExistCircle",context).equals("yes"))
        {
           ArrayList<String> list1=uow.mapData.ListOfPlaces(context);
            for(int i=0;i<list1.size();i++)
            {
             //   Toast.makeText(context,Integer.toString(i), Toast.LENGTH_SHORT).show();
                ArrayList<String> data = uow.mapData.getMapData(context,list1.get(i));
                uow.mapData.drawCircle(mMap,Double.parseDouble(data.get(0))
                        ,Double.parseDouble(data.get(1))
                        ,Double.parseDouble(data.get(2)),context);
                data.clear();
            }

        }

    }
    //////////////////////for set radius /////////////////
    private void GetRadiusAndPlaceName()
    {
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        LayoutInflater infulater = this.getLayoutInflater();
        View forgetView = infulater.inflate(R.layout.custome,null);
        alert.setView(forgetView);
        alert.setCancelable(false);
        alert.setTitle("      Add Location Info here");
        final EditText Radius = (EditText)forgetView.findViewById(R.id.Radius);
        final EditText PlaceName=(EditText)forgetView.findViewById(R.id.placeName);
        TextView list=(TextView)forgetView.findViewById(R.id.list);
        list.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                customeBox(context);
            }
        });
       // Radius.setHint("radius");

        alert.setPositiveButton("Save", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                if(Radius.getText().toString().equals(""))
                {
                    GetRadiusAndPlaceName();
                }
                else if(PlaceName.getText().toString().equals(""))
                {
                    GetRadiusAndPlaceName();

                }
                else
                {
                    ContentValues values=new ContentValues();
                    values.put("PlaceName",PlaceName.getText().toString());
                    values.put("Radius",Radius.getText().toString());
                    values.put("Latitude",Double.toString(uow.mapData.getLatitude()));
                    values.put("Longitude",Double.toString(uow.mapData.getLongitude()));
                    uow.mapData.drawCircle(mMap,Double.parseDouble(Radius.getText().toString())
                            ,(uow.mapData.getLatitude())
                            ,(uow.mapData.getLongitude()),context);
                    uow.mosqueZoneDAC.insert("ListOfPlaces",values);
                    uow.prefrencesMemory.SaveData("yes","ExistCircle","LastCircle",context);

                }

            }
        });

        alert.setNegativeButton("Later",new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {

            }
        });
        alert.show();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,String[] permissions,int[] grantResults)
    {
        switch (requestCode)
        {
            case 1:
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
                {
                    if(ContextCompat.checkSelfPermission(this,
                            Manifest.permission.READ_SMS)==PackageManager.PERMISSION_GRANTED
                            && ContextCompat.checkSelfPermission(this,
                            Manifest.permission.SEND_SMS)==PackageManager.PERMISSION_GRANTED
                            && ContextCompat.checkSelfPermission(this,
                            Manifest.permission.READ_PHONE_STATE)==PackageManager.PERMISSION_GRANTED)
                    {
                       // Toast.makeText(context, "Permission Granted", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        //Toast.makeText(this, "No permission granted", Toast.LENGTH_SHORT).show();
                    }
                    return;
                }
        }
    }
    public void customeBox(final Context context)
    {
        final android.support.v7.app.AlertDialog.Builder alert = new android.support.v7.app.AlertDialog.Builder(context);
        alert.setTitle("Saved Location!!!");
        LayoutInflater infulater = this.getLayoutInflater();
        View forgetView = infulater.inflate(R.layout.listviewcutome,null);
        alert.setView(forgetView);;
        alert.setCancelable(false);
        final ListView list=(ListView)forgetView.findViewById(R.id.listOfPlaces);

        Cursor cursor=uow.mosqueZoneDAC.getAll("ListOfPlaces");
        final ArrayList<String> placeList=new ArrayList<>();
        while (cursor.moveToNext())
        {
            placeList.add(cursor.getString(cursor.getColumnIndex("PlaceName")));
        }
        // ArrayAdapter<String> adapter=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,placeList);
        // list.setAdapter(adapter);
        final IndecatorAdapter indecator=new IndecatorAdapter(context,placeList);
        list.setAdapter(indecator);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id)
            {

                android.app.AlertDialog.Builder builder=new android.app.AlertDialog.Builder(context);
                builder.setTitle("Dou You Want to delete");
                builder.setMessage(placeList.get(position));
                builder.setNegativeButton("No", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {

                    }
                });
                builder.setPositiveButton("Conform", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                       uow.mapData.getIdAndDelete(placeList.get(position));
                       // Toast.makeText(context, "Deleted successfully", Toast.LENGTH_SHORT).show();
                    }
                }).setCancelable(false).show();
            }

        });

        alert.setPositiveButton("OK", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
               // ArrayList<String> SelectedPlaces=indecator.list;
                //Toast.makeText(context,SelectedPlaces.get(0), Toast.LENGTH_SHORT).show();

            }
        });
        alert.show();
    }
    public void SettingBox()
    {
            final android.support.v7.app.AlertDialog.Builder alert = new android.support.v7.app.AlertDialog.Builder(context);
            alert.setTitle("Customize Setting!!!");
            LayoutInflater infulater = this.getLayoutInflater();
            View view = infulater.inflate(R.layout.setting,null);
            alert.setView(view);;
           Spinner  callSpinner=(Spinner)view.findViewById(R.id.callSpinner);
           Spinner  msgSpinner=(Spinner)view.findViewById(R.id.msgSpinner);
           SeekBar ringBar=(SeekBar)view.findViewById(R.id.ringBar);
           final TextView ring=(TextView)view.findViewById(R.id.ring);
           uow.adapterClass.setRingMode(ring.getText().toString());
          ringBar.setMax(2);
          ringBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
              //  Toast.makeText(MapsActivity.this,Integer.toString(progress), Toast.LENGTH_SHORT).show();
                if(progress==0)
                {
                    uow.adapterClass.setRingMode("silent");
                    ring.setText("silent");
                }
                else if(progress==1)
                {
                    uow.adapterClass.setRingMode("vibration");
                    ring.setText("vibration");
                }
                else if(progress==2)
                {
                    uow.adapterClass.setRingMode("normal");
                    ring.setText("normal");

                }
                else
                {
                    uow.adapterClass.setRingMode("normal");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {

            }
        });
        String[] listFormsgs=new String[]{"i will text you ","Prayer time ","iam busy"};
        final String[] listForCalls=new String[]{"i will call you","Prayer time","please wait"};
         uow.adapterClass.listAdapter(listFormsgs,context,msgSpinner,"msg");
         uow.adapterClass.listAdapter(listForCalls,context,callSpinner,"call");
            alert.setCancelable(false);
              alert.setPositiveButton("SAVE", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialog, int which)
                  {
                      uow.prefrencesMemory.SaveData(uow.adapterClass.getRingMode(),"ring","setting",context);
                      Toast.makeText(MapsActivity.this,uow.adapterClass.getRingMode(), Toast.LENGTH_SHORT).show();

                  }
              }).setNegativeButton("LATER", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialog, int which) {

                  }
              }).show();

    }

}
