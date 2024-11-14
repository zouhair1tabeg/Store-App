package com.example.shoes_store_firebase

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ProductAdapter(private val smartphones: List<Smartphone>) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.image)
        val name: TextView = itemView.findViewById(R.id.nom)
        val price: TextView = itemView.findViewById(R.id.prix)
        val addToCartButton: Button = itemView.findViewById(R.id.ajouter_pannier)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product_card, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val smartphone = smartphones[position]
        holder.name.text = smartphone.nom
        holder.price.text = "${smartphone.prix} dh"

        // Load image with Glide or Picasso
        Glide.with(holder.image.context).load(smartphone.image).into(holder.image)

        // Logic for "Add to Cart" button
        holder.addToCartButton.setOnClickListener {
            // Handle adding to cart here
        }
    }

    override fun getItemCount(): Int = smartphones.size
}
