package com.amaurypm.games.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

/**
 * Creado por Amaury Perea Matsumura el 02/12/22
 */
interface GamesApi {

    //Aquí pongo mis endpoints

    @GET
    fun getGames(
        @Url url: String?
    ): Call<ArrayList<Game>>

    @GET("cm/games/game_detail.php")
    fun getGameDetail(
        @Query("id") id: String?
    ): Call<GameDetail>

    @GET
    fun getGamesApiary(
        @Url url: String?
    ): Call<ArrayList<Game>>

    @GET("games/game_detail/{id}")
    fun getGameDetailApiary(
        @Path("id") id: String?
    ): Call<GameDetail>

}