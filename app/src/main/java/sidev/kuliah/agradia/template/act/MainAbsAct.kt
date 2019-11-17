package sidev.kuliah.agradia.template.act

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.ActionBar
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.view.LayoutInflater
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.act_main.*
import sidev.kuliah.agradia.R
import sidev.kuliah.agradia.template.intfc.MainParentView

abstract class MainAbsAct
    : AppCompatActivity(),
    BottomNavigationView.OnNavigationItemSelectedListener,
    MainParentView {

    var backBtnclickedTwice = false

    override val acbarInited: Array<Boolean> = Array(actionBarLayoutId.size){false}
    override val actionBarLayoutView: Array<View?> = Array(actionBarLayoutId.size){null}
    override var currentPage: Int= -1
    override var vpContainer: ViewPager?= null
    // setiap subclass harus iniasi variable ini
    override var userType: Int = 0 //Util.getSharedPref(this, Util.KEY_PREF_USER_TYPE)!!.toInt()
//    override var isActivityView: Boolean = true

    override fun vpContainerId(): Int {
        return R.id.vp_container
    }
    override fun navBar(): BottomNavigationView? {
        return bnv_navbar
    }
    override fun layoutInflater(): LayoutInflater {
        return layoutInflater
    }

    override fun supportActionBar(): ActionBar {
        return supportActionBar!!
    }
    override fun supportFragmentManager(): FragmentManager {
        return supportFragmentManager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(
            R.layout.act_main)

//        Util.getSharedPref(this, Util.KEY_PREF_USER_TYPE)!!.toInt()

        initView()
    }

    override fun onBackPressed() {
       // super.onBackPressed()
        setDoubleBackClick()
    }

    fun setDoubleBackClick(){
        if (backBtnclickedTwice) {
            val i = Intent(Intent.ACTION_MAIN)
            i.addCategory(Intent.CATEGORY_HOME)
            i.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(i)
            finish()
            System.exit(0)
        }else{
            Toast.makeText(this, "Tekan sekali lagi untuk keluar", Toast.LENGTH_LONG)
                .show()
        }
        backBtnclickedTwice = true
        Handler().postDelayed({ backBtnclickedTwice = false }, 3000)
    }

}

/*
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK) {
            when(requestCode){
                Constants.INTENT_RQ_LOC_ADD -> (getFragment(fragList.size - 1) as ProfileFrag?)?.setLatLong(data?.getStringExtra(Constants.INTENT_LAT_ADD), data?.getStringExtra(Constants.INTENT_LONG_ADD))
                BitmapUtil.PICK_IMAGE_GALLERY_REQUEST -> {
                    if (data != null && data.data != null) {
                        val uri = data.data
                        try {
                            val profileFrag = (getFragment(fragList.size - 1) as ProfileFrag?)
                            var bitmap = MediaStore.Images.Media.getBitmap(contentResolver, uri)
                            bitmap= BitmapUtil.pictSquare(bitmap, 500)

                            profileFrag?.picBm = bitmap
                            profileFrag?.setImageProfile()

                        } catch (e: IOException) {
                            e.printStackTrace()
                        }
                    }
                }
            }
        }
    }
 */


/*
: AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener, MainView {


    abstract val navBarMenu: Int
    abstract val fragList: Array<Fragment>
    abstract val actionBarLayoutId: Array<Int>
    val actionBarLayoutView= Array<View?>(actionBarLayoutId.size){null}
    // ini adalah defined id_order bar yang digunakan pada saat manual set
    val actionBarDefined = arrayOf(R.layout.comp_bar_action_search_context, R.layout.comp_bar_action_search_only)
    val acbarInited= Array(actionBarLayoutId.size){false}

    var currentMenu = 0
    lateinit var frag: ProfileFrag

    // setiap subclass harus iniasi variable ini
    var userType: Int = 0


    abstract fun findPageInd(id: Int): Int
    /**
     * Lakukan sesuatu saat item di navbar ditekan
     */
    abstract fun itemSelectedAction(pageInd: Int, itemId: Int): Boolean
    abstract fun initActionBarAtPosition(pos: Int)
    abstract fun initPopMenuActionBar(pos: Int, actBar: View?)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(
            R.layout.activity_main)

        initView()
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        if(currentMenu == 4 && getFragment(4) is ProfileFrag
            && (getFragment(4) as ProfileFrag).editForm){
            Toast.makeText(this, "Anda masih mengedit halaman profil Anda.\nSilakan simpan dulu", Toast.LENGTH_LONG).show()
            return false
        }
        val pageInd= findPageInd(item.itemId)
        if(pageInd >= 0 && pageInd < fragList.size){
            slidePageTo(pageInd)
            return itemSelectedAction(pageInd, item.itemId)
        }
        return false
    }


    override fun initView() {
        val adapter = ViewPagerAdapter(supportFragmentManager, *fragList)
        vp_container.adapter = adapter

        bnv_navbar.inflateMenu(navBarMenu)
        bnv_navbar.setOnNavigationItemSelectedListener(this)
        initActionBar(0)
    }


    override fun slidePageTo(page: Int){
      //  if(userType != Constants.USER_TYPE_KOPERASI)
        initActionBar(page)

        if(currentMenu != page) {
            vp_container.currentItem = page
            currentMenu = page
        }
    }

