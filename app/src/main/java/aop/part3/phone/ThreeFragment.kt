package aop.part3.phone

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import aop.part3.phone.databinding.FragmentThreeBinding
import java.text.SimpleDateFormat

class ThreeFragment : Fragment() {
    lateinit var binding : FragmentThreeBinding
    lateinit var customAdapter: CustomAdapter
    var dataList2 = mutableListOf<DataVO2>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentThreeBinding.inflate(inflater,container,false)
        binding.tv1.setOnClickListener { addNumber(1) }
        binding.tv2.setOnClickListener {addNumber(2)}
        binding.tv3.setOnClickListener {addNumber(3)}
        binding.tv4.setOnClickListener {addNumber(4)}
        binding.tv5.setOnClickListener {addNumber(5)}
        binding.tv6.setOnClickListener {addNumber(6)}
        binding.tv7.setOnClickListener {addNumber(7)}
        binding.tv8.setOnClickListener {addNumber(8)}
        binding.tv9.setOnClickListener {addNumber(9)}
        binding.tv0.setOnClickListener {addNumber(0)}
        binding.ivBack.setOnClickListener {
            if(binding.tvPhonenum.text.length > 0){
                if(binding.tvPhonenum.text.length ==5||binding.tvPhonenum.text.length ==10){
                    binding.tvPhonenum.setText(binding.tvPhonenum.text.toString().substring(0 until binding.tvPhonenum.text.toString().length-2))
                }else{
                    binding.tvPhonenum.setText(binding.tvPhonenum.text.toString().substring(0 until binding.tvPhonenum.text.toString().length-1))
                }
            }else{
                Toast.makeText(binding.root.context,"숫자를 입력해주세요.", Toast.LENGTH_SHORT).show()
            }
        }

        binding.ivBack.setOnLongClickListener {
            binding.tvPhonenum.setText("")
            true
        }

        binding.lnPlusNumber.setOnClickListener {
            val data = binding.tvPhonenum.text.toString()
            if (data.isNotEmpty()){
                val dialog = CustomDialog(binding.root.context)
                dialog.showDialog(data)
            }else{
                Toast.makeText(context,"전화번호를 입력해주세요",Toast.LENGTH_SHORT).show()
            }
        }

        binding.ivPhone.setOnClickListener {
            val phoneNum = binding.tvPhonenum.text.toString()
            val currentTime : Long = System.currentTimeMillis()
            val dataFormat = SimpleDateFormat("yy.MM.dd")
            var now = dataFormat.format(currentTime)
            val dataVO2 : DataVO2

            if(phoneNum.isNotEmpty()){
                dataVO2 = DataVO2(phoneNum,now.toString())
                (context as MainActivity).twoFragment.refreshRecyclerViewAdd2(dataVO2)
            }else{
            }
        }
        return binding.root
    }

    private fun addNumber(i: Int) {
        if(binding.tvPhonenum.text.toString().length==3 || binding.tvPhonenum.text.toString().length==8 ){
            binding.tvPhonenum.setText("${binding.tvPhonenum.text.toString()}-${i}")
        }else{
            binding.tvPhonenum.setText("${binding.tvPhonenum.text.toString()}${i}")
        }
    }

    fun refreshRecyclerViewDrop2(dataVO2: Any) {
        dataList2.remove(dataVO2)
        customAdapter.notifyDataSetChanged()
    }
}
