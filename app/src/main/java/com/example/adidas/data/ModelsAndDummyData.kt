package com.example.adidas.data

import com.example.adidas.R

//region models
data class ShoeCategory(
    val name: String = "",
    val products: List<ShoeModel> = arrayListOf()
)

data class ShoeModel(
    val name: String = "",
    val startingPrice: Double = 0.00,
    val bestChoiceOrNot: Boolean = false,       //to navigate to detail screen
    val imageId: Int
)

//data class ProductDetails(
//    val shoeModel: ShoeModel,
//    val shoeSizes: List<Int>,
//    val chosenSize: Int,
//)
//
//data class OrderDetails(
//    val shoeModel: ShoeModel,
//    val shoeSize: Int
//)
//endregion

//region "database contents"
val listOfManProducts = arrayListOf<ShoeModel>(
    ShoeModel(
        "Adidas Man 1",
        startingPrice = 10.00,
        bestChoiceOrNot = true,
        imageId = R.drawable.adidas_men_one
    ),
    ShoeModel(
        "Adidas Man 2",
        startingPrice = 14.00,
        bestChoiceOrNot = false,
        imageId = R.drawable.shoe_rm
    ),
)

val listOfWomanProducts = arrayListOf(
    ShoeModel(
        "Adidas Woman 1",
        startingPrice = 16.00,
        bestChoiceOrNot = true,
        imageId = R.drawable.adidas_women_one
    ),
    ShoeModel(
        "Adidas Woman 2",
        startingPrice = 10.00,
        bestChoiceOrNot = false,
        imageId = R.drawable.shoe_rm
    ),
)

val listOfNewArrivals = arrayListOf<ShoeModel>(
    ShoeModel(
        "New Arrival 1",
        startingPrice = 77.00,
        bestChoiceOrNot = true,
        imageId = R.drawable.adidas_newarrival_one
    ),
    ShoeModel(
        "New Arrival 2",
        startingPrice = 10.00,
        bestChoiceOrNot = false,
        imageId = R.drawable.shoe_rm
    ),
)

val listOfProductCategories = arrayListOf(
    ShoeCategory("listOfManProducts", listOfManProducts),
    ShoeCategory("listOfWomanProducts", listOfWomanProducts),
    ShoeCategory("listOfNewArrivals", listOfNewArrivals),
)

val listOfShoesSizes = arrayListOf(1, 2, 3, 4, 5, 6, 7)
//endregion

