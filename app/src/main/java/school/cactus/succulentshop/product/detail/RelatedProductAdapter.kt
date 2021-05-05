package school.cactus.succulentshop.product.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import school.cactus.succulentshop.databinding.ItemRelatedProductBinding
import school.cactus.succulentshop.product.list.Product

class RelatedProductAdapter : ListAdapter<Product, RelatedProductAdapter.RelatedProductHolder>(DIFF_CALLBACK) {

    var itemClickListener: (Product) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RelatedProductHolder {
        val binding = ItemRelatedProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return RelatedProductHolder(binding, itemClickListener)
    }

    override fun onBindViewHolder(holder: RelatedProductHolder, position: Int) =
        holder.bind(getItem(position))

    class RelatedProductHolder(
        private val binding: ItemRelatedProductBinding,
        private val itemClickListener: (Product) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) {
            binding.titleText.text = product.title
            binding.priceText.text = product.price
            /*binding.imageView.setImageResource(product.imageUrl)*/
            Glide.with(binding.root.context)
                .load(product.imageUrl)
                .override(512)
                .into(binding.imageView)

            binding.root.setOnClickListener {
                itemClickListener(product)
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Product>() {
            override fun areItemsTheSame(oldItem: Product, newItem: Product) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Product, newItem: Product) = oldItem == newItem
        }
    }
}