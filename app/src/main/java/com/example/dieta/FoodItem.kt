package com.example.dieta

data class FoodItem(
    val breakfastItem: Boolean,
    val calories: Int,
    val correctedTerm: String,
    val foodName: String,
    val foodType: String,
    val ketchupItem: Boolean,
    val sideItem: Boolean
)