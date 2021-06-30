package com.gcp.kotlin.advancedtypes.delegates

import java.util.ArrayList

interface InterfaceDelegates {

}

//接口代理
interface Api {
    fun a()
    fun b()
    fun c()
}

class ApiImpl : Api {
    override fun a() {

    }

    override fun b() {

    }

    override fun c() {

    }
}

class ApiWrapper(private val api: Api) : Api by api {
    override fun c() {
        println("c is called")
        api.c()
    }
}

class SuperArray<E>(
    private val list: MutableList<E?> = ArrayList()
    , private val map: MutableMap<Any, E> = HashMap()
) :
    MutableList<E?> by list, MutableMap<Any, E> by map {
    override fun isEmpty() = list.isEmpty() && map.isEmpty()
    override val size: Int
        get() = list.size + map.size

    override fun clear() {
        list.clear()
        map.clear()
    }

    override operator fun set(index: Int, element: E?): E? {
        if (list.size <= index) {
            repeat(index - list.size + 1) {
                list.add(null)
            }
        }
        return list.set(index, element)
    }

    override fun put(key: Any, value: E): E? {
        return map.put("自定义$key", value)
    }


    override fun toString(): String {
        return """List: [$list]; Map: [$map]"""
    }

}

fun main() {
    val superArray = SuperArray<String>()
    val superArray2 = SuperArray<String>()
    superArray += "Hello"
    superArray["Hello"] = "World"

    superArray[1] = "world"
    superArray[4] = "!!!"
    superArray2[superArray] = "World"
    println(superArray)
    println(superArray2)
}
