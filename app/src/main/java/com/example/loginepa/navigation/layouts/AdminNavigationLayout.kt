package com.example.loginepa.navigation.layouts

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.loginepa.navigation.componentside.SideNavigation
import com.example.loginepa.navigation.typesnavigation.AdminNavigation

@Composable
fun AdminNavigationLayout(navigationViewModel: ViewModelNavigation) {
    val navController = rememberNavController()
    val navigationItems by navigationViewModel.navigationItems.collectAsStateWithLifecycle()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    var isDrawerOpen by remember { mutableStateOf(false) }

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val isTablet = screenWidth >= 600.dp

    Box(modifier = Modifier.fillMaxSize()) {
        if (isTablet) {
            Row(modifier = Modifier.fillMaxSize()) {
                SideNavigation(
                    items = navigationItems,
                    currentRoute = currentRoute,
                    onItemClick = { route ->
                        navController.navigate(route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    onLogout = {
                        navigationViewModel.logout()
                    },
                    text = "Panel de administracion"
                )

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f)
                ) {
                    AdminNavigation(navController = navController)
                }
            }
        } else {
            AdminNavigation(navController = navController)
        }

        if (!isTablet) {
            FloatingActionButton(
                onClick = { isDrawerOpen = !isDrawerOpen },
                modifier = Modifier
                    .padding(38.dp)
                    .align(Alignment.TopStart),
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(
                    imageVector = if (isDrawerOpen) Icons.Default.Close else Icons.Default.Menu,
                    contentDescription = "Menu"
                )
            }
        }

        if (!isTablet && isDrawerOpen) {
            val overlayAlpha by animateFloatAsState(
                targetValue = if (isDrawerOpen) 0.6f else 0f,
                animationSpec = tween(300),
                label = "overlay_alpha"
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = overlayAlpha))
                    .clickable { isDrawerOpen = false }
            )

            AnimatedVisibility(
                visible = isDrawerOpen,
                enter = slideInHorizontally(
                    initialOffsetX = { -it },
                    animationSpec = tween(800)
                ),
                exit = slideOutHorizontally(
                    targetOffsetX = { -it },
                    animationSpec = tween(800)
                )
            ) {
                Surface(
                    modifier = Modifier
                        .width(280.dp)
                        .fillMaxHeight(),
                    color = MaterialTheme.colorScheme.surface,
                    shadowElevation = 8.dp
                ) {
                    SideNavigation(
                        items = navigationItems,
                        currentRoute = currentRoute,
                        onItemClick = { route ->
                            navController.navigate(route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                            isDrawerOpen = false
                        },
                        onLogout = {
                            navigationViewModel.logout()
                            isDrawerOpen = false
                        },
                        text = "Panel de Administrador"
                    )
                }
            }
        }
    }
}