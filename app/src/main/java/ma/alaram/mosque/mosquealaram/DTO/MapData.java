
package ma.alaram.mosque.mosquealaram.DTO;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import ma.alaram.mosque.mosquealaram.DAL.MosqueZoneDAC;

/**
 * Created by alisoomro on 07/04/2018.
 */

public class MapData
{
    private double latitude;
private double radius;
    Context context;
    private MosqueZoneDAC mosqueZoneD;
    public void getIdAndDelete(String placeName)
    {
        Cursor cursor=mosqueZoneD.getAll("ListOfPlaces");
        while (cursor.moveToNext())
        {
           if(cursor.getString(cursor.getColumnIndex("PlaceName")).equals(placeName))
           {
               mosqueZoneD.delete("ListOfPlaces", Integer.valueOf(cursor.getString(cursor.getColumnIndex("Id"))));
           }
        }
    }
    public ArrayList<String> ListOfPlaces(Context context)
    {
        ArrayList<String> list=new ArrayList<>();
        mosqueZoneD=new MosqueZoneDAC(context);
        Cursor cursor=mosqueZoneD.getAll("ListOfPlaces");
        while (cursor.moveToNext())
        {
            list.add(cursor.getString(cursor.getColumnIndex("PlaceName")));
        }
        return list;
    }
    public ArrayList<String> getMapData(Context context,String placeName)
    {
        ArrayList<String> list=new ArrayList<>();
        Cursor cursor=mosqueZoneD.getAll("ListOfPlaces");
        while (cursor.moveToNext())
        {
            if(cursor.getString(cursor.getColumnIndex("PlaceName")).equals(placeName))
            {
                list.add(cursor.getString(cursor.getColumnIndex("Radius")));
                list.add(cursor.getString(cursor.getColumnIndex("Latitude")));
                list.add(cursor.getString(cursor.getColumnIndex("Longitude")));
            }
        }
        return list;
    }
SharedPrefrencesMemory prefrencesMemory=new SharedPrefrencesMemory();
    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setLatitude(double latitude)
    {
        this.latitude = latitude;
    }

    public double getLatitude()
    {
        return latitude;
    }
    private double longitude;

    public void setLongitude(double longitude)
    {
        this.longitude = longitude;
    }

    public double getLongitude()
    {
        return longitude;
    }
    private String centerLat="";
    private String centerLong="";
    private String placeName;

    public void setPlaceName(String placeName)
    {
        this.placeName = placeName;
    }
    public String getPlaceName()
    {
        return placeName;
    }

    public void setCenterLat(String centerLat) {
        this.centerLat = centerLat;
    }

    public String getCenterLat()
    {
        return centerLat;
    }
    public void setCenterLong(String centerLong) {
        this.centerLong = centerLong;
    }

    public String getCenterLong()
    {
        return centerLong;
    }


    public void drawCircle(GoogleMap mMap, Double radius, Double latitude, Double longitude, Context context)
    {
        Circle circle;
        circle = mMap.addCircle(new CircleOptions()
                .center(new LatLng(latitude,longitude))
                .radius(radius)
                .strokeWidth(1)
                .strokeColor(Color.argb(128,128,128,128))
                .fillColor(Color.argb(135,206,250,250))
                .clickable(true));
        setCenterLong(Double.toString(circle.getCenter().longitude));
        setCenterLat(Double.toString(circle.getCenter().latitude));
        setRadius(circle.getRadius());
        mMap.setOnCircleClickListener(new GoogleMap.OnCircleClickListener()
        {
            @Override
            public void onCircleClick(Circle circle)
            {
                int strokeColor = circle.getStrokeColor() ^ 0x00ffffff;
                circle.setStrokeColor(strokeColor);
            }
        });
    }
}
