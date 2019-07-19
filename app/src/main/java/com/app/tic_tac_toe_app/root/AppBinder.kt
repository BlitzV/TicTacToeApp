package com.app.tic_tac_toe_app.root

import com.app.tic_tac_toe_app.activities.MainActivity
import com.app.tic_tac_toe_app.activities.MainActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AppBinder {

    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    internal abstract fun contributeMainActivity(): MainActivity
}