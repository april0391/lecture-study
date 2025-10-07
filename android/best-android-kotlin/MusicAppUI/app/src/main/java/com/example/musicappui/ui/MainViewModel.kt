package com.example.musicappui.ui

import androidx.lifecycle.ViewModel
import com.example.musicappui.ui.navigation.Screen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel : ViewModel() {
    // 1. private MutableStateFlow로 상태를 관리합니다.
    private val _currentScreen = MutableStateFlow<Screen>(Screen.DrawerScreen.AddAccount)

    // 2. 외부에는 읽기 전용인 StateFlow로 노출합니다.
    val currentScreen: StateFlow<Screen> = _currentScreen.asStateFlow()

    fun setCurrentScreen(screen: Screen) {
        _currentScreen.value = screen
    }
}