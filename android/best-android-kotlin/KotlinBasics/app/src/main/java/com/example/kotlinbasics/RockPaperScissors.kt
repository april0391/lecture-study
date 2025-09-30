package com.example.kotlinbasics

import kotlin.random.Random

fun main() {
    val computerHand = Hand.random()
    val userHand = readUserHand()

    val result = getResult(computerHand, userHand)

    println("computer = ${computerHand.name.lowercase()}, user = ${userHand.name.lowercase()}, result = $result")
}

private fun readUserHand(): Hand {
    while (true) {
        println("Enter 0 for ROCK, 1 for PAPER, 2 for SCISSORS:")
        val input = readLine()?.toIntOrNull()
        val hand = input?.let { Hand.fromInt(it) }

        if (hand != null) return hand
        println("Invalid input. Try again.")
    }
}

private fun getResult(computer: Hand, user: Hand): String =
    when ((user.ordinal - computer.ordinal + 3) % 3) {
        0 -> "draw"
        1 -> "win"
        else -> "lose"
    }

enum class Hand {
    ROCK, PAPER, SCISSORS;

    companion object {
        fun fromInt(value: Int): Hand? = when (value) {
            0 -> ROCK
            1 -> PAPER
            2 -> SCISSORS
            else -> null
        }

        fun random(): Hand = Hand.entries[Random.nextInt(Hand.entries.size)]
    }
}
