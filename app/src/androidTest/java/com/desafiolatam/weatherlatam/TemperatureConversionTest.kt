package com.desafiolatam.weatherlatam

import com.desafiolatam.weatherlatam.extension.toCelsius
import com.desafiolatam.weatherlatam.extension.toFahrenheit
import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class TemperatureConversionTest {

    @Test
    fun testCelsiusToFahrenheit_positive() {
        val result = 25.0.toFahrenheit()
        assertEquals(77.0, result, 0.001)
    }

    @Test
    fun testFahrenheitToCelsius_positive() {
        val result = 77.0.toCelsius()
        assertEquals(25.0, result, 0.001)
    }

    @Test
    fun testCelsiusToFahrenheit_negative() {
        val result = (-10.0).toFahrenheit()
        assertEquals(14.0, result, 0.001)
    }
}
