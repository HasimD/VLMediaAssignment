package com.example.vlmedia

import org.junit.Before
import org.mockito.MockitoAnnotations

abstract class AppUnitTestSuite {

    abstract fun setUpBefore()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        setUpBefore()
    }
}