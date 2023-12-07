package com.adityaibrar.simple_application_kotlin

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var gameList: RecyclerView
    private lateinit var icon_about: ImageView
    private val list = ArrayList<Film>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        icon_about = findViewById(R.id.icon_about)
        gameList = findViewById(R.id.film_list)
        gameList.setHasFixedSize(true)
        list.addAll(getFilmList())
        showRecyclerList()

        icon_about.setOnClickListener(this)
    }

    private fun getFilmList(): ArrayList<Film> {
        val filmTitle = resources.getStringArray(R.array.movie_name)
        val filmDescription = resources.getStringArray(R.array.movie_description)
        val filmPhoto = resources.obtainTypedArray(R.array.movie_photo)
        val filmDuration = resources.getStringArray(R.array.movie_duration)
        val filmRating = resources.getStringArray(R.array.movie_rating)

        val listFilm = ArrayList<Film>()
        for (i in filmTitle.indices) {

            val film = Film(
                filmTitle[i],
                filmDescription[i],
                filmPhoto.getResourceId(i, -1),
                filmDuration[i],
                filmRating[i]
            )
            listFilm.add(film)
        }

        filmPhoto.recycle()

        return listFilm
    }



    private fun showRecyclerList() {
        gameList.layoutManager = LinearLayoutManager(this)
        val listFilmAdapter = FilmListAdapter(list)
        gameList.adapter = listFilmAdapter
        listFilmAdapter.setOnItemClickCallback(object : FilmListAdapter.OnItemClickCallBack {
            override fun onItemClicked(game: Film) {
                val moveGameDetail = Intent(this@MainActivity, FilmDetail::class.java)
                moveGameDetail.putExtra(FilmDetail.EXTRA_FILM, game)
                startActivity(moveGameDetail)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.about, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.icon_about -> {
                val intent = Intent(this@MainActivity, About::class.java)
                startActivity(intent)
            }
        }
    }
}