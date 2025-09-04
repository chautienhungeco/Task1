fun main(){
    variable()
    operator()
    typeCasting()
    template()
    conditionalExpressions()
    forLoop()
    doWhile()
    collection()
    set()
    map()
    common()
    remove()
    arrayToString()
}
// khai bao bien và hằng số
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
    val safe: String? = number as? String // bên phải không thể null, kế =t quả của ép kiểu có thể null
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

// Cau truc dieu kien if-else
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
    // gán giá trị cho 1 biến
    val greeting = if ( number < 20){
        "xin chào"
    }
    else{
        "tạm biệt"
    }
    println(greeting)
}

//for loop
fun forLoop(){
    val a = 1
    val b = 10
    var sum = 0
    val language = arrayOf("Lenin","Hồ Chí Minh","Mác")
    for (item in language)
        println(item)
    for ((index,value) in language.withIndex())
        println("$index - $value")
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
    //điều hướng nhảy downto
    for (i in b downTo a step 2){
        sum += i
    }
    println("sum =: $sum")
}

// while và do-while
fun doWhile(){
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

//Set
fun set(){
    //tạo danh sách các số, có số trùng để in ra không thấy trùng
    val phones = setOf("1234","2345","3456","1234")
    println(phones)
    //ket qua chi có 3 số đc in ra
}

//map
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
    //println(devs.distinct())
    //println(devs.toSet())
    //println(devs.toMutableSet())
    println(devs.toHashSet()) //Không duy trì thứ tự ban đầu
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