package com.example.retrofitandrxjava
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET


interface GetData {

   @GET("todos")
   fun getData(): Observable<List<MyData.MyDataItem>>

}
