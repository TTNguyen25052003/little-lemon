package app.kotlin.littlelemon.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import app.kotlin.littlelemon.data.User
import app.kotlin.littlelemon.data.UsersRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class UiState(
    val user: User = User(),
    val listOfValidation: List<Boolean> = listOf(false, false, false, false)
)

class OnboardingScreenViewModel(private val usersRepository: UsersRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    //It used to check if email of _uiState is exist
    private var userGot = User()

    private fun updateFirstname(firstnameInput: String) {
        _uiState.update { currentState ->
            currentState.copy(user = _uiState.value.user.copy(firstName = firstnameInput))
        }
    }

    private fun updateLastname(lastnameInput: String) {
        _uiState.update { currentState ->
            currentState.copy(user = _uiState.value.user.copy(lastName = lastnameInput))
        }
    }

    private fun updateEmail(emailInput: String) {
        _uiState.update { currentState ->
            currentState.copy(user = _uiState.value.user.copy(email = emailInput))
        }
    }

    private fun updatePassword(passwordInput: String) {
        _uiState.update { currentState ->
            currentState.copy(user = _uiState.value.user.copy(password = passwordInput))
        }
    }

    private fun updateFormValidation(position: Int, valueInput: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(
                listOfValidation = _uiState.value.listOfValidation.mapIndexed { index, value ->
                    if (index == position) {
                        valueInput
                    } else {
                        value
                    }
                }
            )
        }
    }

    private val updateFirstnameAction: (String) -> Unit = { firstnameInput ->
        updateFirstname(firstnameInput = firstnameInput)
        updateFormValidation(
            position = 0,
            valueInput = firstnameInput.isNotEmpty()
        )
    }

    private val updateLastnameAction: (String) -> Unit = { lastnameInput ->
        updateLastname(lastnameInput = lastnameInput)
        updateFormValidation(
            position = 1,
            valueInput = lastnameInput.isNotEmpty()
        )
    }

    private val updateEmailAction: (String) -> Unit = { emailInput ->
        updateEmail(emailInput = emailInput)
        updateFormValidation(
            position = 2,
            valueInput = (isValidEmail(email = emailInput) && userGot == User())
        )
    }

    private val updatePasswordAction: (String) -> Unit = { passwordInput ->
        updatePassword(passwordInput = passwordInput)
        updateFormValidation(
            position = 3,
            valueInput = passwordInput.isNotEmpty()
        )
    }
    val listOfUpdateAction: List<(String) -> Unit> = listOf(
        updateFirstnameAction,
        updateLastnameAction,
        updateEmailAction,
        updatePasswordAction
    )

    suspend fun getUser(emailInput: String) {
        userGot = usersRepository.getUser(emailInput = emailInput) ?: User()
    }

    //Check if form input data is valid
    private fun isValidEmail(email: String): Boolean {
        val emailRegex = Regex(pattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}\$")
        return (email.matches(emailRegex))
    }

    fun createAccount() {
        viewModelScope.launch(IO) {
            usersRepository.createAccount(user = _uiState.value.user)
        }
    }
}

@Suppress("UNCHECKED_CAST")
class OnboardingScreenViewModelFactory(private val usersRepository: UsersRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return OnboardingScreenViewModel(usersRepository) as T
    }
}