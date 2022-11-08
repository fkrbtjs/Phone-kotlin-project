package aop.part3.phone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.recyclerview.widget.LinearLayoutManager
import aop.part3.phone.databinding.ActivityMainBinding
import aop.part3.phone.databinding.UsertabButtonBinding
import com.google.android.material.tabs.TabLayoutMediator


class MainActivity : AppCompatActivity() {

    lateinit var oneFragment: OneFragment
    lateinit var twoFragment: TwoFragment
    lateinit var threeFragment: ThreeFragment
    lateinit var toggle : ActionBarDrawerToggle
    lateinit var binding : ActivityMainBinding
    var dataList = mutableListOf<DataVO>()
    lateinit var customAdapter: CustomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //네비게이션 뷰************************************************************************************
        //액션바대신에 툴바로 대체
        setSupportActionBar(binding.toolbar)

        //ActionBarDrawerToggle 버튼적용
        toggle = ActionBarDrawerToggle(this,binding.drawerlayout,R.string.drawer_open,R.string.drawer_close)
        //업버튼 활성화
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //토글 sync
        toggle.syncState()


        //drawlayout fragment***************************************************************************
        val pagerAdapter = PagerAdapter(this)
        val title = mutableListOf<String>("연락처","최근통화","키패드")
        oneFragment = OneFragment()
        twoFragment = TwoFragment()
        threeFragment = ThreeFragment()

        pagerAdapter.addFragment(oneFragment,title[0])
        pagerAdapter.addFragment(twoFragment,title[1])
        pagerAdapter.addFragment(threeFragment,title[2])

        binding.viewpager.adapter = pagerAdapter


        //네비게이션뷰 리사이클러뷰 붙히기***************************************************************************
        val linearLayoutManager = LinearLayoutManager(this)

        //리사이클러뷰에 제공할 어뎁터
        customAdapter = CustomAdapter(dataList)
        //리사이클러뷰에 연결
        binding.naviRecyclerview.layoutManager = linearLayoutManager
        binding.naviRecyclerview.adapter = customAdapter



        // 탭레이아웃과 뷰페이저 연결************************************************************************

        TabLayoutMediator(binding.tablayout,binding.viewpager){ tab, position->
            tab.setCustomView(createTabView(title[position]))
        }.attach()


    }

    fun changeToThreeFragment(){
        binding.viewpager.currentItem = 2
    }


    // 탭레이아웃과 뷰페이저 연결************************************************************************

    private fun createTabView(title: String): View {
        val userTabBinding = UsertabButtonBinding.inflate(layoutInflater)
        userTabBinding.tvtabName.text=title
        when(title){
            "연락처"->{userTabBinding.ivtabIcon.setImageResource(R.drawable.ic_baseline_person_24)}
            "최근통화"->{userTabBinding.ivtabIcon.setImageResource(R.drawable.ic_baseline_access_time_filled_24)}
            "키패드"->{userTabBinding.ivtabIcon.setImageResource(R.drawable.ic_baseline_dialpad_24)}
        }
        return userTabBinding.root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //이벤트가 토글버튼에서 발생하면
        if(toggle.onOptionsItemSelected(item)){
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    fun refreshRecyclerViewAdd3(dataVO: DataVO) {
        Toast.makeText(binding.root.context,"${dataVO.name}님을(를) 즐겨찾기합니다.", Toast.LENGTH_SHORT).show()
        dataList.add(dataVO)
        customAdapter.notifyDataSetChanged()
    }



    fun refreshRecyclerViewDrop3(dataVO: DataVO) {
        dataList.remove(dataVO)
        customAdapter.notifyDataSetChanged()
    }





}