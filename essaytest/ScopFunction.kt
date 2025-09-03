package com.apero.essaytest

fun main(){
    //.let - trả về biểu thức cuối cùng trong cùng khối lệnh
    val Name: String? = "Tiến Hưng"
    Name?.let {
        println("Độ dài của tên la: ${it.length}")
        "chào mừng bạn $it"
    }.also {
        println("kết quả từ let: $it")
    }

    //run - gom chuỗi các lệnh thành 1 khối duy nhất
    val user = User("Hưng nè", 8)
    val description = user.run{
        println("User name: $name")
        "Ten laf: $name, Do Tuoi: $age"
    }
    println(description)

    //with - KHONG PHẢI ham mở rôộng, nận đối tượng làm tham số đầu tiên
    // va khối lệnh làm tham số thu 2
    //trả về kết quả của biêu thức cuối cung
    val numbers = mutableListOf("one","two","three")
    with(numbers){
        println("with được gọi với đối số $this ")
        println("kich thước danh sách: $size")
        add("four")
        println(this)
    }

    //apply - cấu hình một đối tượng
    //thực thi một khối lệnh trên đối tượng, và trả về chinh doi tương đó


}
data class User(val name: String, val age: Int)