package com.example.hellogames

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity(), GameAdapter.OnItemClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.game_list_container)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = gameAdapter

        gameAdapter.setOnItemClickListener(this)

        recyclerView.addItemDecoration(
            DividerItemDecoration(
                baseContext,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )

        getAllGames()
    }

    var games = listOf<GameModel>()
    val gameAdapter = GameAdapter(games)


    private fun getAllGames() {
        val call: Call<List<GameModel>> = HttpServices.getMyApi().getAllGames()
        call.enqueue(object: Callback<List<GameModel>> {

            override fun onResponse(
                call: Call<List<GameModel>>,
                response: Response<List<GameModel>>
            ) {
                val allgames = response.body()
                if (allgames != null) {
                    for (game in allgames) {

                        games = allgames.asSequence().shuffled().take(4).toList()
                    }
                    gameAdapter.updateGames(games)

                    gameAdapter.notifyDataSetChanged()
                } else {
                    Log.d("Game", "None")
                }
            }

            override fun onFailure(call: Call<List<GameModel>>, t: Throwable) {
                Log.d("Game", "Failed")
            }
        })
    }

    override fun onItemClick(game: GameModel) {
        GameData.selectedGame = game
        val intent = Intent(this, GameDetailsActivity::class.java)
        startActivity(intent)
    }
}
