package com.example.itubeapplication.user;

import androidx.room.Database;
import androidx.room.RoomDatabase;
// extends from room database, this is an abstract class

// declare entity user object with first version
@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class UserDataBase extends RoomDatabase{

    //get user data
    public abstract UserDAO getUserDao();
}
