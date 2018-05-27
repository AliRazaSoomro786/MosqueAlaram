package ma.alaram.mosque.mosquealaram.DTO;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.telephony.SmsMessage;
import android.widget.Toast;

/**
 * Created by alisoomro on 10/04/2018.
 */

public class SmsReceiver extends BroadcastReceiver
{
    private static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
    private static final String TAG = "SMSBroadcastReceiver";
    @RequiresApi(api = Build.VERSION_CODES.DONUT)
    @Override
    public void onReceive(Context context, Intent intent)
    {  Bundle myBundle = intent.getExtras();
        SmsMessage [] messages = null;
        String strMessage = "";

        if (myBundle != null)
        {
            //get message in pdus format(protocol discription unit)
            Object [] pdus = (Object[]) myBundle.get("pdus");
            //create an array of messages
            messages = new SmsMessage[pdus.length];

            for (int i = 0; i < messages.length; i++)
            {
                //Create an SmsMessage from a raw PDU.
                messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                strMessage +=messages[i].getOriginatingAddress();
            }
            //show message in a Toast
            String number="0";
            for(int i=3;i<strMessage.length();i++)
            {
                number+=strMessage.charAt(i);
            }
            SharedPrefrencesMemory prefrencesMemory=new SharedPrefrencesMemory();
            HandlingCallsAndMsgs callsAndMsgs=new HandlingCallsAndMsgs();
            if(prefrencesMemory.getPrefrenceData("LastCircle","checkLocationIsInsideTheCircle",context).equals("insideCircle"))
            {
                callsAndMsgs.sendSMS("msg",context,number);
               // Toast.makeText(context, "msg Recived", Toast.LENGTH_SHORT).show();
            }

        }
    }
}
