package com.zxdmjr.gtdsystem.Objects;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dutchman on 3/28/17.
 */

public class AppConfig {


    public static class DB{

        public static final String NAME = "gtdsystem.db";
        public static final int VERSION = 1;

        public static class TB{

            public static class DAILYTASK{

                public static final String NAME = "dailytaskinfo";

                public static final String KEY_ID = "id";
                public static final String KEY_DATE = "date";
                public static final String KEY_TIME = "time";
                public static final String KEY_TASK = "task";
                public static final String KEY_SSOLUTOIN = "suggesting_sultion";
                public static final String KEY_IS_DONE   = "is_done";

                public static final String CREATE_TB = "CREATE TABLE " +
                                                        NAME+ " ("+
                                                        KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT," +
                                                        KEY_DATE+" TEXT NOT NULL," +
                                                        KEY_TIME+" TEXT NOT NULL, " +
                                                        KEY_TASK+" TEXT NOT NULL, " +
                                                        KEY_SSOLUTOIN+" TEXT NOT NULL,"+
                                                        KEY_IS_DONE+" INTEGER NOT NULL "+")";

                public static final String DROP_TB = "DROP TABLE IF EXISTS "+NAME;

                public static final String SQL_INSERT = "insert info "+NAME+ "( "+ KEY_DATE+","+ KEY_TIME+","+ KEY_TASK+","+ KEY_SSOLUTOIN+","+ KEY_IS_DONE+")"+
                                                            " values(?,?,?,?,?)";

                public static final String SELECT_TASK_DATA = "SELECT * FROM "+NAME+" WHERE "+KEY_TASK+" = ? AND "+KEY_SSOLUTOIN+" = ? AND "+KEY_IS_DONE +" = ?";



            }

        }

    }

}
