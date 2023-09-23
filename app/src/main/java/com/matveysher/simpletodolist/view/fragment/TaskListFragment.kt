package com.matveysher.simpletodolist.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.matveysher.simpletodolist.Const.APP
import com.matveysher.simpletodolist.R
import com.matveysher.simpletodolist.databinding.FragmentTaskListBinding
import com.matveysher.simpletodolist.view.adapter.TaskListAdapter
import com.matveysher.simpletodolist.viewmodel.TaskListViewModel

class TaskListFragment : Fragment() {

    private lateinit var _binding: FragmentTaskListBinding
    private val binding: FragmentTaskListBinding get() = _binding

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TaskListAdapter
    private lateinit var taskStateQuery: (Int, Boolean) -> Unit

    private val viewModel: TaskListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTaskListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /**
        * Initializing TaskDatabase
        */
        viewModel.initTaskDatabase()

        /**
         * Sending task state changes to DB
         */
        taskStateQuery = { taskId, isCompleted -> viewModel.sendTaskState(taskId, isCompleted) }

        /**
         * Initializing task list
         */
        recyclerView = binding.taskList
        adapter = TaskListAdapter(taskStateQuery)
        recyclerView.adapter = adapter


        /**
         * Observing task list changes and refreshing it in [TaskListAdapter]
         */
        viewModel.getAllTasks().observe(viewLifecycleOwner) { taskList ->
            adapter.updateData(taskList.asReversed())
        }

        /**
         * Opens the BottomSheetDialog with [CreateTaskFragment]
         */
        binding.addTaskButton.setOnClickListener {
            APP.navController.navigate(R.id.action_taskListFragment_to_createTaskFragment)
        }
    }
}