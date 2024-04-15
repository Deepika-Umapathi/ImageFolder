package com.example.imagepicker.adapter

import android.content.Context
import com.example.imagepicker.OpenFolderActivity
import com.example.imagepicker.databinding.ItemFolderViewBinding
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.imagepicker.dao.FolderEntity


class ItemFolderAdapter(private var context: Context,val menuDeleteClick:DeleteClickListener) :
    RecyclerView.Adapter<ItemFolderAdapter.ViewHolder>() {
    private  val tag = "ItemFolderAdapter"

    private lateinit var binding: ItemFolderViewBinding
    var  allFolders = ArrayList<FolderEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        binding = ItemFolderViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val list = allFolders[position]
        binding.folderName.text = list.name
        println("menuDeleteClick -->>>>>>>>>>> ${allFolders[position].name}")

        binding.listLayout.setOnClickListener {
            val intent = Intent(context, OpenFolderActivity::class.java)
            intent.putExtra("Name", list.name)
            intent.putExtra("uuid", list.folderUuid)
            Log.d(tag,"onCreate ItemFolderAdapter -->>> ${list.folderUuid}")

            context.startActivity(intent)
        }
        binding.menuBar.setOnClickListener{
           menuDeleteClick.menuDeleteClick(list)
        }
    }

    override fun getItemCount(): Int {
        return allFolders.size
    }

    fun updateList(newList: List<FolderEntity>) {

        allFolders.clear()
        allFolders.addAll(newList)
        notifyDataSetChanged()
    }
    class ViewHolder(binding: ItemFolderViewBinding) : RecyclerView.ViewHolder(binding.root) {
    }



    interface DeleteClickListener{

        fun menuDeleteClick(folderEntity: FolderEntity)

    }

}