package ma.alaram.mosque.mosquealaram.COMMON;

import android.app.AlertDialog;
import android.content.Context;

import ma.alaram.mosque.mosquealaram.DAL.MosqueZoneDAC;
import ma.alaram.mosque.mosquealaram.DTO.AdapterClass;
import ma.alaram.mosque.mosquealaram.DTO.AlertDiloagBox;
import ma.alaram.mosque.mosquealaram.DTO.HandlingCallsAndMsgs;
import ma.alaram.mosque.mosquealaram.DTO.HijriCalendar;
import ma.alaram.mosque.mosquealaram.DTO.MapData;
import ma.alaram.mosque.mosquealaram.DTO.SharedPrefrencesMemory;

/**
 * Created by alisoomro on 04/04/2018.
 */

public class UnitOfwork
{
    public AlertDiloagBox alertDialog;
    public AdapterClass adapterClass;
    public MapData mapData;
    public HandlingCallsAndMsgs handlingCallsAndMsgs;
    public MosqueZoneDAC mosqueZoneDAC;
    public HijriCalendar hijriCalendar;
    public SharedPrefrencesMemory prefrencesMemory;
    public UnitOfwork(Context context)
    {
        alertDialog=new AlertDiloagBox();
        prefrencesMemory=new SharedPrefrencesMemory();
        adapterClass=new AdapterClass();
        mapData=new MapData();
        hijriCalendar=new HijriCalendar();
        handlingCallsAndMsgs=new HandlingCallsAndMsgs();
        mosqueZoneDAC=new MosqueZoneDAC(context);
    }
}
