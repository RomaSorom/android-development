
import kotlin.random.Random

fun checkGameOver(openedCell: List<Int>, minedCells: List<List<Int>>) = openedCell in minedCells

fun processPlayerInput() {
    val inputPattern: Regex = Regex("""\d+\s\d+(\s(f|uf))?""")
    var playerInput: String
    while (true) {
        playerInput = readln()
        if (inputPattern.matches(playerInput)) break
    }
    val inputParts: List<String> = playerInput.split(" ")
    val actionType: String
    val cellXPos: Int = inputParts[0].toInt()
    val cellYPos: Int = inputParts[1].toInt()
    if (inputParts.count() == 2) actionType = "o" else actionType = inputParts[2]

    println(
        when (actionType) {
            "o" -> "Open cell"
            "f" -> "Flag cell"
            "uf" -> "Unflag cell"
            else -> "Unknown command"
        }
    )

}

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
    val minesNum: Int = 8
    val fieldSizeX: Int = 5
    val fieldSizeY: Int = 5

    val minedCells: List<List<Int>> = generateMines(minesNum, fieldSizeX, fieldSizeY)
    val openedCells: MutableMap<List<Int>, Int> = mutableMapOf()
    val flaggedCells: MutableList<List<Int>> = mutableListOf()
    while (true) {
        drawField(fieldSizeX, fieldSizeY, openedCells, flaggedCells)
        processPlayerInput()

    }
}

// список з координатами мін - done
// map з ключем - позицією і значенням - кількістю мін поряд - done
// список з координатами прапорів - done
// функція для генерації мін - done
// функція для малювання поля - done
// функція для обрахунку мін поряд - done
// функція обробки вводу користувача