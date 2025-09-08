package com.apero.task2

fun main(){
    regularFunc()
    singleLineFunc()
    defaultParametre()
    extensionFunction()
}

fun regularFunc(){ //Hàm thông thường
    val result = addTwoNumber(5,3)
    println("Tổng 5 + 3 = $result")

    sayHello()

    val userInfor = createUserInfor("Tiến Hưng",23,"Hà Nội")
    println("Người dùng $userInfor")

    printUsers("Lê Văn Luyện", 25)

    val grade = caculateGrade(85)
    println("Mức điểm 85 đạt loại: $grade")

}

fun addTwoNumber(a: Int, b: Int): Int{
    return a + b
}
fun sayHello(){
    println("Xin chào! Tôi là Hưng")
}
fun createUserInfor(name: String, age: Int, city: String): String{
    return "Tên: $name, Tổi: $age, thành phố: $city"
}
fun printUsers(name: String, age: Int): Unit{
    println("Thông tin người dùng:")
    println("Tên: $name")
    println("Tuổi: $age")
}
fun caculateGrade(score: Int): String{
    return when{
        score >= 90 -> "A+"
        score >= 80 -> "A"
        score >= 70 -> "B"
        score >= 60 -> "C"
        score >= 50 -> "D"
        else -> "F"
    }
}

fun singleLineFunc(){ //Hàm một dòng
    val squareResult = square(4)
    println("Binnh phương của số 4 là: $squareResult")

    val degreeFahranheit = temperatureConversion(27.0)
    println("Nhiệt độ 27 độ C = $degreeFahranheit độ F")

    val isEvenNumber = isEven(7)
    print("Số 7 có phải số chẵn không: ")
    if (isEvenNumber == true){
        println("Đúng!")
    }else{
        println("Sai!")
    }

    val firstChar = getFirstChar("Javascript")
    println("Kí tự đầu của chuỗi Javascript: $firstChar")

    val isNull = isEmpty(null)
    println("chuỗi null có rỗng không: $isNull")
}
fun square(number1: Int): Int = number1 * number1

fun temperatureConversion(temparature: Double): Double = ((temparature.times(9.0)).div(5)).plus(32)

fun isEven(number2: Int): Boolean = number2.mod(2) == 0

fun getFirstChar(word: String): Char = word.firstOrNull() ?: ' '

fun isEmpty(text: String?): Boolean = text.isNullOrEmpty()

fun defaultParametre(){
    println("Mẫu chào hỏi")
    greetingDefault("Hưng")
    greetingDefault("Hoa","Chào cả nhà")
    greetingDefault("Hải","Nè","!!!?")

    println(createProfile("Hoàng Anh", 24))
    println(createProfile("An", email = "abc@gmail.com", isActive = false))

}

fun greetingDefault(name: String, greeting: String = "Xin chào", punctuation: String = "!"){
    println("$name $greeting$punctuation")
}

fun createProfile(
    name: String,
    age: Int = 23,
    email: String = "hungct@gmail.com",
    isActive: Boolean = true
): String{
    return "Profile cá nhân: $name, $age, $email, ${if (isActive) "Đang hoạt động" else "Không hoạt động"}"
}

fun extensionFunction(){
    val text = "cHÀO thế giới"
    val number = listOf(9,20,12,88,40)
    val mutableList = mutableListOf("A","B")

    println("auto thêm dấu . vào cuối câu")
    println("${text.addPeriod()}")
    println("$text --> '${text.capitalizeFirst()}'")
    println("$text Chuỗi đảo ngược: ${text.reversed()} ")
    println("Số 16 có phải số nguyên tố không: ${16.isPrime()}")
    println("Tổng dãy $number là: ${number.sumInt()}")
    println("Danh sách ban đầu: $mutableList")
    mutableList.addAll("C","D","E")
    println("Cập nhật thêm các phần tử C,D,E: $mutableList")
}

fun String.addPeriod(): String = this + "."  //Thêm dấu . vào cuối câu

fun String.capitalizeFirst(): String {  //viết hoa chữ cái đầu
    return if (this.isNotEmpty()){
        this[0].uppercaseChar() + this.substring(1).lowercase()
    }else{
        this
    }
}

fun String.revered(): String = this.revered() //hàm đảo nguược

fun Int.isPrime(): Boolean{ // kiểm tra số nguyên tố
    if (this < 2) return false
    for (i in 2 until this ){
        if (this.mod(i) == 0) return false
    }
    return true
}

fun List<Int>.sumInt(): Int{    //tổng phần tử
    var total = 0
    for (number in this){
        total += number
    }
    return total
}

fun <T >MutableList<T>.addAll(vararg items: T){ //thêm phần tử
    for (item in items){
        this.add(item)
    }
}
