package app.kotlin.littlelemon.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import app.kotlin.littlelemon.LittleLemonApplication
import app.kotlin.littlelemon.data.User
import app.kotlin.littlelemon.data.UsersRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class LoginProfileState(
    val user: User = User()
)

class LoginProfileScreenViewModel(private val usersRepository: UsersRepository) : ViewModel() {
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
            userGot = usersRepository.getUser(emailInput = emailInput) ?: User()
        }
    }

    fun isLoginSuccessfully(): Boolean {
        if (userGot != User()) {
            return userGot.password == _uiState.value.user.password
        }
        return false
    }

    fun resetState() {
        userGot = User()
        _uiState.update { currentState ->
            currentState.copy(user = User())
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application: LittleLemonApplication =
                    (this[APPLICATION_KEY] as LittleLemonApplication)
                val usersRepository:UsersRepository = application.container.usersRepository
                LoginProfileScreenViewModel(usersRepository = usersRepository)
            }
        }
    }
}