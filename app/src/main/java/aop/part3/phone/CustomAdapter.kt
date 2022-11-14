package aop.part3.phone

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import aop.part3.phone.databinding.ItemMainBinding

class CustomAdapter(val dataList:MutableList<DataVO>): RecyclerView.Adapter<CustomAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
       
        val binding = ItemMainBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        val customViewHolder = CustomViewHolder(binding)

        //리사이클러뷰 아이템을 클릭했을때 threefrag 넘어가면서 번호 주기 추가
        customViewHolder.itemView.setOnClickListener {
            val position : Int = customViewHolder.bindingAdapterPosition
            val dataVONumber = dataList.get(position).phoneNumber
            (parent.context as MainActivity).threeFragment.binding.tvPhonenum.text= dataVONumber.toString()
            (parent.context as MainActivity).changeToThreeFragment()
        }

        //리사이클러뷰 아이템을 길게 클릭했을때 삭제 or 즐겨찾기 다이얼로그 띄우기
        customViewHolder.itemView.setOnLongClickListener {
            val position : Int = customViewHolder.bindingAdapterPosition
            val name = dataList.get(position).name
            val phoneNum = dataList.get(position).phoneNumber
            val dialog = CustomTwoDialog(binding.root.context)
            dialog.showDialog(name, phoneNum)
            true
        }
        return customViewHolder
    }

    override fun onBindViewHolder(customViewHolder: CustomViewHolder, position: Int) {
        val binding = (customViewHolder as CustomViewHolder).binding
        val dataVO = dataList.get(position)
        binding.ivPicture.setImageResource(dataVO.picture)
        binding.tvName.text = dataVO.name
        binding.tvPhoneNumber.text = dataVO.phoneNumber
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    class CustomViewHolder(val binding: ItemMainBinding):RecyclerView.ViewHolder(binding.root)
}
