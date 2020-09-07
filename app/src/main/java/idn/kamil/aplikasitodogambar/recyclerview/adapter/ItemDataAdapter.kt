package idn.kamil.aplikasitodogambar.recyclerview.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import idn.kamil.aplikasitodogambar.AddDataActivity
import idn.kamil.aplikasitodogambar.databinding.ItemDataBinding
import idn.kamil.aplikasitodogambar.model.ModelData
import idn.kamil.aplikasitodogambar.recyclerview.viewholder.ItemDataVH

/**
 * Created by Imam Fahrur Rofi on 04/09/2020.
 */
class ItemDataAdapter : RecyclerView.Adapter<ItemDataVH>() {
    private var listData = arrayListOf<ModelData>()

    fun addData(data: List<ModelData>) {
        listData.clear()
        listData.addAll(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemDataVH {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemDataBinding.inflate(inflater, parent, false)
        return ItemDataVH(binding)
    }

    override fun onBindViewHolder(holder: ItemDataVH, position: Int) {
        val data = listData[position]
        holder.bind(data)

        holder.itemView.setOnClickListener { view:View ->
            val intent = Intent (view.context, AddDataActivity::class.java)
            intent.putExtra("DATA", data)
            view.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = listData.size
}