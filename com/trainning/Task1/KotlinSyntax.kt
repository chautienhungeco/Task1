package com.apero.com.trainning.Task1

fun main(){
    variable()
    operator()
    typeCasting()
    template()
}

fun variable(){
    val name: String = "Hung"  //Hằng số không đổi
    var age: Int = 22   //hang so co the thay doi
    var gpa: Float = 7.5f   //diem trung binh
    println("HS Tên: $name, Độ Tuổi: $age, Điểm GPA: $gpa")
}

//Toán tu
fun operator(){
    var a = 10
    var b = 3
//    println("Tổng 2 số: ${a + b}")
    println("Tổng 2 số: ${a.plus(b)}")
//    println("Hiệu 2 số: ${a - b}")
    println("Hiệu 2 số: ${a.minus(b)}")
//    println("Tích 2 số: ${a * b}")
    println("Tích 2 số: ${a.times(b)}")
//    println("Thương 2 số: ${a / b}")
    println("Thương phần nguyên: ${a.div(b)}")
//    println("Phép chia dư: ${a % b}")
    println("Thương lấy phần dư: ${a.mod(b)}")
    a += 5
    println("Sau khi a += 5: $a")
    b -= 2
    println("Sau khi b -= 2: $b")
    if (a >= b){
        println("a là phần tử lớn hơn")
        a
    }else{
        println("b là phần tử lớn hơn")
        b
    }
    if (a.equals(b) ?: (b === null)){
        println("a bằng b")
    }else{
        println("a khác b")
    }
}

// ép kiểu
fun typeCasting(){
    val number: Double = 9.75
    val intNumber: Int = number.toInt()
    val stringNumber: String = number.toString()

//    val unsafe: String = null as String   //unsafe
    val unsafe: String? = null as String?
    val safe: String? = number as? String // bên phải không thể null, kết quả của ép kiểu có thể null
    println("Kiểu double: $number, Kiểu Int: $intNumber, Kiểu String: $stringNumber")

    if (stringNumber !is String || stringNumber.length == 0)
        return
    if (stringNumber is String && stringNumber.length > 0 ){
        println("Chuỗi stringNumber có độ dài là: ${stringNumber.length}")
    }

    println(unsafe)
    when(safe){
        is String -> println(safe.length + 1)
    }
}

//Dạng string teamplate
fun template(){
    println("Nhập họ tên: ")
    val name = readln()
    println("Nhập vào độ tuổi")
    val age = readln()
    println("Người dùng $name có độ tuổi $age")
}
