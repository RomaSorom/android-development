
import kotlin.random.Random

fun countMinesAround(cell: List<Int>, minedCells: List<List<Int>>): Int {
    var minesAround: Int = 0

    val xPos = cell[0]
    val yPos = cell[1]

    for (y in  yPos - 1..yPos + 1) {
        for (x in xPos - 1..xPos + 1) {
            if (x != xPos || y != yPos) {
                val checkedCell: List<Int> = listOf(x, y)
                if (checkedCell in minedCells) minesAround++
            }
        }
    }
    return minesAround
}

fun drawField(fieldSizeX: Int, fieldSizeY: Int, openedCells: Map<List<Int>, Int>, flaggedCells: List<List<Int>>) {
    for (y in 0..<fieldSizeY) {
        for (x in 0..<fieldSizeX) {
            val currentCell: List<Int> = listOf(x, y)
            print(
                when {
                    currentCell in flaggedCells -> "[*]"
                    currentCell in openedCells -> "[${openedCells[currentCell]}]"
                    else -> "[ ]"
                }
            )
        }
        print("\n")
    }
}

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
//    val minedCells: List<List<Int>> = generateMines(10, 5, 5)
    val minedCells: List<List<Int>> = listOf(listOf(2, 2), listOf(3, 2), listOf(4, 2), listOf(2, 3), listOf(4, 3), listOf(2, 4), listOf(3, 4), listOf(4, 4),)
    val openedCells: MutableMap<List<Int>, Int> = mutableMapOf(listOf(1, 1) to 3, listOf(1, 2) to 4)
    val flaggedCells: MutableList<List<Int>> = mutableListOf(listOf(3, 3), listOf(3, 4))
    print(minedCells)
    println(countMinesAround(listOf(3, 3), minedCells))

    drawField(5, 5, openedCells, flaggedCells)
}

// список з координатами мін - done
// map з ключем - позицією і значенням - кількістю мін поряд - done
// список з координатами прапорів - done
// функція для генерації мін - done
// функція для малювання поля - done
// функція для обрахунку мін поряд - done