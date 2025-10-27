
import kotlin.random.Random

fun generateMines(minesNum: Int, fieldSizeX: Int, fieldSizeY: Int): MutableList<List<Int>> {
    val minedCells: MutableList<List<Int>> = mutableListOf()
    var minesPlanted: Int = 0

    for (y in 0..<fieldSizeY) {
        for (x in 0..<fieldSizeX) {
            if (Random.nextBoolean() && (minesPlanted < minesNum)) {
                val cellPos: List<Int> = listOf(x, y)
                minedCells.add(cellPos)
                minesPlanted++
            }
        }
    }

    return minedCells
}

fun main() {
    println(generateMines(5, 5, 5))
}

// список з координатами мін
// map з ключем - позицією і значенням - кількістю мін поряд
// список з координатами прапорів
// функція для генерації мін
// функція для малювання поля
// функція для обрахунку мін поряд