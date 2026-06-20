package com.vibhorpatil.schoolmanagement.presentation.enrollment

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.vibhorpatil.schoolmanagement.R
import com.vibhorpatil.schoolmanagement.domain.model.Course
import com.vibhorpatil.schoolmanagement.domain.model.EnrollData
import com.vibhorpatil.schoolmanagement.domain.model.Student
import com.vibhorpatil.schoolmanagement.presentation.components.AppTopBar
import com.vibhorpatil.schoolmanagement.presentation.uiState.UiState
import kotlinx.coroutines.launch

@Composable
fun EnrollIntoScreen(
    viewModel: EnrollmentViewModel,
    navController: NavHostController,
    entityId: Long, isStudent: Boolean
) {
    val uiState by viewModel.studentProfileState.collectAsStateWithLifecycle()
    val uiStateCourse by viewModel.courseProfileState.collectAsStateWithLifecycle()
    val enrollmentState by viewModel.enrollmentUiState.collectAsStateWithLifecycle()

    LaunchedEffect(entityId) {
        viewModel.fetchEntityDataData(entityId, isStudent)
        if (isStudent) {
            viewModel.loadStudentEnrollments(entityId)
        } else {
            viewModel.loadCourseEnrollments(entityId)
        }
    }

    var isOpenBottomSheet by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            AppTopBar(
                title = if (isStudent) "Student Details" else "Course Details",
                navigationIcon = R.drawable.ic_back_arrow,
                onNavigationClick = { navController.popBackStack() },
                actionIcon = R.drawable.ic_back_arrow,
                onActionClick = {
                    if (enrollmentState.hasPendingChanges) {
                        viewModel.saveEnrollmentChanges(entityId, isStudent)
                        navController.popBackStack()
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (val state = if (isStudent) uiState else uiStateCourse) {
                is UiState.Loading -> {
                    CircularProgressIndicator()
                }

                is UiState.Success<*> -> {
                    if (isStudent) {
                        StudentDetails(state.data as Student)
                    } else {
                        CourseDetails(state.data as Course)
                    }
                }

                is UiState.Error -> {}
            }

            Button(
                onClick = { isOpenBottomSheet = !isOpenBottomSheet },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp, top = 8.dp)
            ) {
                Icon(painter = painterResource(R.drawable.icon_course), contentDescription = "")
                Text(text = if (isStudent) "Manage Course" else "Manage Student")
            }

            EnrollNonEnrollPager(
                enrolledItems = enrollmentState.enrolledItems,
                nonEnrolledItems = enrollmentState.nonEnrolledItems
            )

            if (isOpenBottomSheet) {
                EnrollmentBottomSheet(
                    items = enrollmentState.allItems,
                    onEnroll = viewModel::enrollCourse,
                    onUnEnroll = viewModel::unEnrollCourse,
                    onDismiss = {
                        isOpenBottomSheet = false
                    }
                )
            }
        }
    }
}

@Composable
fun StudentDetails(student: Student) {
    Row(
        modifier = Modifier
            .padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
            .background(MaterialTheme.colorScheme.surface)
    ) {

        Image(
            modifier = Modifier.weight(1F),
            painter = painterResource(R.drawable.icon_students),
            contentDescription = "Student Profile Photo"
        )

        Column {
            Text(
                text = student.fullName, style = TextStyle.Default.copy(
                    color = Color.Black,
                    fontWeight = FontWeight.ExtraBold
                )
            )
            Text(
                text = student.email, style = TextStyle.Default.copy(
                    color = Color.Gray,
                    fontWeight = FontWeight.Light
                )
            )
            Text(
                text = student.mobileNumber, style = TextStyle.Default.copy(
                    color = Color.Gray,
                    fontWeight = FontWeight.Light
                )
            )
            Text(
                text = student.enrollmentDate.toString(), style = TextStyle.Default.copy(
                    color = Color.Gray,
                    fontWeight = FontWeight.Light
                )
            )
            Text(
                text = if (student.isActive) "Active" else "Not-Active",
                style = TextStyle.Default.copy(
                    color = if (student.isActive) Color.Green else Color.Gray,
                    fontWeight = FontWeight.Light
                )
            )
        }

    }
}

