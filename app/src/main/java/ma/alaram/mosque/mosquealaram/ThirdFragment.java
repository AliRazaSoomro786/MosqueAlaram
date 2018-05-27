package ma.alaram.mosque.mosquealaram;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.barteksc.pdfviewer.PDFView;


public class ThirdFragment extends Fragment
{
        // Store instance variables
        private String title;
        private int page;

        // newInstance constructor for creating fragment with arguments
        public static ThirdFragment newInstance()
        {
            ThirdFragment thirdFragment = new ThirdFragment();
            return thirdFragment;
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
            View view = inflater.inflate(R.layout.fragment_third,container, false);
            PDFView pdfView=(PDFView)view.findViewById(R.id.quran);
            pdfView.fromAsset("quraan.pdf").load();
            return view;
        }
}