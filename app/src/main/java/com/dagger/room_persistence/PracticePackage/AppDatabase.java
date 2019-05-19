package com.dagger.room_persistence.PracticePackage;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;


@Database(entities ={User.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public  abstract Dao dao();
}
