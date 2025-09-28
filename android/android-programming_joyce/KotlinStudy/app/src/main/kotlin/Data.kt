fun main() {
    val memo = Memo("Go to grocery", "Eggs, Milk, Pork", false)
    println(memo.toString())

    val memo2 = memo.copy("Go to home")
    println(memo2.toString())
}

data class Memo(val title: String, val content: String, var isDone: Boolean)
