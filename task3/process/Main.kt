package com.apero.task3.process

import kotlinx.coroutines.*
suspend fun main() = runBlocking {
    val menuHandler = MenuHandler(this)
    menuHandler.runMenu()
}