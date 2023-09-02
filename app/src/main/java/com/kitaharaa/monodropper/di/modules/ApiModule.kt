package com.kitaharaa.monodropper.di.modules

import com.kitaharaa.monodropper.mono_api.MonoApiImpl
import com.kitaharaa.monodropper.mono_api.MonoApiRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class ApiModule {
    @Binds
    abstract fun bindApi(api: MonoApiImpl): MonoApiRepository
}