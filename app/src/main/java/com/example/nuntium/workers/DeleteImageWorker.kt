package com.example.nuntium.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

class DeleteImageWorker(ctx: Context, params: WorkerParameters): CoroutineWorker(ctx, params) {
    override suspend fun doWork(): Result {

        val imageUid = inputData.getString("UUID")!!
        return withContext(Dispatchers.IO) {
            return@withContext try {
                val outputDirectory = File(OUTPUT_PATH)
                if(outputDirectory.exists()) {
                    val entries = outputDirectory.listFiles()
                    if (entries != null) {
                        for (entry in entries) {
                            val name = entry.name
                            if(imageUid in name){
                               entry.delete()
                            }
                        }
                    }
                }
                Result.success()
            } catch (e: Exception) {
                Result.failure()
            }
        }
    }
}