package com.bigdudeapps.pictureviewer.ui.viewholder

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bigdudeapps.pictureviewer.R
import com.bigdudeapps.pictureviewer.ui.ImageDetailActivity
import com.bigdudeapps.pictureviewer.ui.model.PixaImage
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import android.support.v4.app.ActivityOptionsCompat
import kotlinx.android.synthetic.main.pixa_image_square.view.*


class PixaImageViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(layout, parent, false)) {

    fun bind(pixaImage: PixaImage) {
        val options = RequestOptions()
        Glide.with(itemView.context)
                .load(pixaImage.largeImageURL)
                .apply(options.centerCrop())
                .into(itemView.image_square)

        itemView.setOnClickListener {
            val intent = Intent(itemView.context, ImageDetailActivity::class.java)
            val bundle = Bundle()
            bundle.putParcelable(ImageDetailActivity.EXTRA_IMAGE_DATA, pixaImage)
            intent.putExtras(bundle)
            val transitionOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(itemView.context as Activity, itemView.image_square, "image")
            itemView.context.startActivity(intent, transitionOptions.toBundle())
        }
    }

    companion object {

        private val layout: Int
            get() = R.layout.pixa_image_square

        val viewType: Int
            get() = layout
    }

}