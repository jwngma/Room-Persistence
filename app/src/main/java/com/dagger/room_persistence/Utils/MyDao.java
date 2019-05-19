package com.dagger.room_persistence.Utils;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface MyDao {
    @Insert
    public void AddUser(User user);

    //reading

    @Query("select * from users")
    public List<User> getUsers();


    @Update
    public  void UpdateUser(User user);


    @Delete
    public  void  DeleteUser(User user);
}
