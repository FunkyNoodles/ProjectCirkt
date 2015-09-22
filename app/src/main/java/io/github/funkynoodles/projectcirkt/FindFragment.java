package io.github.funkynoodles.projectcirkt;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

/**
 * Created by Louis on 9/14/2015.
 * contains contents for "Find" tab
 */
public class FindFragment extends Fragment {

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
                System.out.println(obj.getSubjectID());
                System.out.println(obj.getSubjectName());
                System.out.println(obj.getCourseID());
                System.out.println(obj.getCourseName());
                System.out.println(obj.getDescription());
                System.out.println(obj.getSectionNumber());
                System.out.println(obj.getStatusCode());
                System.out.println(obj.getSectionNotes());
                System.out.println(obj.getStartDate());
                System.out.println(obj.getEndDate());
                System.out.println(obj.getMeetingType());
                System.out.println(obj.getMeetingStart());
                System.out.println(obj.getMeetingEnd());
                System.out.println(obj.getDaysOfTheWeek());
                System.out.println(obj.getRoomNumber());
                System.out.println(obj.getBuildingName());
                System.out.println(obj.getInstructors());
            }
        });
        return myFragmentView;
    }
}