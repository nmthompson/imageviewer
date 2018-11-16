package com.bigdudeapps.pictureviewer.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bigdudeapps.pictureviewer.R
import com.bigdudeapps.pictureviewer.ui.model.PixaImage
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_image_detail.*

class ImageDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_IMAGE_DATA = "imageData"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_detail)

        setSupportActionBar(detail_toolbar)
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setHomeButtonEnabled(true)
        actionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back)

        val intent = intent
        val imageData = intent.getParcelableExtra<PixaImage>(EXTRA_IMAGE_DATA)

        Glide.with(this)
                .load(imageData.largeImageURL)
                .into(image_square)

        user_view.text = getString(R.string.user_detail, imageData.user)
        likes_view.text = getString(R.string.image_likes, imageData.likes.toString())
        downloads_view.text = getString(R.string.downloads_detail, imageData.downloads.toString())
        tags_view.text = getString(R.string.tags_detail, imageData.tags)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}