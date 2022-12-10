package com.amaurypm.games.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.amaurypm.games.databinding.ActivityDetailsBinding
import com.amaurypm.games.model.GameDetail
import com.amaurypm.games.model.GamesApi
import com.amaurypm.games.util.Constants
import com.bumptech.glide.Glide
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Thread.sleep

class Details : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val bundle = intent.extras

        val id = bundle?.getString("id", "")

        val call = Constants.getRetrofit().create(GamesApi::class.java).getGameDetail(id)

        CoroutineScope(Dispatchers.IO).launch {
            call.enqueue(object : Callback<GameDetail> {
                override fun onResponse(call: Call<GameDetail>, response: Response<GameDetail>) {
                    binding.pbConexion.visibility = View.GONE

                    with(binding){

                        tvTitle.text = response.body()?.title

                        Glide.with(this@Details)
                            .load(response.body()?.image)
                            .into(ivImage)

                        tvLongDesc.text = response.body()?.longDesc
                    }
                    
                }



                override fun onFailure(call: Call<GameDetail>, t: Throwable) {
                    binding.pbConexion.visibility = View.GONE
                    Toast.makeText(
                        this@Details,
                        "Error de conexión: ${t.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            })
        }
    }
}