package sidev.kuliah.agradia.template.intfc

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import sidev.kuliah.agradia.R
import sidev.kuliah.agradia.template.adp.ViewPagerAdapter

interface MainParentView: BottomNavigationView.OnNavigationItemSelectedListener {
    var currentPage: Int
    var vpContainer: ViewPager?
    val navBarMenuId: Int
    val fragList: Array<Fragment>
    val acbarInited: Array<Boolean>
    val actionBarLayoutId: Array<Int>
    val actionBarLayoutView: Array<View?>
    val userType: Int

    fun initView() {
        val adapter = ViewPagerAdapter(supportFragmentManager(), *fragList)
        vpContainer().adapter = adapter

        navBar()?.inflateMenu(navBarMenuId)
        navBar()?.setOnNavigationItemSelectedListener(this)
        initActionBar(0)
        setMinFragmentKept()
    }
    fun setMinFragmentKept(min: Int= fragList.size){
        vpContainer().offscreenPageLimit= min
    }

    fun vpContainerId(): Int
    fun navBar(): BottomNavigationView?
    fun supportActionBar(): ActionBar
    fun supportFragmentManager(): FragmentManager
    fun actionBarInitAction(actBar: ViewGroup, pos: Int)
    fun inflateActBarAction(actBar: View, pos: Int){}
    fun layoutInflater(): LayoutInflater

    fun findPageInd(id: Int): Int
    /**
     * Lakukan sesuatu saat item di navbar ditekan
     */
    fun itemSelectedAction(pageInd: Int, itemId: Int): Boolean
//    fun initActionBarAtPosition(pos: Int)
    fun initPopMenuActionBar(pos: Int, actBar: View?)


    fun vpContainer(): ViewPager{
        if(vpContainer == null){
            if(this is Activity)
                vpContainer= findViewById(vpContainerId())
            else if(this is Fragment)
                vpContainer= view!!.findViewById(vpContainerId())
        }
        return vpContainer!!
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
/*
        if(currentPage == 4 && getFragment(4) is ProfileFrag
            && (getFragment(4) as ProfileFrag).editProfile){
            Toast.makeText(this as Context, "Anda masih mengedit halaman profil Anda.\nSilakan simpan dulu", Toast.LENGTH_LONG).show()
            return false
        }
 */
        val pageInd= findPageInd(item.itemId)
        if(pageInd >= 0 && pageInd < fragList.size){
            slidePageTo(pageInd)
            return itemSelectedAction(pageInd, item.itemId)
        }
        return false
    }

    fun slidePageTo(page: Int){
        initActionBar(page)

        if(currentPage != page) {
            vpContainer().currentItem = page
            currentPage = page
        }
    }

/*
==========================
Action Bar Section -START
==========================
*/

    fun initActionBar(position: Int) {
        val actBar= updateActBarCustomView(position)
        val toolBar = supportActionBar().customView.parent as Toolbar

        toolBar.setContentInsetsAbsolute(0,0)

        acbarInited[position]= true
        actionBarInitAction(actBar, position)
        Log.e("TES_A", "getFragment($position)==null >> " +(getFragment(position)==null))
    }

    /**
     * Inflate view dari Action Bar sesuai layout id di actionBarLayoutId
     */
    fun inflateActBarView(pos: Int): View {
        var v= actionBarLayoutView[pos]
        if(v == null){
            v= layoutInflater().inflate(actionBarLayoutId[pos], null)
            actionBarLayoutView[pos]= v
            inflateActBarAction(v, pos)
        }
        return v!!
    }

    /**
     * Untuk init ActBar yang berupa title saja
     */
    fun initTitleActBar(pos: Int, title: String){
        actionBarLayoutView[pos]!!.findViewById<TextView>(R.id.tv_header).text = title
    }
/*
    /**
     * Untuk init ActBar yang berupa Search dan context
     */
    fun initSearchContextActBar(pos: Int, searchHint: String){
        val actBar= actionBarLayoutView[pos]!!
        val ed= actBar.findViewById<View>(R.id.search_box)
            .findViewById<EditText>(R.id.ed_fill)
        ed.hint = searchHint
        ed.textChangedListener {
            afterTextChanged { s->
                //                (getFragment(position) as TokelKomoditiFr).search(s.toString())
                (getFragment(pos) as SearchFun).search(s.toString())
            }
        }

        if(userType != Constants.USER_TYPE_DISTRIBUTOR) {
                if(actBar.ll_menu_container != null)
                    actBar.ll_menu_container.setOnClickListener { initPopMenuActionBar(pos, actBar) }
        }else {
            if(pos != 2) // ini tidak akan terconstraint pada KoperasiKulakanFrag dengan posisi 0, 1, //amir
                actBar.ll_menu_container.setOnClickListener { initPopMenuActionBar(pos, actBar) }
        }
    }
 */

