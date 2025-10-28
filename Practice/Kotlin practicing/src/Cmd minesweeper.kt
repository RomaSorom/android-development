
import kotlin.random.Random

fun openCell(selectedCell: List<Int>,
             fieldSizeX: Int,
             fieldSizeY: Int,
             openedCells: MutableMap<List<Int>, Int>,
             minedCells: List<List<Int>>,
             flaggedCells: Set<List<Int>>) {

    if (!inBounds(selectedCell, fieldSizeX, fieldSizeY)) return
    if (isOpen(selectedCell, openedCells)) return
    if (isFlagged(selectedCell, flaggedCells)) return
    if (isMined(selectedCell, minedCells)) return gameOver(selectedCell, minedCells)

    openedCells[selectedCell] = countMinesAround(selectedCell, minedCells)

    if (openedCells[selectedCell] == 0) {
        for (neighborCell in getCellNeighbors(selectedCell)) {
            openCell(neighborCell, fieldSizeX, fieldSizeY, openedCells, minedCells, flaggedCells)
        }
    }
}

fun flagCell(selectedCell: List<Int>, flaggedCells: MutableSet<List<Int>>, minedCells: List<List<Int>>) {
    if (isMined(selectedCell, minedCells)) flaggedCells.add(selectedCell) else wrongFlag(minedCells)
    flaggedCells.add(selectedCell)
    checkVictory(flaggedCells, minedCells)
}

fun inBounds(selectedCell: List<Int>, fieldSizeX: Int, fieldSizeY: Int) =
    selectedCell[0] in 0..<fieldSizeX && selectedCell[1] in 0..<fieldSizeY

fun isOpen(selectedCell: List<Int>, openedCells: Map<List<Int>, Int>) = selectedCell in openedCells

fun isMined(selectedCell: List<Int>, minedCells: List<List<Int>>) = selectedCell in minedCells

fun isFlagged(selectedCell: List<Int>, flaggedCells: Set<List<Int>>) = selectedCell in flaggedCells

fun getCellNeighbors(selectedCell: List<Int>): List<List<Int>> {
    val xPos: Int = selectedCell[0]
    val yPos: Int = selectedCell[1]
    return listOf(listOf(xPos - 1, yPos - 1), listOf(xPos, yPos - 1), listOf(xPos + 1, yPos - 1),
        listOf(xPos - 1, yPos), listOf(xPos + 1, yPos),
        listOf(xPos - 1, yPos + 1), listOf(xPos, yPos + 1), listOf(xPos + 1, yPos + 1))
}

fun gameOver(selectedCell: List<Int>, minedCells: List<List<Int>>) {
    println("Booooom!")
    println("Cell ${selectedCell[0] + 1}, ${selectedCell[1] + 1} was mined.")
    println("All mines:")
    println(minedCells)
    return main()
}

fun checkVictory(flaggedCells: Set<List<Int>>, minedCells: List<List<Int>>) {
    if (minedCells.toSet() == flaggedCells) {
        println("VICTORY!!!")
        println("All mines under flags")
        return main()
    }
}

fun wrongFlag(minedCells: List<List<Int>>) {
    println("Wrong FLAG")
    println("Cell is not mined")
    println("All mines:")
    println(minedCells)
    return main()
}

fun processPlayerInput(minedCells: List<List<Int>>,
                       openedCells: MutableMap<List<Int>, Int>,
                       flaggedCells: MutableSet<List<Int>>,
                       fieldSizeX: Int,
                       fieldSizeY: Int) {

    val inputPattern: Regex = Regex("""\d+\s\d+(\s(f|uf))?""")
    var playerInput: String
    while (true) {
        playerInput = readln()
        if (inputPattern.matches(playerInput)) break
    }
    val inputParts: List<String> = playerInput.split(" ")
    val actionType: String
    val cellXPos: Int = inputParts[0].toInt() - 1
    val cellYPos: Int = inputParts[1].toInt() - 1
    if (inputParts.count() == 2) actionType = "o" else actionType = inputParts[2]
    val selectedCell: List<Int> = listOf(cellXPos, cellYPos)

    when (actionType) {
        "o" -> openCell(selectedCell, fieldSizeX, fieldSizeY, openedCells, minedCells, flaggedCells)
        "f" -> flagCell(selectedCell, flaggedCells, minedCells)
        "uf" -> flaggedCells.remove(selectedCell)
        else -> println("Unknown command")
    }
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

fun drawField(fieldSizeX: Int, fieldSizeY: Int, openedCells: Map<List<Int>, Int>, flaggedCells: Set<List<Int>>) {
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
    val minesNum: Int = 20
    val fieldSizeX: Int = 10
    val fieldSizeY: Int = 10

    val minedCells: List<List<Int>> = generateMines(minesNum, fieldSizeX, fieldSizeY)
    val openedCells: MutableMap<List<Int>, Int> = mutableMapOf()
    val flaggedCells: MutableSet<List<Int>> = mutableSetOf()
    println("Let's play minesweeper")
    while (true) {
        drawField(fieldSizeX, fieldSizeY, openedCells, flaggedCells)
        processPlayerInput(minedCells, openedCells, flaggedCells, fieldSizeX, fieldSizeY)

    }
}

// список з координатами мін - done
// map з ключем - позицією і значенням - кількістю мін поряд - done
// список з координатами прапорів - done
// функція для генерації мін - done
// функція для малювання поля - done
// функція для обрахунку мін поряд - done
// функція обробки вводу користувача - done