package ma.alaram.mosque.mosquealaram.DTO;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.widget.Toast;

public class CallReceiver extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent)
    {
        String incoming_number = intent.getStringExtra("incoming_number");
        //Toast.makeText(context,incoming_number, Toast.LENGTH_SHORT).show();
        SharedPrefrencesMemory prefrencesMemory=new SharedPrefrencesMemory();
        HandlingCallsAndMsgs callsAndMsgs=new HandlingCallsAndMsgs();
        if(prefrencesMemory.getPrefrenceData("LastCircle","checkLocationIsInsideTheCircle",context).equals("insideCircle"))
        {
           callsAndMsgs.sendSMS("call",context,incoming_number);
        }
        }


    }

