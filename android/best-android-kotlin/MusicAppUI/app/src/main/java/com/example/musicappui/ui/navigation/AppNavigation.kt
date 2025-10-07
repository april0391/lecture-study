package com.example.musicappui.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.musicappui.ui.AccountView
import com.example.musicappui.ui.MainViewModel

@Composable
fun AppNavigation(
    navController: NavHostController,
    viewModel: MainViewModel,
    padding: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = Screen.DrawerScreen.Account.dRoute,
        modifier = Modifier.padding(padding)
    ) {
        composable(Screen.DrawerScreen.Account.dRoute) {
            AccountView()
        }

        composable(Screen.DrawerScreen.Subscription.dRoute) {
            Text("구독 화면")
        }

        composable(Screen.DrawerScreen.AddAccount.dRoute) {
            Text("계정 추가 화면")
        }

    }
}