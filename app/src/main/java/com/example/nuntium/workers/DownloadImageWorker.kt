package com.example.nuntium.workers

import android.content.Context
import android.content.ContextWrapper
import android.graphics.Bitmap
import androidx.core.graphics.drawable.toBitmap
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import coil.Coil
import coil.request.ImageRequest
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


class DownloadImageWorker(
     ctx: Context,
    params: WorkerParameters): CoroutineWorker(ctx, params) {

    override suspend fun doWork(): Result {

        val url = inputData.getString(KEY_IMAGE_URL)!!
        val fileName = inputData.getString(KEY_FILE_NAME)!!

        val bitmap = downloadBitmapFromUrl(url)

        saveBitmapToInternalStorage(bitmap = bitmap, fileName)

        return Result.success()

    }

    private suspend fun downloadBitmapFromUrl(url: String): Bitmap {
        val request = ImageRequest.Builder(applicationContext)
            .data(url)
            .allowHardware(false)
            .build()

        return Coil.imageLoader(applicationContext).execute(request).drawable!!.toBitmap()
    }

    private fun saveBitmapToInternalStorage(bitmap: Bitmap?, fileName: String): String {
        if (bitmap == null) return ""

        val context = applicationContext

        val wrapper = ContextWrapper(context)
        // Creating a directory within the app's private storage where the image will be saved
        val directory = wrapper.getDir("images", Context.MODE_PRIVATE)

        // Generate a unique file name for the image
        val file = File(directory, "${fileName}.jpg")

        try {
            val fileOutputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream)
            fileOutputStream.flush()
            fileOutputStream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        // Return the absolute path of the saved image
        return file.absolutePath
    }

}
