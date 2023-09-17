package com.kitaharaa.monodropper.di.modules

import android.content.Context
import androidx.room.Room
import com.kitaharaa.monodropper.model.room.MonoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class SingletonModule {

    @Provides
    fun provideDatabase(@ApplicationContext application: Context) = Room.databaseBuilder(
        application,
        MonoDatabase::class.java,
        "MonoDatabase"
    )
        .fallbackToDestructiveMigration()
        .build()
}