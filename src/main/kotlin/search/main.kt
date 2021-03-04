package search

import java.util.Scanner
import java.io.File

val sin = Scanner(System.`in`)

fun parseData(): MutableList<String> {
    println("Enter the number of people:")
    val n = sin.nextInt()
    sin.nextLine()
    println("Enter all people:")
    val data = mutableListOf<String>()

    repeat(n) {
        val toAdd = sin.nextLine()
        data.add(toAdd)
    }
    println()
    return data
}

fun printMenu() {
    println("=== Menu ===")
    Menu.values().filter { it.name != "EXIT" }
        .forEach { println(it) }
    println(Menu.EXIT)
}

fun findPersonsInfo(data: List<String>): List<String> {
    println("Enter a name or email to search all suitable people.")
    val searchFor = sin.nextLine()
    val found = mutableListOf<String>()
    for (item in data) {
        if (item.contains(searchFor, true)) {
            found.add(item)
        }
    }
    return found
}

fun printAllData(data: List<String>) {
    println("=== List of people ===")
    data.forEach { println(it) }
}

fun runSearchEngine(data: List<String>) {
    while (true) {
        printMenu()
        val choice = sin.nextInt()
        sin.nextLine()
        println()

        when (choice) {
            0 -> break
            1 -> findPersonsInfo(data).forEach { println( it ) }
            2 -> printAllData(data)
            else -> println("Incorrect option! Try again.")
        }
        println()
    }
    println("Bye!")
}

fun main(args: Array<String>) {
    val data = if (args.isNotEmpty()) {
        val filename = args[1]
        File(filename).readLines()
    } else {
        parseData()
    }
    runSearchEngine(data)
}
