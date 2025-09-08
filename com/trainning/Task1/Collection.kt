package com.trainning.Task1

fun main(){
    collection()
    set()
    map()
    common()
    remove()
    arrayToString()
}

fun  collection(){
    val list = mutableListOf(3,6,9)
    val people = listOf("Hưng","Đạt","Linh","Chung","Nam")
    people.forEach(){
        println(it)
    }
    people.forEachIndexed{index: Int, value: String ->
        println("$index - $value")
    }
    //any tra vê true nếu có 1 trong các giá trị đúng
    people.any { it == "Hưng" }

    //all yeu cầu tất cả phải đúng
    people.all { it == "Hưng" && it == "Đạt" }

    //count trả về số lượng ptu thỏa mãn điều kiện
    people.count { it == "Hưng" }
    //fold tổng từ gtri khơi tạo -> cuối, foldright tổng từ cuối -> đầu)
    val result = list.fold(0, {total: Int, i: Int ->
        total + i
    })
    println("kết quả tổng các phần tử: $result")

    //sumby tổng tất cả nhưng được xử lý thông qua logic truyền vào
    val sum = list.sumBy { it * 3 }
    println("Tổng các phần tử của mảng sau khi * 3: $sum")
}

fun set(){
    val phones = setOf("1234","2345","3456","1234")
    println(phones)
}

fun map(){
    //tạo map id-> tên, in theo id
    val students = mapOf(1 to "Tiến Hưng", 2 to "Đạt", 3 to "Linh", 4 to "Chung", 5 to "Trần Hưng")
    println("Học sinh có mã ID = 2 là: ${students[2]}")
    //tìm theo tên
    val findStudents = students.filterValues { it.contains("Hưng", ignoreCase = true) }.values
    println("Danh sách hs tên Hưng: $findStudents")
}

//một vài hàm thường dùng
fun  common(){
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

//remove duplicate strings
fun remove(){
    val devs = arrayOf("Hưng", "Hoài", "Hoa", "Huệ","Hưng","hưng")
    println(devs.toSet())
    //println(devs.distinct())
    //println(devs.toMutableSet())
    //println(devs.toHashSet()) //Không duy trì thứ tự ban đầu
}
//convert array or list to string
fun arrayToString(){
    val listCities = listOf("Hà Nội", "Bắc Ninh", "Đà Nẵng")
    val messenge = listCities.joinToString(
        separator = ",",
        postfix = ": Là các tỉnh thành ở Hà Nội"
    )
    print(messenge)
}