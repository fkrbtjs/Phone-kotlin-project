package aop.part3.phone

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import aop.part3.phone.databinding.ItemRecordBinding

class CustomAdapter2(val dataList2:MutableList<DataVO2>): RecyclerView.Adapter<CustomAdapter2.CustomViewHolder2>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder2 {
        val binding = ItemRecordBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        val customViewHolder2 = CustomViewHolder2(binding)

        //리사이클러뷰 아이템을 길게 클릭했을때 삭제
        customViewHolder2.itemView.setOnLongClickListener {
            val position: Int = customViewHolder2.bindingAdapterPosition
            val dataVO2 = dataList2.get(position)
            (parent.context as MainActivity).twoFragment.refreshRecyclerViewDrop2(dataVO2)
            true
        }
        return customViewHolder2
    }

    override fun onBindViewHolder(customViewHolder2: CustomViewHolder2, position: Int) {
        val binding = (customViewHolder2 as CustomViewHolder2).binding
        val dataVO2 = dataList2.get(position)
        binding.recordName.text = dataVO2.phoneNum
        binding.recordDateTime.text = dataVO2.date
    }

    override fun getItemCount(): Int {
        return dataList2.size
    }

    class CustomViewHolder2(val binding: ItemRecordBinding): RecyclerView.ViewHolder(binding.root)
}
