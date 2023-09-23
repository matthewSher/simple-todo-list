package com.matveysher.simpletodolist.view.adapter.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.matveysher.simpletodolist.model.entities.Task

class TaskListDataDiffCallback(
    private val oldList: List<Task>,
    private val newList: List<Task>
) : DiffUtil.Callback()  {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].title == newList[newItemPosition].title
    }

}