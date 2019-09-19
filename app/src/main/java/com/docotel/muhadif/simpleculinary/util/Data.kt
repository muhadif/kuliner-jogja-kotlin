package com.docotel.muhadif.simpleculinary.util

import android.content.Context
import com.docotel.muhadif.simpleculinary.R
import com.docotel.muhadif.simpleculinary.model.Culinary

object Data {
    fun getCulinaries(context: Context) : List<Culinary>{
        var culinaries : MutableList<Culinary> = mutableListOf()

        val culinaryName = context.resources.getStringArray(R.array.culinary_list_name)
        val culinaryImage = context.resources.obtainTypedArray(R.array.culinary_list_img)
        val culinaryCity = context.resources.getStringArray(R.array.culinary_list_city)
        val culinaryContent = context.resources.getStringArray(R.array.culinary_list_content)
        for (i in culinaryName.indices){
            culinaries.add(
                Culinary(
                    culinaryName[i],
                    culinaryImage.getResourceId(i, 0),
                    culinaryCity[i],
                    culinaryContent[i]
                )
            )
        }

        return culinaries
    }

    fun getOtherCulinaries(context: Context, culinary: Culinary) : List<Culinary> {
        val culinaries = getCulinaries(context)

        return culinaries.filterNot {
            it == culinary
        }
    }
}