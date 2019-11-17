package sidev.kuliah.agradia.template.adp

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import java.util.ArrayList

class ViewPagerAdapter(fm: FragmentManager, vararg items: Fragment) : FragmentPagerAdapter(fm) {
    private val items = ArrayList<Fragment>()

    init {
        for(element in items)
            this.items.add(element)
    }

    override fun getItem(position: Int): Fragment {
        return items[position]
    }

    override fun getCount(): Int {
        return items.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return null
    }
}
