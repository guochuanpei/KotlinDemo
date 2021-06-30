package com.gcp.kotlin.advancedtypes.delegates
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

//属性代理
class PropertyDelegates(name: String) {
    val firstName by lazy { name.split(" ")[0] }

}

class StateManager {
    var state: Int by Delegates.observable(0) { property, oldValue, newValue ->
        println("State changed from $oldValue -> $newValue")
    }
}

class Foo {
    val x: Int by X(::x)
    var y: Int by X(::y)
}

class X(property: KProperty<*>) {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): Int {
        return 2
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, i: Int) {

    }
}

fun main() {
    val stateManager = StateManager()
    stateManager.state = 4
    Foo().y = 555
    println(Foo().x)
}
