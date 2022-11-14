package aop.part3.phone

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import aop.part3.phone.databinding.DialogCustomBinding
import aop.part3.phone.databinding.FragmentOneBinding

class CustomDialog(val context: Context) {

    val dialog = Dialog(context)

    fun showDialog(data: String){
        val binding = DialogCustomBinding.inflate(LayoutInflater.from(context))
        dialog.setContentView(binding.root)
        dialog.window!!.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.setCanceledOnTouchOutside(false)
        dialog.setCancelable(false)
        dialog.show()
        binding.etPhoneNumber.setText(data)

        //취소버튼 이벤트처리
        binding.btnCancle.setOnClickListener {
            dialog.dismiss()
        }
        //확인버튼 이벤트처리
        binding.btnOk.setOnClickListener {
            val name = binding.etName.text.toString()
            val phoneNumber = binding.etPhoneNumber.text.toString()
            val dataVO :DataVO

            if(name.isNotEmpty() && phoneNumber.isNotEmpty() ){
                dataVO = DataVO(name,phoneNumber,R.drawable.ic_baseline_person_24)
                (context as MainActivity).oneFragment.refreshRecyclerViewAdd(dataVO)
                dialog.dismiss()
            }else{
                Toast.makeText(context,"이름과 전화번호를 모두 입력해주세요", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
