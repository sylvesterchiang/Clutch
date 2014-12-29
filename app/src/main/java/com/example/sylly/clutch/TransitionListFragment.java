package com.example.sylly.clutch;

import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;

import com.example.sylly.clutch.menu.ResideMenu;
import com.example.sylly.clutch.utils.UISwipableList;
import com.example.sylly.clutch.menu.ResideMenu;
import com.example.sylly.clutch.menu.ResideMenuItem;
import com.parse.FindCallback;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Sylly on 2014-12-26.
 */
public class TransitionListFragment extends Fragment {

    //Views & Widgets
    private View parentView;
    private UISwipableList listView;
    private EventListAdapter mAdapter;
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
        mAdapter = new EventListAdapter(getActivity(), getEventData());
        listView.setActionLayout(R.id.hidden_view);
        listView.setItemLayout(R.id.front_layout);
        listView.setAdapter(mAdapter);
        listView.setIgnoredViewHandler(resideMenu);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View viewa, int i, long l) {
                //ListItem item = (ListItem) listView.getAdapter().getItem(i);

                /*
                //Intent intent = new Intent(getActivity(), TransitionDetailActivity.class);

                Bundle bundle = new Bundle();
                bundle.putString("title", item.getTitle());
                bundle.putInt("img", item.getImageId());
                bundle.putString("descr", item.getDesc());

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
                */

                changeFragment(new PersonListFragment());
            }
        });
    }

    private void sendData(){

            Event post = new Event();

            post.setName("Please Work");
            post.setDate(new Date());
            ParseACL acl = new ParseACL();
            acl.setPublicReadAccess(true);
            post.setACL(acl);

            post.saveInBackground(new SaveCallback(){
                @Override
                public void done(ParseException e){
                }
            });
    }

    private ArrayList<Event> getEventData(){

        final ArrayList<Event> eventData = new ArrayList<Event>();

        ParseQuery<Event> query = Event.getQuery();
        query.findInBackground(new FindCallback<Event>() {
            public void done(List<Event> objects, com.parse.ParseException e) {
            if (e == null){
                for (int i = 0; i < objects.size(); i++){
                    Event temp = new Event();
                    temp.setName(objects.get(i).getName());
                    temp.setDate(objects.get(i).getDate());
                    eventData.add(temp);
                }
            }
            }
        });

        return eventData;
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