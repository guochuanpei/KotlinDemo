package com.gcp.kotlin
//test
fun main() {
    println("Hello World")
    runBlock {
        List(1,init = {
            println("HelloWorld")
        })
    }
}

fun runBlock(block: () -> Unit) {
    val currentTimeMillis = System.currentTimeMillis()
    block()
    println(System.currentTimeMillis() - currentTimeMillis)
}