package com.example.taskmate

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class TaskViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(TaskUiState())
    val uiState: StateFlow<TaskUiState> = _uiState.asStateFlow()

    fun onTitleChange(value: String) {
        _uiState.update {
            it.copy(titleInput = value, errorMessage = "")
        }
    }

    fun onCourseChange(value: String) {
        _uiState.update {
            it.copy(courseInput = value, errorMessage = "")
        }
    }

    fun addTask() {
        val state = _uiState.value
        val title = state.titleInput.trim()
        val course = state.courseInput.trim()

        if (title.isEmpty() || course.isEmpty()) {
            _uiState.update {
                it.copy(errorMessage = "Nama tugas dan mata kuliah harus diisi.")
            }
            return
        }

        val newTask = Task(
            id = System.currentTimeMillis(),
            title = title,
            course = course
        )

        _uiState.update {
            it.copy(
                tasks = it.tasks + newTask,
                titleInput = "",
                courseInput = "",
                errorMessage = ""
            )
        }
    }

    fun deleteTask(id: Long) {
        _uiState.update {
            it.copy(tasks = it.tasks.filter { task -> task.id != id })
        }
    }
}