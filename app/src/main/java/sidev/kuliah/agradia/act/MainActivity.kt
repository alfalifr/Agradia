package sidev.kuliah.agradia.act

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import sidev.kuliah.agradia.R
import sidev.kuliah.agradia.template.act.MainAbsAct

class MainActivity : MainAbsAct() {

    override val navBarMenuId: Int
        get() = R.menu.navbar_main
    override val fragList: Array<Fragment>
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
    override val actionBarLayoutId: Array<Int>
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun actionBarInitAction(actBar: ViewGroup, pos: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun findPageInd(id: Int): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun itemSelectedAction(pageInd: Int, itemId: Int): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initPopMenuActionBar(pos: Int, actBar: View?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_main)
    }
}
