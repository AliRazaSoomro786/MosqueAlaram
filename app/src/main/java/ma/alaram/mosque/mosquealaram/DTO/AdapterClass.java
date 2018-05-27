package ma.alaram.mosque.mosquealaram.DTO;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by alisoomro on 05/04/2018.
 */

public class AdapterClass
{
 private String msgFormsg;
    private String msgForCall;
    private String RingMode;

    public void setMsgForCall(String msgForCall) {
        this.msgForCall = msgForCall;
    }

    public void setMsgFormsg(String msgFormsg) {
        this.msgFormsg = msgFormsg;
    }

    public void setRingMode(String ringMode) {
        RingMode = ringMode;
    }

    public String getMsgForCall() {
        return msgForCall;
    }

    public String getMsgFormsg() {
        return msgFormsg;
    }

    public String getRingMode() {
        return RingMode;
    }

    public void listAdapter(final String[] arrayList, final Context context, Spinner spinner,final String msg)
    {
        final ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(context,android.R.layout.simple_list_item_1,arrayList);
        spinner.setAdapter(arrayAdapter);
         spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
         {
             @Override
             public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
             {
                 SharedPrefrencesMemory prefrencesMemory=new SharedPrefrencesMemory();

                 if(msg.equals("call"))
                 {
                     setMsgForCall(arrayList[position]);
                     prefrencesMemory.SaveData(getMsgForCall(),"msgForcall","setting",context);

                 }
                 else if(msg.equals("msg"))
                 {
                     setMsgFormsg(arrayList[position]);
                     prefrencesMemory.SaveData(getMsgFormsg(),"msgFormsg","setting",context);

                 }
                 else
                 {
                     //nothing happend
                 }
             }

             @Override
             public void onNothingSelected(AdapterView<?> parent) {

             }
         });
    }
}
