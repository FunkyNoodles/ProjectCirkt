package io.github.funkynoodles.projectcirkt;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

            /*Spinner spinner = (Spinner) getView().findViewById(R.id.timeSpinner);
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                    R.array.timeArray, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);*/
        View myFragmentView = inflater.inflate(R.layout.fragment_find, container, false);
        Spinner dropdown = (Spinner)myFragmentView.findViewById(R.id.timeSpinner);
        String[] items = new String[]{"Saturn V", "2", "three"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, items);
        dropdown.setAdapter(adapter);
        return myFragmentView;

    }
}