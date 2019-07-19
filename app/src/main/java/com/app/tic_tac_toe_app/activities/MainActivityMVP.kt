package com.app.tic_tac_toe_app.activities

import com.app.tic_tac_toe_app.root.BasePresenter
import com.app.tic_tac_toe_app.root.BaseView

interface MainActivityMVP{

    interface View: BaseView<Presenter>{

    }

    interface Presenter: BasePresenter<View>{

    }
}