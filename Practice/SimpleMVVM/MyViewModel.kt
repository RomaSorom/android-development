package com.example.composecomeback

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MyViewModel(
    val myModel: MyModel = MyModel()
) : ViewModel() {
    private val _clicksNum = MutableStateFlow(myModel.getClicks())
    // не реагує на зміни ВСЕРЕДИНІ об'єкта за яким спостерігає
    // реагує саме на зміну ОБ'ЄКТУ спостереження

    val clicksNum: StateFlow<Int> = _clicksNum

    fun incr() {
        myModel.increment()
        _clicksNum.value = myModel.getClicks()
    }
}