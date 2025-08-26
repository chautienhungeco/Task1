package com.apero.essaytest

fun main(){
    bai1()
    bai2()
    bai3()
    bai4()
    bai5()
    bai6()
    bai7()
    bai8()
    bai9()
    bai10()
    bai11()
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

// Cau truc dieu kien if-else
fun bai5(){
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

//for loop
fun bai6(){
    //in ra cac so từ 1-10
    for (i in 1..10){
        println("$i")
    }
    println()
    //in ra dáy số chăn 2-20
    for (i in 2..20 step 2){
        println("$i")
    }
    println()
}

// while và do-while
fun bai7(){
    // tinh tong tu 1-100 dung while
    var sum = 0
    var i = 1
    while (i <= 100){
        sum = sum + i
        i++
    }
    println("Tổng các số từ 1-100 = $sum")

    //nhập cho đến khi = 0
    var num: Int
    do{
        print("Nhập số (0 để dừng lại): ")
        num = readLine()!!.toInt()
        println("Bạn vừa nhập số: $num")
    }while (num != 0)
}


//Collection
fun  bai8(){
    //Tạo danh sách tên của 5 người và in ra
    val people = listOf("Hưng","Đạt","Linh","Chung","Nam")
    people.forEach(){
        println(it)
    }
}

//Set
fun bai9(){
    //tạo danh sách các số, có số trùng để in ra không thấy trùng
    val phones = setOf("1234","2345","3456","1234")
    println(phones)
    //ket qua chi có 3 số đc in ra
}

//map
fun bai10(){
    //tạo map id-> tên, in theo id
    val students = mapOf(1 to "Hưng", 2 to "Đạt", 3 to "Linh", 4 to "Chung", 5 to "Nam")
    println("Học sinh có mã ID = 2 là: ${students[2]}")
}

//một vài hàm thường dùng
fun  bai11(){
    val scores = listOf(4,7,9,10)
    //nhân đôi giá trị
    val double = scores.map { it * 2 }
    println("Dãy số được nhân đôi: $double")

    //in ra số lớn hơn 5
    val passed = scores.filter { it > 5 }
    println("Điểm lớn hơn 5: $passed")

    //tính tổng cộng dồn
    val total = scores.reduce { acc, i -> acc + i }
    println("Tổng các số: $total")

    //duyệt qua it và lấy phần tử đầu, cuối trong list
    scores.forEach() { println("Điểm: $it") }
    println("Số đầu tiên: ${scores.first()}, Số cuối cùng: ${scores.last()}")
}
