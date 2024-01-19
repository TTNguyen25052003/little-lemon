package app.kotlin.littlelemon.data

import android.content.Context
import androidx.room.Room

interface UsersRepository {
    suspend fun getUser(emailInput: String): User?
    suspend fun createAccount(user: User)
}

class LocalUsersRepository(context: Context) : UsersRepository {
    private val userDatabase = Room.databaseBuilder(
        context = context,
        UserDatabase::class.java,
        name = "userDatabase"
    ).build()

    override suspend fun getUser(emailInput: String) =
        userDatabase.userDao().getUser(emailInput = emailInput)

    override suspend fun createAccount(user: User) =
        userDatabase.userDao().createAccount(user = user)
}