/*
==========================
Action Bar Section -START
==========================
*/
    override fun initActionBar(position: Int) {
//        if(acbarInited[position]) return

        val actBar = inflateActBarView(position) //layoutInflater.inflate(actionBarLayoutId[position], null)

        supportActionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar!!.setDisplayShowCustomEnabled(true)
        supportActionBar!!.setCustomView(actBar)

        val toolBar = supportActionBar!!.customView.parent as Toolbar

        toolBar.setContentInsetsAbsolute(0,0)

        acbarInited[position]= true
        initActionBarAtPosition(position)
    }

    //untuk set manual id_order bar ketika ada perbedaan tertentu yang dibutuhkan
    override fun setManualActionBar(ind: Int) {
//        if(acbarInited[position]) return

        val actBar = layoutInflater.inflate(actionBarDefined[ind], null)

        supportActionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar!!.setDisplayShowCustomEnabled(true)
        supportActionBar!!.setCustomView(actBar)

        val toolBar = supportActionBar!!.customView.parent as Toolbar

        toolBar.setContentInsetsAbsolute(0, 0)
    }

        /**
     * Inflate view dari Action Bar sesuai layout id di actionBarLayoutId
     */
    override fun inflateActBarView(pos: Int): View{
        val v= layoutInflater.inflate(actionBarLayoutId[pos], null)
        actionBarLayoutView[pos]= v
        return v
    }

    override fun initTitleActBar(pos: Int, title: String){
        (actionBarLayoutView[pos]!!.tv_header as TextView).text = title
    }
    override fun initSearchContextActBar(pos: Int, searchHint: String){
        val actBar= actionBarLayoutView[pos]!!
        (actBar.search_box.ed_fill as EditText).hint = searchHint
        (actBar.search_box.ed_fill as EditText).textChangedListener {
            afterTextChanged { s->
//                (getFragment(position) as TokelKomoditiFr).search(s.toString())
                (getFragment(pos) as SearchFun).search(s.toString())
            }
        }

        if(userType != Constants.USER_TYPE_DISTRIBUTOR)
                actBar.ll_menu_container.setOnClickListener { initPopMenuActionBar(pos, actBar) }
        else {
            if(pos != 2)
                actBar.ll_menu_container.setOnClickListener { initPopMenuActionBar(pos, actBar) }
        }

    }

    /**
     * Bagian implementasi tunggal - Profil
     */
    override fun initProfileActBar(pos: Int, title: String){
        val actBar= actionBarLayoutView[pos]!!
        (actBar.tv_header as TextView).text = title
        initProfilePopupMenu(pos)
    }
    /**
     * Bagian implementasi tunggal - Profil
     */
    fun initProfilePopupMenu(pos: Int){
        val actBar= actionBarLayoutView[pos]!!
        val dropMenu = androidx.appcompat.widget.PopupMenu(this, actBar!!.btn_menu_context)

        //      val frag= (getFragment(position) as ProfileFrag)

        dropMenu.menuInflater.inflate(R.menu.bar_act_profile, dropMenu.menu)
        dropMenu.setOnMenuItemClickListener {
            if(frag.editForm)
                false
            else{
                when (it.itemId) {
                    R.id.actbar_edit -> {
                        frag.editForm(!frag.editForm)
                    }
                    R.id.actbar_logout -> {
                        frag.presenter.logout()
                    }
                }
                true
            }
        }

        actBar.rl_menu_container.setOnClickListener {
            if(!frag.editForm)
                dropMenu.show()
            else
                frag.editForm(!frag.editForm)
        }
    }
/*
==========================
Action Bar Section -END
==========================
*/

    override fun getFragment(pos: Int) : Fragment? {
        val page =
            /*fragList[pos]*/supportFragmentManager.findFragmentByTag("android:switcher:" + R.id.vp_container + ":" + vp_container.currentItem)
        // based on the current position you can then cast the page to the correct
        // class and call the method:
        if (vp_container.currentItem == pos && page != null) {
            return page
        }
        return null
    }

    //--Sesuatu
    override fun pickImageGallery(){
        BitmapUtil.pickImageGallery(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode === BitmapUtil.PICK_IMAGE_GALLERY_REQUEST
            && resultCode === RESULT_OK
            && data != null && data.data != null) {

            val uri = data.data

            try {
                var bitmap = MediaStore.Images.Media.getBitmap(contentResolver, uri)
                bitmap= BitmapUtil.pictSquare(bitmap, 700)
                //--Sesuatu
                val filePict= BitmapUtil.savePict(bitmap, EnvUtil.projectTempDir(this))
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
}
*/