package com.example.loginepa.navigation.componentside


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Output
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.loginepa.navigation.navigationData.NavigationItem

@Composable
fun SideNavigation(
    items : List<NavigationItem>,
    currentRoute : String?,
    onItemClick : (String) -> Unit,
    onLogout : () -> Unit,
    text : String
) {
    Surface(
        modifier = Modifier
            .fillMaxHeight()
            .width(280.dp)
            .padding(18.dp),
        color = MaterialTheme.colorScheme.surface,
        tonalElevation = 8.dp
    ) {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ){
            Text(
                text = text,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            items.forEach { item ->
                NavigationDrawerItem(
                    label = {Text(item.title)},
                    selected = currentRoute == item.route,
                    onClick = { onItemClick(item.route) },
                    modifier = Modifier.padding(vertical = 4.dp),
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.title
                        )
                    },
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = onLogout,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    contentColor = MaterialTheme.colorScheme.surface
                )
            ) {
                Icon(
                    imageVector = Icons.Default.Output,
                    contentDescription = "Cerrar Sesion"
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Cerrar Sesion")
            }
        }
    }
}