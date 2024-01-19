package app.kotlin.littlelemon.model

import kotlinx.serialization.Serializable

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