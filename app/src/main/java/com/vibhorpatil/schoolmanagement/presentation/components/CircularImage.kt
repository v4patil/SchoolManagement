package com.vibhorpatil.schoolmanagement.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.vibhorpatil.schoolmanagement.R
import java.io.File


@Composable
@Preview(showBackground = true)
fun CircularImagePreview() {
    CircularImage()
}

@Composable
fun CircularImage(
    uri: String? = null,
    onImageClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier.size(120.dp),
        contentAlignment = Alignment.BottomEnd
    ) {

        AsyncImage(
            model = uri?.let { File(it) },
            contentDescription = "Profile Image",
            modifier = Modifier
                .fillMaxSize()
                .clip(CircleShape)
                .border(
                    2.dp,
                    MaterialTheme.colorScheme.outline,
                    CircleShape
                ),
            contentScale = ContentScale.Crop,
            placeholder = rememberVectorPainter(Icons.Default.Person),
            error = rememberVectorPainter(Icons.Default.Person)
        )

        Surface(
            modifier = Modifier
                .size(36.dp)
                .clickable { onImageClick() },
            shape = CircleShape,
            tonalElevation = 4.dp,
            shadowElevation = 4.dp
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_gallary),
                    contentDescription = "Change Profile Picture",
                    modifier = Modifier.size(18.dp)
                )
            }
        }
    }
}
