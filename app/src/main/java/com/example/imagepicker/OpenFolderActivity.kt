package com.example.imagepicker

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.imagepicker.adapter.OpenImageFoldAdapter
import com.example.imagepicker.databinding.ActivityOpenFolderBinding
import com.example.imagepicker.viewModel.OpenFolderViewModel

class OpenFolderActivity : AppCompatActivity() {

    private val tag = "OpenFolder"
    private lateinit var binding: ActivityOpenFolderBinding
    private lateinit var openImageFoldAdapter: OpenImageFoldAdapter
    private lateinit var value: String
    private lateinit var uuid: String
    lateinit var openFolderViewModel: OpenFolderViewModel


    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityOpenFolderBinding.inflate(layoutInflater)
        setContentView(binding.root)
        openFolderViewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application))[OpenFolderViewModel::class.java]

        galleryOpen()
        value = intent.extras?.getString("Name") ?: ""
        uuid = intent.extras?.getString("uuid") ?: ""

        //Change the status Bar Color
        window.statusBarColor = ContextCompat.getColor(this, R.color.blue)
        initialise()
        checkPermission()
        observeImageFolder()

        binding.backPressed.setOnClickListener{
            onBackPressedDispatcher.onBackPressed()
        }
    }
    private fun initialise() {
        binding.addImages.text = value
        openImageFoldAdapter = OpenImageFoldAdapter(this)
        binding.imagesRecyclerview.layoutManager = LinearLayoutManager(this)
        binding.imagesRecyclerview.layoutManager = GridLayoutManager(this, 3)
        binding.imagesRecyclerview.adapter = openImageFoldAdapter

    }
    private fun observeImageFolder() {
        openFolderViewModel.allImageFolder?.observe(this) { it ->
            Log.d(tag,"openFolderViewModel -->>> ${it}")

            it?.let { imageList ->
                val filteredImages = imageList.filter { it.imageUuid == uuid }
                Log.d(tag,"openFolderViewModel OpenFolderActivity -->>> ${filteredImages}")
                openImageFoldAdapter.updateImage(filteredImages.map { it.folderImageList })
            }
        }
    }
    private val someActivityResultLauncher =
        registerForActivityResult(ActivityResultContracts.PickMultipleVisualMedia()) { it ->
            if (it != null) {
                val imageUrls = it.map { it.toString() }
                Log.d(tag,"currentImageUrl 000-->>>>>>> $imageUrls")

                openFolderViewModel.insert(imageUrls,uuid)
            }
        }

    private fun galleryOpen() {
        binding.selectImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            someActivityResultLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageAndVideo))
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun checkPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.READ_MEDIA_IMAGES
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.READ_MEDIA_IMAGES),
                RECEIVER_EXPORTED
            )
        } else {
            galleryOpen()
        }
    }
}

