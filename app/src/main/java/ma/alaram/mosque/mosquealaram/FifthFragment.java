package ma.alaram.mosque.mosquealaram;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.gms.common.api.Response;

import org.json.JSONException;
import org.json.JSONObject;

import static android.content.ContentValues.TAG;

public class FifthFragment extends Fragment
{
    String tag_json_obj = "json_obj_req";

    String url="http://muslimsalat.com/lahore.json?key=e22596b16091e2d871e9a776db266e41";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static FifthFragment newInstance()
    {
        FifthFragment fragment = new FifthFragment();

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        final ProgressDialog pDialog = new ProgressDialog(getActivity().getApplicationContext());
        pDialog.setMessage("Loading...");
        pDialog.show();
        final View view = inflater.inflate(R.layout.namaz_timing,container, false);
        final TextView fajr=(TextView)view.findViewById(R.id.fajar);
        final TextView zuhr=(TextView)view.findViewById(R.id.zuhar);
        final TextView  isha=(TextView)view.findViewById(R.id.isha);
        final TextView  asar=(TextView)view.findViewById(R.id.asr);
        final TextView  magrib=(TextView)view.findViewById(R.id.magrib);
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new com.android.volley.Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response)
                    {
                        try {
                            String Fajr=response.getJSONArray("items").getJSONObject(0).get("fajr").toString();
                            String Zuhr=response.getJSONArray("items").getJSONObject(0).get("dhuhr").toString();
                            String Asr=response.getJSONArray("items").getJSONObject(0).get("asr").toString();
                            String Magrib=response.getJSONArray("items").getJSONObject(0).get("maghrib").toString();
                            String Isha=response.getJSONArray("items").getJSONObject(0).get("isha").toString();
                            fajr.setText(Fajr);
                            zuhr.setText(Zuhr);
                            asar.setText(Asr);
                            magrib.setText(Magrib);
                            isha.setText(Isha);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        // Log.d(TAG, response.toString());
                        pDialog.hide();
                    }
                }, new com.android.volley.Response.ErrorListener()
        {

            @Override
            public void onErrorResponse(VolleyError error)
            {
                //   VolleyLog.d(TAG, "Error: " + error.getMessage());
                // hide the progress dialog
                //pDialog.hide();
            }
        });

// Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq,tag_json_obj);
        return view;
    }


}
