package com.example.sylly.clutch;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.example.sylly.clutch.Event;
import com.example.sylly.clutch.EventActivity;
import com.example.sylly.clutch.PersonListAdapter;
import com.example.sylly.clutch.ListItem;
import com.example.sylly.clutch.R;
import com.example.sylly.clutch.menu.ResideMenu;
import com.example.sylly.clutch.utils.UISwipableList;
import com.parse.FindCallback;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PersonListFragment extends Fragment {

    //Views & Widgets
    private View parentView;
    private UISwipableList listView;
    private PersonListAdapter mAdapter;
    private ResideMenu resideMenu;

    //Vars
    private String PACKAGE = "IDENTIFY";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.fragment_list_transition, container, false);
        listView   = (UISwipableList) parentView.findViewById(R.id.listView);
        EventActivity parentActivity = (EventActivity) getActivity();
        resideMenu = parentActivity.getResideMenu();
        initView();
        return parentView;
    }

    private void initView(){
        mAdapter = new PersonListAdapter(getActivity(), getPersonData());
        listView.setActionLayout(R.id.hidden_view);
        listView.setItemLayout(R.id.front_layout);
        listView.setAdapter(mAdapter);
        listView.setIgnoredViewHandler(resideMenu);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View viewa, int i, long l) {
                Person item = (Person) listView.getAdapter().getItem(i);

                Intent intent = new Intent(getActivity(), ProfileActivity.class);

                Bundle bundle = new Bundle();
                bundle.putString("firstName", item.getFirstName());
                bundle.putString("lastName", item.getLastName());

                int[] screen_location = new int[2];
                View view = viewa.findViewById(R.id.item_image);
                view.getLocationOnScreen(screen_location);

                bundle.putInt(PACKAGE + ".left", screen_location[0]);
                bundle.putInt(PACKAGE + ".top", screen_location[1]);
                bundle.putInt(PACKAGE + ".width", view.getWidth());
                bundle.putInt(PACKAGE + ".height", view.getHeight());

                //intent.putExtras(bundle);

                //startActivity(intent);
                getActivity().overridePendingTransition(0, 0);
            }
        });
    }

    private ArrayList<Person> getPersonData(){

        final ArrayList<Person> personData = new ArrayList<Person>();

        ParseQuery<Person> query = Person.getQuery();
        query.findInBackground(new FindCallback<Person>() {
            public void done(List<Person> objects, com.parse.ParseException e) {
                if (e == null){
                    for (int i = 0; i < objects.size(); i++){
                        Person temp = new Person();
                        temp.setFirstName(objects.get(i).getFirstName());
                        temp.setLastName(objects.get(i).getLastName());
                        personData.add(temp);
                    }
                }
            }
        });

        return personData;
    }

    private ArrayList<ListItem> getListData(){
        ArrayList<ListItem> listData = new ArrayList<ListItem>();
        listData.add(new ListItem(R.drawable.ph_1, "Henry Smith", "Vacation!", null, null));
        listData.add(new ListItem(R.drawable.ph_2, "Martinez", "Still exited from my trip last week!", null, null));
        listData.add(new ListItem(R.drawable.ph_3, "Olivier Smith", "Visiting Canada next week!", null, null));
        listData.add(new ListItem(R.drawable.ph_4, "Aria Thompson", "Can not go shopping tomorrow :(", null, null));
        listData.add(new ListItem(R.drawable.ph_5, "Sophie Hill", "Live every day like it is the last one!", null, null));
        listData.add(new ListItem(R.drawable.ph_6, "Addison Adams", "Not available, working...", null, null));
        listData.add(new ListItem(R.drawable.ph_7, "Harper Clark", "Whats up?", null, null));
        listData.add(new ListItem(R.drawable.ph_8, "Micheal Green", "Guess who has to work? Pfff..", null, null));
        listData.add(new ListItem(R.drawable.ph_9, "Benjamin Lewis", "Playing games all week", null, null));
        listData.add(new ListItem(R.drawable.ph_10, "Luke Wilson", "Anybody got any plans for this weekend?", null, null));
        listData.add(new ListItem(R.drawable.ph_11, "Daniel Moore", "Going to the movies, so do not call me :)", null, null));
        listData.add(new ListItem(R.drawable.ph_12, "Ella Smith", "Going on a trip with the family next week!", null, null));
        return listData;
    }

    private void changeFragment(Fragment targetFragment){
        resideMenu.clearIgnoredViewList();
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment, targetFragment, "fragment")
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }
}