package com.example.collieapplication.collie

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {

    var state = MutableStateFlow("")

    private var _signUpValue by mutableStateOf("")
    val signUpValue: String
        get() = _signUpValue
                                  //TODO LiveData vs stateflow
    private var _loginValue by mutableStateOf("")
    val loginValue: String
        get() = _loginValue

    fun inputSignUpValue(input: String) {
        _signUpValue = input
    }

    fun clearSignUpValue() {
        _signUpValue = ""
    }

    fun completeSignUp(): Boolean {
        return if(signUpValue.isBlank()) {
            state.value = "blank"
            //Toast.makeText(context, "Please, Enter the id!", Toast.LENGTH_LONG).show()
            false
        } else {
            state.value = "completeSignUp"
            clearSignUpValue()
            //Toast.makeText(context, "Welcome~! Complete Sign Up!", Toast.LENGTH_LONG).show()
            true
        }
    }

    fun inputLoginValue(input: String) {
        _loginValue = input
    }

    fun clearLoginValue() {
        _loginValue = ""
    }
    //
    fun logPrint() {
        viewModelScope.launch {

        }
    }
}