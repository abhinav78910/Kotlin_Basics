/**
 * Object declarations
If you need a singleton
- a class that only has got one instance
- you can declare the class in the usual way, but use the object keyword instead of class:
 * */

data class Car(val horsepowers: Int)

object CarFactory{
    val cars = mutableListOf<Car>()
    fun makeCar(horsepowers: Int): Car {
        val car = Car(horsepowers)
        cars.add(car)
        return car
    }
}

/**
 * Companion objects
 * If you need a function or a property to be tied to a class rather than to instances
 * of it (similar to @staticmethod in Python), you can declare it inside a companion object:
 * */

class Car1(val horsepowers: Int) {
    companion object Factory {
        val cars = mutableListOf<Car1>()

        fun makeCar(horsepowers: Int): Car1 {
            val car = Car1(horsepowers)
            cars.add(car)
            return car
        }
    }
}

/**
 * Facts-
 * In spite of this syntactical convenience, the companion object is a proper object on its own,
 * and can have its own supertypes - and you can assign it to a variable and pass it around.
 * If you're integrating with Java code and need a true static member, you can annotate a member
 * inside a companion object with @JvmStatic.
 *
 * A companion object is initialized when the class is loaded (typically the first time it's referenced
 * by other code that is being executed), in a thread-safe manner. You can omit the name, in which case
 * the name defaults to Companion. A class can only have one companion object, and companion objects can not be nested.
 *
 **/

/**
 * Object expressions
 * Consider an interface or a (possibly abstract) class, as well a function that takes an instance of it:
 * **/
interface Vehicle {
    fun drive(): String
}

fun start(vehicle: Vehicle) = println(vehicle.drive())

fun main(){
    /**
     * There will only ever be one instance of this class,
     * and the instance (which is created the first time it is accessed,
     * in a thread-safe manner) has got the same name as the class:
     * */

    val car = CarFactory.makeCar(150)
    println(CarFactory.cars.size)

    val car1 = Car1.makeCar(150)
//    println(Car1.cars.size)   //same as written below
    println(Car1.Factory.cars.size)

    /**
     * By using an object expression, you can now define an anonymous,
     * unnamed class and at the same time create one instance of it, called an anonymous object:
     * */

    start(object : Vehicle {
        override fun drive() = "Driving really fast"
    })
}
