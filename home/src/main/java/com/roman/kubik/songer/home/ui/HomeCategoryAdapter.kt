package com.roman.kubik.songer.home.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.roman.kubik.songer.home.databinding.ItemHomeCategoryBinding

class HomeCategoryAdapter(private val clickListener: (HomeCategory) -> Unit) :
    RecyclerView.Adapter<HomeCategoryAdapter.CategoryHolder>() {

    private var items = mutableListOf<HomeCategory>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CategoryHolder(
            ItemHomeCategoryBinding.inflate(inflater, parent, false),
            clickListener
        )
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

    class CategoryHolder(
        private val binding: ItemHomeCategoryBinding,
        private val clickListener: (HomeCategory) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(homeCategory: HomeCategory) {
            binding.categoryIcon.setImageResource(homeCategory.icon)
            binding.categoryTitle.text = homeCategory.title
            binding.categorySubtitle.text = homeCategory.subtitle
            itemView.setOnClickListener {
                clickListener.invoke(homeCategory)
            }
        }

    }
}