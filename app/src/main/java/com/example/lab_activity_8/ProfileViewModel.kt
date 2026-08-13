package com.example.lab_activity_8

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ProfileViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState())

    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    // Update profile fields

    fun onNameChange(value: String) {
        _uiState.update {
            it.copy(name = value)
        }
    }

    fun onEmailChange(value: String) {
        _uiState.update {
            it.copy(email = value)
        }
    }

    fun onContactChange(value: String) {
        _uiState.update {
            it.copy(contactNumber = value)
        }
    }

    fun onAddressChange(value: String) {
        _uiState.update {
            it.copy(address = value)
        }
    }

    fun onUsernameChange(value: String) {
        _uiState.update {
            it.copy(username = value)
        }
    }

    fun onNewSkillChange(value: String) {
        _uiState.update {
            it.copy(newSkill = value)
        }
    }

    // Add skill
    // Prevents duplicate skills

    fun addSkill() {

        val skill = _uiState.value.newSkill.trim()

        if (skill.isEmpty()) return

        val alreadyExists = _uiState.value.skills.any {
            it.equals(skill, ignoreCase = true)
        }

        if (alreadyExists) return

        _uiState.update { current ->

            current.copy(
                skills = current.skills + skill,
                newSkill = ""
            )
        }
    }

    // Remove skill

    fun removeSkill(skill: String) {

        _uiState.update { current ->

            current.copy(
                skills = current.skills - skill
            )
        }
    }

    // Show preview

    fun showPreview() {

        _uiState.update {
            it.copy(isPreview = true)
        }
    }

    // Back to edit

    fun backToEdit() {

        _uiState.update {
            it.copy(isPreview = false)
        }
    }

    // BONUS: Clear everything

    fun clearAll() {

        _uiState.value = ProfileUiState()
    }
}