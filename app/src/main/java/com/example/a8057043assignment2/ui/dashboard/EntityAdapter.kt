package com.example.a8057043assignment2.ui.dashboard
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.a8057043assignment2.R
import com.example.a8057043assignment2.data.model.Entity

class EntityAdapter(
    private val onItemClick: (Entity) -> Unit
) : RecyclerView.Adapter<EntityAdapter.EntityViewHolder>() {

    private var entityList: List<Entity> = listOf()

    class EntityViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val concept: TextView = view.findViewById(R.id.itemConcept)
        private val scientist: TextView = view.findViewById(R.id.itemScientist)
        private val fieldYear: TextView = view.findViewById(R.id.itemFieldYear)
        private val branch: TextView = view.findViewById(R.id.itemBranch)

        fun bind(entity: Entity, onItemClick: (Entity) -> Unit) {
            concept.text = entity.concept
            scientist.text = entity.scientist
            fieldYear.text = "${entity.field} · ${entity.yearProposed}"
            branch.text = entity.branch
            itemView.setOnClickListener { onItemClick(entity) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntityViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_entity, parent, false)
        return EntityViewHolder(view)
    }

    override fun onBindViewHolder(holder: EntityViewHolder, position: Int) {
        holder.bind(entityList[position], onItemClick)
    }

    override fun getItemCount() = entityList.size

    fun setData(newList: List<Entity>) {
        entityList = newList
        notifyDataSetChanged()
    }
}
