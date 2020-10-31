package com.max.moviedbtask.core.utils

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Environment
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.max.moviedbtask.BuildConfig
import com.max.moviedbtask.R
import java.io.File
import java.io.FileOutputStream
import java.lang.Exception

/**
 * Created by µðšţãƒâ ™ on 29/10/2020.
 *  ->
 */

fun ImageView.loadUrl(url: String?) {
    url?.let {
        Glide.with(context)
            .load(if (it.isEmpty()) R.drawable.person_placeholder else BuildConfig.URL_STORAGE + it)
            .into(this)
    }
}

fun ImageView.loadDrawable(@DrawableRes drawableId: Int?) {
    drawableId?.let {
        Glide.with(context)
            .load(drawableId)
            .into(this)
    }
}

fun ImageView.savePhoto(download: (statusMessage: String) -> Any) {
    val bitmapDrawable: BitmapDrawable = drawable as BitmapDrawable
    val bitmap: Bitmap = bitmapDrawable.bitmap

    val outputStream: FileOutputStream

    val filePicture =
        File(Environment.getExternalStorageDirectory().path + "/${context.getString(R.string.app_name)}")
    if (!filePicture.exists())
        filePicture.mkdir()

    val fileName = String.format("person-%d.jpg", System.currentTimeMillis())
    val outFile = File(filePicture.absolutePath, fileName)

    try {
        outputStream = FileOutputStream(outFile)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
        outputStream.flush()
        outputStream.close()
        download(context.getString(R.string.save_successfully))
    } catch (e: Exception) {
        download(context.getString(R.string.save_failed))
        e.printStackTrace()
    }
}


fun TextView.listToString(list: List<String>) {
    if (list.isNotEmpty()) {
        val knownForString = list.toString()
        text = knownForString.substring(1, knownForString.length - 1)
    }
}