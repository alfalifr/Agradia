package id.go.surabaya.disperdagin.utilities.customview

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.widget.AppCompatAutoCompleteTextView
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.go.surabaya.disperdagin.R
import id.go.surabaya.disperdagin.adapters.DialogListAdapter
import kotlinx.android.synthetic.main.dialog_list.view.*

class DialogListView(c: Context): DialogAbsView(c){
    override val layoutId: Int
        get() = R.layout.dialog_list

    private var adp: DialogListAdapter
    private var rv: RecyclerView
    private var autoTv: AppCompatAutoCompleteTextView

    private var listener: DialogListAdapter.DialogListListener?= null
    private var filterListener: DialogListFilterListener?= null

    var noDataViewVis= false

    interface DialogListFilterListener{
        fun onFilterItemClick(v: View, pos: Int, data: String)
    }

    init{
//        layoutView= LayoutInflater.from(ctx).inflate(layoutId, null, false)
        adp= DialogListAdapter(c, null, listener)
        adp.dialog= dialog
        rv= findView(R.id.rv_list)
        rv.adapter= adp
        rv.layoutManager= LinearLayoutManager(c)

        addTextWatcher(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                adp.search(s.toString())
            }
        })
        autoTv= findView<View>(R.id.fill_filter)
            .findViewById(R.id.ed_fill_auto)
        autoTv.threshold= 1 //will start working from first character
    }

    fun setItemClickListener(func: (v: View?, pos: Int, data: String, item: MenuItem?) -> Unit){
        listener= object: DialogListAdapter.DialogListListener{
            override fun onItemClick(v: View?, pos: Int, data: String, item: MenuItem?) {
                func(v, pos, data, item)
            }
        }
        adp.listener= this.listener
    }

    fun setFilterClickListener(func: (v: View?, pos: Int, data: String) -> Unit){
        filterListener= object: DialogListFilterListener{
            override fun onFilterItemClick(v: View, pos: Int, data: String) {
                func(v, pos, data)
            }
        }
        adp.listener= this.listener
    }

    fun setMenu(menuId: Int){
        adp.setMenu(menuId)
    }

    fun setSearchBarVisible(visible: Boolean){
        val vis= if(visible) View.VISIBLE
        else View.GONE
        layoutView.ll_search_container.visibility= vis
    }

    fun setFilterBarVisible(visible: Boolean){
        val vis= if(visible) View.VISIBLE
        else View.GONE
        layoutView.ll_filter_container.visibility= vis
    }

    fun setNoDataViewVisible(visible: Boolean){
        val vis= if(visible) View.VISIBLE
        else View.GONE
        findView<View>(R.id.tv_no_data).visibility= vis
        noDataViewVis= visible
    }

    fun setNoDataString(str: String){
        findView<TextView>(R.id.tv_no_data).text= str
    }

    fun updateData(data: ArrayList<String>?){
        adp.updateData(data, true)
        setNoDataViewVisible(noDataViewVis && data.isNullOrEmpty())
    }

    fun updateFilterData(data: ArrayList<String>?){ //: AppCompatAutoCompleteTextView
        val adapter=
            if(data != null) ArrayAdapter<String>(c, android.R.layout.select_dialog_item, data)
            else null
        autoTv.setAdapter(adapter)
        autoTv.setOnItemClickListener { parent, view, position, id ->
            filterListener?.onFilterItemClick(view, position, data!![position])
        }
//        (this, android.R.layout.select_dialog_item, fruits);
    }

    fun addTextWatcher(tw: TextWatcher){
        layoutView.findViewById<EditText>(R.id.ed_search)
            .addTextChangedListener(tw)
    }
}

/*
{
    lateinit var dialog: AlertDialog
    lateinit var adp: DialogListAdapter
    lateinit var rv: RecyclerView
=======
class DialogListView(val ctx: Context){
    var dialog: AlertDialog
    var adp: DialogListAdapter
    var rv: RecyclerView
>>>>>>> 7abb3fb3e521b17325fd051609efdfb3870219f7
    val layoutId= R.layout.dialog_list
    val layoutView: View

    private var listener: DialogListAdapter.DialogListListener?= null

    init{
        layoutView= LayoutInflater.from(ctx).inflate(layoutId, null, false)
        dialog= AlertDialog.Builder(ctx)
            .setView(layoutView)
            .setCancelable(true)
            .create()
        dialog.window
            .setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        adp= DialogListAdapter(ctx, null, listener)
        adp.dialog= dialog
        rv= layoutView.findViewById(R.id.rv_list)
        rv.adapter= adp
        rv.layoutManager= LinearLayoutManager(ctx)

        addTextWatcher(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
              //  Toast.makeText(ctx, "sedang search sekarang", Toast.LENGTH_SHORT).show()
                adp.search(s.toString())
            }
        })
    }

    fun setItemClickListener(func: (v: View?, pos: Int, data: String, item: MenuItem?) -> Unit){
        listener= object: DialogListAdapter.DialogListListener{
            override fun onItemClick(v: View?, pos: Int, data: String, item: MenuItem?) {
                func(v, pos, data, item)
            }
        }
        adp.listener= this.listener
    }

    fun setMenu(menuId: Int){
        adp.setMenu(menuId)
    }

    fun setTitle(title: String){
        layoutView.tv_title.text= title
    }

    fun setTitleVisible(visible: Boolean){
        val vis= if(visible) View.VISIBLE
            else View.GONE
        layoutView.tv_title.visibility= vis
    }

    fun setSearchBarVisible(visible: Boolean){
        val vis= if(visible) View.VISIBLE
        else View.GONE
        layoutView.ll_search_container.visibility= vis
    }

    fun updateData(data: ArrayList<String>?){
        adp.updateData(data, true)
    }

    fun show(){
        dialog.show()
    }

    fun cancel(){
        dialog.cancel()
    }

    fun addTextWatcher(tw: TextWatcher){
        layoutView.findViewById<EditText>(R.id.ed_search)
            .addTextChangedListener(tw)
    }
}*/