@Composable
fun CourseDetails(course: Course) {
    Row(
        modifier = Modifier
            .padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
            .background(MaterialTheme.colorScheme.surface)
    ) {

        Image(
            modifier = Modifier.weight(1F),
            painter = painterResource(R.drawable.icon_students),
            contentDescription = "Student Profile Photo"
        )

        Column(modifier = Modifier.weight(3f)) {
            Text(
                text = "Android Development", style = TextStyle.Default.copy(
                    color = Color.Black,
                    fontWeight = FontWeight.ExtraBold
                )
            )

            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                Column(modifier = Modifier.weight(1F)) {
                    Text(
                        text = "Instructor", style = TextStyle.Default.copy(
                            color = Color.Gray,
                            fontWeight = FontWeight.Light,
                            fontSize = 10.sp
                        )
                    )
                    Text(
                        text = "Amit Shekhar", style = TextStyle.Default.copy(
                            color = Color.Gray,
                            fontWeight = FontWeight.Light
                        )
                    )
                }

                Column(modifier = Modifier.weight(1F)) {
                    Text(
                        text = "Instructor", style = TextStyle.Default.copy(
                            color = Color.Gray,
                            fontWeight = FontWeight.Light,
                            fontSize = 10.sp
                        )
                    )
                    Text(
                        text = "Amit Shekhar", style = TextStyle.Default.copy(
                            color = Color.Gray,
                            fontWeight = FontWeight.Light
                        )
                    )
                }

                Column(modifier = Modifier.weight(1F)) {
                    Text(
                        text = "Instructor", style = TextStyle.Default.copy(
                            color = Color.Gray,
                            fontWeight = FontWeight.Light,
                            fontSize = 10.sp
                        )
                    )
                    Text(
                        text = "Amit Shekhar", style = TextStyle.Default.copy(
                            color = Color.Gray,
                            fontWeight = FontWeight.Light
                        )
                    )
                }
            }


        }

    }
}

@Composable
fun EnrollNonEnrollPager(enrolledItems: List<EnrollData>, nonEnrolledItems: List<EnrollData>) {
    val tabs = listOf("Enrolled", "Non-Enrolled")
    val pagerState = rememberPagerState(pageCount = { tabs.size })

    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TabRow(
            selectedTabIndex = pagerState.currentPage
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = pagerState.currentPage == index,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    text = {
                        Text(text = title)
                    }
                )
            }
        }

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) { page ->
            when (page) {
                0 -> EnrolledListScreen(items = enrolledItems)

                1 -> NonEnrolledListScreen(items = nonEnrolledItems)
            }
        }
    }
}

@Composable
fun EnrolledListScreen(items: List<EnrollData>) {
    LazyColumn {
        items(items) {
            ListItem(
                enrollData = it,
                onItemClick = {}
            )
        }
    }
}

@Composable
fun NonEnrolledListScreen(items: List<EnrollData>) {
    LazyColumn {
        items(items) {
            ListItem(
                enrollData = it,
                onItemClick = {}
            )
        }
    }
}

@Composable
fun ListItem(enrollData: EnrollData, onItemClick: (Long) -> Unit) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 4.dp)
            .clickable(onClick = { onItemClick(enrollData.id) }),
        shape = MaterialTheme.shapes.medium
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {

            Icon(imageVector = Icons.Default.Build, contentDescription = "")

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = enrollData.title, style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.5.sp
                    ),
                    color = MaterialTheme.colorScheme.primary,
                    maxLines = 1
                )

                Text(
                    text = enrollData.subTitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1
                )
            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EnrollmentBottomSheet(
    items: List<EnrollData>,
    onEnroll: (Long) -> Unit,
    onUnEnroll: (Long) -> Unit,
    onDismiss: () -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss
    ) {
        LazyColumn {
            items(
                items = items,
                key = { it.id }
            ) { item ->
                CourseEnrollmentItem(
                    courseName = item.title,
                    instructorName = item.subTitle,
                    isEnrolled = item.isEnrolled,
                    onEnrollClick = { onEnroll(item.id) },
                    onUnenrollClick = { onUnEnroll(item.id) }
                )
            }
        }
    }
}

@Composable
fun CourseEnrollmentItem(
    courseName: String,
    instructorName: String,
    isEnrolled: Boolean,
    onEnrollClick: () -> Unit,
    onUnenrollClick: () -> Unit
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 6.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Email,
                contentDescription = null,
                modifier = Modifier.size(30.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = courseName,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold
                )

                Text(
                    text = instructorName,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(4.dp))

//                AssistChip(
//                    onClick = { },
//                    label = {
//                        Text(
//                            if (isEnrolled)
//                                "Enrolled"
//                            else
//                                "Not Enrolled"
//                        )
//                    }
//                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            if (isEnrolled) {
                OutlinedButton(
                    onClick = onUnenrollClick
                ) {
                    Text(text = "Unenroll",
                        style = MaterialTheme.typography.bodyMedium,)
                }
            } else {
                Button(
                    onClick = onEnrollClick
                ) {
                    Text("Enroll")
                }
            }
        }
    }
}

