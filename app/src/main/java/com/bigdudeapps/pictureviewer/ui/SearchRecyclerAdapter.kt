package com.bigdudeapps.pictureviewer.ui

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.bigdudeapps.pictureviewer.ui.model.PixaImage
import com.bigdudeapps.pictureviewer.ui.viewholder.PixaImageViewHolder

class SearchRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var imageList: ArrayList<IImageDataItem>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (PixaImageViewHolder.viewType == viewType) {
            return PixaImageViewHolder(parent)
        }
        return object : RecyclerView.ViewHolder(parent) {

        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = imageList?.get(position)
        if (holder is PixaImageViewHolder) {
            holder.bind(item as PixaImage)
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = imageList?.get(position)
        if (item is PixaImage) {
            return PixaImageViewHolder.viewType
        }

        return super.getItemViewType(position)
    }

    override fun getItemCount(): Int {
        imageList?.run { return this.size }
        return 0
    }

    fun setData(imageList: ArrayList<IImageDataItem>?) {
        this.imageList = imageList
        //todo Partial binding by item if theres time
        notifyDataSetChanged()
    }

    fun clear() {
        val size = imageList?.size
        imageList?.clear()
        size?.let { notifyItemRangeRemoved(0, it) }
    }
}