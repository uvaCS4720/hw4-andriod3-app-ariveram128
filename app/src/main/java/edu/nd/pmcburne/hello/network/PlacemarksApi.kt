package edu.nd.pmcburne.hello.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface PlacemarksApi {
    @GET("placemarks.json")
    suspend fun getPlacemarks(): List<PlacemarkResponse>

    companion object {
        private const val BASE_URL = "https://www.cs.virginia.edu/~wxt4gm/"

        fun create(): PlacemarksApi {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(PlacemarksApi::class.java)
        }
    }
}
