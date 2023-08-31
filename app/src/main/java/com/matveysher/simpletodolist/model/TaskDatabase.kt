package com.matveysher.simpletodolist.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.matveysher.simpletodolist.model.dao.TaskDao
import com.matveysher.simpletodolist.model.entities.Task

@Database(entities = [Task::class], version = 1)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao

    companion object {
        private var INSTANCE: TaskDatabase? = null

        /**
         * Static method, which creates only one instance of [TaskDatabase] class
         */
        @Synchronized
        fun getInstance(context: Context): TaskDatabase {
            return if (INSTANCE != null) INSTANCE as TaskDatabase
            else {
                INSTANCE = Room.databaseBuilder(context, TaskDatabase::class.java, "task_database")
                    .build()
                INSTANCE as TaskDatabase
            }
        }
    }
}