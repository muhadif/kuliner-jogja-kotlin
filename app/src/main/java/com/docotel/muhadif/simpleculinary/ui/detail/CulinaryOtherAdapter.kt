package com.docotel.muhadif.simpleculinary.ui.detail

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.docotel.muhadif.simpleculinary.R
import com.docotel.muhadif.simpleculinary.model.Culinary
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_culiner.*
import kotlinx.android.synthetic.main.item_culiner_detail.*


class CulinaryOtherAdapter(
    private val context : Context,
    private val culinaries : List<Culinary>,
    val listener : (Culinary) -> Unit
) : RecyclerView.Adapter<CulinaryOtherAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_culiner_detail,
                parent,
                false
            )
        )


    override fun getItemCount(): Int = culinaries.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val culinary = culinaries[position]
        holder.bindItem(culinary)
        holder.itemView.setOnClickListener{
            listener.invoke(culinary)
        }
    }

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bindItem(culinary: Culinary) {
            tv_item_other.text = culinary.name
            Picasso
                .get()
                .load(culinary.image)
                .into(iv_item_other)
        }
    }

}