    fun initContextBar(pos: Int, title: String,
                       menu: Int, funCtx: (idMenu: Int) -> Boolean, funInit: (actBar: View) -> Unit){
        val actBar= actionBarLayoutView[pos]!!
        actBar.findViewById<TextView>(R.id.tv_header).text = title
        initContextPopupMenu(pos, menu, funCtx, funInit)
//        initProfilePopupMenu(pos)
    }

    fun initContextPopupMenu(pos: Int, menu: Int, funCtx: (idMenu: Int) -> Boolean, funInit: (actBar: View) -> Unit){
        val actBar= actionBarLayoutView[pos]!!
        val dropMenu =
            androidx.appcompat.widget.PopupMenu(this as Context, actBar.findViewById<View>(R.id.btn_menu_context))

        //      val frag= (getFragment(position) as ProfileFrag)

        dropMenu.menuInflater.inflate(menu, dropMenu.menu)
        dropMenu.setOnMenuItemClickListener {
            funCtx(it.itemId)
        }
        funInit(actBar)
    }

/*
    /**
     * Bagian implementasi tunggal - Profil
     */
    fun initProfileActBar(pos: Int, title: String){
        val actBar= actionBarLayoutView[pos]!!
        actBar.findViewById<TextView>(R.id.tv_header).text = title
        initProfilePopupMenu(pos)
    }
/ *
    /**
     * Bagian implementasi tunggal - Profil
     */
    fun initProfilePopupMenu(pos: Int){
        val actBar= actionBarLayoutView[pos]!!
        val dropMenu =
            androidx.appcompat.widget.PopupMenu(this as Context, actBar.findViewById<View>(R.id.btn_menu_context))

        //      val frag= (getFragment(position) as ProfileFrag)


        dropMenu.menuInflater.inflate(R.menu.bar_act_profile, dropMenu.menu)
        dropMenu.setOnMenuItemClickListener {
            val frag= getFragment(pos) as ProfileFrag
            if(frag.editProfile)
                false
            else{
                when (it.itemId) {
                    R.id.actbar_edit -> {
                        frag.editProfile(!frag.editProfile)
                    }
                    R.id.actbar_logout -> {
                        frag.presenter.logout()
                    }
                }
                true
            }
        }

        actBar.findViewById<View>(R.id.rl_menu_container).setOnClickListener {
            val frag= getFragment(pos) as ProfileFrag
            if(!frag.editProfile)
                dropMenu.show()
            else
                frag.saveProfile()//.editForm(!frag.editForm)
        }
    }
 */
/*
==========================
Action Bar Section -END
==========================
*/

    fun getFragment(pos: Int) : Fragment? {
        val tag= "android:switcher:" + vpContainerId() /*R.id.vp_container*/ + ":" + pos/*vpContainer().currentItem*/
        val page =
            /*fragList[pos]*/supportFragmentManager().findFragmentByTag(tag)
/*
        Log.e("TES_AG", "tag= $tag")
        Log.e("TES_AG", "pos= $pos")
        Log.e("TES_AG", "page==null= ${page==null}")*/
        // based on the current position you can then cast the page to the correct
        // class and call the method:
        if (vpContainer().currentItem == pos && page != null) {
            return page
        }
        return null
    }

    //TAB LAYOUT==============


    fun initActBarCustomViewHolder(){
        if(supportActionBar().customView == null){
            val mainViewHolder= layoutInflater().inflate(R.layout.comp_bar_action_main, null, false)
            supportActionBar().customView= mainViewHolder
            supportActionBar().displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
            supportActionBar().setDisplayShowCustomEnabled(true)
//            supportActionBar().hide()
//            supportActionBar()
            supportActionBar().setCustomView(mainViewHolder,
                ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT))
        }
    }
    fun updateActBarCustomView(pos: Int): ViewGroup{
        initActBarCustomViewHolder()
        val actBarView = inflateActBarView(pos) //layoutInflater.inflate(actionBarLayoutId[position], null)
        val actBarMain= supportActionBar().customView!!
            .findViewById<ViewGroup>(R.id.rl_main_container)
        actBarMain.removeAllViews()
        actBarMain.addView(actBarView)
        return actBarView as ViewGroup
    }
/*
    fun setUpTabLayoutWithChildPager(page: Int){
        val frag= fragList[page]
        if(frag is MainParentFrag){
            frag.setupTabLayoutWithPager()
            (this as AppCompatActivity).setTheme(R.style.AppThemeOrange_WithTabLayout)
        } else if(this is AppCompatActivity){
            setTheme(R.style.AppThemeOrange)
        }
    }
 */
/*
    abstract val tabLayoutTitle: Array<String>
    private val tabLayoutKeterangan= Array<String?>(tabLayoutTitle.size){null}



    fun tabLayout(): TabLayout {
        return (activity as AppCompatActivity).supportActionBar!!.customView.findViewById(tabLayoutId)
    }




    private fun setTabLayoutTitle(){
        for((i, title) in tabLayoutTitle.withIndex()){
//            tl_order.getTabAt(i)!!.text = title
            tabLayout().getTabAt(i)!!.text= title
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
            val tab= tabLayout().getTabAt(i)
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

 */
}