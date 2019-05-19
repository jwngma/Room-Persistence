package com.dagger.room_persistence.PracticePackage;

import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;


@android.arch.persistence.room.Dao
public interface Dao {
    @Insert
    public  void AddUser(User user);


    @Query("select * from userss")
    public List<User> getUsers();



}
