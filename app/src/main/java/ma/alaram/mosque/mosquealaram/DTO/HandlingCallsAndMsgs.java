package ma.alaram.mosque.mosquealaram.DTO;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.telephony.SmsManager;
import android.widget.Toast;

/**
 * Created by alisoomro on 12/04/2018.
 */

public class HandlingCallsAndMsgs
{
    SharedPrefrencesMemory sharedPrefrencesMemory=new SharedPrefrencesMemory();
    private String SenderNumber;
    public void setSenderNumber(String senderNumber)
    {
        SenderNumber = senderNumber;
    }



    public String getSenderNumber()
    {
        return SenderNumber;
    }
    @RequiresApi(api = Build.VERSION_CODES.DONUT)
    public void sendSMS(String Type, Context context, String number)
    {
        if(Type.equals("msg"))
        {

            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(number,null,sharedPrefrencesMemory.getPrefrenceData("setting","msgFormsg",context),null,null);
        }
        else if(Type.equals("call"))
        {
            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(number, null,sharedPrefrencesMemory.getPrefrenceData("setting","msgForcall",context),null,null);
        }

    }

}
