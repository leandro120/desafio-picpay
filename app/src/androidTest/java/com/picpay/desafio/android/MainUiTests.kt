package com.picpay.desafio.android

import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test

@LargeTest
class MainUiTests {
    private val screen: MainScreen = MainScreen()

    @Rule
    @JvmField
    var rule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun shouldRenderContactsFromRepository(){
        screen {
            recycler {
                hasSize(0)
            }
        }
    }
}