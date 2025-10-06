package com.example.sqliteexample

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.sqliteexample.ui.theme.SQLiteExampleTheme

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    lateinit var myHelper: MyDBHelper
    lateinit var sqlDB: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. MyDBHelper를 초기화합니다. this는 MainActivity 자신(Context)을 의미합니다.
        myHelper = MyDBHelper(this)

        // 2. myHelper를 통해 쓰기 가능한 데이터베이스 객체를 얻어와 sqlDB에 할당합니다.
        sqlDB = myHelper.writableDatabase

        enableEdgeToEdge()
        setContent {
            SQLiteExampleTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = {
                                Text("가수 그룹 관리 DB")
                            }
                        )
                    },
                ) { innerPadding ->
                    App(
                        modifier = Modifier.padding(innerPadding),
                        onResetClick = {
                            myHelper.onUpgrade(sqlDB, 1, 2)
                        },
                        onInsertClick = { gName, gNumber ->
                            sqlDB.execSQL("INSERT INTO groupTBL VALUES ('$gName', '$gNumber');")
                        },
                        onQueryClick = {
                            val cursor = sqlDB.rawQuery("SELECT * FROM groupTBL;", null)
                            val mutableGroups = mutableListOf<Pair<String, String>>()

                            while (cursor.moveToNext()) {
                                mutableGroups.add(
                                    Pair(
                                        cursor.getString(0),
                                        cursor.getString(1)
                                    )
                                )
                            }
                            cursor.close()
                            mutableGroups
                        }
                    )
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        sqlDB.close()
    }

    inner class MyDBHelper(context: Context) :
        SQLiteOpenHelper(context, "groupDB", null, 1) {
        override fun onCreate(db: SQLiteDatabase?) {
            db!!.execSQL("CREATE TABLE groupTBL (gName CHAR(20) PRIMARY KEY, gNumber INTEGER);")
            Log.d("MyDBHelper", "테이블 생성")
        }

        override fun onUpgrade(
            db: SQLiteDatabase?,
            p1: Int,
            p2: Int
        ) {
            db!!.execSQL("DROP TABLE IF EXISTS groupTBL;")
            Log.d("MyDBHelper", "테이블 삭제")
            onCreate(db)
        }
    }

}

@Composable
fun App(
    modifier: Modifier = Modifier,
    onResetClick: () -> Unit,
    onInsertClick: (String, String) -> Unit,
    onQueryClick: () -> List<Pair<String, String>>
) {
    var groupName by remember { mutableStateOf("") }
    var groupMembersCountText by remember { mutableStateOf("") }
    var groups by remember { mutableStateOf(listOf<Pair<String, String>>()) }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("그룹 이름: ")
            TextField(
                value = groupName,
                onValueChange = { groupName = it }
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("그룹 인원: ")
            TextField(
                value = groupMembersCountText,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                onValueChange = { newValue ->
                    // 숫자 또는 빈 문자열만 입력되도록 필터링합니다.
                    if (newValue.all { it.isDigit() }) {
                        groupMembersCountText = newValue
                    }
                }
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(onClick = { onResetClick() }) {
                Text("초기화")
            }
            Button(onClick = {
                val count: Int? = groupMembersCountText.toIntOrNull()
                if (count != null) {
                    onInsertClick(groupName, count.toString())
                }
            }) {
                Text("입력")
            }
            Button(onClick = { groups = onQueryClick() }) {
                Text("조회")
            }
        }

        LazyColumn {
            items(groups.size) {
                Text("${groups[it].first} - ${groups[it].second}")
            }
        }
    }
}

