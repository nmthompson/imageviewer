package com.bigdudeapps.pictureviewer.service.model

import com.google.gson.annotations.SerializedName

data class PixaResponse(@SerializedName("hits")
                        val hits: List<PixaPhotosData>?)