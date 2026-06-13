package com.vibhorpatil.schoolmanagement.presentation.components

import androidx.annotation.DrawableRes
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.vibhorpatil.schoolmanagement.presentation.theme.surfaceWhite

@Preview(showBackground = true)
@Composable
fun AppTopBarPreview(){
    AppTopBar(title = "App Bar")
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    title: String,
    @DrawableRes navigationIcon: Int? = null,
    @DrawableRes actionIcon: Int? = null,
    onNavigationClick: (() -> Unit)? = null,
    onActionClick: (() -> Unit)? = null
) {

    TopAppBar(
        title = {
            Text(text = title, style = MaterialTheme.typography.titleLarge, color = Color.Black)
        },
        navigationIcon = {
            navigationIcon?.let {
                IconButton(
                    onClick = { onNavigationClick?.invoke() }
                ) {
                    Icon(
                        painter = painterResource(navigationIcon),
                        contentDescription = "Navigation"
                    )
                }
            }
        },
        actions = {
            actionIcon?.let {
                IconButton(
                    onClick = { onActionClick?.invoke() }
                ) {
                    Icon(
                        painter = painterResource(actionIcon),
                        contentDescription = "Action"
                    )
                }

            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = surfaceWhite,
            titleContentColor = surfaceWhite,
            navigationIconContentColor = surfaceWhite,
            actionIconContentColor = surfaceWhite
        )
    )

}