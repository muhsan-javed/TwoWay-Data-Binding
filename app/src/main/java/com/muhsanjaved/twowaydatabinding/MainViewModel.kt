package com.muhsanjaved.twowaydatabinding

import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    val user = User("muhsanjaved@gmail.com","muhsan288", Gender.MALE, Cities.QAMBER)
}