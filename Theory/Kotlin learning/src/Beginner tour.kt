
val c = 3 // оголошення (declaration) змінної верхнього рівня

// fun - оголошення (declaration) функції
// main() - точка входу програми
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

    // тест

}


