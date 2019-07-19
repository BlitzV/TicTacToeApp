package com.app.tic_tac_toe_app.root

interface BasePresenter<T> {

    fun setView(view: T)

    fun dropView()
}