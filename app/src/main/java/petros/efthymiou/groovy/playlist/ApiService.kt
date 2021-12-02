package petros.efthymiou.groovy.playlist

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://0.0.0.0:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}