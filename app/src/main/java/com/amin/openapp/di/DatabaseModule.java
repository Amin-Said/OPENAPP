package com.amin.openapp.di;

import android.app.Application;

import androidx.room.Room;

import com.amin.openapp.db.TeamDB;
import com.amin.openapp.db.TeamDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

@InstallIn(ApplicationComponent.class)
@Module
public class DatabaseModule {

    @Provides
    @Singleton
    public static TeamDB provideDB(Application application){
        return Room.databaseBuilder(application,TeamDB.class,"fav_team")
                .fallbackToDestructiveMigration().allowMainThreadQueries().build();
    }

    @Provides
    @Singleton
    public static TeamDao provideDao(TeamDB teamDB){
        return teamDB.teamDao();
    }
}
