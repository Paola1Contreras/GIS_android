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
            String url= "https://gis-api.aiesec.org/v2/programmes.json?access_token=c1634c94ff71b652fe896c3c18cdefff2b4053b1c0cbadd548a9030008073141";
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
                        Log.e(TAG, "CACA "+ id);
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
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            ListAdapter adapter = new SimpleAdapter(getContext(), oppo,
                    R.layout.list_view, new String[]{ "short_name","consumer_name"},
                    new int[]{R.id.short_name, R.id.consumer_name});
            lv.setAdapter(adapter);
            
        }
    }
}
