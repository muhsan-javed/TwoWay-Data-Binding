@file:JvmName("BindingUtils")

package com.muhsanjaved.twowaydatabinding

import android.util.Log
import android.view.View
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Spinner
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import androidx.databinding.InverseMethod

// region used for infinite loop problem and its solution
//@BindingAdapter("android:text")
//fun setText(view:EditText, value:String){
//    Log.d("MyTag", "setText: Called for $value")
//    if (value != view.text.toString())
//        view.setText(value)
//}

//region BindingAdapter For setCity
//// Step 1
//@BindingAdapter("setCity")
//fun setCity(view: Spinner, city: Cities){
//    view.setSelection(city.ordinal)
//}
//// Step 2
//@InverseBindingAdapter(attribute = "setCity")
//fun getCity(view:Spinner): Cities {
//    return Cities.values()[view.selectedItemPosition]
//}
//// Step 3
//@BindingAdapter("setCityAttrChanged")
//fun setCityListener(view:Spinner, listener: InverseBindingListener){
//
//    if (view.onItemSelectedListener == null){
//        view.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//                listener.onChange()
//            }
//
//            override fun onNothingSelected(parent: AdapterView<*>?) {
//
//            }
//
//        }
//    }
//}

//endregion

// Custom
@BindingAdapter("gender")
fun setGender(view:RadioGroup, gender: Gender){
    when(gender){
        Gender.MALE-> view.check(R.id.rb_male)
        Gender.FEMALE-> view.check(R.id.rb_female)
    }
}

@InverseBindingAdapter(attribute = "gender", event = "genderAttrChanged")
fun getGender(view:RadioGroup): Gender{
    return  when(view.checkedRadioButtonId){
        R.id.rb_male -> Gender.MALE
        R.id.rb_female -> Gender.FEMALE
        else -> Gender.MALE
    }
}

@BindingAdapter("genderAttrChanged")
fun setGenderListener(view: RadioGroup, listener: InverseBindingListener) {
//    Log.d("MyTag", "setText: Called for $listener")
    view.setOnCheckedChangeListener { group, checkedId ->
        listener.onChange()
    }
}

// build
@InverseMethod("buttonIdToGender")
fun genderToButtonId(gender: Gender): Int{
    return if (gender == Gender.MALE) R.id.rb_male else R.id.rb_female
}

fun buttonIdToGender(buttonId:Int ): Gender {
    return if (buttonId == R.id.rb_male) Gender.MALE else Gender.FEMALE
}

@InverseMethod("positionToCity")
fun cityToPosition(city: Cities) : Int{
    return city.ordinal
}

fun positionToCity(position:Int): Cities{
    return Cities.values()[position]
}