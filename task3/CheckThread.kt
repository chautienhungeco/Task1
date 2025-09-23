package com.apero.task3

import kotlin.coroutines.*
import kotlin.random.Random
import kotlin.time.Duration.Companion.seconds

suspend fun main() {
    testCoroutineScope()
    testJob()
    testSupervisorJob()
    testSupervisorScope()
    runAll()
    testStream()
}

suspend fun testStream(): Unit = coroutineScope {
    repeat(1000) {
        launch { // or launch(Dispatchers.Default) {
            // To make it busy
            List(1_000_000) { Random.nextLong() }.maxOrNull()

            val threadName = Thread.currentThread().name
            println("Running on thread: $threadName")
        }
    }
}

suspend fun testCoroutineScope(){
    try{
        coroutineScope() {
            launch {
                println("1 Start: ${Thread.currentThread().name}")
                delay(1000L)
                println("1 Done: ${Thread.currentThread().name}")
            }
            launch {
                println("2 start: ${Thread.currentThread().name}")
                delay(700)
                println("2 có lỗi: ${Thread.currentThread().name}")
                throw RuntimeException("Lỗi ở 2")
            }
            launch {
                println("3 Start: ${Thread.currentThread().name}")
                delay(2000L)
                println("3 Done: ${Thread.currentThread().name}")
            }
        }
    }catch (e: Exception){
        println("Ngoại lệ được ném ra: $e")
    }
}

suspend fun testJob() = coroutineScope{
    val parentJob = Job()

    val scope = CoroutineScope(Dispatchers.Default + parentJob)
    scope.launch {
        println("Start 1")
        delay(500)
        println("Bug 1")
        throw RuntimeException("Lỗi 1")
    }
    scope.launch {
        try {
            println("Start 2")
            delay(1000)
            println("Done 2")
            throw RuntimeException("Lỗi thực thi 2")
        }catch (e: Exception){
            println("Lỗi thực thi 2: $e")
        }
    }
    delay(1500)
}

suspend fun testSupervisorJob() = coroutineScope{
    val superVisorJob = SupervisorJob()
    val scope = CoroutineScope(Dispatchers.Default + superVisorJob)

    scope.launch {
        println("Start 1: ${Thread.currentThread().name}")
        delay(1000)
        println("Thực thi 1: ${Thread.currentThread().name}")
        //throw RuntimeException("Lỗi với 1: ${Thread.currentThread().name}")
    }
    scope.launch {
        try {
            println("Start 2: ${Thread.currentThread().name}")
            delay(500)
            println("Thực thi 2: ${Thread.currentThread().name}")
        }catch (e: Exception){
            println("Xử lý lỗi: $e")
        }

    }

    delay(2000)
}

suspend fun fun1(){

    delay(1000)
    println("Log1: ${Thread.currentThread().name}")

}
suspend fun fun2(){

    delay(998)
    println("Log2: ${Thread.currentThread().name}")

}
suspend fun fun3() {

    delay(1000)
    println("Log3: ${Thread.currentThread().name}")

}

suspend fun runAll() = coroutineScope {
    launch { fun2() }
    fun1()
    launch { fun3() }
    println("done: ${Thread.currentThread().name}")
}

suspend fun testSupervisorScope(){
    supervisorScope {
        launch {
            println("Start 1")
            delay(500)
            println("Lỗi 1")
            throw RuntimeException("Ngoại lệ 1")
        }

        launch {
            println("Start 2")
            delay(1000)
            println("Done 2")
        }

        val deffent = async {
            println("Start 3")
            delay(1500)
            println("Lỗi 3")
            throw RuntimeException("Ngoại lệ 3")
        }
        deffent.await()
    }
    println("Done 3 task")
}