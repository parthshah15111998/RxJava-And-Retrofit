package com.example.retrofitandrxjava

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitandrxjava.databinding.ActivityMainBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private var myAdapter: MyAdapter? = null
    private var myCompositeDisposable: CompositeDisposable? = null
    private var myRetroCryptoArrayList: ArrayList<MyData.MyDataItem>? = null
    private val BASE_URL = "https://jsonplaceholder.typicode.com/users/1/"
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        myCompositeDisposable = CompositeDisposable()
        loadData()
        initRecyclerView()


    }

    private fun initRecyclerView() {
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        binding.cryptocurrencyList.layoutManager = layoutManager
    }

    private fun loadData() {
        val requestInterface = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(GetData::class.java)

        myCompositeDisposable?.add(
            requestInterface.getData()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse)
        )

    }

    private fun handleResponse(dataList: List<MyData.MyDataItem>) {

        myRetroCryptoArrayList = ArrayList(dataList)
        myAdapter = MyAdapter(myRetroCryptoArrayList!!)
        binding.cryptocurrencyList.adapter = myAdapter

    }

    override fun onDestroy() {
        super.onDestroy()
        myCompositeDisposable?.clear()

    }


}