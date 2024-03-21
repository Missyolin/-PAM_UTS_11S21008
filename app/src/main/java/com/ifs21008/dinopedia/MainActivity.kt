package com.ifs21008.dinopedia

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ifs21008.dinopedia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val dataFamily = ArrayList<Family>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val btnProfile = findViewById<ImageView>(R.id.btnProfile)

        binding.rvFamily.setHasFixedSize(false)
        dataFamily.addAll(getListFamily())
        showRecyclerList()

        btnProfile.setOnClickListener{
            val intent = Intent(this@MainActivity,
                MyProfile::class.java)
            intent.addFlags(
                Intent.FLAG_ACTIVITY_CLEAR_TOP or
                        Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }
    }

    @SuppressLint("Recycle")
    private fun getListFamily() : ArrayList<Family> {
        val dataName = resources.getStringArray(R.array.family_name)
        val dataIcon = resources.obtainTypedArray(R.array.family_icon)
        val dataDesc = resources.getStringArray(R.array.family_desc)
        val dataPeriodTime = resources.getStringArray(R.array.family_period_time)
        val dataCharacteristic = resources.getStringArray(R.array.family_physics_characteristic)
        val dataHabit = resources.getStringArray(R.array.family_habit)
        val dataEnv = resources.getStringArray(R.array.family_environment)
        val dataStart = resources.getStringArray(R.array.start_index)
        val dataEnd = resources.getStringArray(R.array.end_index)

        val listFamily = ArrayList<Family>()
        for(i in dataName.indices) {
            val dataStart = dataStart[i].toIntOrNull() ?: 0
            val dataEnd = dataEnd[i].toIntOrNull() ?: 0

            val family = Family(dataIcon.getResourceId(i, -1), dataName[i], dataDesc[i], dataPeriodTime[i], dataCharacteristic[i], dataHabit[i], dataEnv[i], dataStart, dataEnd)
            listFamily.add(family)
        }
        return listFamily
    }

    private fun showRecyclerList() {
        if (resources.configuration.orientation ==
            Configuration.ORIENTATION_LANDSCAPE
        ) {
            binding.rvFamily.layoutManager =
                GridLayoutManager(this, 2)
        } else {
            binding.rvFamily.layoutManager =
                LinearLayoutManager(this)
        }

        val listItemAdapter = ListFamilyAdapter(dataFamily)
        binding.rvFamily.adapter = listItemAdapter
        listItemAdapter.setOnItemClickCallback(object :
            ListFamilyAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Family) {
                showSelectedFamily(data)
            }
        })
    }

    private fun showSelectedFamily(family: Family) {
        val intentWithData = Intent(this@MainActivity, FamilyDetailActivity::class.java)
        intentWithData.putExtra(FamilyDetailActivity.EXTRA_FAMILY, family)
        startActivity(intentWithData)
    }
}