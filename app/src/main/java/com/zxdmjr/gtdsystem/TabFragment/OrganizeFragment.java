package com.zxdmjr.gtdsystem.TabFragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;

import com.zxdmjr.gtdsystem.Adapters.ExpandableListAdapter;
import com.zxdmjr.gtdsystem.DB.DBHandler;
import com.zxdmjr.gtdsystem.Objects.Task;
import com.zxdmjr.gtdsystem.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrganizeFragment extends Fragment{

    private ExpandableListAdapter adapter;
    private ExpandableListView expListViewOrgTask;

    private List<Task> tasks;

    private List<String> lstHeaders;
    private HashMap<String, List<String>> lstHashMap;

    private Context context;

    private DBHandler handler;


    public OrganizeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_organize, container, false);

        context = getActivity().getApplicationContext();
        handler = new DBHandler(context);


        init(view);


        return view;
    }

    private void init(View view){

        //init all

        lstHeaders = new ArrayList<>();
        lstHashMap = new HashMap<>();

        //ExpandableListView
        expListViewOrgTask = (ExpandableListView) view.findViewById(R.id.expListViewOrgTask);

        getData();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("Organize","onActivityCreated");
    }

    public void getData(){

        String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        int isDone = 1;

        tasks = handler.getData(date, isDone);

        if(tasks.size() > 0) {
            for (Task task : tasks) {

                if(task != null) {
                    lstHeaders.add(task.getTask());

                    List<String> childrens = new ArrayList<>();
                    childrens.add(task.getSuggestingSolution());

                    lstHashMap.put(task.getTask(), childrens);
                }
            }

            //adapter

            adapter = new ExpandableListAdapter(context, lstHeaders, lstHashMap);
            adapter.notifyDataSetChanged();
            expListViewOrgTask.setAdapter(adapter);

            expListViewOrgTask.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    int itemType = ExpandableListView.getPackedPositionType(id);
                    int childPosition;
                    int groupPosition;
                    boolean retVal = false;

                    if ( itemType == ExpandableListView.PACKED_POSITION_TYPE_CHILD) {
                        childPosition = ExpandableListView.getPackedPositionChild(id);
                        groupPosition = ExpandableListView.getPackedPositionGroup(id);

                        //lstHashMap.remove(lstHeaders.get(groupPosition));
                        //lstHeaders.remove(groupPosition);

                        updateData(view, groupPosition);

                        //adapter.notifyDataSetChanged();

                        //do your per-item callback here
                        return retVal; //true if we consumed the click, false if not

                    } else if(itemType == ExpandableListView.PACKED_POSITION_TYPE_GROUP) {
                        groupPosition = ExpandableListView.getPackedPositionGroup(id);

//                        lstHashMap.remove(lstHeaders.get(groupPosition));
//                        lstHeaders.remove(groupPosition);
//
//                        adapter.notifyDataSetChanged();
                        updateData(view, groupPosition);

                        //do your per-group callback here
                        return retVal; //true if we consumed the click, false if not

                    } else {
                        // null item; we don't consume the click
                        return false;
                    }
                }
            });
        }

    }


    private void updateData(View view, int groupPosition){
        Task giventask = tasks.get(groupPosition); // task given Task message, Suggesting solution

        if(giventask != null) {
            Log.d("OrganizeFragment", giventask.getDate() + " " + giventask.getTask() + " " + giventask.getSuggestingSolution() + " ");


            boolean bool = handler.updateTaskData(giventask);


            if (bool) {
                Snackbar.make(view, giventask.getTask() + " is completed", Snackbar.LENGTH_SHORT).show();

            } else {
                Snackbar.make(view, giventask.getTask() + " is not updated", Snackbar.LENGTH_SHORT).show();

            }
            lstHashMap.remove(lstHeaders.get(groupPosition));
            lstHeaders.remove(groupPosition);
            tasks.remove(groupPosition);

            adapter.notifyDataSetChanged();
        }
    }
}
