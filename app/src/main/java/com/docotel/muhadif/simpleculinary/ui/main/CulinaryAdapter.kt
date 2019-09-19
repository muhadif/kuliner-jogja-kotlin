package com.docotel.muhadif.simpleculinary.ui.main

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
import androidx.core.content.ContextCompat

class CulinaryAdapter(
    private val context : Context,
    private val culinaries : List<Culinary>,
    val listener : (Culinary) -> Unit
) : RecyclerView.Adapter<CulinaryAdapter.ViewHolder>() {

    private var selectedItem = 0
    private var lastSelected = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_culiner,
                parent,
                false
            )
        )


    override fun getItemCount(): Int = culinaries.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val backgroundColor =
            if (position === selectedItem) R.color.colorPrimary  else R.color.colorAccent


        val culinary = culinaries[position]
        holder.bindItem(culinary)

        holder.changeTextColor(ContextCompat.getColor(context, backgroundColor))

        holder.itemView.setOnClickListener{
            listener.invoke(culinary)
            lastSelected = selectedItem;
            selectedItem = position;

            notifyItemChanged(lastSelected);
            notifyItemChanged(selectedItem)
        }
    }

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bindItem(culinary: Culinary) {
            tv_culinery.text = culinary.name
            tv_city.text = culinary.city
            Picasso
                .get()
                .load(culinary.image)
                .into(iv_culinery)
        }

        fun changeTextColor(colorId : Int){
            tv_culinery.setTextColor(colorId)
        }
    }

}