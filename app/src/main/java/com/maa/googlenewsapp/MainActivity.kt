package com.maa.googlenewsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.maa.googlenewsapp.adapter.MyAdapter
import com.maa.googlenewsapp.model.Articles
import com.maa.googlenewsapp.model.News
import com.maa.googlenewsapp.retrofit.RetroClass
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    var itemList= ArrayList<News>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView= findViewById<RecyclerView>(R.id.recyclerview)
        setupRecyclerview(recyclerView)

        val adapter= MyAdapter(itemList)
        recyclerView.adapter= adapter

        getNews(adapter)
    }

    fun setupRecyclerview(recyclerView: RecyclerView){
        recyclerView.layoutManager= LinearLayoutManager(this)

    }

    fun getNews(adapter: MyAdapter){

        val apiService= RetroClass().apiService()
        val apiKey="your---api---key"
        val call= apiService.getData("v2/top-headlines?country=tr&apiKey="+apiKey)
        call.enqueue(object : retrofit2.Callback<Articles>{
            override fun onResponse(call: Call<Articles?>, response: Response<Articles?>) {
                for(i in 0 until response.body()!!.articles.size){
                    itemList.add(News(response.body()!!.articles[i].title,response.body()!!.articles[i].description,response.body()!!.articles[i].url,
                        response.body()!!.articles[i].urlToImage,response.body()!!.articles[i].publishedAt))
                }
                adapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<Articles>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error", Toast.LENGTH_SHORT).show()
            }

        })

    }
}