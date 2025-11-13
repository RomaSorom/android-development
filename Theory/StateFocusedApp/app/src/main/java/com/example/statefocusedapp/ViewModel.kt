package com.example.statefocusedapp

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.toMutableStateList


// логіка застосунку:
// 1. інтерфейсу (відображення інформації)
// вказує як відобразити стан,
// як застосунок має поводитися,
// як реагувати на зміну стану,

// 2. бізнес-логіка (робота з інформації)
// вказує, що робити зі станом
// (оскільки стан містить в собі якусь інформацію)

// логіку інтерфейсу та бізнес-логіку слід розіляти


// viewModel надає доступ інтерфейсу до стану та його бізнес-логіки
// переживає зміни у конфігураці
// слідує життєвому циклу activities, fragments, destination
// не є частиною композиції (не використовувати remember)
// дізнатися БІЛЬШЕ про життєвий цикл



// viewModel() повертає існуючий екземпляр ViewModel
// або створює новий у разі відсутності наявного
// viewModel повертатиме той самий екземпляр
// поки жива певна область, у якій він викликався (activities, fragments, destination)


// ViewModel слід використовувати на рівні екрану біля кореневих композбл
    // (високо в ієрархії)
// неможна передавати viewModel вниз по ієрахії (іншим композбл), а
// передавати лише інформацію, яка необхідна цим композбл

class MyViewModel : ViewModel() {
    private val _list: MutableList<Task> = getTasksList().toMutableStateList()
    val list: List<Task>
        get() = _list

    fun remove(item: Task) {
        _list.remove(item)
    }

    fun changeTaskChecked(item: Task, checked: Boolean) =
        _list.find { it.id == item.id }?.let { task ->
            task.checked = checked
        }
}

fun getTasksList() = List(30) { i -> Task(i, "Task # $i") }