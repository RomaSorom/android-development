package com.example.composecomeback

class MyModel {
    private var _clicksNum = 0

    fun getClicks(): Int {
        return _clicksNum
    }

    fun increment() {
        _clicksNum++
    }
}