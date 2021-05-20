package com.example.recyclerviewdavaleba

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.GridLayoutManager
import com.example.recyclerviewdavaleba.databinding.ActivityMainBinding


/**LongClickListener-ისა და getItemViewType-ს გამოყენება,
*აგრეთვე ორი ვიუ ჰოლდერი ფოტოების მაგალითზე */

class MainActivity : AppCompatActivity(), RecyclerAdapter.OnItemModelClickListener {

    private var countries = mutableListOf<Countries>()

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        binding.btnAdd.setOnClickListener {
            addCountry()
        }
        setContentView(binding.root)
        enabledButton()
        init()
    }

    private fun init() {
        setData()
        adapter = RecyclerAdapter(countries, this)
        binding.recyclerView.layoutManager = GridLayoutManager(this, 2)
        binding.recyclerView.adapter = adapter
    }


    override fun onItemClick(position: Int) {
        countries.removeAt(position)
        adapter.notifyItemRemoved(position)
        Toast.makeText(this, "Country Removed!", Toast.LENGTH_SHORT).show()

    }

    private fun addCountry() {

        val name = binding.etEnterCountry.text.toString()
        val continent = binding.etEnterContinent.text.toString()
        val country = Countries(name,continent)
        countries.add(country)
        adapter.notifyItemInserted(countries.size - 1)
        Toast.makeText(this, "New Country Added!", Toast.LENGTH_SHORT).show()
    }

    private fun enabledButton() {

        binding.etEnterCountry.doOnTextChanged { _, _, _, _ ->

            binding.btnAdd.isEnabled = binding.etEnterCountry.text.toString().trim { it <= ' ' }.isNotEmpty()
        }

    }







    private fun setData() {

        countries.add(Countries("Georgia", "Europe",R.drawable.georgia))
        countries.add(Countries("Iceland", "Europe",R.drawable.iceland))
        countries.add(Countries("Switzerland", "Europe",R.drawable.switzerland))
        countries.add(Countries("India", "Asia",R.drawable.india))
        countries.add(Countries("Turkey", "Asia"))
        countries.add(Countries("Qatar", "Middle East",R.drawable.qatar))
        countries.add(Countries("United Kingdom", "Europe",R.drawable.united_kingdom))
        countries.add(Countries("Italy", "Europe"))
        countries.add(Countries("Greece", "Europe"))
        countries.add(Countries("switzerland", "Europe",R.drawable.switzerland))
        countries.add(Countries("Lebanon", "Western Asia",R.drawable.lebanon))
        countries.add(Countries("Germany", "Europe",R.drawable.germany))
        countries.add(Countries("Azerbaijan", "Europe",R.drawable.azerbaijan))
        countries.add(Countries("Croatia", "Europe",R.drawable.croatia))
        countries.add(Countries("Armenia", "Europe"))
        countries.add(Countries("United_states", "North America",R.drawable.united_states))
        countries.add(Countries("Canada", "North America",R.drawable.canada))
        countries.add(Countries("Spain", "Europe",R.drawable.spain))
        countries.add(Countries("France", "Europe",R.drawable.france))
        countries.add(Countries("Ecuador", "South America",R.drawable.ecuador))
        countries.add(Countries("Brazil", "South America",R.drawable.brazil))

    }
}