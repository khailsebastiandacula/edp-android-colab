package com.example.lab_activity_8

data class ProfileUiState(
    val name: String = "",
    val email: String = "",
    val contactNumber: String = "",
    val address: String = "",
    val username: String = "",
    val skills: List<String> = emptyList(),
    val newSkill: String = "",
    val isPreview: Boolean = false
)