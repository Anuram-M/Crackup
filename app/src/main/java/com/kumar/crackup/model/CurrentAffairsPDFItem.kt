package com.kumar.crackup.model

import com.kumar.crackup.currentaffairs.DownloadState

data class CurrentAffairsPDFItem(
    val id: String,
    val title: String,
    val subtitle: String,
    val sizeLabel: String,
    var downloadState: DownloadState = DownloadState.NOT_DOWNLOADED,
    var progress: Float = 0f,
)