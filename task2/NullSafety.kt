package com.apero.task2

import com.apero.com.trainning.nullable

fun main(){
    nullableAndNonNullable()
    elvisOperator()
    safeCall()
    notNullAssertion()
}

fun  nullableAndNonNullable(){
    val nonNullableName: String = "Tiến Hưng"   // Không thể gán gtri null
    println("Biến: $nonNullableName có độ dài là: ${nonNullableName.length} ")

    var nullableName: String? = "hungct"
    println("Tên người dùng: $nullableName")

    nullableName = null
    println("Tên người dùng được đặt null: $nullableName")
}

fun elvisOperator(){
    val nameUser: String? = null
    val displayName = nameUser ?: "NoName"
    println("Tên hiển thị (với elvis): $displayName")

    val points: Int? = null
    val safePoints: Int = points ?: 0
    println("Điểm an toàn (với elvis): $safePoints")

}

fun safeCall(){
    val textNull: String? = null
    val lengthOrNull: Int? = textNull?.length
    val textReal: String? = "Android"
    val lengthNotNull: Int? = textReal?.length

    println("Độ dài chuỗi khi = null: $lengthOrNull")
    println("Độ dài chuỗi 'Android' (safe call): $lengthNotNull")

    textReal?.let { value ->
        println("Giá trị non-null trong let: '$value' (length = ${value.length})")
    }
}

fun notNullAssertion(){
    val nullableNumber: Int? = 10
    val assureNumber: Int = nullableNumber!!
    println("Giá trị sau khi dùng !! (ko null): ${assureNumber}")
    val willBeNull: String? = null
    try {
        val willCrash = willBeNull!!
        println("Nếu không null sẽ in ra hehe:$willCrash")

    }catch (e: NullPointerException){
        println("Nếu null thì in ra cái này")
    }
}

