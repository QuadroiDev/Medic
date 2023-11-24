package com.quadroidev.medic.core.local.db.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.quadroidev.medic.core.local.db.dao.UserDao
import com.quadroidev.medic.core.local.models.UserEntity
import org.junit.After
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
        val user = UserEntity(name = "Jack")
        userDao.upsertUser(user)
        // TODO fix problems for this test
//        val byName = userDao.findWithName("george")
//        assertEquals(byName[0], user)
    }
}