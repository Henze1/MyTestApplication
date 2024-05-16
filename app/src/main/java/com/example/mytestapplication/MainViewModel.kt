package com.example.mytestapplication

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mytestapplication.data.User
import com.example.mytestapplication.data.UsersApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val api: UsersApi
) : ViewModel() {

    private val _userState = mutableStateOf(UserState())
    val userState: State<UserState> = _userState

    init {
        getUser()
    }

    fun getUser() {
        viewModelScope.launch {
            try {
                _userState.value = userState.value.copy(isLoading = true)
                _userState.value = userState.value.copy(
                    user = api.getUser(),
                    isLoading = false)
                Log.i("MainViewModel", "User: ${userState.value.user?.name}")
            } catch (e: Exception) {
                _userState.value = userState.value.copy(isLoading = false)
                Log.e("MainViewModel", "Error: ${e.message}")
            }
        }
    }

    data class UserState(
        val user: User? = null,
        val isLoading: Boolean = false,
    )

}