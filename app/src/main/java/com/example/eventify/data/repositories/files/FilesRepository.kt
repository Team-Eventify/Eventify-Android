package com.example.eventify.data.repositories.files

import android.graphics.Bitmap
import java.io.InputStream

interface FilesRepository {
    suspend fun getFileById(fileId: String): InputStream
}