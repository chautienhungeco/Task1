package com.apero.com.trainning.Task1
fun main(){
    conditionalExpressions()
    forLoop()
    doWhile()
}
fun conditionalExpressions(){
    val number: Int = 10
    if(number > 0){
        println("Đây là số dương")
    }
    else if (number < 0){
        println("Đây là số âm")
    }
    else{
        println("Đây là số 0")
    }

    val greeting = if ( number < 20){
        "xin chào"
    }
    else{
        "tạm biệt"
    }
    println(greeting)
}

fun forLoop(){
    val a = 1
    val b = 10
    var sum = 0
    val language = arrayOf("Lenin","Hồ Chí Minh","Mác")

    for (item in language)
        println(item)
    for ((index,value) in language.withIndex())
        println("$index - $value")

    for (i in 1..10){
        println("$i")
    }
    println()

    for (i in 2..20 step 2){
        println("$i")
    }
    println()

    for (i in b downTo a step 2){
        sum += i
    }
    println("sum =: $sum")
}

fun doWhile(){
    var sum = 0
    var i = 1
    var num: Int

    while (i <= 100){
        sum = sum + i
        i++
    }
    println("Tổng các số từ 1-100 = $sum")


    do{
        print("Nhập số (0 để dừng lại): ")
        num = readLine()!!.toInt()
        println("Bạn vừa nhập số: $num")
    }while (num != 0)
}
