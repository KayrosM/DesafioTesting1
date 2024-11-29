package com.desafiolatam.weatherlatam

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.desafiolatam.weatherlatam.data.local.WeatherDao
import com.desafiolatam.weatherlatam.data.local.WeatherDatabase
import com.desafiolatam.weatherlatam.data.toEntity
import com.desafiolatam.weatherlatam.model.WeatherDto
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WeatherDaoInsertTest {

    private lateinit var db: WeatherDatabase
    private lateinit var dao: WeatherDao

    private val weather = WeatherDto(
        id = 1,
        currentTemp = 20.0,
        maxTemp = 25.0,
        minTemp = 15.0,
        pressure = 1010.0,
        humidity = 70.0,
        windSpeed = 5.5,
        sunrise = 1625484367,
        sunset = 1625535967,
        cityName = "Test City"
    )

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, WeatherDatabase::class.java).build()
        dao = db.weatherDao()
    }

    @After
    fun teardown() {
        db.close()
    }

    @Test
    fun testInsertSingle() = runTest {
        dao.insertData(weather.toEntity())
        val result = dao.getWeatherData().first()
        assertEquals(1, result?.size)
    }

    @Test
    fun testInsertMultiple() = runTest {
        dao.insertData(weather.toEntity())
        dao.insertData(weather.copy(id = 2).toEntity())
        val result = dao.getWeatherData().first()
        assertEquals(2, result?.size)
    }

    @Test
    fun testInsertDuplicateId() = runTest {
        dao.insertData(weather.toEntity())
        dao.insertData(weather.toEntity()) // Should replace the existing entry
        val result = dao.getWeatherData().first()
        assertEquals(1, result?.size)
    }
}
