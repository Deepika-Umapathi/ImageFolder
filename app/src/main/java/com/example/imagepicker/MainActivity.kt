package com.example.imagepicker

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.imagepicker.adapter.ItemFolderAdapter
import com.example.imagepicker.dao.FolderEntity
import com.example.imagepicker.databinding.ActivityMainBinding
import com.example.imagepicker.viewModel.MainActivityViewModel
import kotlin.random.Random

class MainActivity : AppCompatActivity(),ItemFolderAdapter.DeleteClickListener {

    private val tag = "MainActivity"
    private lateinit var binding: ActivityMainBinding
    private lateinit var itemFolderAdapter: ItemFolderAdapter
    private lateinit var mainActivityViewModel:MainActivityViewModel
    private var number = 0
    var noteID = -1;
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        mainActivityViewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application))[MainActivityViewModel::class.java]


        itemFolderAdapter = ItemFolderAdapter(this,this)
        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        binding.recyclerview.adapter = itemFolderAdapter

        binding.selectImage.setOnClickListener { addInfo() }
        setToolBar()

        mainActivityViewModel.allFolders?.observe(this) {
            itemFolderAdapter.updateList(it)
            itemFolderAdapter.notifyDataSetChanged()

        }
        binding.backPressed.setOnClickListener{
            onBackPressedDispatcher.onBackPressed()
        }

    }

    private fun setToolBar() {
        window.statusBarColor = ContextCompat.getColor(this, R.color.blue)
    }

    private fun addInfo() {

        val layout = LayoutInflater.from(this).inflate(R.layout.add_item, null)

        val alertDialog = AlertDialog.Builder(this)

        val folderName = layout.findViewById<EditText>(R.id.folder_name_item)

        alertDialog.setView(layout)

        alertDialog.setPositiveButton("Ok") { dialog, _ ->

            mainActivityViewModel.insert(FolderEntity(generateOrderedNDigitNumber(5).toString(), folderName.text.toString()))

            itemFolderAdapter?.notifyDataSetChanged()
            dialog.dismiss()
        }
        alertDialog.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
        }

        alertDialog.create()
        alertDialog.show()

    }
    private fun generateOrderedNDigitNumber(n: Int): Int {
        require(n > 0) { "Number of digits must be greater than 0" }
        repeat(n) { // Repeat n times to generate each digit
            val digit = Random.nextInt(10) // Generate a random digit (0-9)
            number = number * 10 + digit // Append the digit to the number
        }
        return number
    }
    override fun menuDeleteClick(folderEntity: FolderEntity) {
       showDeleteConfirmationPopup(folderEntity)
    }
        private fun showDeleteConfirmationPopup(folderEntity: FolderEntity) {
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle("Delete Confirmation")
        alertDialogBuilder.setMessage("Are you sure you want to delete this item?")
        alertDialogBuilder.setPositiveButton("Yes") { dialog, _ ->
            mainActivityViewModel.delete(folderEntity)
        }
        alertDialogBuilder.setNegativeButton("No") { dialog, _ ->
            dialog.dismiss()
        }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

}