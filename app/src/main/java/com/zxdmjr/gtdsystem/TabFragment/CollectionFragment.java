package com.zxdmjr.gtdsystem.TabFragment;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.zxdmjr.gtdsystem.Adapters.ExpandableListAdapter;
import com.zxdmjr.gtdsystem.DB.DBHandler;
import com.zxdmjr.gtdsystem.MainActivity;
import com.zxdmjr.gtdsystem.Objects.AppConfig;
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
public class CollectionFragment extends Fragment implements View.OnClickListener {

    private OnDataUpdateListener mCallback;

    private Button btnAdd;
    private Button btnClear;
    private Button btnSave;
    private Button btnClearAll;

    private EditText etTask;
    private EditText etSuggestingSolution;

    private List<Task> tasks;


    private ExpandableListView expListViewTask;
    private ExpandableListAdapter adapter;
    private List<String> lstHeaders;
    private List<String> childrens;
    private HashMap<String, List<String>> lstHashMap;

    private Context context;

    private DBHandler handler;

    public CollectionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_collection, container, false);

        context = getActivity().getApplicationContext();

        handler = new DBHandler(context);

        init(view);

        return view;
    }

    private void init(View view){

        tasks = new ArrayList<>();
        lstHashMap = new HashMap<>();
        lstHeaders = new ArrayList<>();

        // EditText

        etTask               = (EditText) view.findViewById(R.id.etTask);
        etSuggestingSolution = (EditText) view.findViewById(R.id.etSuggestingSolution);

        //Button
        btnAdd   = (Button) view.findViewById(R.id.btnAdd);
        btnClear = (Button) view.findViewById(R.id.btnClear);

        btnSave  = (Button) view.findViewById(R.id.btnSave);
        btnClearAll = (Button) view.findViewById(R.id.btnClearAll);

        //ExpandableListView
        expListViewTask = (ExpandableListView) view.findViewById(R.id.expListViewTask);


        //Action

        btnAdd.setOnClickListener(this);
        btnClear.setOnClickListener(this);

        btnSave.setOnClickListener(this);
        btnClearAll.setOnClickListener(this);

    }

    private Task getTask(){

        Task task;

        String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        String time = new SimpleDateFormat("hh:mm").format(new Date());

        String taskMessage = etTask.getText().toString().trim();
        String suggestingSolution = etSuggestingSolution.getText().toString().trim();

        task = new Task(date, time, taskMessage, suggestingSolution);

        return task;

    }

    private String getTaskMessage(){

        return etTask.getText().toString().trim();
    }

    private String getSuggestingSolution(){

        return etSuggestingSolution.getText().toString().trim();
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);


        if(mCallback == null)
            mCallback = (OnDataUpdateListener) activity;

    }

    @Override
    public void onClick(View v) {

        int id = v.getId();

        switch (id){

            case R.id.btnAdd:

                addDataInListView();

                break;

            case R.id.btnClear:

                clearData(v);

                break;

            case R.id.btnSave:

                if(isDataInserted()){

                    lstHashMap.clear();
                    lstHeaders.clear();
                    tasks.clear();

                    adapter.notifyDataSetChanged();

                    mCallback.onDataUpdate();

                    Snackbar.make(v, "Successfully Inserted", Snackbar.LENGTH_SHORT).show();
                } else{
                    Snackbar.make(v, "Data can not inserted", Snackbar.LENGTH_SHORT).show();
                }
                break;

            case R.id.btnClearAll:

                clearAllData(v);

                break;
        }

    }

    private boolean isDataInserted(){

        if(tasks != null && tasks.size() > 0)
            return handler.isInsertedAllData(tasks);
        else
            return false;
    }

    private void clearData(){

        etTask.setText("");
        etSuggestingSolution.setText("");
        etTask.requestFocus();
        etSuggestingSolution.clearFocus();
    }

    private void clearData(View v){
        if(etTask.getText().toString().trim().length() > 0){

            if(etSuggestingSolution.getText().toString().trim().length() > 0){

                etTask.setText("");
                etSuggestingSolution.setText("");
                Snackbar.make(v, "ALL data cleared", Snackbar.LENGTH_SHORT).show();
                etTask.requestFocus();
                etSuggestingSolution.clearFocus();
            } else {
                etTask.setText("");
                etSuggestingSolution.setText("");
                Snackbar.make(v, "Task cleared", Snackbar.LENGTH_SHORT).show();
                etTask.requestFocus();
            }
        } else{

            if(etSuggestingSolution.getText().toString().trim().length() > 0){

                etTask.setText("");
                etSuggestingSolution.setText("");
                Snackbar.make(v, "SuggestingSolution cleared", Snackbar.LENGTH_SHORT).show();
            } else{
                etTask.setText("");
                etSuggestingSolution.setText("");
                Snackbar.make(v, "Data already cleared", Snackbar.LENGTH_SHORT).show();
            }

        }
    }

    private void clearAllData(View v){

        if(etTask.getText().toString().trim().length() > 0){

            etTask.setText("");
            if(etSuggestingSolution.getText().toString().trim().length() > 0) {

                etSuggestingSolution.setText("");
                if (lstHashMap.size() > 0 && lstHeaders.size() > 0 && tasks.size() > 0) {
                    lstHashMap.clear();
                    lstHeaders.clear();
                    tasks.clear();

                    adapter.notifyDataSetChanged();

                    Snackbar.make(v, "ALL data cleared", Snackbar.LENGTH_SHORT).show();
                }
            }
            if(lstHashMap.size() > 0 && lstHeaders.size() > 0 && tasks.size() > 0) {
                lstHashMap.clear();
                lstHeaders.clear();
                tasks.clear();

                adapter.notifyDataSetChanged();

                Snackbar.make(v, "ALL data cleared", Snackbar.LENGTH_SHORT).show();
            }

        } else{
            if(etSuggestingSolution.getText().toString().trim().length() > 0) {

                etSuggestingSolution.setText("");
                if (lstHashMap.size() > 0 && lstHeaders.size() > 0 && tasks.size() > 0) {
                    lstHashMap.clear();
                    lstHeaders.clear();
                    tasks.clear();

                    adapter.notifyDataSetChanged();

                    Snackbar.make(v, "ALL data cleared", Snackbar.LENGTH_SHORT).show();
                }
            }
        }
    }


    private void addDataInListView(){

        if(getTaskMessage().length() > 0){

            if(getSuggestingSolution().length() > 0){

                //Ready Task for insert
                tasks.add(getTask());

                //headers
                lstHeaders.add(getTaskMessage());

                //Childrens
                childrens = new ArrayList<>();
                childrens.add(etSuggestingSolution.getText().toString());

                //All data
                lstHashMap.put(etTask.getText().toString(), childrens);

                //Adapter
                adapter = new ExpandableListAdapter(context, lstHeaders, lstHashMap);
                adapter.notifyDataSetChanged();
                expListViewTask.setAdapter(adapter);

                expListViewTask.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                        int itemType = ExpandableListView.getPackedPositionType(id);
                        int childPosition;
                        int groupPosition;
                        boolean retVal = false;

                        if ( itemType == ExpandableListView.PACKED_POSITION_TYPE_CHILD) {
                            childPosition = ExpandableListView.getPackedPositionChild(id);
                            groupPosition = ExpandableListView.getPackedPositionGroup(id);

                            lstHashMap.remove(lstHeaders.get(groupPosition));
                            lstHeaders.remove(groupPosition);

                            tasks.remove(groupPosition);

                            adapter.notifyDataSetChanged();

                            //do your per-item callback here
                            return retVal; //true if we consumed the click, false if not

                        } else if(itemType == ExpandableListView.PACKED_POSITION_TYPE_GROUP) {
                            groupPosition = ExpandableListView.getPackedPositionGroup(id);

                            lstHashMap.remove(lstHeaders.get(groupPosition));
                            lstHeaders.remove(groupPosition);

                            tasks.remove(groupPosition);

                            adapter.notifyDataSetChanged();

                            //do your per-group callback here
                            return retVal; //true if we consumed the click, false if not

                        } else {
                            // null item; we don't consume the click
                            return false;
                        }
                    }
                });

                clearData();

            } else{
                etSuggestingSolution.requestFocus();
                etTask.clearFocus();
                etSuggestingSolution.setError("Enter your Suggesting Solution for "+etTask.getText().toString());
            }

        } else{
            etTask.requestFocus();
            etSuggestingSolution.clearFocus();
            etTask.setError("Enter your task");
        }
    }


    public interface OnDataUpdateListener{

        public void onDataUpdate();

    }

}
