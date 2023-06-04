package com.example.hellogames

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GameDetailsActivity : AppCompatActivity() {

    var selectedGame = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_details)

        selectedGame = GameData.selectedGame?.id ?: 0
        if (selectedGame != null) {
            // Utilise les détails du jeu pour afficher les informations dans cette activité
        } else {
            Log.d("GameDetail", "NULL")
        }

        getGameDetails()
    }

    fun getGameDetails() {
        val call: Call<GameDetails> = HttpServices.getMyApi().getDetailsGames(selectedGame)
        call.enqueue(object: Callback<GameDetails> {
            override fun onResponse(call: Call<GameDetails>, response: Response<GameDetails>) {
                val gameDetails = response.body()
                if (gameDetails !== null) {
                    findViewById<TextView>(R.id.game_name).text = gameDetails.name
                    findViewById<TextView>(R.id.game_type).text = gameDetails.type
                    findViewById<TextView>(R.id.game_players).text = gameDetails.players.toString()
                    findViewById<TextView>(R.id.game_year).text = gameDetails.year.toString()
                    findViewById<TextView>(R.id.game_desc).text = gameDetails.descEn

                    val urlButton = findViewById<Button>(R.id.url_button)
                    urlButton.setOnClickListener {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(gameDetails.url))
                        startActivity(intent)
                    }

                    Glide.with(findViewById<ConstraintLayout>(R.id.game_container))
                        .load(gameDetails.pictureUrl)
                        .into(findViewById<ImageView>(R.id.game_image))

                }
            }

            override fun onFailure(call: Call<GameDetails>, t: Throwable) {
                Log.d("Game", "Failed")
            }
        })
    }
}