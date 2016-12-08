package edu.tecii.android.proyectoportalaiesec;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {
    ListView listView;
    ArrayAdapter adapter;


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v2 = inflater.inflate(R.layout.fragment_profile, container, false);
        listView = (ListView)v2.findViewById(R.id.profile_list);

        adapter = new ProfilePostAdapter(getContext());
        listView.setAdapter(adapter);
        return v2;
    }

}
