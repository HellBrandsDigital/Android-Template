package com.hellbrandsdigital.android_template.model.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import app.cash.turbine.test
import com.hellbrandsdigital.android_template.model.AppDatabase
import com.hellbrandsdigital.android_template.model.entity.Item
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@MediumTest
@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class ItemDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: AppDatabase
    private lateinit var dao: ItemDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()

        dao = database.itemDao()
        fillDB()
    }

    private fun fillDB() {
        val items = listOf(
            Item("TestItem", "TestCategory"),
            Item("TestItem2", "TestCategory"),
            Item("TestItem3", "TestCategory2")
        )
        runTest {
            items.forEach {
                dao.insert(it)
            }
        }
    }

    @After
    fun teardown() = database.close()

    @Test
    fun getAllStatic() = runTest {
        val allItems = dao.getAllStatic()
        assertThat(allItems.size).isEqualTo(3)
        assertThat(allItems[0].name).isEqualTo("TestItem")
    }

    @Test
    fun getAll() = runTest {
        val allItems = dao.getAll()
        allItems.test {
            assertThat(awaitItem()[0].name).isEqualTo("TestItem")
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun getByCategory() = runTest {
        val items = dao.getByCategory("TestCategory2")
        items.test {
            assertThat(awaitItem()[0].name).isEqualTo("TestItem3")
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun findItemByNameAndCategory() = runTest {
        val item = dao.findItemByNameAndCategory("TestItem2", "TestCategory")
        assertThat(item).isNotNull()
    }
}
