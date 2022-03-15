package com.example.retrofitandrxjava

class MyData : ArrayList<MyData.MyDataItem>(){
    data class MyDataItem(
        val completed: Boolean,
        val id: String,
        val title: String,
        val userId: Int
    )
}
