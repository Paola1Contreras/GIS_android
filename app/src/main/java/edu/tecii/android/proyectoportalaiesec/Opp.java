package edu.tecii.android.proyectoportalaiesec;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.HttpAuthHandler;

import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.HashMap;
import android.support.v4.app.Fragment;

/**
 * Created by Paola on 02/12/2016.
 */

public class Opp extends Fragment {


    private String TAG = MainActivity.class.getSimpleName();
    private ListView lv;
    private ListView lv2;

    ArrayList<HashMap<String, String>> oppo;
    ArrayList<HashMap<String, String>> oppo2;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.opportunities, container, false);

        oppo=new ArrayList<>();
        oppo2=new ArrayList<>();
        lv=(ListView) view.findViewById(R.id.list);
        lv2=(ListView) view.findViewById(R.id.list2);

        //if()
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
            String url= "https://gis-api.aiesec.org/v2/programmes.json?access_token=611c4b0c587fdf2a3614ad64da6bc7337a5f90c12416535856316629b53e68cc";
            String jsonStr= sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: "+jsonStr);
            if (jsonStr !=null){
                try {
                    //JSONObject jsonObj= new JSONObject(jsonStr);
                    //JSONArray ops=jsonObj.getJSONArray("ops");
                    JSONArray ops = new JSONArray(jsonStr);
                    for (int i=0; i<ops.length(); i++){
                        JSONObject c= ops.getJSONObject(i);
                        String id= c.getString("id");
                        String short_name= c.getString("short_name");
                        String consumer_name= c.getString("consumer_name");
                        String description=c.getString("description");
                        //String color= c.getString("color");
                        int group_id= c.getInt("group_id");
                        String organisation_id= c.getString("profile_photo_urls");
                        String cover_photo_urls=c.getString("cover_photo_urls");


                        HashMap<String, String> opp = new HashMap<>();

                        opp.put("id", id);
                        opp.put("short_name", short_name);
                        opp.put("consumer_name", consumer_name);
                        opp.put("description", description);

                        oppo.add(opp);

                    }


                } catch (JSONException e){
                    Log.e(TAG, "Something is wrong");
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getContext(),
                                    "Couldn't get json from server. Check LogCat for possible errors!",
                                    Toast.LENGTH_LONG).show();
                        }
                    });
                }

            } else {
                Log.e(TAG, "AUIDAAAAAAAA");
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG).show();
                    }
                });
            }

            //OPPORTUNITIES
            String url2 = "https://gis-api.aiesec.org:443/v2/opportunities.json?access_token=611c4b0c587fdf2a3614ad64da6bc7337a5f90c12416535856316629b53e68cc";
            String jsonStr2 = sh.makeServiceCall(url2);
            Log.e(TAG, "Response from url: " + jsonStr2);
            if (jsonStr2 != null) {
                try {
                    JSONObject jsonObj2 = new JSONObject(jsonStr2);
                    JSONArray ops = jsonObj2.getJSONArray("data");
                    for (int i = 0; i < ops.length(); i++) {
                        JSONObject c = ops.getJSONObject(i);
                        int opportunity_id = c.getInt("id");
                        String title = c.getString("title");
                        String status = c.getString("status");
                        JSONObject pro = c.getJSONObject("programmes");
                        String p_short_name = pro.getString("short_name");
                        Log.e(TAG, "PROGRAM: " + p_short_name);
                        //String location = c.getString("location");
                        //String programmes = c.getString("programmes");
                        int aplication_count = c.getInt("applications_count");
                        boolean is_favorited = c.getBoolean("is_favourited");

                        HashMap<String, String> opp2 = new HashMap<>();

                        opp2.put("id",opportunity_id+"");
                        opp2.put("title",title);
                        opp2.put("status",status);
                        opp2.put("program",p_short_name);
                        oppo2.add(opp2);
                    }


                } catch (JSONException e) {
                    Log.e(TAG, "Something is wrong");
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getContext(),
                                    "Couldn't get json from server. Check LogCat for possible errors!",
                                    Toast.LENGTH_LONG).show();
                        }
                    });
                }

            } else {
                Log.e(TAG, "ACCESS TOKEN");
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getContext(),
                                "Couldn't get json from server. Access token fail",
                                Toast.LENGTH_LONG).show();
                    }
                });
            }

            return null;

        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            ListAdapter adapter = new SimpleAdapter(getContext(), oppo,
                    R.layout.list_view, new String[]{ "short_name","consumer_name"},
                    new int[]{R.id.short_name, R.id.consumer_name});
            lv.setAdapter(adapter);

            ListAdapter adapter2 = new SimpleAdapter(getContext(), oppo2,
                    R.layout.opp_list, new String[]{ "title","status","program"},
                    new int[]{R.id.op_name, R.id.op_status, R.id.op_prog});
            lv2.setAdapter(adapter2);
            //flag = true;
        }
    }
}


