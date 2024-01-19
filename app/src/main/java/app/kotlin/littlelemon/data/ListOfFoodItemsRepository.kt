package app.kotlin.littlelemon.data

import app.kotlin.littlelemon.model.ListOfFoodItem
import app.kotlin.littlelemon.network.fetchData
import io.ktor.client.HttpClient

interface ListOfFoodItemsRepository {
    suspend fun getListOfFoodItem(): ListOfFoodItem
}

class NetWorkListOfFoodItemsRepository(
    private val httpClient: HttpClient,
    private val baseUrl: String
) : ListOfFoodItemsRepository {
    override suspend fun getListOfFoodItem(): ListOfFoodItem {
        return fetchData(
            httpClient = httpClient,
            baseUrl = baseUrl
        )
    }
}