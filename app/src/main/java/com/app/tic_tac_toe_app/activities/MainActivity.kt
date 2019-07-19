package com.app.tic_tac_toe_app.activities

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import com.app.tic_tac_toe_app.R
import com.app.tic_tac_toe_app.Services.SoundService
import com.app.tic_tac_toe_app.helpers.DialogsHelpers.Companion.DialogSimpleOkButton
import com.app.tic_tac_toe_app.helpers.PLAYINGPLAYER
import com.app.tic_tac_toe_app.helpers.WINNER_OF_GAME
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainActivityMVP.View {

    @Inject
    lateinit var presenter: MainActivityMVP.Presenter

    // Instance variables

    var playingplayer: PLAYINGPLAYER? = null
    var winnerOfGame: WINNER_OF_GAME? = null

    var player1Options: ArrayList<Int> = ArrayList()
    var player2Options: ArrayList<Int> = ArrayList()
    var allDisabledImages: ArrayList<ImageButton?> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        playingplayer = PLAYINGPLAYER.FIRST_PLAYER
        winnerOfGame = WINNER_OF_GAME.NO_ONE
    }

    override fun onResume() {
        super.onResume()
        presenter.setView(this)

        try {
            startService(Intent(this@MainActivity, SoundService::class.java))
        } catch (e: Exception){
            e.printStackTrace()
        }
    }

    fun imageButtonTapped(view: View){

        val selectedImageButton: ImageButton = view as ImageButton
        var optionNumber = 0

        when ((Math.random() * 9 + 1).toInt()) {

            1 -> tableLayout.setBackgroundColor(Color.YELLOW)
            2 -> tableLayout.setBackgroundColor(Color.DKGRAY)
            3 -> tableLayout.setBackgroundColor(Color.GREEN)
            4 -> tableLayout.setBackgroundColor(Color.LTGRAY)
            5 -> tableLayout.setBackgroundColor(Color.CYAN)
            6 -> tableLayout.setBackgroundColor(Color.MAGENTA)
            7 -> tableLayout.setBackgroundColor(Color.RED)
            8 -> tableLayout.setBackgroundColor(Color.BLUE)
            9 -> tableLayout.setBackgroundColor(Color.WHITE)
        }

        when (selectedImageButton.id) {

            R.id.imgButton1 -> optionNumber = 1
            R.id.imgButton2 -> optionNumber = 2
            R.id.imgButton3 ->  optionNumber =3
            R.id.imgButton4 ->  optionNumber =4
            R.id.imgButton5 ->  optionNumber =5
            R.id.imgButton6 ->  optionNumber =6
            R.id.imgButton7 ->  optionNumber =7
            R.id.imgButton8 ->  optionNumber =8
            R.id.imgButton9 ->  optionNumber = 9

        }
        action(optionNumber, selectedImageButton)
    }

    private fun action(optionNumber: Int, _selectedImageButton: ImageButton) {

        var selectedImageButton = _selectedImageButton

        if (playingplayer == PLAYINGPLAYER.FIRST_PLAYER) {

            selectedImageButton.setImageResource(R.drawable.x_letter)
            player1Options.add(optionNumber)
            selectedImageButton.isEnabled = false
            allDisabledImages.add(selectedImageButton)
            playingplayer = PLAYINGPLAYER.SECOND_PLAYER


        }
        if (playingplayer == PLAYINGPLAYER.SECOND_PLAYER) {

//            // Algorithm for playing with ourselves
//            selectedImageButton.setImageResource(R.drawable.o_letter)
//            player2Options.add(optionNumber)
//            selectedImageButton.isEnabled = false
//            allDisabledImages.add(selectedImageButton)
//            playingPlayer = PLAYINGPLAYER.FIRST_PLAYER


            // Algoeithm for playing with the computer
            var notSelectedImageNumbers = ArrayList<Int>()
            for (imageNumber in 1..9) {
                if (!(player1Options.contains(imageNumber))){
                    if (!player2Options.contains(imageNumber)) {
                        // notSelectedImageNumbers is created in order to hold
                        // the image numbers of the image buttons that are not selected
                        notSelectedImageNumbers.add(imageNumber)
                    }
                }
            }

            try {

                var randomNumber = ((Math.random() * notSelectedImageNumbers.size)).toInt()
                var imageNumber = notSelectedImageNumbers[randomNumber]
                when (imageNumber) {

                    1 -> selectedImageButton = imgButton1
                    2 -> selectedImageButton = imgButton2
                    3 -> selectedImageButton = imgButton3
                    4 -> selectedImageButton = imgButton4
                    5 -> selectedImageButton = imgButton5
                    6 -> selectedImageButton = imgButton6
                    7 -> selectedImageButton = imgButton7
                    8 -> selectedImageButton = imgButton8
                    9 -> selectedImageButton = imgButton9

                }
                selectedImageButton.setImageResource(R.drawable.o_letter)
                player2Options.add(imageNumber)
                selectedImageButton.isEnabled = false
                allDisabledImages.add(selectedImageButton)
                playingplayer = PLAYINGPLAYER.FIRST_PLAYER

            } catch (e: Exception) {

                e.printStackTrace()

            }
        }
        specifyTheWinnerOfGame()
    }

    private fun specifyTheWinnerOfGame() {

        if (player1Options.contains(1)
            && player1Options.contains(2)
            && player1Options.contains(3)) {

            winnerOfGame = WINNER_OF_GAME.PLAYER_ONE
        } else if (player2Options.contains(1)
            && player2Options.contains(2)
            && player2Options.contains(3)) {


            winnerOfGame = WINNER_OF_GAME.PLAYER_TWO

        } else if (player1Options.contains(4)
            && player1Options.contains(5)
            && player1Options.contains(6)) {

            winnerOfGame = WINNER_OF_GAME.PLAYER_ONE

        } else if (player2Options.contains(4)
            && player2Options.contains(5)
            && player2Options.contains(6)) {

            winnerOfGame = WINNER_OF_GAME.PLAYER_TWO

        }  else if (player1Options.contains(7)
            && player1Options.contains(8)
            && player1Options.contains(9)) {

            winnerOfGame = WINNER_OF_GAME.PLAYER_ONE

        } else if (player2Options.contains(7)
            && player2Options.contains(8)
            && player2Options.contains(9)) {

            winnerOfGame = WINNER_OF_GAME.PLAYER_TWO

        } else if (player1Options.contains(1)
            && player1Options.contains(4)
            && player1Options.contains(7)) {

            winnerOfGame = WINNER_OF_GAME.PLAYER_ONE

        } else if (player2Options.contains(1)
            && player2Options.contains(4)
            && player2Options.contains(7)) {

            winnerOfGame = WINNER_OF_GAME.PLAYER_TWO

        } else if (player1Options.contains(2)
            && player1Options.contains(5)
            && player1Options.contains(8)) {

            winnerOfGame = WINNER_OF_GAME.PLAYER_ONE

        } else if (player2Options.contains(2)
            && player2Options.contains(5)
            && player2Options.contains(8)) {

            winnerOfGame = WINNER_OF_GAME.PLAYER_TWO

        } else if (player1Options.contains(3)
            && player1Options.contains(6)
            && player1Options.contains(9)) {

            winnerOfGame = WINNER_OF_GAME.PLAYER_ONE

        } else if (player2Options.contains(3)
            && player2Options.contains(6)
            && player2Options.contains(9)) {

            winnerOfGame = WINNER_OF_GAME.PLAYER_TWO

        } else if (player1Options.contains(1)
            && player1Options.contains(5)
            && player1Options.contains(9)) {

            winnerOfGame = WINNER_OF_GAME.PLAYER_ONE

        } else if (player2Options.contains(1)
            && player2Options.contains(5)
            && player2Options.contains(9)) {


            winnerOfGame = WINNER_OF_GAME.PLAYER_TWO

        } else if (player1Options.contains(3)
            && player1Options.contains(5)
            && player1Options.contains(7)) {

            winnerOfGame = WINNER_OF_GAME.PLAYER_ONE

        } else if (player2Options.contains(3)
            && player2Options.contains(5)
            && player2Options.contains(7)) {

            winnerOfGame = WINNER_OF_GAME.PLAYER_TWO
        }


        if (winnerOfGame == WINNER_OF_GAME.PLAYER_ONE) {

            DialogSimpleOkButton(this,getString(R.string.win),getString(R.string.congratulation),DialogInterface.OnClickListener { dialog, _ ->
                resetGame()
                dialog.cancel()
            }).show()
            return

        } else if (winnerOfGame == WINNER_OF_GAME.PLAYER_TWO) {

            DialogSimpleOkButton(this,getString(R.string.win2),getString(R.string.congratulation),DialogInterface.OnClickListener { dialog, _ ->
                resetGame()
                dialog.cancel()
            }).show()
            return

        }

        checkDrawState()

    }

    private fun resetGame() {

        player1Options.clear()
        player2Options.clear()
        allDisabledImages.clear()
        winnerOfGame = WINNER_OF_GAME.NO_ONE
        playingplayer = PLAYINGPLAYER.FIRST_PLAYER

        imgButton1.setImageResource(R.drawable.place_holder)
        imgButton2.setImageResource(R.drawable.place_holder)
        imgButton3.setImageResource(R.drawable.place_holder)
        imgButton4.setImageResource(R.drawable.place_holder)
        imgButton5.setImageResource(R.drawable.place_holder)
        imgButton6.setImageResource(R.drawable.place_holder)
        imgButton7.setImageResource(R.drawable.place_holder)
        imgButton8.setImageResource(R.drawable.place_holder)
        imgButton9.setImageResource(R.drawable.place_holder)


        imgButton1.isEnabled = true
        imgButton2.isEnabled = true
        imgButton3.isEnabled = true
        imgButton4.isEnabled = true
        imgButton5.isEnabled = true
        imgButton6.isEnabled = true
        imgButton7.isEnabled = true
        imgButton8.isEnabled = true
        imgButton9.isEnabled = true

    }

    private fun checkDrawState() {

        if (allDisabledImages.size == 9 && winnerOfGame != WINNER_OF_GAME.PLAYER_ONE &&
            winnerOfGame != WINNER_OF_GAME.PLAYER_TWO) {

            DialogSimpleOkButton(this, getString(R.string.draw),getString(R.string.draw_message),DialogInterface.OnClickListener { dialog, _ ->
                resetGame()
                dialog.cancel()
            }).show()
        }
    }

    override fun fetchContext(): Context {
        return this
    }
}
