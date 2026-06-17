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
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
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
import androidx.navigation.NavHostController
import com.vibhorpatil.schoolmanagement.R
import com.vibhorpatil.schoolmanagement.domain.model.EnrollData
import com.vibhorpatil.schoolmanagement.presentation.components.AppTopBar
import kotlinx.coroutines.launch

@Composable
fun EnrollIntoScreen(
    enrollmentViewModel: EnrollmentViewModel,
    navController: NavHostController,
    entityId: Long, isStudent: Boolean
) {

    var isOpenBottomSheet by remember {
        mutableStateOf(false)
    }

    Scaffold(
        topBar = {
            AppTopBar(
                title = if (isStudent) "Student Details" else "Course Details",
                navigationIcon = R.drawable.ic_back_arrow,
                onNavigationClick = { navController.popBackStack() })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (isStudent) {
                StudentDetails()
            } else {
                CourseDetails()
            }

            SubDetails()


            Button(
                onClick = { isOpenBottomSheet = !isOpenBottomSheet },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp, top = 8.dp)
            ) {
                Icon(painter = painterResource(R.drawable.icon_course), contentDescription = "")
                Text(text = if (true) "Manage Student" else "Manage Course")
            }

            EnrollNonEnrollPager()

            if (isOpenBottomSheet) {
                EnrollmentBottomSheet( {isOpenBottomSheet = ! isOpenBottomSheet})
            }
        }
    }
}

@Composable
fun StudentDetails() {
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

        Column() {
            Text(
                text = "Vibhor Patil", style = TextStyle.Default.copy(
                    color = Color.Black,
                    fontWeight = FontWeight.ExtraBold
                )
            )
            Text(
                text = "Vibhorbpatil@Gmail.com", style = TextStyle.Default.copy(
                    color = Color.Gray,
                    fontWeight = FontWeight.Light
                )
            )
            Text(
                text = "+91 7350916174", style = TextStyle.Default.copy(
                    color = Color.Gray,
                    fontWeight = FontWeight.Light
                )
            )
            Text(
                text = "Enrolled On", style = TextStyle.Default.copy(
                    color = Color.Gray,
                    fontWeight = FontWeight.Light
                )
            )
            Text(
                text = "Active", style = TextStyle.Default.copy(
                    color = Color.Gray,
                    fontWeight = FontWeight.Light
                )
            )
        }

    }
}

@Composable
fun CourseDetails() {
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
fun SubDetails() {
    Row() {
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Total Courses", style = MaterialTheme.typography.titleMedium)
            Text(text = "4", style = MaterialTheme.typography.labelSmall)

        }

        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Total Courses", style = MaterialTheme.typography.titleMedium)
            Text(text = "4", style = MaterialTheme.typography.labelSmall)

        }

    }
}

@Composable
fun EnrollNonEnrollPager() {
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
                0 -> EnrolledListScreen()
                1 -> NonEnrolledListScreen()
            }
        }
    }
}

@Composable
fun EnrolledListScreen() {
    LazyColumn() {
        items(10) {
            ListItem(onItemClick = {})
        }
    }
}

@Composable
fun NonEnrolledListScreen() {
    LazyColumn() {
        items(10) {
            ListItem(onItemClick = {})
        }
    }
}

@Composable
fun ListItem(
    enrollData: EnrollData = EnrollData(
        id = 1,
        title = "Title",
        subTitle = "SubTitle",
        2,
        true
    ), onItemClick: (Long) -> Unit
) {
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
    onDismiss: () -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss
    ) {
        LazyColumn(
            modifier = Modifier.padding(16.dp)
        ) {
            items(3) {
                CourseEnrollmentItem(
                    "Android Development",
                    "Amit Shekhar",
                    true,
                    {},
                    {}
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
            .padding(horizontal = 16.dp, vertical = 6.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                imageVector = Icons.Default.Email,
                contentDescription = null,
                modifier = Modifier.size(48.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = courseName,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )

                Text(
                    text = instructorName,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(4.dp))

                AssistChip(
                    onClick = { },
                    label = {
                        Text(
                            if (isEnrolled)
                                "Enrolled"
                            else
                                "Not Enrolled"
                        )
                    }
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            if (isEnrolled) {
                OutlinedButton(
                    onClick = onUnenrollClick
                ) {
                    Text("Unenroll")
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

