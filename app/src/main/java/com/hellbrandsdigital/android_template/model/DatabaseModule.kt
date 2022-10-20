package com.hellbrandsdigital.android_template.model

import android.content.Context
import androidx.room.Room
import com.hellbrandsdigital.android_template.model.dao.ItemDao
import com.hellbrandsdigital.android_template.model.dao.ItemListDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Provides
    fun provideItemDao(appDatabase: AppDatabase) : ItemDao = appDatabase.itemDao()

    @Provides
    fun provideItemListDao(appDatabase: AppDatabase) : ItemListDao = appDatabase.itemListDao()

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room
            .databaseBuilder(appContext, AppDatabase::class.java, "Test-Database")
            .fallbackToDestructiveMigration()
            .build()
    }
}
