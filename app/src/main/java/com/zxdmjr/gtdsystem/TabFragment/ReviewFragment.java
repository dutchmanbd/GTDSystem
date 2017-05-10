package com.zxdmjr.gtdsystem.TabFragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zxdmjr.gtdsystem.Objects.AppConfig;
import com.zxdmjr.gtdsystem.Objects.Task;
import com.zxdmjr.gtdsystem.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReviewFragment extends Fragment{



    public ReviewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_review, container, false);
    }


}
