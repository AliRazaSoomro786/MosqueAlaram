package ma.alaram.mosque.mosquealaram;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import ma.alaram.mosque.mosquealaram.DTO.PraysAdapter;

public class FirstFragment extends Fragment
{// Store instance variables

    // newInstance constructor for creating fragment with arguments
    public static FirstFragment newInstance()
    {
        FirstFragment first = new FirstFragment();
        Bundle args = new Bundle();
        first.setArguments(args);
        return first;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }
    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_first,container, false);
        ListView listView=(ListView)view.findViewById(R.id.prays);
        PraysAdapter praysAdapter=new PraysAdapter(getActivity().getApplicationContext());
        listView.setAdapter(praysAdapter);
        // String a=R.string.After_rainfall;
        //Toast.makeText(getActivity().getApplicationContext(),"", Toast.LENGTH_SHORT).show();
        return view;
    }
}