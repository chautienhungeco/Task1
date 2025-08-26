package com.apero.essaytest

fun main(){
    bai1()
    bai2()
    bai3()
    bai4()
    bai5()
}
// khai bao bien và hằng số
fun bai1(){
    val name: String = "Hung"  //Hằng số không đổi
    var age: Int = 22   //hang so co the thay doi
    var gpa: Float = 7.5f   //diem trung binh
    println("HS Tên: $name, Độ Tuổi: $age, Điểm GPA: $gpa")

}
//Toán tu
fun bai2(){
    var a = 10
    var b = 3
    println("Tổng 2 số: ${a + b}")
    println("Hiệu 2 số: ${a - b}")
    println("Tích 2 số: ${a * b}")
    println("Thương 2 số: ${a / b}")
    println("Phép chia dư: ${a % b}")

    a += 5
    println("Sau khi a += 5: $a")
    b -= 2
    println("Sau khi b -= 2: $b")
}

// ép kiểu
fun bai3(){
    val number: Double = 9.75
    val intNumber: Int = number.toInt()
    val stringNumber: String = number.toString()

    println("Kiểu double: $number, Kiểu Int: $intNumber, Kiểu String: $stringNumber")
}

//Dạng string teamplate
fun bai4(){
    val name = "Hung"
    val age = 22
    println("Xin chào anh, em là $name và em $age tuổi!")
}

// Cau truc dieu kien va vong lap
fun bai5(){
    // dieu kien if-else
    val number: Int = 0
    if(number > 0){
        println("Đây là số dương")
    }
    else if (number < 0){
        println("Đây là số âm")
    }
    else{
        println("Đây là số 0")
    }
}