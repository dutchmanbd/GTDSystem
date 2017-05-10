package com.zxdmjr.gtdsystem.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.zxdmjr.gtdsystem.Objects.AppConfig;
import com.zxdmjr.gtdsystem.Objects.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dutchman on 3/28/17.
 */

public class DBHandler extends SQLiteOpenHelper{


    public DBHandler(Context context) {
        super(context, AppConfig.DB.NAME, null, AppConfig.DB.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(AppConfig.DB.TB.DAILYTASK.CREATE_TB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(AppConfig.DB.TB.DAILYTASK.DROP_TB);

        onCreate(db);
    }

    // Insert Data on Table

    public boolean isInsertData(SQLiteDatabase db, Task task){

        long res = 0;
        int isDone = 1;

        ContentValues values = new ContentValues();

        values.put(AppConfig.DB.TB.DAILYTASK.KEY_DATE, task.getDate());                             //Date
        values.put(AppConfig.DB.TB.DAILYTASK.KEY_TIME, task.getTime());                             //Time
        values.put(AppConfig.DB.TB.DAILYTASK.KEY_TASK, task.getTask());                             //TASK
        values.put(AppConfig.DB.TB.DAILYTASK.KEY_SSOLUTOIN, task.getSuggestingSolution());          //Suggesting Solution
        values.put(AppConfig.DB.TB.DAILYTASK.KEY_IS_DONE, isDone);

        // Inserting Row
        res = db.insert(AppConfig.DB.TB.DAILYTASK.NAME, null, values);

        return res > 0;
    }

    public boolean isInsertedAllData(List<Task> tasks){

        SQLiteDatabase db = this.getWritableDatabase();

        boolean isInsert = false;

        for(Task task : tasks){

            isInsert = isInsertData(db, task);

            if(!isInsert)
                break;
        }

        db.close();

        return isInsert;
    }

    public List<Task> getData(String date, int isDone){

        List<Task> tasks = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(AppConfig.DB.TB.DAILYTASK.NAME, new String[] {
                        AppConfig.DB.TB.DAILYTASK.KEY_TASK, AppConfig.DB.TB.DAILYTASK.KEY_SSOLUTOIN }, AppConfig.DB.TB.DAILYTASK.KEY_DATE + " = ? and "+AppConfig.DB.TB.DAILYTASK.KEY_IS_DONE +" = ?",
                new String[] { date, String.valueOf(isDone) }, null, null, null, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                String taskMessage = cursor.getString(0);
                String suggestingSolution = cursor.getString(1);

                Task task = new Task(taskMessage, suggestingSolution);

                // Adding contact to list
                tasks.add(task);
            } while (cursor.moveToNext());
        }
        db.close();
        cursor.close();

        // return contact list
        return tasks;
    }

    public Task getTaskData(Task givenTask){

        Task task = null;

        String isDone = "1";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(AppConfig.DB.TB.DAILYTASK.SELECT_TASK_DATA,new String[]{givenTask.getTask(), givenTask.getSuggestingSolution(), isDone});

        if (cursor.moveToFirst()) {

            // 0 id
            String date = cursor.getString(1); // 1 date
            String time = cursor.getString(2); // 2 time
            String taskmsg = cursor.getString(3); // 3 task
            String ssoluiton = cursor.getString(4); // 4 suggesting solution


            task = new Task(date,time,taskmsg, ssoluiton);

        }
        db.close();
        cursor.close();

        return task;

    }


    public boolean updateTaskData(Task giventask){

        Log.d("OrganizeFragment", "In DB");
        long res = 0;
        Task task = getTaskData(giventask);

        if(task != null) {

            SQLiteDatabase db = this.getWritableDatabase();
            int isDone = 0;

            ContentValues values = new ContentValues();

            values.put(AppConfig.DB.TB.DAILYTASK.KEY_DATE, task.getDate());                             //Date
            values.put(AppConfig.DB.TB.DAILYTASK.KEY_TIME, task.getTime());                             //Time
            values.put(AppConfig.DB.TB.DAILYTASK.KEY_TASK, task.getTask());                             //TASK
            values.put(AppConfig.DB.TB.DAILYTASK.KEY_SSOLUTOIN, task.getSuggestingSolution());          //Suggesting Solution
            values.put(AppConfig.DB.TB.DAILYTASK.KEY_IS_DONE, isDone);


            res = db.update(AppConfig.DB.TB.DAILYTASK.NAME, values, AppConfig.DB.TB.DAILYTASK.KEY_DATE + " = ? AND " + AppConfig.DB.TB.DAILYTASK.KEY_TIME + " = ? AND " + AppConfig.DB.TB.DAILYTASK.KEY_TASK + " = ? AND " + AppConfig.DB.TB.DAILYTASK.KEY_SSOLUTOIN + " = ?", new String[]{task.getDate(), task.getTime(), task.getTask(), task.getSuggestingSolution()});

            db.close();
        }

        return res > 0;
    }



}
