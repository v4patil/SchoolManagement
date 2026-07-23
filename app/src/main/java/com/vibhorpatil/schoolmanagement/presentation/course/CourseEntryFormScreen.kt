package com.vibhorpatil.schoolmanagement.presentation.course

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.vibhorpatil.schoolmanagement.R
import com.vibhorpatil.schoolmanagement.presentation.components.AppTopBar
import com.vibhorpatil.schoolmanagement.presentation.components.CircularImage
import com.vibhorpatil.schoolmanagement.utils.Util


@Composable
fun CourseEntryFormScreen(
    viewModel: CourseEntryFormViewModel,
    navController: NavHostController,
    courseId: Long = -1L
) {

    LaunchedEffect(courseId) {
        viewModel.loadCourse(courseId)
    }

    val context = LocalContext.current
    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            val savePath = Util.copyImageToAppStorage(context, uri)
            viewModel.courseProfilePhoto = savePath
        }
    }

    Scaffold(
        topBar = {
            AppTopBar(
                title = if (viewModel.isInEditMode) "Add Course" else "Edit Course",
                navigationIcon = R.drawable.ic_back_arrow,
                onNavigationClick = { navController.popBackStack() }
            )
        }
    ) { paddingValues ->
        if (viewModel.isLoading) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                CircularProgressIndicator()
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {

                CircularImage(viewModel.courseProfilePhoto) { imagePickerLauncher.launch("image/*") }

                OutlinedTextField(
                    value = viewModel.courseName,
                    onValueChange = { viewModel.courseName = it },
                    label = { Text("Course Name") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                OutlinedTextField(
                    value = viewModel.courseDescription,
                    onValueChange = { viewModel.courseDescription = it },
                    label = { Text("Course Description") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                OutlinedTextField(
                    value = viewModel.courseDuration.toString(),
                    onValueChange = { viewModel.courseDuration = it.toIntOrNull() ?: 0 },
                    label = { Text("Course Duration") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    keyboardOptions= KeyboardOptions(keyboardType = KeyboardType.Number)
                )

                OutlinedTextField(
                    value = viewModel.courseFees.toString(),
                    onValueChange = { viewModel.courseFees = it.toDoubleOrNull() ?: 0.0 },
                    label = { Text("Course Fees") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    keyboardOptions= KeyboardOptions(keyboardType = KeyboardType.Number)
                )

                OutlinedTextField(
                    value = viewModel.courseInstructorName,
                    onValueChange = { viewModel.courseInstructorName = it },
                    label = { Text("Instructor Name") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Is Active",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Switch(
                        checked = viewModel.isActive,
                        onCheckedChange = { viewModel.isActive = it }
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {
                        viewModel.saveCourse {
                            navController.popBackStack()
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Text(
                        text = if (viewModel.isInEditMode) "Update Course" else "Save Course",
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }
            }
        }
    }

}