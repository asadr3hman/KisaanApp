package com.example.kisaanapp.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

val retrofit = Retrofit.Builder()
    .baseUrl("http://192.168.0.102:3000/") // Replace with your actual API base URL
    .addConverterFactory(GsonConverterFactory.create())
    .build()

interface ApiEndPoints {
    @GET("books")
    suspend fun getBooks(): List<Book>

    @GET("books/{id}")
    suspend fun getBookById(@Path("id") id: Int): Book

    @POST("books")
    suspend fun createBook(@Body book: Book): Book

    @DELETE("books/{id}")
    suspend fun deleteBook(@Path("id") id: Int): Book
}
object BookApi {
    val retrofitService: ApiEndPoints by lazy { retrofit.create(ApiEndPoints::class.java) }
}