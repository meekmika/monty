import java.util.*

val rand = Random()

fun main(args: Array<String>) {
    val totalGames = args[0].toInt()
    val wins: Pair<Int, Int> = monty(totalGames)

    val stayPercentage = "%.2f".format(wins.first / totalGames.toDouble() * 100)
    val switchPercentage = "%.2f".format(wins.second / totalGames.toDouble() * 100)

    println("Wins by staying: ${wins.first} - $stayPercentage%")
    println("Wins by switching: ${wins.second} - $switchPercentage%")

    val boxCount = 100
    println("\nAlt. running $totalGames simulations with $boxCount doors")
    montyhall(boxCount = boxCount, totalGames = totalGames)
}

fun monty(totalGames: Int): Pair<Int, Int> {
    val rand = Random()

    var stayWins = 0
    var switchWins = 0

    (1..totalGames).forEach {
        val prize = rand.nextInt(3)
        val pick = rand.nextInt(3)

        val shown = remainingBox(prize, pick)
        val pick2 = remainingBox(pick, shown)

        if (pick2 == prize) switchWins++ else stayWins++
    }

    return Pair(stayWins, switchWins)
}

fun remainingBox(boxA: Int, boxB: Int): Int {
    val rand = Random()
    var remainingBox: Int
    do {
        remainingBox = rand.nextInt(3)
    } while (remainingBox == boxA || remainingBox == boxB)

    return remainingBox
}

fun montyhall(totalGames: Int = 10000, boxCount: Int = 3) {
    var switchWins = 0
    var stayWins = 0

    (1..totalGames).forEach {
        val boxes = MutableList(boxCount) { 0 }
        boxes[rand.nextInt(boxCount)] = 1

        val pick = rand.nextInt(boxCount)

        boxes.removeAt(pick)

        val switch = boxes.contains(1)

        if (switch) switchWins++ else stayWins++
    }

    val stayPercentage = "%.2f".format(stayWins / totalGames.toDouble() * 100)
    val switchPercentage = "%.2f".format(switchWins / totalGames.toDouble() * 100)

    println("Wins by staying: $stayWins - $stayPercentage%")
    println("Wins by switching: $switchWins - $switchPercentage%")
}
