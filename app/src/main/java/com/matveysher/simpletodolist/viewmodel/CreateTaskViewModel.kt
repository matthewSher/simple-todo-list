package com.matveysher.simpletodolist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matveysher.simpletodolist.Const.REPOSITORY
import com.matveysher.simpletodolist.model.entities.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CreateTaskViewModel : ViewModel() {

    fun insert(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.insertTask(task)
        }
    }
}