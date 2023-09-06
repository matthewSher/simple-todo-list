package com.matveysher.simpletodolist.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.matveysher.simpletodolist.R
import com.matveysher.simpletodolist.databinding.TaskListItemBinding
import com.matveysher.simpletodolist.model.entities.Task
class TaskListAdapter(private val taskStateQuery: (Int, Boolean) -> Unit) :
    RecyclerView.Adapter<TaskListAdapter.TaskViewHolder>() {

    private var taskList = emptyList<Task>()

    class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding: TaskListItemBinding = TaskListItemBinding.bind(view)

        /**
         * Binding views of task_list_item.xml
         */
        fun bind(task: Task, sendTaskState: (Int, Boolean) -> Unit) {
            binding.apply {
                taskTitle.text = task.title
                taskCompletedCheckbox.isChecked = task.isCompleted
                taskCompletedCheckbox.setOnCheckedChangeListener { _, isChecked ->
                    sendTaskState(task.id, isChecked)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.task_list_item, parent, false)
        return TaskViewHolder(view)
    }

    override fun getItemCount(): Int = taskList.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(taskList[position], taskStateQuery)
    }

    /**
     * Refreshing [taskList] when it changes
     */
    // TODO: change notifyDataSetChanged() to DiffUtil
    @SuppressLint("NotifyDataSetChanged")
    fun setList(listOfTasks: List<Task>) {
        taskList = listOfTasks
        notifyDataSetChanged()
    }
}