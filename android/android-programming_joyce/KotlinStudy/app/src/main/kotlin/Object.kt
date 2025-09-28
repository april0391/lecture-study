fun main() {
//    println(MyFirstObject.number)
//    MyFirstObject.sayHello()

//    println(Dinner.MENU)
//    println(Dinner.eatDinner())

    var a: String? = null
    println(a?.reversed())
}

object MyFirstObject {
    val number = 1
    fun sayHello() {
        println("Hello")
    }
}

class Dinner {
    val lunch = "steak"
    companion object {
        const val MENU = "pasta"
        fun eatDinner() {
            println("Today's Menu is $MENU")
        }
    }
}