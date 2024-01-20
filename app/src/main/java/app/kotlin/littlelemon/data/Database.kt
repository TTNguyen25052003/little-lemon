package app.kotlin.littlelemon.data

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase

@Entity
data class User(
    var firstName: String = "",
    var lastName: String = "",
    @PrimaryKey var email: String = "",
    var password: String = ""
)

@Dao
interface UserDao {
    @Query("SELECT * FROM User WHERE email = :emailInput")
    suspend fun getUser(emailInput: String): User?

    @Insert(onConflict = OnConflictStrategy.NONE)
    suspend fun createAccount(user: User)
}

@Database(
    entities = [User::class],
    version = 1,
    exportSchema = false
)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}