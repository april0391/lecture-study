package com.example.mywishlistapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun AddEditDetailView(
    id: Long,
    viewModel: WishViewModel,
    navController: NavController
) {
    // 1. M3 방식으로 SnackbarHostState를 직접 생성하고 remember로 기억합니다.
    val snackbarHostState = remember { SnackbarHostState() }
    val snackMessage = remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    // val scaffoldState = rememberScaffoldState() // 이 줄은 삭제합니다.

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            AppBarView(
                title =
                    if (id != 0L) stringResource(id = R.string.update_wish)
                    else stringResource(id = R.string.add_wish)
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            WishTextField(
                label = "Title",
                value = viewModel.wishTitleState,
                onValueChange = {
                    viewModel.onWishTitleChanged(it)
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
            WishTextField(
                label = "Description",
                value = viewModel.wishTitleState,
                onValueChange = {
                    viewModel.onWishDescriptionChanged(it)
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = {
                if (viewModel.wishTitleState.isNotEmpty() &&
                    viewModel.wishDescriptionState.isNotEmpty()
                ) {
                    //TODO UpdateWish
                } else {
                    snackMessage.value = "Enter fields to create a wish"
                }
            }) {
                Text(
                    text =
                        if (id != 0L) stringResource(id = R.string.update_wish)
                        else stringResource(id = R.string.add_wish),
                    style = TextStyle(fontSize = 18.sp)
                )
            }
        }
    }
}

@Composable
fun WishTextField(label: String, value: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label, color = Color.Black) },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        colors = TextFieldDefaults.colors(
            unfocusedTextColor = Color.Gray,
            focusedTextColor = Color.Black,
            cursorColor = colorResource(id = R.color.app_bar_color), // 앱의 주요 색상 사용
            focusedIndicatorColor = colorResource(id = R.color.app_bar_color), // 포커스 시 테두리 색상
            unfocusedIndicatorColor = Color.LightGray, // 비포커스 시 테두리 색상
            unfocusedContainerColor = Color.Transparent, // 배경색을 투명하게
            focusedContainerColor = Color.Transparent
        )
    )
}

@Preview(showBackground = true)
@Composable
fun WishTestFieldPrev() {
    WishTextField("label", "text", {})
}