package com.ifs21008.dinopedia

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
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

        binding.rvFamily.setHasFixedSize(false)
        dataFamily.addAll(getListFamily())
        showRecyclerList()
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

        val listFamily = ArrayList<Family>()
        for(i in dataName.indices) {
            val family = Family(dataIcon.getResourceId(i, -1), dataName[i], dataDesc[i], dataPeriodTime[i], dataCharacteristic[i], dataHabit[i], dataEnv[i])
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

    private fun showSelectedFamily(item: Family) {
        val intentWithData = Intent(this@MainActivity, DinosaurDetailActivity::class.java)
        intentWithData.putExtra(DinosaurDetailActivity.EXTRA_ITEM, item)
        startActivity(intentWithData)
    }
}