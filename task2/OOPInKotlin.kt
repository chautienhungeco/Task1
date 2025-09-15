package com.apero.task2

import kotlin.math.pow

private const val TAG = "OOPInKotlin"

fun main(){
    showClassAndObject()
    showConstructor()
    showInheritant()
    showInterfaceAndAbstractClass()
    showDataClass()
    showSingletonAndCompanionObject()
    showOuterClass()
    showInnerClass()
    showEnumClass()
}
fun showClassAndObject(){
    val person1 = Person("Châu Tiến Hưng", 23)
    person1.greeting()
    person1.haveBirthday()
}

fun showConstructor(){
    val student1= Students("hs01","Tiến Hưng","A")
    student1.study()

    val student2 = Students("hs02","Văn Đạt",21, "A+")
    student2.study()
}

fun showInheritant(){
    val car = Vehicle("Rolls royce","Oto")
    car.makeCar()
    car.move()

    val motobike = Motobike("Vision","Honda")
    motobike.makeCar()
    motobike.move()
}

fun showInterfaceAndAbstractClass(){
    val eagle = Eagle("Đại bàng")
    eagle.buildNest()
    eagle.fly()
    eagle.land()
    eagle.swim()
}

fun showDataClass(){
    val user1 = User(1,"Long","Log@gmail.com")
    val user2 = User(2, "Hà nè","ha@gmail.com",false)

    println("Người dùng 1: ${user1.disPlayName()}")
    println("Người dùng 2: ${user2.disPlayName()}")

    val user1Copy = user1.copy(name = "Trường")
    println("Sao chép người dùng 1 + thay tên: $user1Copy")
    println("So sanh người dùng 1 == người dùng 1 copy: ${user1 == user1Copy}")

    val product = Product("Adr001", "Android", 999.99,true)
    println("Thông tin sản phẩm: ${product.name} - ${product.getFormatteredPrice()}VND")
    println("Sản phẩm có đắt không: ${product.isExpensive()}")
}
fun showSingletonAndCompanionObject(){
    Appconfig.set("Tên App","Android studio")
    Appconfig.set("Phiên bản","1.1.1.1")

    println("App name: ${Appconfig.get("Tên App")}")
    println("Version: ${Appconfig.get("Version")}")

    println("Giá trị của PI: ${Matthutils.PI}")
    println("Tông 5+3: ${Matthutils.add(5,3)}")
    println("Diện tích hình tròn có bán kính 5: ${Matthutils.circleArea(5.0)}")

}
class Person(val name: String, var age: Int){
    fun greeting(){
        println("Xin chào mọi người, mình là $name")
    }
    fun haveBirthday(){
        age++
        println("Nay là sinh nhật $age tuổi của $name")
    }
}

class Students(val studentId: String, private val name: String, private var grade: String){ //constructor chính
    constructor(studentId: String, name: String, age: Int, grade: String): this(studentId, name, grade){    //constructor phụ
        println("Thêm học sinh ID: $studentId  , tên $name, $age tuổi, $grade điểm")
    }
    fun study(){
        println("Học sinh $name đang có điểm là $grade")
    }
}

open class Vehicle(val name: String, private val vehicleType: String){
    open fun makeCar(){
        println("$name ($vehicleType) ")
    }
    open fun move(){
        println("$name đang di chuyển")
    }
}

class Motobike(name: String, private val brand: String): Vehicle(name, "Xe máy"){
    override fun makeCar() {
        println("$name (thương hiệu $brand)")
    }
    override fun move(){
        println("$name di chuyển bằng 2 bánh")
    }
}

interface Flyable{
    fun fly()
    fun land()
}

interface Swinable{
    fun swim()
}

abstract class Bird(val name: String) : Flyable{
    abstract fun buildNest()

    override fun fly() {
        println("$name bay được trên trời")
    }

    override fun land() {
        println("$name đi độ trên mặt đất")
    }
}

class Eagle(name: String) : Bird(name), Swinable{
    override fun buildNest() {
        println("$name xây tổ trên cây")
    }

    override fun swim() {
        println("$name không biết bơi")
    }
}

data class User(val id: Int, val name: String, val email: String, val isActive: Boolean = true){
    fun disPlayName(): String = if (isActive) name else "$name chưa kích hoạt"
    companion object{
        fun createSample(): User = User(1, "Hưng nè","hungct@gmail.com")
    }
}

data class Product(val id: String, val name: String, val price: Double, val inStock: Boolean){
    fun getFormatteredPrice(): String = String.format("%.2f",price)
    fun isExpensive(): Boolean = price > 100.0
}

object Appconfig{
    private val config = mutableMapOf<String, Any>()

    fun set(key: String, value: Any){
        config[key] = value
        println("đặt cấu hình: $key = $value ")
    }

    fun get(key: String): Any? = config[key]
}

class Matthutils{
    companion object{
        const val PI = 3.14159

        fun add(a: Int, b: Int): Int = a.plus(b)
        fun circleArea(radius: Double): Double = PI.times(radius.pow(2))
    }
}

class OuterClass{
private var name: String = "Tiến Hưng"
class NestedClass {
    val description: String = "Thông báo từ nesated class"
    private val id: Int = 101
    fun foo(){
//        println("Tên tôi là: $name") // không thể truy cập
        println("Id của tôi là : $id")
    }
}}

class OuterClass2{
    private var age: Int = 23
    inner class InnerClass{
        fun printAge() = "Tôi $age tuổi - Inner class"
    }
}

enum class Color(val red: Int, val green: Int, val blue: Int){
    RED(255,0,0),
    GREEN(255,165,0),
    BLUE(0,0,255)
}

fun showOuterClass(){
    println(OuterClass.NestedClass().description)
    val obj = OuterClass.NestedClass()
    obj.foo()
}

fun showInnerClass(){
    val obj2 = OuterClass2().InnerClass().printAge()
    println(obj2)
}

fun showEnumClass(){
    val color = Color.RED
    println("Mã màu của Red trong RGB: R: ${color.red}, G: ${color.blue}, B: ${color.blue}")
}

