package com.example.eventify.data.repositories.files

import com.example.eventify.data.remote.api.FilesAPI
import java.io.InputStream
import javax.inject.Inject

class FilesRepositoryImpl @Inject constructor(
    private val dataSource: FilesAPI
): FilesRepository{
    override suspend fun getFileById(fileId: String): InputStream {
        val response = dataSource.getFile(id = fileId)
        val file = when (response.code()){
            200 -> response.body()?.byteStream()
            else -> null
        }
        return file ?: throw Exception()
    }
}