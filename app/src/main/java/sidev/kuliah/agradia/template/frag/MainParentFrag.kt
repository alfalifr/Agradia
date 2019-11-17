package sidev.kuliah.agradia.template.frag

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import sidev.kuliah.agradia.template._init.R_id
import sidev.kuliah.agradia.template._init.R_layout
import sidev.kuliah.agradia.template.act.MainAbsAct
import sidev.kuliah.agradia.template.adp.ViewPagerAdapter
import sidev.kuliah.agradia.template.intfc.MainParentView

abstract class MainParentFrag : Fragment(), MainParentView {
    abstract val tabLayoutTitle: Array<String>
    private val tabLayoutKeterangan= Array<String?>(tabLayoutTitle.size){null}

    override var vpContainer: ViewPager?= null
    override var currentPage: Int= -1
    override val navBarMenuId: Int
        get() = 0
    override val acbarInited: Array<Boolean> = Array(actionBarLayoutId.size){false}
    override val actionBarLayoutView: Array<View?> = Array(actionBarLayoutId.size){null}
    override var userType: Int= 0
//    override var isActivityView: Boolean = false
    open val tabLayoutId: Int= R_id.tl

    override fun vpContainerId(): Int {
        return R_id.vp_frag_container
    }
    override fun navBar(): BottomNavigationView? {
        return null
    }
    override fun supportActionBar(): ActionBar {
        return (activity as MainAbsAct).supportActionBar!!
    }
    override fun supportFragmentManager(): FragmentManager {
        return childFragmentManager
    }
    override fun layoutInflater(): LayoutInflater {
        return layoutInflater
    }

    fun tabLayout(): TabLayout?{
        return view?.findViewById(tabLayoutId) //(activity as AppCompatActivity?)?.supportActionBar?.customView?.findViewById(tabLayoutId)
    }
/*
    override fun onAttach(context: Context) {
        super.onAttach(context)
        userType =  Util.getSharedPref(context, Util.KEY_PREF_USER_TYPE)!!.toInt()
    }
*/
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R_layout.frag_general_tab_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }


    override fun initView() {
        val adapter = ViewPagerAdapter(
            childFragmentManager,
            *fragList
        )
        vpContainer().adapter = adapter
        setupTabLayoutWithPager()

        vpContainer().addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
            override fun onPageSelected(position: Int) {
                initActionBar(position)
            }
        })
        // loadPagerData()
    }

    fun setupTabLayoutWithPager(){
        tabLayout()?.setupWithViewPager(vpContainer())
        setTabLayoutTitle()
    }

    private fun setTabLayoutTitle(){
        for((i, title) in tabLayoutTitle.withIndex()){
//            tl_order.getTabAt(i)!!.text = title
            tabLayout()?.getTabAt(i)?.text= title
        }
        refreshTabKeterangan()
    }

    fun updateKeteranganAtTab(pos: Int, ket: String){
//        tl_order.getTabAt(pos)!!.text = tabLayoutTitle[pos] +" ($ket)"
//        tabLayout().getTabAt(pos)!!.text= tabLayoutTitle[pos] +" ($ket)"
        tabLayoutKeterangan[pos]= ket
        refreshTabKeterangan()
    }

    fun updateKeteranganAtTab(frag: Fragment, ket: String){
        val pos= getFragInd(frag)
//        tl_order.getTabAt(pos)!!.text = tabLayoutTitle[pos] +" ($ket)"
//        tabLayout().getTabAt(pos)!!.text= tabLayoutTitle[pos] +" ($ket)"
        tabLayoutKeterangan[pos]= ket
        refreshTabKeterangan()
    }

    fun refreshTabKeterangan(){
        for((i, keterangan) in tabLayoutKeterangan.withIndex()){
            val tab= tabLayout()?.getTabAt(i)
            if(tab != null){
                var str= tabLayoutTitle[i]
                if(keterangan != null)
                    str += " ($keterangan)"
                tab.text= str
            }
        }
    }

    fun getFragInd(frag: Fragment): Int{
        for((i, fr) in fragList.withIndex()){
            val nameFragList= fr::class.java.name
            val nameFrag= frag::class.java.name
            if(nameFrag == nameFragList)
                return i
        }
        return -1
    }
}
