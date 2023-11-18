package app.kotlin.littlelemon.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import app.kotlin.littlelemon.data.User
import app.kotlin.littlelemon.di.LittleLemonRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class LoginProfileState(
    val user: User = User()
)

class LoginProfileScreenViewModel(
    private val littleLemonViewModel: LittleLemonRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(LoginProfileState())

    var userGot = User()

    fun updateEmail(emailInput: String) {
        _uiState.update { currentState ->
            currentState.copy(user = _uiState.value.user.copy(email = emailInput))
        }
    }

    fun updatePassword(passwordInput: String) {
        _uiState.update { currentState ->
            currentState.copy(user = _uiState.value.user.copy(password = passwordInput))
        }
    }

    fun getUser(emailInput: String) {
        viewModelScope.launch(IO) {
            userGot = littleLemonViewModel.getUser(emailInput = emailInput) ?: User()
        }
    }

    fun isLoginSuccessfully(): Boolean {
        if (userGot != User()) {
            return userGot.password != _uiState.value.user.password
        }
        return false
    }

    fun resetState() {
        userGot = User()
        _uiState.update { currentState ->
            currentState.copy(user = User())
        }
    }
}

@Suppress("UNCHECKED_CAST")
class LoginProfileScreenViewModelFactory(private val littleLemonRepository: LittleLemonRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginProfileScreenViewModel(littleLemonRepository) as T
    }
}