package com.bigdudeapps.pictureviewer.ui.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.bigdudeapps.pictureviewer.manager.SearchRequestManager
import com.bigdudeapps.pictureviewer.service.model.PixaResponse
import com.bigdudeapps.pictureviewer.ui.IImageDataItem
import com.bigdudeapps.pictureviewer.ui.model.PixaImage

class SearchViewModel : ViewModel() {

    val searchImageData = MutableLiveData<ArrayList<IImageDataItem>>()
    private var searchRequestManager: SearchRequestManager? = null
    private var query: String? = null

    /**
     * Triggers a refresh of the data for pixa images
     */
    private fun refresh() {
        query?.let { query ->
            if (searchRequestManager == null) {
                searchRequestManager = SearchRequestManager(query, object : SearchRequestManager.SearchRequestResponseListener {
                    override fun onRequestResponse(pixaResponse: PixaResponse?) {
                        val imageList = ArrayList<IImageDataItem>()
                        pixaResponse?.hits?.forEach { imageData ->
                            val image = PixaImage(imageData.previewURL,
                                    imageData.likes,
                                    imageData.id,
                                    imageData.views,
                                    imageData.tags,
                                    imageData.downloads,
                                    imageData.user,
                                    imageData.largeImageURL)

                            imageList.add(image)
                        }
                        searchImageData.postValue(imageList)
                    }

                })
            }
            searchRequestManager?.query = query
            searchRequestManager?.requestPixaImages()
        }
    }

    fun setQuery(query: String?) {
        this.query = query
        refresh()
    }
}