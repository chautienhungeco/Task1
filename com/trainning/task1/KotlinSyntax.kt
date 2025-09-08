package com.apero.com.trainning.task1

import java.io.File.separator

fun main(){
    variable()
    operator()
    typeCasting()
    template()
    normalizeName()
    showString()
    Formatting(5,6)
}

fun variable(){
    val name: String = "Hung"  //Hằng số không đổi
    val age: Int = 22   //hang so co the thay doi
    val gpa: Float = 7.5f   //diem trung binh
    val graduation: Boolean = true
    val salaryCoafficien: Double = 2.3451212
    val monthOfBirthday: Byte = 9

    println("HS Tên: $name, Độ Tuổi: $age, Điểm GPA: $gpa, Đã tốt nghiệp: $graduation")
    println("Hs Lương: $salaryCoafficien, Tháng sinh: $monthOfBirthday")
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
    val name: String = "cHâu tIẾN HưNg"
    val age: Int = 23

    println("Họ tên in hoa: ${name.uppercase()}")
    println("Họ tên in thường: ${name.lowercase()}")
    println("Độ tuổi là: $age")
    println("Thông tin: ${name.plus(age)}")
}

fun normalizeName(name: String = "cHau Tien huNG") : String {
    val strName: List<String> = name.trim().toLowerCase().split(" ")
    val resultUperCase = strName.map { word ->
        val trimmed = word.trim()
        if (trimmed.isNotEmpty()) {
            trimmed.substring(0, 1).uppercase() + trimmed.substring(1)
        } else {
            trimmed
        }
    }.joinToString (" ")
    println("Tên $name sau khi đặt quy tắc: $resultUperCase")
    return resultUperCase
}

fun showString(){
    val lineString = """
        Tiến Hưng
        23 tuổi
        đã tốt nghiệp
    """.trimIndent()
    println(lineString)
}

fun Formatting(a: Int, b: Int) {
    println(String.format("%d + %d = %d", a, b, a + b))
    val pi = 3.14159
    println(String.format("%.2f", pi))
}
