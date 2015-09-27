package io.github.funkynoodles.projectcirkt;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class NearByClasses extends ActionBarActivity {

    public static ArrayList<String> listViewArrayList = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_near_by_classes);
        Intent intent = getIntent();
        String[] listViewArray = new String[listViewArrayList.size()];//{"1","34df","1dsfosf"};//
        for(int loop1=0;loop1<listViewArrayList.size();loop1++){
            if(listViewArrayList.get(loop1)==null){
                listViewArrayList.remove(loop1);
            }
            //listViewArray[loop1] = listViewArrayList.get(loop1);
        }
        ArrayAdapter findListViewAdapter = new ArrayAdapter<String>(this, R.layout.activity_find_list_view, listViewArrayList);

        ListView findListView = (ListView)findViewById(R.id.findListView);
        findListView.setAdapter(findListViewAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_near_by_classes, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
