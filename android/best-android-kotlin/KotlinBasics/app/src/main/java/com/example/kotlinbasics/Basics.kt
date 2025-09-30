package com.example.kotlinbasics

fun main() {
//    val shoppingList = listOf("Processor", "RAM", "Graphics Card", "SSD")

    val shoppingList = mutableListOf("Processor", "RAM", "Graphics Card", "SSD")

    for (idx in 0 until shoppingList.size) {
        println("item = ${shoppingList[idx]}")
    }

}
