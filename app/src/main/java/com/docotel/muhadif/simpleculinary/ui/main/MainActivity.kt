package com.docotel.muhadif.simpleculinary.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.docotel.muhadif.simpleculinary.R
import com.docotel.muhadif.simpleculinary.model.Culinary
import com.docotel.muhadif.simpleculinary.ui.about.AboutActivity
import com.docotel.muhadif.simpleculinary.ui.detail.DetailActivity
import com.docotel.muhadif.simpleculinary.util.Data
import kotlinx.android.synthetic.main.activity_main.*
import java.util.zip.Inflater

class MainActivity : AppCompatActivity() {

    private var culinaries : List<Culinary> = mutableListOf()
    lateinit var culinaryAdapter : CulinaryAdapter
    lateinit var layoutManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        initData()
        initView()

        rv_culinery.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = culinaryAdapter
        }

    }

    private fun initView() {
        culinaryAdapter = CulinaryAdapter(this, culinaries) {
            val detailIntent = Intent(this@MainActivity, DetailActivity::class.java)
            detailIntent.putExtra(DetailActivity.CULINARY_DATA, it)
            startActivity(detailIntent)
        }
    }

    private fun initData() {
        culinaries = Data.getCulinaries(this@MainActivity)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_about -> {
                val aboutIntent = Intent(this@MainActivity, AboutActivity::class.java)
                startActivity(aboutIntent)
            }
        }

        return super.onOptionsItemSelected(item)
    }
}
