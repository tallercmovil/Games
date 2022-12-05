package com.amaurypm.games.util

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Creado por Amaury Perea Matsumura el 02/12/22
 */

object Constants {
    //const val BASE_URL = "https://www.serverbpw.com/" //Debe terminar en /
    //const val BASE_URL = "http://10.0.2.2:8888/" //Localhost en el emulador
    const val BASE_URL = "https://private-a649a-games28.apiary-mock.com/"


    const val LOGTAG = "LOGS"

    fun getRetrofit(): Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

}