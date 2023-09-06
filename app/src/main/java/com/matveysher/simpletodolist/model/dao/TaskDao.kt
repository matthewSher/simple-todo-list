package com.matveysher.simpletodolist.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.matveysher.simpletodolist.model.entities.Task

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(task: Task)

    @Delete
    suspend fun delete(task: Task)

    @Query("UPDATE tasks SET task_completed = :isCompleted WHERE id = :taskId")
    suspend fun updateTaskState(taskId: Int, isCompleted: Boolean)

    @Query("SELECT * FROM tasks")
    fun getAllTasks(): LiveData<List<Task>>
}