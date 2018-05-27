package ma.alaram.mosque.mosquealaram;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.github.barteksc.pdfviewer.PDFView;


public class FourthFragment extends Fragment
{
    private ViewFlipper myViewFlipper;
    private float initialXPoint;
    int[] image = {R.drawable.gallery_seven,R.drawable.gallery_one,R.drawable.gallery_two,R.drawable.gallery_three,
            R.drawable.gallery_four,R.drawable.gallery_five,R.drawable.gallery_six,R.drawable.gallery_seven};


    public static FourthFragment newInstance()
    {
        FourthFragment fourthFragment = new FourthFragment();
        return fourthFragment ;
        ////
    }


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_fourth,container, false);
        myViewFlipper = (ViewFlipper)view.findViewById(R.id.myflipper);

        for (int i = 0; i < image.length; i++)
        {
            ImageView imageView = new ImageView(getActivity().getApplicationContext());
            imageView.setImageResource(image[i]);
            myViewFlipper.addView(imageView);
        }
        myViewFlipper.setAutoStart(true);
        myViewFlipper.setFlipInterval(4000);
        myViewFlipper.startFlipping();


        return view;
    }


}
