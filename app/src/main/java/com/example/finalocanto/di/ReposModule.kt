package com.example.finalocanto.di

import com.example.finalocanto.data.GoogleAuthRepo
import com.example.finalocanto.data.TargetRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module @InstallIn(ApplicationComponent::class)
class ReposModule {

    @Provides
    @Singleton
    fun provideAuthRepo()  = GoogleAuthRepo()

    @Provides
    @Singleton
    fun provideTargetRepo()  = TargetRepo()
}