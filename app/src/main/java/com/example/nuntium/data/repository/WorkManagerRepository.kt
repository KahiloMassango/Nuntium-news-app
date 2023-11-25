package com.example.nuntium.data.repository

import android.content.Context
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.nuntium.R
import com.example.nuntium.workers.DeleteImageWorker
import com.example.nuntium.workers.DownloadImageWorker
import com.example.nuntium.workers.KEY_FILE_NAME
import com.example.nuntium.workers.KEY_IMAGE_URL

interface WorkManagerRepository {
    fun downloadImage(url: String, fileName: String)

    fun deleteImage(uri: String)
}

class WorkManagerRepositoryImpl(private val context: Context): WorkManagerRepository {

    private val workManager = WorkManager.getInstance(context)

    override fun downloadImage(url: String, fileName: String) {
        val data = Data.Builder()
            .putString(KEY_IMAGE_URL, url)
            .putString(KEY_FILE_NAME, fileName)
            .build()
        val imageDownload = OneTimeWorkRequestBuilder<DownloadImageWorker>()
            .setInputData(data)
            .build()
        workManager.enqueue(imageDownload)
    }

    override fun deleteImage(uri: String) {
        val matchUUID = context.getString(R.string.matchUUID).toRegex()
        val articleUUID = matchUUID.find(uri)!!.value

        val data = Data.Builder()
            .putString("UUID", articleUUID)
            .build()


        val deleteWorker = OneTimeWorkRequestBuilder<DeleteImageWorker>()
            .setInputData(data)
            .build()

        workManager.enqueue(deleteWorker)
    }
}