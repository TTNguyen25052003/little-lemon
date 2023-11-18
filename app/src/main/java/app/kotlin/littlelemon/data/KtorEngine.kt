package app.kotlin.littlelemon.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

private const val BASE_URL =
    "https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json"

private val httpClient: HttpClient = HttpClient(Android) {
    install(plugin = ContentNegotiation) {
        json(contentType = ContentType(contentType = "application", contentSubtype = "json"))
    }
}

@Serializable
data class ListOfFoodItem(
    val menu: List<FoodItem>
)

@Serializable
data class FoodItem(
    val id: Int,
    val title: String,
    val description: String,
    val price: String,
    val image: String,
    val category: String
)

suspend fun fetchData(): ListOfFoodItem {
    val jsonRawString: String = httpClient.get(BASE_URL).body()
    return Json.decodeFromString(jsonRawString)
}
