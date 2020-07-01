package com.roman.kubik.songer.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.roman.kubik.songer.R
import kotlinx.android.synthetic.main.item_home_category.view.*

class HomeCategoryAdapter(private val clickListener: (HomeCategory) -> Unit)
    : RecyclerView.Adapter<HomeCategoryAdapter.CategoryHolder>() {

    private var items = mutableListOf<HomeCategory>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CategoryHolder(inflater.inflate(R.layout.item_home_category, parent, false), clickListener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        holder.bind(items[position])
    }

    fun publishItems(items: List<HomeCategory>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    class CategoryHolder(itemView: View, private val clickListener: (HomeCategory) -> Unit)
        : RecyclerView.ViewHolder(itemView) {

        fun bind(homeCategory: HomeCategory) {
            itemView.categoryIcon.setImageResource(homeCategory.icon)
            itemView.categoryTitle.text = homeCategory.title
            itemView.categorySubtitle.text = homeCategory.subtitle
            itemView.setOnClickListener {
                clickListener.invoke(homeCategory)
            }
        }

    }
}