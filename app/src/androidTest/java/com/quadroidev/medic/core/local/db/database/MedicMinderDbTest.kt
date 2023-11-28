package com.quadroidev.medic.core.local.db.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.quadroidev.medic.core.local.db.dao.UserDao
import com.quadroidev.medic.core.local.models.UserEntity
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.junit.After
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class MedicMinderDbTest {
    private lateinit var userDao: UserDao
    private lateinit var db: MedicMinderDb

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, MedicMinderDb::class.java
        ).build()
        userDao = db.userDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeUserAndReadInList() {
        runBlocking {
            // Mock data
            val users = listOf(
                UserEntity(name = "User1"),
                UserEntity(name = "User2"),
                UserEntity(name = "User3")
            )
            userDao.upsertUser(*users.toTypedArray())

            // Call the method to be tested
            val resultFlow = userDao.getAll()
            val result = resultFlow.toList()

            // Verify the result
            assertThat(result, `is`(users))
        }
    }
}