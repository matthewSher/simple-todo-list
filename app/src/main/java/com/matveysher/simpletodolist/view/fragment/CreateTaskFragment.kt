package com.matveysher.simpletodolist.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.matveysher.simpletodolist.Const.APP
import com.matveysher.simpletodolist.R
import com.matveysher.simpletodolist.databinding.FragmentCreateTaskBinding
import com.matveysher.simpletodolist.model.entities.Task
import com.matveysher.simpletodolist.viewmodel.CreateTaskViewModel

class CreateTaskFragment : BottomSheetDialogFragment() {

    companion object {
        private const val TASK_TITLE_MAX_LENGTH = 120
    }

    private var _binding: FragmentCreateTaskBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CreateTaskViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreateTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /**
         * Task title EditText setting up
         */
        with(binding.taskTitleEditText) {
            isCounterEnabled = true
            counterMaxLength = TASK_TITLE_MAX_LENGTH
            editText?.doOnTextChanged { inputText, _, _, _ ->
                if (inputText.isNullOrEmpty()) {
                    this.error = getString(R.string.task_title_et_error_text)
                    binding.createTaskButton.isEnabled = false
                } else {
                    this.error = null
                    binding.createTaskButton.isEnabled = true
                }
            }
        }

        binding.createTaskButton.setOnClickListener {
            viewModel.insert(Task(
                title = binding.taskTitleEditText.editText?.text.toString(),
                description = binding.taskDescriptionEditText.editText?.text.toString()
            ))
            APP.navController.navigateUp()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}