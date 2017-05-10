package com.zxdmjr.gtdsystem.Objects;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;

/**
 * Created by dutchman on 3/29/17.
 */

public class CustomDailog extends Dialog{

    private Context context;


    public CustomDailog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);



    }
}
