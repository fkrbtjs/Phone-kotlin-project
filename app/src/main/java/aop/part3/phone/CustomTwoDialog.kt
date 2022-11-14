package aop.part3.phone

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.WindowManager
import android.widget.Toast
import aop.part3.phone.databinding.DialogCustom2Binding
import aop.part3.phone.databinding.DialogCustomBinding

class CustomTwoDialog (val context: Context) {

    val dialog = Dialog(context)
    var dataList = mutableListOf<DataVO>()

    fun showDialog(name: String,phoneNum:String){
        val binding = DialogCustom2Binding.inflate(LayoutInflater.from(context))
        dialog.setContentView(binding.root)
        dialog.window!!.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.setCanceledOnTouchOutside(false)
        dialog.setCancelable(false)
        dialog.show()

        //취소버튼 이벤트처리
        binding.btnDelete.setOnClickListener {
            val dataVO = DataVO(name,phoneNum,R.drawable.ic_baseline_person_24)
            (context as MainActivity).oneFragment.refreshRecyclerViewDrop(dataVO)
            (context as MainActivity).refreshRecyclerViewDrop3(dataVO)
            dialog.dismiss()
        }
        //확인버튼 이벤트처리
        binding.btnFavor.setOnClickListener {
            val dataVO = DataVO(name,phoneNum,R.drawable.ic_baseline_person_24)
                (context as MainActivity).refreshRecyclerViewAdd3(dataVO)
                dialog.dismiss()
        }
    }
}
