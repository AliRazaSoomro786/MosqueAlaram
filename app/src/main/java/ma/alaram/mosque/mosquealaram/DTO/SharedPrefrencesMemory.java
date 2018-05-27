package ma.alaram.mosque.mosquealaram.DTO;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;

/**
 * Created by soomro on 4/1/2018.
 */

public class SharedPrefrencesMemory
{
  public ArrayList<String> data;
    public ArrayList<String> keys;

    public void setData(ArrayList<String> data) {
        this.data = data;
    }

    public void setKeys(ArrayList<String> keys) {
        this.keys = keys;
    }

    public ArrayList<String> getData() {
        return data;
    }

    public ArrayList<String> getKeys() {
        return keys;
    }

    public void SaveData(String data,String key, String name, Context context)
   {
       SharedPreferences sharedPreferences=context.getSharedPreferences(name,Context.MODE_PRIVATE);
       SharedPreferences.Editor editor=sharedPreferences.edit();
       editor.putString(key,data);
       editor.apply();
       editor.commit();



   }
   public String getPrefrenceData(String name,String key,Context context)
   {
       SharedPreferences sharedPreferences=context.getSharedPreferences(name,Context.MODE_PRIVATE);
       return sharedPreferences.getString(key,"");
   }
}
