package app.kotlin.littlelemon.data

import android.content.Context
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json

interface AppContainer {
    val listOfFoodItemsRepository: ListOfFoodItemsRepository
    val usersRepository: UsersRepository
}

class DefaultAppContainer(context: Context) : AppContainer {
    private val BASE_URL =
        "https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json"

    private val httpClient: HttpClient = HttpClient(Android) {
        install(plugin = ContentNegotiation) {
            json(
                contentType = ContentType(
                    contentType = "application",
                    contentSubtype = "json"
                )
            )
        }
    }


    override val listOfFoodItemsRepository by lazy {
        NetWorkListOfFoodItemsRepository(
            httpClient = httpClient,
            baseUrl = BASE_URL
        )
    }

    override val usersRepository by lazy { LocalUsersRepository(context = context) }
}