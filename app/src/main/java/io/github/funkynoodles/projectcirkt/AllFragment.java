package io.github.funkynoodles.projectcirkt;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Louis on 9/26/2015.
 */
public class AllFragment extends Fragment {

    public static AllFragment newInstance(){
        AllFragment fragment = new AllFragment();

        return fragment;
    }
    private HandleXML hXML;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View myFragmentView = inflater.inflate(R.layout.fragment_all, container, false);
        String testString = "http://courses.illinois.edu/cisapp/explorer/schedule.xml";
        hXML = new HandleXML(testString);
        hXML.fetchXML();
        while(hXML.parsingComplete);

        ArrayList<String> allListViewArrayList = hXML.getCalendarYears();
        ArrayAdapter<String> allListViewAdapter = new ArrayAdapter<String>(getActivity(), R.layout.activity_all_list_view, allListViewArrayList);
        ListView allListView = (ListView)(myFragmentView.findViewById(R.id.allListView));
        allListView.setAdapter(allListViewAdapter);

        allListView.setClickable(true);
        allListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3){
                Intent intent = new Intent(arg0.getContext(),NearByClasses.class);
                startActivity(intent);
                //getActivity().overridePendingTransition(R.animator.page_enter_animation, R.animator.page_exit_animation);
            }
        });
        return myFragmentView;
    }
}
