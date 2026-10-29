package com.example.taskmate

data class TaskUiState(
    val tasks: List<Task> = emptyList(),
    val titleInput: String = "",
    val courseInput: String = "",
    val errorMessage: String = ""
)