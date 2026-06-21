package com.vibhorpatil.schoolmanagement.utils

import android.content.Context
import android.net.Uri
import java.io.File
import java.io.FileOutputStream

object Util {

    fun copyImageToAppStorage(
        context: Context,
        uri: Uri
    ): String {

        val fileName = "student_${System.currentTimeMillis()}.jpg"

        val file = File(context.filesDir, fileName)

        context.contentResolver.openInputStream(uri)?.use { input ->
            FileOutputStream(file).use { output ->
                input.copyTo(output)
            }
        }

        return file.absolutePath
    }
}