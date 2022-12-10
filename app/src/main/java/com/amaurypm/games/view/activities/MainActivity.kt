package com.amaurypm.games.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View

import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.amaurypm.games.databinding.ActivityMainBinding
import com.amaurypm.games.model.Game
import com.amaurypm.games.model.GamesApi
import com.amaurypm.games.util.Constants
import com.amaurypm.games.view.adapters.Adapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        CoroutineScope(Dispatchers.IO).launch{
            //val call = Constants.getRetrofit().create(GamesApi::class.java).getGamesApiary("games/games_list")
            val call = Constants.getRetrofit().create(GamesApi::class.java).getGames("cm/games/games_list.php")

            call.enqueue(object: Callback<ArrayList<Game>>{
                override fun onResponse(
                    call: Call<ArrayList<Game>>,
                    response: Response<ArrayList<Game>>
                ) {
                    Log.d(Constants.LOGTAG, "Respuesta del servidor: ${response.toString()}")
                    Log.d(Constants.LOGTAG, "Datos: ${response.body().toString()}")

                    /*val gameTmp: Game

                    for(gameTmp in response.body()!!){
                        Toast.makeText(this@MainActivity, "Nombre del juego: ${gameTmp.title}", Toast.LENGTH_SHORT).show()
                    }*/

                    binding.rvMenu.layoutManager = LinearLayoutManager(this@MainActivity)
                    binding.rvMenu.adapter = Adapter(this@MainActivity, response.body()!!)

                    binding.pbConexion.visibility = View.GONE
                }

                override fun onFailure(call: Call<ArrayList<Game>>, t: Throwable) {
                    binding.pbConexion.visibility = View.GONE
                    Toast.makeText(this@MainActivity, "Error de conexión: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    fun selectedGame(game: Game) {
        //Aquí programamos el click de cada elemento del recycler view
        val parametros = Bundle()

        parametros.apply{
            putString("id", game.id)
        }

        val intent = Intent(this@MainActivity, Details::class.java)

        intent.putExtras(parametros)

        startActivity(intent)
    }
}