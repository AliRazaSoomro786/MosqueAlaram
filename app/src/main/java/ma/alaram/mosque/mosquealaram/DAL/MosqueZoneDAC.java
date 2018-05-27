package ma.alaram.mosque.mosquealaram.DAL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by alisoomro on 23/04/2018.
 */

public class MosqueZoneDAC extends SQLiteOpenHelper
{
    public MosqueZoneDAC(Context context)
    {
        super(context,"MosqueZone",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE ListOfPlaces(Id INTEGER PRIMARY KEY AUTOINCREMENT,PlaceName TEXT,Radius TEXT,Latitude TEXT,Longitude TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }
    public void insert(String tableName, ContentValues values)
    {
        SQLiteDatabase db = getWritableDatabase();
        db.insert(tableName,null,values);
    }
    public Cursor getAll(String tableName)
    {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor =db.rawQuery("select * from "+tableName,null);
        return  cursor;


    }
    public void delete(String tableName,Integer id)
    {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(tableName,"Id=?",new String[]{id.toString()});

    }


}
