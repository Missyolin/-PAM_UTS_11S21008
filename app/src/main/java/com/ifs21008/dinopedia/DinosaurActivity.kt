package com.ifs21008.dinopedia

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ifs21008.dinopedia.databinding.ActivityDinosaurBinding

class DinosaurActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDinosaurBinding
    private lateinit var listDinosaurAdapter: ListDinosaurAdapter
    private val dataDinosaurs = ArrayList<Dinosaurus>()
    private var family: Family? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDinosaurBinding.inflate(layoutInflater)
        setContentView(binding.root)

        family = intent.getParcelableExtra(FamilyDetailActivity.EXTRA_FAMILY)
        Log.d("DinosaurActivity", "Family: $family")

        binding.rvDino.setHasFixedSize(true)
        setupRecyclerView()

        dataDinosaurs.addAll(getListDinosaur())
        listDinosaurAdapter.notifyDataSetChanged()
    }

    private fun setupRecyclerView() {
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            binding.rvDino.layoutManager = GridLayoutManager(this, 2)
        } else {
            binding.rvDino.layoutManager = LinearLayoutManager(this)
        }
        listDinosaurAdapter = ListDinosaurAdapter(dataDinosaurs)
        binding.rvDino.adapter = listDinosaurAdapter
        listDinosaurAdapter.setOnItemClickCallback(object : ListDinosaurAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Dinosaurus) {
                showSelectedDinosaur(data)
            }
        })
    }

    @SuppressLint("Recycle")
    private fun getListDinosaur(): List<Dinosaurus> {
        val listDinosaurs = ArrayList<Dinosaurus>()
        val dataNames = resources.getStringArray(R.array.dino_name)
        val dataIcons = resources.obtainTypedArray(R.array.dino_icon)
        val dataDescriptions = resources.getStringArray(R.array.dino_desc)
        val dataPeriodTimes = resources.getStringArray(R.array.dino_period_time)
        val dataCharacteristics = resources.getStringArray(R.array.dino_physics_characteristic)
        val dataHabits = resources.getStringArray(R.array.dino_habit)
        val dataFoods = resources.getStringArray(R.array.dino_food)
        val dataLengths = resources.getStringArray(R.array.dino_length)
        val dataHeights = resources.getStringArray(R.array.dino_height)
        val dataWeights = resources.getStringArray(R.array.dino_weight)
        val dataWeaknesses = resources.getStringArray(R.array.dino_weakness)

        val startIndex = family?.family_start ?: 0
        val endIndex = family?.family_end ?: 0
        val actualStartIndex = if (startIndex < 0) 0 else startIndex
        val actualEndIndex = if (endIndex > dataNames.size) dataNames.size else endIndex

        for (i in actualStartIndex..actualEndIndex) {
            val dinosaur = Dinosaurus(
                dataIcons.getResourceId(i, -1),
                dataNames[i],
                dataDescriptions[i],
                dataPeriodTimes[i],
                dataCharacteristics[i],
                dataHabits[i],
                dataFoods[i],
                dataLengths[i],
                dataHeights[i],
                dataWeights[i],
                dataWeaknesses[i]
            )
            listDinosaurs.add(dinosaur)
        }
        dataIcons.recycle()
        return listDinosaurs
    }

    private fun showSelectedDinosaur(dinosaur: Dinosaurus) {
        val intent = Intent(this@DinosaurActivity, DinosaurDetailActivity::class.java)
        intent.putExtra(DinosaurDetailActivity.EXTRA_DINOSAURUS,dinosaur)
        startActivity(intent)
    }
}
