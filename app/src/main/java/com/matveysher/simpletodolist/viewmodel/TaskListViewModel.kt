package com.matveysher.simpletodolist.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.matveysher.simpletodolist.Const.REPOSITORY
import com.matveysher.simpletodolist.model.TaskDatabase
import com.matveysher.simpletodolist.model.entities.Task
import com.matveysher.simpletodolist.model.repository.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskListViewModel(application: Application) : AndroidViewModel(application) {
    private val context = application

    fun initTaskDatabase() {
        val database = TaskDatabase.getInstance(context)
        REPOSITORY = TaskRepository(database.taskDao())
    }

    fun getAllTasks(): LiveData<List<Task>> {
        return REPOSITORY.allTasks
    }

    fun sendTaskState(taskId: Int, completed: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.updateTaskState(taskId, completed)
        }
    }
}