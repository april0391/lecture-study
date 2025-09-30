package com.example.myshoppinglistapp

fun main() {
    // nullable string
    val name: String? = "Ella"

    name?.let {
        println(it.uppercase())
    }
}