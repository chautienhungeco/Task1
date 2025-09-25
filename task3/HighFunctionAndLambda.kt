package com.apero.task3

fun main() {
    functionAsParameter()
    lambaFunc()
    inlineFunctionExample()

}

fun functionAsParameter() {
    val resultSum = calculateSum()

    println(resultSum(4, 5))
    println(calculate(4, 5, ::sum))
    println(calculate(12, 3, ::minus))
    println(calculate(3, 8, ::times))
    println(calculate(12, 3, ::div))


}

fun lambaFunc() {
    val lambdaSum = { i1: Int, i2: Int, i3: Int -> i1.plus(i2).plus(i3) }
    println(lambdaSum(4, 1, 8))
}

fun calculateSum(): (Int, Int) -> Int {
    return ::sum // trả về hàm khác
}

fun calculate(x: Int, y: Int, operation: (Int, Int) -> Int): Int { //nhận hàm làm  tham số
    return operation(x, y)
}

fun sum(x: Int, y: Int) = x.plus(y)

fun minus(x: Int, y: Int) = x.minus(y)

fun times(x: Int, y: Int) = x.times(y)

fun div(x: Int, y: Int) = x.div(y)


inline fun operateInline(x: Int, y: Int, operation: (Int, Int) -> Int): Int {
    return operation(x, y)
}

inline fun withStudent(name: String, action: (String) -> Unit) {
    println("Chuẩn bị xử lý sinh viên: $name")
    action(name)
    println("Xong: $name")
}

inline fun ifPassed(score: Double, block: () -> Unit) {
    if (score >= 5.0) block()
}

fun average(vararg scores: Double): Double =
    scores.average() //vararg - nhận 1 số lượng biến cùng kiểu

fun inlineFunctionExample() {
    val result = operateInline(12, 3, ::minus)
    println("operateInline result: $result")

    val avg = average(7.5, 8.0, 6.5)
    withStudent("An") { studentName ->
        println("Điểm TB của $studentName: $avg")
        ifPassed(avg) {
            println("Kết quả: Đậu")
        }
    }
}