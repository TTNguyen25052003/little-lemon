package app.kotlin.littlelemon.network

import app.kotlin.littlelemon.model.ListOfFoodItem
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

private const val BASE_URL =
    "https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json"

private val httpClient: HttpClient = HttpClient(Android) {
    install(plugin = ContentNegotiation) {
        json(contentType = ContentType(contentType = "application", contentSubtype = "json"))
    }
}

suspend fun fetchData(): ListOfFoodItem {
    val jsonRawString: String = httpClient.get(BASE_URL).body()
    return Json.decodeFromString(jsonRawString)
}