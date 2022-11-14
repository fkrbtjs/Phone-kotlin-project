package aop.part3.phone

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import aop.part3.phone.databinding.FragmentOneBinding
import aop.part3.phone.databinding.FragmentTwoBinding

class TwoFragment : Fragment() {
    lateinit var binding : FragmentTwoBinding
    lateinit var customAdapter2: CustomAdapter2
    var dataList2 = mutableListOf<DataVO2>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTwoBinding.inflate(inflater,container,false)

        //리사이클러뷰에 보여줄 레이아웃 결정
        val linearLayoutManager = LinearLayoutManager(container?.context)

        //리사이클러뷰에 제공할 어뎁터
        customAdapter2 = CustomAdapter2(dataList2)
        binding.f2Recyclerview.layoutManager = linearLayoutManager
        binding.f2Recyclerview.adapter = customAdapter2
//        binding.f2Recyclerview.addItemDecoration(MyDecoration(binding.root.context))
        return binding.root
    }

    fun refreshRecyclerViewDrop2(dataVO2: DataVO2) {
        dataList2.remove(dataVO2)
        customAdapter2.notifyDataSetChanged()
    }

    fun refreshRecyclerViewAdd2(dataVO2: DataVO2) {
        Toast.makeText(binding.root.context,"${dataVO2.phoneNum}으로 전화를 걸었습니다. ", Toast.LENGTH_LONG).show()
        dataList2.add(dataVO2)
        customAdapter2.notifyDataSetChanged()
    }
}
