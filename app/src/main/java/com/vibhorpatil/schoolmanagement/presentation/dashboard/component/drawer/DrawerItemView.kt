package com.vibhorpatil.schoolmanagement.presentation.dashboard.component.drawer

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vibhorpatil.schoolmanagement.presentation.navigation.Screen


@Preview(showBackground = true)
@Composable
fun PreviewDrawerItemView() {
    DrawerItemView(false, Screen.DrawerScreen.StudentList) {}
}

@Composable
fun DrawerItemView(
    selected: Boolean,
    item: Screen.DrawerScreen,
    onItemClick: () -> Unit
) {
    val background = if (selected) Color.LightGray else Color.White
    Row(
        modifier = Modifier
            .background(background)
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .clickable {
                onItemClick()
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(painter = painterResource(item.icon),
            modifier = Modifier.size(16.dp),
            contentDescription = item.title)

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = item.title, modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            style = MaterialTheme.typography.headlineSmall
        )
    }
}