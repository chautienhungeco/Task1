package com.apero.task2

import java.lang.StringBuilder

fun main(){
    letFunction()
    runFuction()
    withFunction()
    applyFunction()
}
fun letFunction(){
    val name: String? = "Hưng"
    val  result = name?.let {
        println("Tên không được phép null: $it")
        it.uppercase()
    }
    println("Kết quả: $result")

    val nullName: String? = null
    val nullrResult = nullName?.let {
        println("Nếu null sẽ không in ra được")
        it.uppercase()
    }
    println("Kết quả null: $nullrResult")
}

fun runFuction(){
    val person = Person("Đạt", 22) //class Person ở file OOPInKotlin
    val info = person.run {
        println("In ra $name")
        "Name: $name, $age tuổi"
    }
    println("Thong tin người: $info")

    val nullablePerson: Person? = Person("ALong",30)
    val nullableInfor = nullablePerson?.run {
        "Name: $name, Age: $age"
    } ?: "Tên không tồn tại"   //toán tử elvis
}

fun withFunction(){
    val person = Person("Trường",20)
    val desciption = with(person){
        "Họ tên: $name, Tuổi: $age"
    }
    println("Thông tin mô tả: $desciption")

    val text = with(StringBuilder()){//tạo chuỗi
        append("Hello")
        append("World")
        toString()
    }
    println("Text: $text")
}

fun applyFunction(){
    val numbers = mutableListOf<Int>().apply {
        add(1)
        add(2)
        add(3)
        addAll(listOf(4, 5))
    }
    println("Numbers: $numbers")

    val config = mutableMapOf<String, Any>().apply {
        put("host", "localhost")
        put("port", 8080)
        put("debug", true)
    }
    println("Config: $config")
}
