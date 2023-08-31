package com.matveysher.simpletodolist.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.matveysher.simpletodolist.Const.REPOSITORY
import com.matveysher.simpletodolist.model.TaskDatabase
import com.matveysher.simpletodolist.model.entities.Task
import com.matveysher.simpletodolist.model.repository.TaskRepository

class TaskListViewModel(application: Application) : AndroidViewModel(application) {
    private val context = application

    fun initTaskDatabase() {
        val database = TaskDatabase.getInstance(context)
        REPOSITORY = TaskRepository(database.taskDao())
    }

    fun getAllTasks(): LiveData<List<Task>> {
        return REPOSITORY.allTasks
    }
}