package com.app.tic_tac_toe_app.activities

class MainActivityPresenter : MainActivityMVP.Presenter {

    private var view: MainActivityMVP.View? = null

    override fun setView(view: MainActivityMVP.View) {
        this.view = view
    }

    override fun dropView() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}