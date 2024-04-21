package com.example.collieapplication.collie

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp

// comp
// method vs 함수
@Composable // 단방향 흐름
fun LoginScreen(
    modifier: Modifier = Modifier
    , str: String
    , onValueChange: (String) -> Unit
    , onGoToSignUpButtonClicked: (Int) -> Unit
) {

    Surface(modifier, color = MaterialTheme.colorScheme.background) {
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = str
                , onValueChange = onValueChange
                , modifier = Modifier.padding(vertical = 24.dp)
            )
            Button(onClick = { /*TODO*/ }) {
                Text(text = "login")
            }
            ClickableText(
                text = AnnotatedString("Go to Sign Up!")
                , onClick = onGoToSignUpButtonClicked
            )
        }
    }
}


// prev
//@Preview
//@Composable
//private fun LoginScreenPreview() {
//    var value by remember {
//        mutableStateOf("")
//    }
//    LoginScreen(
//        Modifier.fillMaxSize()
//        , value
//        , onValueChange = {value = it}
//        , onSignUpButtonClicked =
//    )
//}

// 상태 관리 주체를 넘기는거
// 상태 호이스팅
// compose 는 상태를 가진다
// 숙제 : 시작화면 만들기

// lifecycle 참고
//https://developer.android.com/codelabs/basic-android-kotlin-compose-activity-lifecycle?hl=ko#4
// onCreate - onStart - onResume - onPause - onStop - onDestroy
// 앱이 백그라운드에 가면 onStop
// 앱에 포커스가 없어지면 onPause - 앱에 포커스가 생기면 onResume?
// 앱이 포그라운드로 오면 onStart - 그러나 아직 사용자와 앱 간 상호작용 못하는 상태
// remember vs rememberSavable : 후자는 앱을 bundle 에 저장한다 -> 앱이 강제 종료되지 않는 이상 상태 값 저장
// 전자인 remember 은 onCreate 가 호출되는 경우(activity 가 새로 그려지는 경우) 상태가 유지되지 않는다


