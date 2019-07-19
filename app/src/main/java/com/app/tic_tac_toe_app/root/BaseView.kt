package com.app.tic_tac_toe_app.root

import android.content.Context

interface BaseView<T> {

    fun fetchContext(): Context
}