package app.kotlin.littlelemon.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import app.kotlin.littlelemon.data.ListOfFoodItem
import app.kotlin.littlelemon.data.User
import app.kotlin.littlelemon.data.UserDatabase
import app.kotlin.littlelemon.data.fetchData

interface Repository {
    suspend fun getListOfFoodItem(): ListOfFoodItem
    val userDatabase: RoomDatabase
}

class LittleLemonRepository(
    context: Context
) : Repository {
    override suspend fun getListOfFoodItem(): ListOfFoodItem {
        return fetchData()
    }


    override val userDatabase = Room.databaseBuilder(
        context = context,
        UserDatabase::class.java,
        name = "userDatabase"
    ).build()

    suspend fun getUser(emailInput: String) =
        userDatabase.userDao().getUser(emailInput = emailInput)

    suspend fun createAccount(user: User) =
        userDatabase.userDao().createAccount(user = user)
}