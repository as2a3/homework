package com.said.homework.base.presentation.util

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import java.io.File

object ImageLoader {
    fun loadImage(context: Context?, imageUrl: String?, targetImageView: ImageView) {
        targetImageView.post {
            if (isValidContext(context)) {
                Glide.with(context!!)
                    .load(imageUrl)
                    .into(targetImageView)
            }
        }
    }

    fun loadImage(
        context: Context?, imageUrl: String?, @DrawableRes placeHolder: Int,
        @DrawableRes errorDrawable: Int, targetImageView: ImageView
    ) {
        targetImageView.post {
            if (isValidContext(context)) {
                Glide.with(context!!)
                    .load(imageUrl)
                    .placeholder(placeHolder)
                    .error(errorDrawable)
                    .into(targetImageView)
            }
        }
    }

    fun loadImage(context: Context?, imageFile: File?, targetImageView: ImageView) {
        targetImageView.post {
            if (isValidContext(context)) {
                Glide.with(context!!)
                    .load(imageFile)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .into(targetImageView)
            }
        }
    }

    fun loadImageFromURI(selectedImage: Uri?, ctx: Context): Bitmap {
        val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = ctx.contentResolver.query(
            selectedImage!!,
            filePathColumn, null, null, null
        )
        cursor?.moveToFirst()
        val columnIndex = cursor?.getColumnIndex(filePathColumn[0]) ?: 0
        val imagePath = cursor!!.getString(columnIndex)
        cursor.close()
        return BitmapFactory.decodeFile(imagePath)
    }

    private fun isValidContext(context: Context?): Boolean {
        if (context == null) {
            return false
        } else if (context is Activity) {
            return !context.isDestroyed
        }
        return true
    }
}