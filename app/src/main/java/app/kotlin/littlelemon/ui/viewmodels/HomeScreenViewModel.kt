package app.kotlin.littlelemon.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import app.kotlin.littlelemon.LittleLemonApplication
import app.kotlin.littlelemon.data.ListOfFoodItemsRepository
import app.kotlin.littlelemon.model.ListOfFoodItem
import coil.network.HttpException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface ConnectionState {
    object Successful : ConnectionState
    object Loading : ConnectionState
    object Error : ConnectionState
}

data class HomeScreenUiState(
    val listOfFoodItem: ListOfFoodItem = ListOfFoodItem(menu = emptyList()),
    val listOfFilterStatus: List<Boolean> = listOf(false, false, false, false),
    val foodChosenIndex: Int = 0
)

class HomeScreenViewModel(
    private val listOfFoodItemsRepository: ListOfFoodItemsRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeScreenUiState())
    val uiState: StateFlow<HomeScreenUiState> = _uiState.asStateFlow()

    var connectionState: ConnectionState by mutableStateOf(ConnectionState.Loading)

    val listOfCategory = listOf("starters", "desserts", "mains", "sides")
    private var _listOfFoodItem: ListOfFoodItem = ListOfFoodItem(menu = emptyList())

    var listOfFilterAction = mutableListOf<() -> Unit>()

    init {
        getListOfFoodItem()
        updateListOfFilterAction()
    }

    fun getListOfFoodItem() {
        viewModelScope.launch {
            try {
                connectionState = ConnectionState.Loading
                val result: ListOfFoodItem = listOfFoodItemsRepository.getListOfFoodItem()
                _listOfFoodItem = result
                updateListOfFoodItem(listOfFoodItem = _listOfFoodItem)
                connectionState = ConnectionState.Successful
            } catch (e: IOException) {
                connectionState = ConnectionState.Error
            } catch (e: HttpException) {
                connectionState = ConnectionState.Error
            }
        }
    }

    private fun updateListOfFoodItem(listOfFoodItem: ListOfFoodItem) {
        _uiState.update { currentState ->
            currentState.copy(listOfFoodItem = listOfFoodItem)
        }
    }

    private fun updateListOfFilterStatus(position: Int, valueChange: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(
                listOfFilterStatus = _uiState.value.listOfFilterStatus.mapIndexed { index, value ->
                    if (index == position) {
                        valueChange
                    } else {
                        value
                    }
                }
            )
        }
    }

    private fun ListOfFoodItem.search(keyword: String): ListOfFoodItem {
        return ListOfFoodItem(
            menu = this.menu.filter {
                it.title.contains(keyword, ignoreCase = true)
                        || it.category.contains(keyword, ignoreCase = true)
                        || it.description.contains(keyword, ignoreCase = true)
                        || it.price.contains(keyword, ignoreCase = true)
            }
        )
    }

    private fun ListOfFoodItem.filterByCategory(category: String): ListOfFoodItem {
        return ListOfFoodItem(
            menu = this.menu.filter { it.category == category }
        )
    }

    val searchAction: (String) -> Unit = { keyword ->
        _uiState.update { currentState ->
            currentState.copy(
                listOfFilterStatus = _uiState.value.listOfFilterStatus.map { false }
            )
        }
        updateListOfFoodItem(listOfFoodItem = _listOfFoodItem.search(keyword = keyword))
    }

    private fun updateListOfFilterAction() {
        for (i: Int in listOfCategory.indices) {
            listOfFilterAction.add {
                if (_uiState.value.listOfFilterStatus[i]) {
                    updateListOfFilterStatus(i, valueChange = false)
                    updateListOfFoodItem(listOfFoodItem = _listOfFoodItem)
                } else {
                    _uiState.update { currentState ->
                        currentState.copy(
                            listOfFilterStatus = _uiState.value.listOfFilterStatus.map { false }
                        )
                    }
                    updateListOfFilterStatus(i, valueChange = true)
                    updateListOfFoodItem(
                        listOfFoodItem = _listOfFoodItem.filterByCategory(category = listOfCategory[i])
                    )
                }
            }
        }
    }

    val chooseFoodItemAction: (Int) -> Unit = { index ->
        _uiState.update { currentState ->
            currentState.copy(foodChosenIndex = index)
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application: LittleLemonApplication =
                    (this[APPLICATION_KEY] as LittleLemonApplication)
                val listOfFoodItemsRepository: ListOfFoodItemsRepository =
                    application.container.listOfFoodItemsRepository
                HomeScreenViewModel(listOfFoodItemsRepository = listOfFoodItemsRepository)
            }
        }
    }
}