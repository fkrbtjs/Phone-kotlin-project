package aop.part3.phone

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import aop.part3.phone.databinding.FragmentOneBinding


class OneFragment : Fragment() {

    lateinit var binding : FragmentOneBinding
    var dataList = mutableListOf<DataVO>()
    lateinit var customAdapter: CustomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentOneBinding.inflate(inflater,container,false)
        //리사이클러뷰에 보여줄 레이아웃 결정
        val linearLayoutManager = LinearLayoutManager(container?.context)

        //리사이클러뷰에 제공할 어뎁터
        customAdapter = CustomAdapter(dataList)
        //리사이클러뷰에 연결
        binding.f1Recyclerview.layoutManager = linearLayoutManager
        binding.f1Recyclerview.adapter = customAdapter
        //데코레이션 여기서 연결
//        binding.f1Recyclerview.addItemDecoration(MyDecoration(binding.root.context))

        //플로팅 탭을 누르면 사용자 다이얼로그 실행 후 입력데이터 데이터리스트에 저장
        binding.f1Fab.setOnClickListener {
            val dialog = CustomDialog(binding.root.context)
            dialog.showDialog("")
        }


        return binding.root
    }

    fun refreshRecyclerViewAdd(dataVO: DataVO) {
        Toast.makeText(binding.root.context,"${dataVO.name}님을(를) 추가합니다", Toast.LENGTH_SHORT).show()
        dataList.add(dataVO)
        customAdapter.notifyDataSetChanged()
    }

    fun refreshRecyclerViewDrop(dataVO: DataVO) {
        Toast.makeText(binding.root.context,"${dataVO.name}님을(를) 삭제합니다", Toast.LENGTH_SHORT).show()
        dataList.remove(dataVO)
        customAdapter.notifyDataSetChanged()
    }




}