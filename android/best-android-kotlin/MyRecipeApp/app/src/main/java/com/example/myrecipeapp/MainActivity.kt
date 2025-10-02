package com.example.myrecipeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.myrecipeapp.ui.theme.MyRecipeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            MyRecipeAppTheme {
                // 1. Scaffold가 TopAppBar나 시스템 바 등으로 인해 발생할 수 있는
                //    padding 값을 계산해서 'innerPadding'으로 전달합니다.
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    // 2. Box가 그 'innerPadding'을 자신의 패딩으로 적용합니다.
                    //    이 Box는 순수하게 패딩을 적용하기 위한 '컨테이너' 역할을 합니다.
                    Box(modifier = Modifier.padding(innerPadding)) {

                        // 3. RecipeApp 패딩이 적용된 Box 안에서 그려지므로,
                        //    다른 UI 요소에 의해 가려지는 일 없이 안전한 영역에 표시됩니다.
                        RecipeApp(navController)
                    }
                }

            }
        }
    }
}

