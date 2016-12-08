package edu.tecii.android.proyectoportalaiesec;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
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

        View view = inflater.inflate(R.layout.opportunities, container, false);

        oppo = new ArrayList<>();
        //lv=(ListView) view.findViewById(R.id.list);

        new GetOp().execute();
        return view;

    }

    private class GetOp extends AsyncTask<Object, Object, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(getContext(), "Json Data is downloading", Toast.LENGTH_LONG).show();

        }

        @Override
        protected Void doInBackground(Object... arg0) {
            HttpHandler sh = new HttpHandler();
            String url = "https://gis-api.aiesec.org:443/v1/programmes.json?access_token=79c6af431ea30f04a8394aebb6f884c95121cac70cbf8fe8016869b12cb2c413";
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    JSONArray ops = jsonObj.getJSONArray("profile");
                    for (int i = 0; i < ops.length(); i++) {
                        JSONObject c = ops.getJSONObject(i);
                        String id = c.getString("id");
                        String short_name = c.getString("short_name");
                        String constumer_name = c.getString("constumer_name");
                        String description = c.getString("description");
                        String color = c.getString("color");
                        int group_id = c.getInt("group_id");
                        String organisation_id = c.getString("profile_photo_urls");
                        String cover_photo_urls = c.getString("cover_photo_urls");
                    }

                    String url2 = "https://gis-api.aiesec.org:443/v2/opportunities.json?access_token=79c6af431ea30f04a8394aebb6f884c95121cac70cbf8fe8016869b12cb2c413";
                    String jsonStr2 = sh.makeServiceCall(url);
                } catch (Exception e) {
                }
                Log.e(TAG, "Response from url: " + jsonStr);
                if (jsonStr != null) {
                    try {
                        JSONObject jsonObj = new JSONObject(jsonStr);
                        JSONArray ops = jsonObj.getJSONArray("opportunities");
                        for (int i = 0; i < ops.length(); i++) {
                            JSONObject c = ops.getJSONObject(i);
                            int opportunity_id = c.getInt("id");
                            String status = c.getString("status");
                            // URL url= c.getString("");
                            String location = c.getString("location");
                            String programmes = c.getString("programmes");
                            int aplication_count = c.getInt("aplication_count");
                            String is_favorited = c.getString("profile_photo_urls");
                            int organisation_id = c.getInt("organisation_id");
                        }


                    } catch (Exception e) {
                    }

                }
                return null;
            }
            return null;
        }
    }
}


