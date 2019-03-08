import java.util.Random

fun main(args: Array<String>) {
    val totalGames = args[0].toInt()
    val wins: Pair<Int, Int> = monty(totalGames)

    val stayPercentage = "%.2f".format(wins.first / totalGames.toDouble() * 100)
    val switchPercentage = "%.2f".format(wins.second / totalGames.toDouble() * 100)

    println("Wins by staying: ${wins.first} - $stayPercentage%")
    println("Wins by switching: ${wins.second} - $switchPercentage%")
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

fun remainingBox(doorA: Int, doorB: Int): Int {
    val rand = Random()
    var remainingBox: Int
    do {
        remainingBox = rand.nextInt(3)
    } while (remainingBox == doorA || remainingBox == doorB)

    return remainingBox
}
