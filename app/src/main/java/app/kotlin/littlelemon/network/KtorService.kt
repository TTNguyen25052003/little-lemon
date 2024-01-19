package app.kotlin.littlelemon.network

import app.kotlin.littlelemon.model.ListOfFoodItem
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.serialization.json.Json

suspend fun fetchData(
    httpClient: HttpClient,
    baseUrl: String = ""
): ListOfFoodItem {
    val jsonRawString: String = httpClient.get(baseUrl).body()
    return Json.decodeFromString(jsonRawString)
}