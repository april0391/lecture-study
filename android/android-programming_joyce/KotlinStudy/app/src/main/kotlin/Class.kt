fun main() {
    val car1 = Car("red", "Volvo", 13)
    val car2 = Car("yellow", "Hyundai", 15)
    val car3 = Car("yellow", "Hyundai")

//    println("car color = ${car1.color}")

}

class Car(val color: String, val name: String, val age: Int) {
    init {
        println("Primary constructor is called")
    }

    init {
        println("Color : $color Name : $name Age : $age" )
    }

    constructor(color: String, name:String) : this(color,name,12) {
        println("Secondary constructor is called")
    }
}
