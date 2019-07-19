package com.app.tic_tac_toe_app.activities

import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {

    @Provides
    fun mainActivityMVPPresenter(): MainActivityMVP.Presenter{
        return MainActivityPresenter()
    }
}