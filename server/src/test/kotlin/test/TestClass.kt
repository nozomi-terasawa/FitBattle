package test

import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

class TestClass {
    @BeforeTest
    fun setup() {
        println("Before test")
    }

    @AfterTest
    fun teardown() {
        println("After test")
    }

    @Test
    fun test1() {
        val a = 1
        val b = 2
        val c = a + b
        println("Test 1")
        assert(c == 3)
    }

    @Test
    fun test2() {
        val a = 1
        val b = 2
        val c = a + b
        println("Test 2")
        assert(c == 4)
    }

    @Test
    fun test3() {
        val testList = listOf(1, 2, 3)
        println("Test 3")
        assert(testList.contains(2))
    }
}
