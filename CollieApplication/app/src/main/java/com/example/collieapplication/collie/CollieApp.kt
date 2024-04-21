package com.example.collieapplication.collie

import SignupScreen
import androidx.compose.foundation.layout.fillMaxSize
import androidx.navigation.compose.NavHost
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

enum class CollieScreen {
    Login, Signup, Home, Adding, Settings
}

@Composable
fun CollieApp(userInputViewModel: UserViewModel = viewModel()) {


    val navController = rememberNavController()

    // TODO navController 는 프리뷰에서는 못 쓰는지?


    // 스크린 하당 뷰모델을 가지도록? vs 액티비티 하나당 뷰모델? -- 세기의 논란

    NavHost(
        navController = navController,
        startDestination = CollieScreen.Login.name,
    ) {
        composable(route = CollieScreen.Login.name) {
            LoginScreen(
                Modifier.fillMaxSize()
                , userInputViewModel.loginValue
                , onValueChange = { userInputViewModel.inputLoginValue(it) }
                , onGoToSignUpButtonClicked = {
                    userInputViewModel.clearLoginValue()
                    navController.navigate(CollieScreen.Signup.name)
                }
            )
        }
        composable(route = CollieScreen.Signup.name) {
            SignupScreen(
                Modifier.fillMaxSize()
                , userInputViewModel.signUpValue
                , onValueChange = { userInputViewModel.inputSignUpValue(it) }
                , onCompleteButtonClicked = {

                    if (userInputViewModel.completeSignUp()) {
                        navController.navigate(CollieScreen.Login.name)
                    }
                }
            )
        }
        composable(route = CollieScreen.Home.name) {

        }
        composable(route = CollieScreen.Adding.name) {

        }
        composable(route = CollieScreen.Settings.name) {

        }
    }

}

@Preview
@Composable
private fun CollieAppPreview() {
    CollieApp()
}