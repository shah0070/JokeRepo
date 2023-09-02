package com.example.joke.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Created by Md Shah Hussain on 02/09/23.
 */
@Database(entities = arrayOf(JokeTableModel::class), version = 1, exportSchema = false)
abstract class JokeDatabase : RoomDatabase() {

    abstract fun loginDao() : DAOAccess

    companion object {

        @Volatile
        private var INSTANCE: JokeDatabase? = null

        fun getDatabaseClient(context: Context) : JokeDatabase {

            if (INSTANCE != null) return INSTANCE!!

            synchronized(this) {

                INSTANCE = Room
                    .databaseBuilder(context, JokeDatabase::class.java, "JOKE_DATABASE")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()

                return INSTANCE!!

            }
        }

    }

}