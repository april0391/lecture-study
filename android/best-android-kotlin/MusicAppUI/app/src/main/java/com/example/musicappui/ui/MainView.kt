package com.example.musicappui.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.musicappui.ui.navigation.AppNavigation
import com.example.musicappui.ui.navigation.Screen
import com.example.musicappui.ui.navigation.screensInDrawer
import kotlinx.coroutines.launch

@Composable
fun MainView() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val viewModel = viewModel<MainViewModel>()

    val navController: NavHostController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val dialogOpen = remember { mutableStateOf(false) }

    val currentScreen by viewModel.currentScreen.collectAsState()

    // 1. Scaffold를 ModalNavigationDrawer로 감싸줍니다.
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            // 2. 서랍이 열렸을 때 보여줄 UI를 여기에 만듭니다.
            ModalDrawerSheet {
                LazyColumn(Modifier.padding(16.dp)) {
                    items(screensInDrawer) {
                        DrawerItem(
                            selected = currentRoute == it.dRoute,
                            item = it,
                            onDrawerItemClicked = {
                                scope.launch {
                                    drawerState.close()
                                }
                                if (it.dRoute == "add_account") {
                                    dialogOpen.value = true
                                } else {
                                    viewModel.setCurrentScreen(it)
                                    navController.navigate(it.dRoute)
                                }
                            }
                        )
                    }
                }
            }
        }
    ) {
        // 3. 기존 Scaffold는 ModalNavigationDrawer의 메인 컨텐츠가 됩니다.
        Scaffold(
            topBar = {
                MainViewTopAppBar(
                    title = currentScreen.title,
                    onNavigationIconClick = {
                        scope.launch {
                            drawerState.open()
                        }
                    })
            }
        ) {
            AppNavigation(
                navController = navController,
                viewModel = viewModel,
                padding = it
            )
            AccountDialog(dialogOpen)
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun MainViewTopAppBar(
    title: String,
    onNavigationIconClick: () -> Unit
) {
    TopAppBar(
        title = { Text(title) },
        navigationIcon = {
            IconButton(
                onClick = { onNavigationIconClick() },
                content = {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = "Menu"
                    )
                }
            )
        }
    )
}

@Composable
fun DrawerItem(
    selected: Boolean,
    item: Screen.DrawerScreen,
    onDrawerItemClicked: () -> Unit
) {
    val background = if (selected) Color.DarkGray else Color.White

    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 16.dp)
            .background(background)
            .clickable { onDrawerItemClicked() }
    ) {
        Icon(
            painter = painterResource(id = item.icon),
            contentDescription = item.dTitle,
            Modifier.padding(end = 8.dp, top = 4.dp)
        )
        Text(
            text = item.dTitle,
            style = MaterialTheme.typography.headlineMedium
        )
    }
}

