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
        //lv=(ListView) view.findViewById(R.id.list);

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
            String url= "https://gis-api.aiesec.org:443/v1/programmes.json?access_token=fbdff6c275de0739b4ac85b857550195d4d121e7fe3a4104d26eacc74a6213aa";
            String jsonStr= sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: "+jsonStr);
            if (jsonStr !=null){
                try {
                    JSONObject jsonObj= new JSONObject(jsonStr);
                    JSONArray ops=jsonObj.getJSONArray("ops");
                    for (int i=0; i<ops.length(); i++){
                        JSONObject c= ops.getJSONObject(i);
                        String id= c.getString("id");
                        String short_name= c.getString("short_name");
                        String constumer_name= c.getString("constumer_name");
                        String description=c.getString("description");
                        String color= c.getString("color");
                        int group_id= c.getInt("group_id");
                        String organisation_id= c.getString("profile_photo_urls");
                        String cover_photo_urls=c.getString("cover_photo_urls");








                    }


                } catch (Exception e){}

            }
            return null;
        }
    }
}
