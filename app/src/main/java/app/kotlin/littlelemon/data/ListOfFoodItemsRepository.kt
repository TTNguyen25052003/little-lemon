package app.kotlin.littlelemon.data

import app.kotlin.littlelemon.model.ListOfFoodItem
import app.kotlin.littlelemon.network.fetchData

interface ListOfFoodItemsRepository {
    suspend fun getListOfFoodItem(): ListOfFoodItem
}

class NetWorkListOfFoodItemsRepository() : ListOfFoodItemsRepository {
    override suspend fun getListOfFoodItem(): ListOfFoodItem {
        return fetchData()
    }
}