package com.trainning.Task1

fun main(){
    collectionList()
    collectionSet()
    collectionMap()
    commonFunction()
    array()
    interativeCollection()
}

fun  collectionList(){
    val listNumber = mutableListOf(3,6,9,12,4,7)
    val people = listOf("Hưng","Đạt","Linh","Chung","Nam")

    println("Phần tử đầu của dãy số: ${listNumber.first()}")
    println("Phần tử cuối của dãy số: ${listNumber.last()}")
    println("Kích thước dãy số: ${listNumber.size}")
    println("Sắp xếp các phần tử lớn hơn 5: ${listNumber.sortedBy { it > 5 }}")
    println("Tổng các phần tử của mảng sau khi * 3: ${listNumber.sumBy { it * 3 }}")

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
    val result = listNumber.fold(0, {total: Int, i: Int ->
        total + i
    })
    println("kết quả tổng các phần tử: $result")


}

fun collectionSet(){
    val phones = setOf("1234","2345","3456","1234")
    val mutableFruit: MutableSet<String> = mutableSetOf("Chuối", "Táo", "Cam")
    val immutableFlower = setOf("Hoa Sen", "Hoa Hồng", "Hoa Quỳnh")


    println(phones)
    println(mutableFruit)
    println(immutableFlower)

    mutableFruit.add("Đào") //thêm phần tử
    println("Thêm quả đào: $mutableFruit")

    mutableFruit.remove("Táo") //xóa ptu
    println("xóa bỏ quả táo: $mutableFruit")

    val containsApple = mutableFruit.contains("Táo") //kiểm tra tồn tại phần tử -> bool
    println("Kiểm tra quả táo có không: $containsApple")

    println("Danh sách các loại quả: ")
    for (fruit in mutableFruit){ //Lặp qua từng phần tử
        println(fruit)
    }

    val immutableToArray = immutableFlower.toTypedArray() //chuyển tập hợp không đổi thành mảng
    println("Chuyển tập hợp thành mảng: ${immutableToArray.contentToString()}")

}

fun collectionMap(){
    //tạo map id-> tên, in theo id
    val students = mapOf(1 to "Tiến Hưng", 2 to "Đạt", 3 to "Linh", 4 to "Chung", 5 to "Trần Hưng")

    println(students.keys)
    println(students.values)
    println(students)

    println("Học sinh có mã ID = 2 là: ${students[2]}") //tìm kiếm theo id

    val findStudents = students.filterValues { it.contains("Hưng", ignoreCase = true) }.values // tìm theo tên
    println("Danh sách hs tên Hưng: $findStudents")

    val filterMap = students.filter { (key, value) -> value.endsWith("Hưng") && key == 1  }
    println(filterMap)

    println(students + Pair("Hoàng Hà", 6))
    println(students - "Đạt")
    println(students - listOf("Linh", "Chung"))
}

//một vài hàm thường dùng
fun  commonFunction(){
    val scores = listOf(4,7,9,10)

    val double = scores.map { it * 2 }
    println("Dãy số được nhân đôi: $double")


    val passed = scores.filter { it > 5 }
    println("Điểm lớn hơn 5: $passed")

    val total = scores.reduce { acc, i -> acc + i }
    println("Tổng các số: $total")

    //duyệt qua it và lấy phần tử đầu, cuối trong list
    scores.forEach() { println("Điểm: $it") }
    println("Số đầu tiên: ${scores.first()}, Số cuối cùng: ${scores.last()}")
}

//convert array or list to string
fun array(){
    val listCities = listOf("Hà Nội", "Bắc Ninh", "Đà Nẵng")
    val devs = arrayOf("Hưng", "Hoài", "Hoa", "Huệ","Hưng","hưng")
    val arrayWithLambda = Array(7, {i -> i*1})

    val messenge = listCities.joinToString(
        separator = ",",
        postfix = ": Là các tỉnh thành ở Việt Nam"
    )
    print(messenge)

    println(devs.toSet())   //Loại bỏ hàm trùng lặp
    //println(devs.distinct())
    //println(devs.toMutableSet())
    //println(devs.toHashSet()) //Không duy trì thứ tự ban đầu
    println(arrayWithLambda.get(3))
    println("kích thước của mảng: ${arrayWithLambda.size}")
    println("Phần tử đầu của mảng: ${arrayWithLambda.first()}")
    println("Phân tử cuối cùng của mảng: ${arrayWithLambda.last()}")
    println("Vị trí của phần tử giá trị 5: ${arrayWithLambda.indexOf(5)}")
}

fun interativeCollection(){
    val number1 = setOf(10,2,3,12)
    val number2 = setOf(1,9,8,2,12,9)
    val number = mutableSetOf(9,9,8,"a",1,"a",6,9)

    println("Number1: $number1")
    println("Number2: $number2")
    println("Gộp 2 chuỗi bỏ lặp: ${number1 union number2}")
    println("Gộp 2 chuỗi phần tử trùng: ${number1 intersect number2}")
    println("Phần tử number2 cos mà number1 không có: ${number2 subtract number1}")
}
