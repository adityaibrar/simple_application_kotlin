package com.adityaibrar.simple_application_kotlin

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class FilmDetail : AppCompatActivity(), View.OnClickListener {
    private lateinit var icon_back_left: ImageView
    private lateinit var titleDetails: TextView
    private lateinit var descriptionDetails: TextView
    private lateinit var imgDetails: ImageView
    private lateinit var durationDetails: TextView
    private lateinit var ratingDetails: TextView

    companion object {
        const val EXTRA_FILM = "extra_film"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film_detail)
        supportActionBar?.hide()

        icon_back_left = findViewById(R.id.icon_back_left)
        titleDetails = findViewById(R.id.title_details)
        descriptionDetails = findViewById(R.id.description_details)
        imgDetails = findViewById(R.id.img_details)
        durationDetails = findViewById(R.id.duration_details)
        ratingDetails = findViewById(R.id.rating_details)


        val film = intent.getParcelableExtra<Film>(EXTRA_FILM)

        film?.let {
            titleDetails.text = it.name
            descriptionDetails.text = it.description
            it.photo?.let { it1 -> imgDetails.setImageResource(it1) }
            durationDetails.text = it.duration
            ratingDetails.text = it.rating
        }

        icon_back_left.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.icon_back_left -> {
                onBackPressed()
            }
        }
    }
}
