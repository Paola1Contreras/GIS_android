package edu.tecii.android.proyectoportalaiesec;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.HttpAuthHandler;

import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Paola on 02/12/2016.
 */

public class Opp extends Fragment {


    private String TAG = MainActivity.class.getSimpleName();
    private ListView lv;

    ArrayList<HashMap<String, String>> oppo;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.opportunities, container, false);

        oppo=new ArrayList<>();
        lv=(ListView) view.findViewById(R.id.list);

        new GetOp().execute();
        return view;

    }

    private class GetOp extends AsyncTask<Void, Void, Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(getContext(),"Json Data is downloading", Toast.LENGTH_LONG).show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh= new HttpHandler();
            String url= "https://gis-api.aiesec.org:443/v2/opportunities.json?access_token=b74673f56ab049c657f12ede268b5fc4e3bdfa0775c0130edf1ad29b8d04e869";
            String jsonStr= sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: "+jsonStr);
            if (jsonStr !=null){
                try {
                    JSONObject jsonObj= new JSONObject(jsonStr);
                    JSONArray ops=jsonObj.getJSONArray("ops");
                    for (int i=0; i<ops.length(); i++){
                        JSONObject c= ops.length(i);
                        String id= c.getString("id");
                        String title= c.getString("title");
                        String status= c.getString("status");
                        String location= c.getString("location");
                        String programmes= c.getString("programmes");
                        String applications_count= c.getString("applications_count");
                        String organisation_id= c.getString("organisation_id");







                    }


                }

            }
            return null;
        }
    }
}
