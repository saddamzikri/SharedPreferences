package com.saddam.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import com.saddam.sharedpreferences.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val sharedPrefFile = "kotlinsharedpreference"
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences: SharedPreferences =
            this.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)

        binding.btnSave.setOnClickListener {
            if (binding.etInputId.text.isNullOrEmpty() && binding.etInputName.text.isNullOrEmpty()){
                Toast.makeText(this, "Kolom Data Masih Kosong !", Toast.LENGTH_SHORT).show()
            } else if (binding.etInputId.text.isNullOrEmpty()) {
                Toast.makeText(this, "Kolom ID Masih Kosong !", Toast.LENGTH_SHORT).show()
            } else if (binding.etInputName.text.isNullOrEmpty()) {
                Toast.makeText(this, "Kolom Name Masih Kosong !", Toast.LENGTH_SHORT).show()
            } else {
                val id: Int = Integer.parseInt(binding.etInputId.text.toString())
                val name: String = binding.etInputName.text.toString()
                val editor: SharedPreferences.Editor = sharedPreferences.edit()
                editor.putInt("id_key", id)
                editor.putString("name_key", name)
                editor.apply()
                Toast.makeText(this@MainActivity, "Data Saved", Toast.LENGTH_SHORT).show()
            }
        }
        binding.btnView.setOnClickListener {
            val sharedIdValue = sharedPreferences.getInt("id_key", 0)
            val sharedNameValue = sharedPreferences.getString("name_key", "defaultname")
            if (sharedIdValue.equals(0) && sharedNameValue.equals("defaultname")) {
                binding.tvShowName.setText("default name: ${sharedNameValue}").toString()
                binding.tvShowId.setText("default id: ${sharedIdValue.toString()}")
                Toast.makeText(this, "Data View Kosong", Toast.LENGTH_SHORT).show()
            } else {
                binding.tvShowName.setText(sharedNameValue).toString()
                binding.tvShowId.setText(sharedIdValue.toString())
                Toast.makeText(this@MainActivity, "Data View Ditampilkan", Toast.LENGTH_SHORT).show()
            }
        }
        binding.btnClear.setOnClickListener {
            val editor = sharedPreferences.edit()
            editor.clear()
            editor.apply()
            binding.tvShowName.setText("")
            binding.tvShowId.setText("")
            Toast.makeText(this@MainActivity, "Data Clear", Toast.LENGTH_SHORT).show()
        }
    }
}