package com.muhsanjaved.twowaydatabinding

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.Observable

class User(
    email: String,
    username: String,
    gender: Gender,
    city: Cities
) : BaseObservable() {

    @get:Bindable
    var email = email
        set(value) {
            if (value != email){
                field = value
                notifyChange()
            }

        }

    @get:Bindable
    var username = username
        set(value) {
            if (value != username){
                field = value
                notifyChange()
            }
        }

    @get:Bindable
    var gender = gender
        set(value) {
            field = value
            notifyChange()
        }

    @get:Bindable
    var city = city
        set(value) {
            field = value
            notifyChange()
        }

    override fun toString(): String {
        return "Email: $email\n" +
                "Username: $username\n" +
                "Gernder: $gender\n" +
                "City: $city"
    }
}