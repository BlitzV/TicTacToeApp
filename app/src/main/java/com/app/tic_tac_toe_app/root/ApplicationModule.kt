package com.app.tic_tac_toe_app.root

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
internal class ApplicationModule {

    @Provides
    @Singleton
    fun provideContext(application: Application) : Context {
        return application
    }
}