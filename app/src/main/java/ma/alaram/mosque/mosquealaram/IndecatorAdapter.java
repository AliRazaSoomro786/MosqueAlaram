package ma.alaram.mosque.mosquealaram;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by alisoomro on 24/04/2018.
 */

public class IndecatorAdapter extends BaseAdapter
{
    boolean flag=true;
    LayoutInflater layoutInflater;
    ArrayList<String> list;
    ArrayList<String> on=new ArrayList<>();
    Context context;
    public IndecatorAdapter(Context context, ArrayList<String> list)
    {
        this.list=list;
        this.context=context;
    }
    @Override
    public int getCount()
    {
        return list.size();
    }

    @Override
    public Object getItem(int position)
    {
        return position;
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {

            layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.indecator,null);
            TextView name=(TextView)convertView.findViewById(R.id.placeName);
            name.setText(list.get(position));
        return convertView;
    }
}
