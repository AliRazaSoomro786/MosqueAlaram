package ma.alaram.mosque.mosquealaram.DTO;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import ma.alaram.mosque.mosquealaram.R;

/**
 * Created by soomro on 4/1/2018.
 */

public class AlertDiloagBox
{
    public void box(String value,Context context,String button)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setTitle(value);

        builder.setPositiveButton(button, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {

            }
        }).setCancelable(false).show();
    }
    public void ToastMessage(String message, Context context)
    {
        Toast.makeText(context,message, Toast.LENGTH_SHORT).show();
    }

}
