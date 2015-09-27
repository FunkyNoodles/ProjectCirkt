package io.github.funkynoodles.projectcirkt;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

/**
 * Created by Louis on 9/14/2015.
 * contains contents for "Find" tab
 */
public class FindFragment extends Fragment {

    //public ArrayList<String> listViewArray = new ArrayList<String>();
    NearByClasses nbc = new NearByClasses();

    public static FindFragment newInstance(){
        FindFragment fragment = new FindFragment();

        return fragment;
    }
    private HandleXML obj;
    Button findBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myFragmentView = inflater.inflate(R.layout.fragment_find, container, false);
        Spinner spinner = (Spinner) myFragmentView.findViewById(R.id.timeSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.timeArray, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        findBtn = (Button)myFragmentView.findViewById(R.id.findButton);
        findBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                String testURL = "http://courses.illinois.edu/cisapp/explorer/schedule/2015/fall/CS/225/35917.xml";
                obj = new HandleXML(testURL);
                obj.fetchXML();
                while(obj.parsingComplete);
                NearByClasses.listViewArrayList.clear();
                NearByClasses.listViewArrayList.add(obj.getCourseName() + ", " + obj.getSubjectName());
                NearByClasses.listViewArrayList.add(obj.getSubjectID() + " " + obj.getCourseID());
                NearByClasses.listViewArrayList.add(obj.getDescription());
                NearByClasses.listViewArrayList.add(obj.getSectionNumber());
                NearByClasses.listViewArrayList.add(obj.getSectionNotes());
                NearByClasses.listViewArrayList.add(obj.getStatusCode());
                NearByClasses.listViewArrayList.add(obj.getStartDate());
                NearByClasses.listViewArrayList.add(obj.getEndDate());
                NearByClasses.listViewArrayList.add(obj.getMeetingType());
                NearByClasses.listViewArrayList.add(obj.getMeetingStart());
                NearByClasses.listViewArrayList.add(obj.getMeetingEnd());
                NearByClasses.listViewArrayList.add(obj.getDaysOfTheWeek());
                NearByClasses.listViewArrayList.add(obj.getBuildingName() + " " + obj.getRoomNumber());
                for(int loop1=0;loop1<obj.getInstructors().size();loop1++){
                    NearByClasses.listViewArrayList.add(obj.getInstructors().get(loop1));
                }

                Intent intent = new Intent(v.getContext(),NearByClasses.class);
                startActivity(intent);
            }
        });
        return myFragmentView;
    }

    /**
     * Called when Find button is pressed
     */
    public void findClasses(View view){

    }
}