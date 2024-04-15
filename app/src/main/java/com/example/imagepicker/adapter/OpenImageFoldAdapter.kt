package com.example.imagepicker.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.imagepicker.OpenImageActivity
import com.example.imagepicker.dao.FolderEntity
import com.example.imagepicker.dao.OpenImageEntity
import com.example.imagepicker.databinding.OpenImageItemViewBinding
import com.example.imagepicker.viewModel.MainActivityViewModel
import java.util.ArrayList

class OpenImageFoldAdapter(
    private var context: Context,
): RecyclerView.Adapter<OpenImageFoldAdapter.ViewHolder>() {

    private lateinit var binding: OpenImageItemViewBinding
    private var imageList: List<String> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {


        binding = OpenImageItemViewBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return ViewHolder(binding)

    }

    override fun getItemCount(): Int {
        return imageList.size

    }

    @SuppressLint("SuspiciousIndentation")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val currentImageUrl = imageList[position]
        Log.d("tag","openFolderViewModel OpenImageFoldAdapter-->>> $currentImageUrl")

        Glide.with(context)
            .load(currentImageUrl)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(holder.binding.photosInside)

        holder.binding.photosInside.setOnClickListener{
            val intent = Intent(context, OpenImageActivity::class.java)
            intent.putExtra("currentImageUrl",currentImageUrl)
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
            intent.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION)
            context.startActivity(intent)
        }
    }

    fun updateImage(newImageUrl: List<String>) {
        this.imageList = newImageUrl
        notifyDataSetChanged()
    }

    class ViewHolder(var binding:OpenImageItemViewBinding):RecyclerView.ViewHolder(binding.root){}
}


