package ma.alaram.mosque.mosquealaram.DTO;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import ma.alaram.mosque.mosquealaram.R;

/**
 * Created by alisoomro on 30/04/2018.
 */

public class PraysAdapter extends BaseAdapter {
    Context context;
    LayoutInflater layoutInflater;
    Integer[] Prays = {R.string.before_entering, R.string.exits_Masjid, R.string.Leaving_Home, R.string.before_entering, R.string.Feeling_Stressed,
            R.string.After_rainfall, R.string.Before_sleeping
            , R.string.healthy_life, R.string.Everyday_Duas, R.string.For_forgiveness};
    Integer[] Translation = {R.string.before_entering1, R.string.exits_Masjid1, R.string.Leaving_Home1, R.string.before_entering1, R.string.Feeling_Stressed1,
            R.string.After_rainfall1, R.string.Before_sleeping1
            , R.string.healthy_life1, R.string.Everyday_Duas1, R.string.For_forgiveness1};
    String[] names = {"Before entering Masjid", "Exits From Masjid", "When Leaving Home", "When Entering Home", "When Feeling Stressed", "After rainfall", "Before sleeping"
            , "For healthy life", "Everyday Dua ", "For forgiveness"};

    public PraysAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return names.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(R.layout.prays, null);
        TextView name = (TextView) convertView.findViewById(R.id.name);
        name.setText(names[position]);
        //  TextView translation=(TextView)convertView.findViewById(R.id.translation);
        //  translation.setText(Translation[position]);
        TextView prays = (TextView) convertView.findViewById(R.id.prays);
        prays.setText(Prays[position]);

        return convertView;


    }
}