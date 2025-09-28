fun main(arg: Array<String>) {
    val numRange : IntRange = 1 .. 5
//    println(numRange.contains(2))

    for (i in 1..5) {

    }

    val grade = getGrade(40)
//    println(grade)

    /**
     * List
     */
    val fruitList = listOf("Banana", "Apple", "Melon")
//    println("First fruit ${fruitList[0]}")

    val mutableFruitList = mutableListOf("Banana", "Apple", "Melon")
    println("mutableFruitList = ${mutableFruitList[0]}")
    mutableFruitList[0] = "Strawberry"
    println("mutableFruitList = ${mutableFruitList[0]}")
    println("mutableFruitList = ${mutableFruitList}")

    mutableFruitList.forEach { fruit -> println("fruit = $fruit") }

    /**
     * Set
     */
    val immutableSet = setOf(1,1,2,3,4,4,5)
    println("numRange = ${immutableSet}")

    /**
     * Map
     */
    val immutableMap = mapOf("name" to "Joyce", "height" to "160", "age" to 20)
    println("immutableMap = $immutableMap")

    val mutableMap = mutableMapOf("name" to "Joyce", "height" to "160", "age" to 20)
    println("mutableMap = ${mutableMap}")
}

fun getGrade(score: Int): Char {
    return when (score) {
        in 0..40 -> 'D'
        in 41..70 -> 'C'
        in 71 .. 90 -> 'B'
        in 91 .. 100 -> 'A'
        else -> 'Z'
    }
}
