package com.example.vlmedia

import org.mockito.MockitoAnnotations
import kotlin.test.BeforeTest

abstract class AppUnitTestSuite {

    abstract fun setUpBefore()

    @BeforeTest
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        setUpBefore()
    }
}