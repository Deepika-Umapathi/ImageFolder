package com.example.imagepicker

import android.Manifest.permission.READ_MEDIA_IMAGES
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.imagepicker.databinding.OpenImageActivityBinding

class OpenImageActivity:AppCompatActivity() {
    private val tag = "OpenImageActivity"
    private lateinit var binding: OpenImageActivityBinding
    private var currentImageUrl: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = OpenImageActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = ContextCompat.getColor(this, R.color.blue)
        val currentImageUrl = intent.getStringExtra("currentImageUrl")
        this.currentImageUrl = currentImageUrl
        Log.d(tag, "currentImageUrl -->>>>>>> $currentImageUrl")
        loadImage(currentImageUrl)
        binding.backPressed.setOnClickListener{
            onBackPressedDispatcher.onBackPressed()
        }

    }


    @SuppressLint("WrongConstant")
    private fun loadImage(currentImageUrl: String?) {
        if (currentImageUrl != null) {
            try {
                Glide.with(this)
                    .load(currentImageUrl)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(binding.imageView)
            } catch (e: SecurityException) {
                Log.e("OpenImageActivity", "Error loading image", e)
            }
        } else {
            Log.e("OpenImageActivity", "No image URL provided")
        }
    }
}
