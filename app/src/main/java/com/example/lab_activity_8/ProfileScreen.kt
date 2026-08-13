package com.example.lab_activity_8

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

private val Background = Color(0xFF1C1C1C)
private val CardColor = Color(0xFF252525)

private val OrangeLight = Color(0xFFFFB74D)
private val Orange = Color(0xFFFF9800)
private val OrangeDark = Color(0xFFE65100)

private val White = Color.White
private val GrayText = Color(0xFFBDBDBD)

private val OrangeGradient = Brush.horizontalGradient(
    colors = listOf(
        OrangeLight,
        Orange,
        OrangeDark
    )
)

@Composable
fun ProfileForm(
    state: ProfileUiState,
    viewModel: ProfileViewModel
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Background),
        contentAlignment = Alignment.Center
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .widthIn(max = 520.dp)
                .padding(horizontal = 20.dp)
                .verticalScroll(rememberScrollState())
                .clip(RoundedCornerShape(24.dp))
                .background(CardColor)
                .padding(24.dp),

            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // TITLE

            Text(
                text = "MY PROFILE",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = White
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Create and update your profile",
                fontSize = 14.sp,
                color = GrayText
            )

            Spacer(modifier = Modifier.height(24.dp))

            // FULL NAME

            GradientTextField(
                value = state.name,
                onValueChange = {
                    viewModel.onNameChange(it)
                },
                label = "Full name"
            )

            Spacer(modifier = Modifier.height(12.dp))

            // EMAIL

            GradientTextField(
                value = state.email,
                onValueChange = {
                    viewModel.onEmailChange(it)
                },
                label = "Email"
            )

            Spacer(modifier = Modifier.height(12.dp))

            // CONTACT

            GradientTextField(
                value = state.contactNumber,
                onValueChange = {
                    viewModel.onContactChange(it)
                },
                label = "Contact number"
            )

            Spacer(modifier = Modifier.height(12.dp))

            // ADDRESS

            GradientTextField(
                value = state.address,
                onValueChange = {
                    viewModel.onAddressChange(it)
                },
                label = "Address"
            )

            Spacer(modifier = Modifier.height(12.dp))

            // USERNAME

            GradientTextField(
                value = state.username,
                onValueChange = {
                    viewModel.onUsernameChange(it)
                },
                label = "Username"
            )

            Spacer(modifier = Modifier.height(28.dp))

            // SKILLS TITLE

            Text(
                text = "SKILLS",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = OrangeLight
            )

            Spacer(modifier = Modifier.height(12.dp))

            // ADD SKILL

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                GradientTextField(
                    value = state.newSkill,
                    onValueChange = {
                        viewModel.onNewSkillChange(it)
                    },
                    label = "Add a skill",
                    modifier = Modifier.weight(1f)
                )

                Spacer(modifier = Modifier.width(10.dp))

                GradientButton(
                    text = "ADD",
                    onClick = {
                        viewModel.addSkill()
                    },
                    modifier = Modifier.width(85.dp)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // SKILL LIST

            state.skills.forEach { skill ->

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 3.dp),

                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "• $skill",
                        color = White,
                        fontSize = 16.sp,
                        modifier = Modifier.weight(1f)
                    )

                    TextButton(
                        onClick = {
                            viewModel.removeSkill(skill)
                        }
                    ) {

                        Text(
                            text = "Remove",
                            color = OrangeLight
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            // PREVIEW BUTTON
            //
            // BONUS:
            // Disabled until name AND email are filled.

            GradientButton(
                text = "PREVIEW",
                onClick = {
                    viewModel.showPreview()
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = state.name.isNotBlank() &&
                        state.email.isNotBlank()
            )

            Spacer(modifier = Modifier.height(12.dp))

            // CLEAR ALL BUTTON

            OutlinedButton(
                onClick = {
                    viewModel.clearAll()
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp),
                border = BorderStroke(
                    width = 1.dp,
                    color = Orange
                )
            ) {

                Text(
                    text = "CLEAR ALL",
                    color = OrangeLight,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}

@Composable
fun ProfilePreview(
    state: ProfileUiState,
    onBack: () -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Background),

        contentAlignment = Alignment.Center
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .widthIn(max = 520.dp)
                .padding(horizontal = 20.dp)
                .verticalScroll(rememberScrollState())
                .clip(RoundedCornerShape(24.dp))
                .background(CardColor)
                .padding(28.dp),

            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "PROFILE PREVIEW",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = White
            )

            Spacer(modifier = Modifier.height(24.dp))

            ProfileInfo(
                label = "NAME",
                value = state.name
            )

            ProfileInfo(
                label = "EMAIL",
                value = state.email
            )

            ProfileInfo(
                label = "CONTACT",
                value = state.contactNumber
            )

            ProfileInfo(
                label = "ADDRESS",
                value = state.address
            )

            ProfileInfo(
                label = "USERNAME",
                value = state.username
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "SKILLS",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = OrangeLight
            )

            Spacer(modifier = Modifier.height(12.dp))

            if (state.skills.isEmpty()) {

                Text(
                    text = "No skills added yet.",
                    color = GrayText
                )

            } else {

                state.skills.forEach { skill ->

                    Text(
                        text = "• $skill",
                        color = White,
                        fontSize = 16.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 3.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            OutlinedButton(
                onClick = onBack,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp),
                border = BorderStroke(
                    width = 1.dp,
                    color = Orange
                )
            ) {

                Text(
                    text = "BACK TO EDIT",
                    color = OrangeLight,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
private fun GradientTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier
) {

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,

        label = {
            Text(
                text = label,
                color = GrayText
            )
        },

        modifier = modifier.fillMaxWidth(),

        shape = RoundedCornerShape(14.dp),

        singleLine = true,

        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = White,
            unfocusedTextColor = White,

            cursorColor = OrangeLight,

            focusedBorderColor = Orange,
            unfocusedBorderColor = Color(0xFF666666),

            focusedLabelColor = OrangeLight,
            unfocusedLabelColor = GrayText
        )
    )
}

@Composable
private fun GradientButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {

    val buttonBrush = if (enabled) {
        OrangeGradient
    } else {
        Brush.horizontalGradient(
            colors = listOf(
                Color(0xFF555555),
                Color(0xFF444444)
            )
        )
    }

    Box(
        modifier = modifier
            .height(52.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(buttonBrush),

        contentAlignment = Alignment.Center
    ) {

        Button(
            onClick = onClick,
            enabled = enabled,

            modifier = Modifier.fillMaxSize(),

            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent
            ),

            shape = RoundedCornerShape(14.dp)
        ) {

            Text(
                text = text,
                color = if (enabled) {
                    White
                } else {
                    Color(0xFF888888)
                },

                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
        }
    }
}

@Composable
private fun ProfileInfo(
    label: String,
    value: String
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 7.dp)
    ) {

        Text(
            text = label,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = OrangeLight
        )

        Spacer(modifier = Modifier.height(3.dp))

        Text(
            text = if (value.isBlank()) {
                "Not provided"
            } else {
                value
            },

            fontSize = 16.sp,
            color = White
        )
    }
}

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = viewModel()
) {

    val state by viewModel.uiState.collectAsStateWithLifecycle()

    if (state.isPreview) {

        ProfilePreview(
            state = state,
            onBack = {
                viewModel.backToEdit()
            }
        )

    } else {

        ProfileForm(
            state = state,
            viewModel = viewModel
        )
    }
}