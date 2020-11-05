package info.noahortega.bluecointracker.BCList

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import info.noahortega.bluecointracker.R
import kotlinx.android.synthetic.main.list_item.view.*

class BCAdapter(
    private val bcList: List<BCListItem>,
    private val listener: OnItemClickListener,
    private val context:Context
) : RecyclerView.Adapter<BCAdapter.BCViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BCViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.list_item, parent, false
        )
        return BCViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: BCViewHolder, position: Int) {
        val currentItem = bcList[position]

        holder.checkBox.isChecked = currentItem.collected
        holder.title.text = context.resources.getString(
            context.resources.getIdentifier(currentItem.title_text_1, "string", context.packageName)
        )
        holder.description.text = currentItem.description_text_2

        // DON'T DO: holder.itemView.title_text.text = currentItem.title_text
    }

    override fun getItemCount() = bcList.size

    inner class BCViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val checkBox: CheckBox = itemView.checkbox_view
        val title: TextView = itemView.title_text
        val description: TextView = itemView.description_text

        init {
            checkBox.setOnClickListener(this)
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position:Int = adapterPosition
            val checked = checkBox.isChecked
            if (position != RecyclerView.NO_POSITION && v != null) {
                listener.onItemClick(position, checked, v::class.simpleName!!)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int, checked: Boolean, viewType: String)
    }
}