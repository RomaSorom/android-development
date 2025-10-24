
val c = 3 // оголошення (declaration) змінної верхнього рівня

// fun - оголошення (declaration) функції
// main() - точка входу програми

fun hello() {
    println("Hello, world!")
}

fun sum(x: Int, y: Int): Int { // х, у - параметри + ОБОВ'ЯЗКОВА явна типізація параметрів та значення, що повертається
    return x + y
}

fun div(a: Int = 15, b: Int = 3): Int { // значення параметрів за замовчуванням
    return a / b
}

fun withoutReturn() {
    println("hello")
}

fun sub(a: Int = 10, b: Int): Int = a - b // Одновиразна функція


fun function(num: Int): Int { // Раннє повернення в фукнції
    if (num == 5) {
        return 0
    }

    return num + 2
}

class Example( // ( властивості (properties) ) + вміст в дужках - заголовок класу (class header)
    val a: Int,
    val b: Int = 3, // властивість за замовчуванням
    i: Int = 0, // "приватна" властивість
    ) {

    // може бути декілька конструкторів

    // властивості у тілі класу
    val c: Int = 1 // властивість в тілі повинна бути проініціалізованою, тобто за замовчуванням
    var d: String = "Name"
    // f: String = "Name1" - помилка, приватні властивості тільки в заголовку


    fun sumAB(): Int { // функція-член (function member)
        return a + b
    }
}

data class DataClass( // клас даних (Data class) з попередньо визначеними функціями-членами для
    // зручного виводу
    // порівняння екземплярів
    // копіювання
    // та інше
    val name: String,
    var age: Int,
) {

}

fun getLambda(): (Int) -> Int  = {x -> x / 2}

