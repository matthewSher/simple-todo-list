package com.matveysher.simpletodolist.model.repository

import androidx.lifecycle.LiveData
import com.matveysher.simpletodolist.model.dao.TaskDao
import com.matveysher.simpletodolist.model.entities.Task

class TaskRepository(private val taskDao: TaskDao) {

    val allTasks: LiveData<List<Task>> = taskDao.getAllTasks()

    suspend fun insertTask(task: Task) {
        taskDao.insert(task)
    }

    suspend fun deleteTask(task: Task) {
        taskDao.delete(task)
    }

    suspend fun updateTaskIsCompleted(taskId: Int, isCompleted: Boolean) {
        taskDao.updateTaskIsCompleted(taskId, isCompleted)
    }
}