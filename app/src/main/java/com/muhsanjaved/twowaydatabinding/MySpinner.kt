package com.muhsanjaved.twowaydatabinding

import android.content.Context
import android.nfc.Tag
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatSpinner
import androidx.databinding.BindingMethod
import androidx.databinding.BindingMethods
import androidx.databinding.InverseBindingListener
import androidx.databinding.InverseBindingMethod
import androidx.databinding.InverseBindingMethods

const val TAG = "MyTag"

@BindingMethods(
    BindingMethod(type = MySpinner::class, attribute = "city", method = "setSelectedCity"),
    BindingMethod(type = MySpinner::class, attribute = "cityAttrChanged", method = "setBindingListener")
)

@InverseBindingMethods(
    InverseBindingMethod(type = MySpinner::class, attribute = "city", method = "getSelectedCity")
)
class MySpinner : AppCompatSpinner {


    private lateinit var inverseBindingListener: InverseBindingListener

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    private fun init() {

        onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
               inverseBindingListener.onChange()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                Toast.makeText(context, "Nothing is selected", Toast.LENGTH_SHORT).show()
            }

        }
    }

    fun setSelectedCity(city: Cities) {
        Log.d(TAG, "setSelectedCity: called")
        setSelection(city.ordinal)
    }

    fun getSelectedCity(): Cities {
        Log.d(TAG, "getSelectedCity: called")
        return Cities.values()[selectedItemPosition]
    }

    fun setBindingListener(listener: InverseBindingListener){
        Log.d(TAG, "setBindingListener: called")
        this.inverseBindingListener = listener
    }
}