fun main() {

    // тіло функції
    println("Hello, world!") // вивід аргументів на стандартний вихід


    // змінні бувають:
    val a = 1 // тільки для читання (read-only)
    // після першого присвоєння значення змінити не можливо
    // a = 1 - помилка

    var b = 1// значення яких змінюється (mutable)
    b = 2

    // Використовувати val/var за призначенням


    println("b = $b") // $ - шаблонний вираз (string template)
    println("b + 1= ${b + 1}") // шаблонний вираз з {}


    val num = 1 //  Виведення типу (type inference)
    // необхідно для визначення властивостей та функцій змінної чи структури даних компілятором


    // оператори розширеного присвоєння (augmented assignment)
    b += 1
    b -= 1
    b *= 1
    b /= 1
    b %= 1


    // Базові типи:
    val year: Int = 100
    val score: UInt = 10u
    val price: Float = 10.11f
    val isEnabled: Boolean = true
    val separator: Char = '.'
    val message: String = "Hello, world!"


    var e: Int // оголошення (declaration) з явною типізацією
    e = 4 // ініціалізація (initialization)
    var f = 2 // оголошення + ініціалізація
    // ініціалізувати до першого використання


    // колекції (collections):
    // список (list) - елементи упорядковані + не унікальні
    val listA = listOf(1, 2, 2, 4) // неявний тип елементів + лише для читання (read-only)

    val listB: MutableList<String> = mutableListOf("nameA", "nameB") // Явний (explicit) тип елементів + змінний (mutable)

    val listBLocked: List<String> = listB // перетворення типу (casting) / представлення (view) змінного списку лише для читання


    println(listA[1]) // [] - оператор індексованого доступу (indexed access operator)
    // функції для отримання першого та останнього елемента списку / функії розширень (extension functions)
    println(listA.first())
    println(listA.last())
    println(listB.count()) // кількість елементів в списку
    println("nameA" in listB) // перевірка наявності елемента в списку / in - оператор
    listB.add("nameC") // додавання елементу в список
    listB.remove("nameA") // видалення елемента зі писку
    println(listB)


    // множина (set) - елементи неупорядковані + елементи унікальні
    val setA = setOf("nameA", "nameB", "nameC") // неявне визначення типу + незмінна множина (read-only)

    val setB: MutableSet<Int> = mutableSetOf(1, 2, 3, 4) // явне визначення типу + змінна множина (mutable)

    val setBLocked: Set<Int> = setB // перетворення типу (casting) / представлення (view) змінної множини лише для читання

    // оператор індексного доступу не доступний в множинах

    // список функцій розширень для множини:
    // .count()
    // .add() / .remove()
    // in - оператор


    // мапи (map) - елементи у вигляді ключ - значення
    // ключі - унікальні, значення можуть повторюватися
    val mapA = mapOf(1 to "nameA", 2 to "nameB") // неявне визначення типу + незмінна мапа

    val mapB: MutableMap<Int, String> = mutableMapOf(1 to "nameA", 2 to "nameB") // явне визначення типу + змінна мапа

    val mabBLocked: Map<Int, String> = mapB // перетворення типу (casting)

    println(mapB[1]) // доступ до значення елементу за його ключем за допомогою [] - оператор індексного доступу
    println(mapB[3]) // доступ ключ-пари, якої не існує, повертає null
    mapB[3] = "nameC" // додавання елемента в мапу за допомогою []

    // функції розширення для мап:
    // .remove()
    // .count()
    // .containsKey()
    // .keys .values - властивості (properties), які містять колекції ключів та значень мапи
    println(mapB.keys)
    println(3 in mapB)
    println("nameC" in mapB.values)


    // умовні вирази (condition expressions) - перевірка певного фрагмента коду на істинність

    // if, when - конструкції для перевірки умов
    // if < when ==>
    // читабельність
    // простота додавання гілок
    // менше помилок

    val isTrue = true
    if (isTrue) { // ( умовний вираз (conditional expressions) )
        println(true) // дія, яку необхідно виконати за умови істинності
    } else {
        println(false) // (дія, яка виконається, якщо умовний вираз не виконується)
    }

    // тернарного оператора не існує
    if (isTrue) println(true) else println(false) // максимум 1 рядок коду



    // when - конструкція для перевірки умов
    // використовувати, коли є умовний вираз з кількома гілками
    val g = 2
    when (g) {
        2 -> println(g)
        1 -> println(1)
    }
    // when - інструкція (statement) або вираз (expression)
    // інструкція не повертає значення, а лише виконує дію
    // перевірка послідовна, виконується лише перша підходяща


    // приклад виразу
    val pos = 5
    var month = when (pos) {
        5 -> "May"
        else -> ">Not May"
    }

    // when без об'єкта для перевірки ( )
    month = when {
        pos == 5 -> "May"
        else -> "Not May"
    }

    1..5 // діапазон (range) 1, 2, 3, 4, 5
    1..<5 // 1, 2, 3, 4
    5 downTo 1 // 5, 4, 3, 2, 1
    1..5 step 2 // 1, 3, 5
    // також працює з символами

    // for та while - структури циклів
    for (i in 5 downTo 1) { // i - ітератор
        println(i)
    }

    val months = listOf("May", "June", "July")
    for (month in months) {
        println(month)
    }

    // while - поки умовний вираз істинний
    // do-while - дія => перевірка

    val maxNum = 5
    var currentNum = 0
    while (currentNum < 5) {
        currentNum++ // ++ - інкремент
        println(currentNum)
    }

    println("---")
    currentNum = 0
    do {
        currentNum++
        println(currentNum)
    } while (currentNum < maxNum)



    hello()
    println(sum(3, 5)) // виклик функції з аргументами 3 та 5

    // називати функції у camelCase'і

    println(sum(y = 4, x = 3)) // іменовані аргументи

    println(div())
    println(div(b = 1))

    withoutReturn() // повертає unit

    println(sub(b = 5))

    function(5)

    val mul = {a: Int, b: Int -> a * b} // лямбда вираз
    mul(3, 5)


    val numList = listOf(-1, 2, 3, 0, -2, -4, 2)

    val negatives = numList.filter({x -> x < 0}) // лямбда вираз, як придикат
    val isPositive = {x: Int -> x > 0}
    val positives = numList.filter(isPositive)


    // val lambda = {x -> x * 2} помилка, оскільки не можна визначити тип функції по типу параметрів
    val lambda: (Int) -> Int = {x -> x * 2} // явний тип лямбда функції

    val lambda1 = getLambda() // повернення лямбда функції
    println(numList.map(getLambda()))

    val y = {x: Int -> x + 1}(3) // окреме викликання
    println(y)

    println(numList.filter {x -> x < 0}) // кінцева лямбда, якщо параметр один або останній



    val myClass: Example = Example(a = 2, i = 2) // оголошення екземпляра (instance) класу example
    myClass.a // властивість, оголошена в заголовку класу
    // myClass.i - помилка, оскільки i приватна властивість
    myClass.d // властивість оголошена в тілі класу
    println(myClass.sumAB()) // виклик функції-члену екземпляра касу

    val dataClass: DataClass = DataClass(name = "Roma", age = 20)
    println(dataClass.toString()) // вивід + явний виклик функції
    println(dataClass)
    val dataClass1: DataClass = dataClass.copy(name = "Name") // копіювання екземпляра з оновленням параметра
    println(dataClass.equals(dataClass1)) // порівняння екземплярів
    println(dataClass == dataClass1)


    val j: String? = null // nullable тип
    var k = 1
    // k = null - помилка, оскільки k має тип Int, а не Int?
    // val m: Int = null - помилка, non-nullable тип

    val l = null
    if (l == null) println("Is null") // перевірка на null за допомогою умовного виразу


    val ua: String? = null
    ua?.length // оператор безпечного виклику для повернення null (а не помилки),
    // якщо об'єкт або його властивість є null

    // також використовується для функцій та функцій-розширень,
    // у такому випаду замість виклику функції повертається null

    ua?.length ?: 2 // ?: - оператор елвіса, який повертає значення за замовчуванням, якщо виявлено null



}


