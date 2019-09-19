package com.docotel.muhadif.simpleculinary.ui.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.docotel.muhadif.simpleculinary.R
import com.docotel.muhadif.simpleculinary.model.Culinary
import com.docotel.muhadif.simpleculinary.util.Data
import com.google.android.material.appbar.AppBarLayout
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.item_culiner.*

class DetailActivity : AppCompatActivity() {

    companion object {
        const val CULINARY_DATA = "culinary_data"
    }

    lateinit var culinary : Culinary
    lateinit var otherCulinaries : List<Culinary>
    lateinit var detailTitle : String
    lateinit var culinaryOtherAdapter: CulinaryOtherAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        initToolbar()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setAllTitle("")

        intent?.getParcelableExtra<Culinary>(CULINARY_DATA)?.let {
            culinary = it
        }

        culinary?.let {
            showData(it)
            otherCulinaries = Data.getOtherCulinaries(this, it)
        }

        initRecycleView()


    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)
        collapsing_toolbar_detail.isTitleEnabled = false

        appbar.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener{
            internal var isShow = false
            internal var scrollRange = -1

            override fun onOffsetChanged(p0: AppBarLayout?, p1: Int) {
                if (scrollRange == -1) {
                    scrollRange = appbar.totalScrollRange
                }
                if (scrollRange + p1 == 0) {
                    setAllTitle(detailTitle)
                    isShow = true
                } else if (isShow) {
                    setAllTitle("")
                    isShow = false
                }
            }

        })
    }

    private fun initRecycleView() {
        culinaryOtherAdapter = CulinaryOtherAdapter(this@DetailActivity, otherCulinaries) {
            val detailIntent = Intent(this@DetailActivity, DetailActivity::class.java)
            detailIntent.putExtra(CULINARY_DATA, it)
            startActivity(detailIntent)
        }

        rv_culinery_other.apply {
            adapter = culinaryOtherAdapter
        }
    }

    private fun showData(culinary: Culinary) {
        detailTitle = culinary.name
        tv_detail_title.text = culinary.name
        tv_content_detail.text = culinary.content
        Picasso
            .get()
            .load(culinary.image)
            .into(iv_detail)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun setAllTitle(title: String) {
        collapsing_toolbar_detail.title = title
        this.title = title
    }
}
