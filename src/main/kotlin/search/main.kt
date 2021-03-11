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

fun findPersonsInfo(data: List<String>, indexMap: Map<String, List<Int>>): Set<String> {
    println("Select a matching strategy: ALL, ANY, NONE")
    val matchingStrategy = sin.next()
    sin.nextLine()

    println("Enter a name or email to search all suitable people.")
    val searchFor = sin.nextLine().split(' ')
    val found = mutableSetOf<String>()

    for (key in indexMap.keys) {
        if (indexMap[key]!!.isNotEmpty()) {
            for (index in indexMap[key]!!) {
                when (matchingStrategy.toUpperCase()) {
                    "ALL" -> if (!searchFor.all {
                            data[index].contains(it, true) }) { continue }
                    "ANY" -> if (!searchFor.any {
                            data[index].contains(it, true) }) { continue }
                    "NONE" -> if (!searchFor.none {
                            data[index].contains(it, true) }) { continue }
                }
                found.add(data[index])
            }
        }
    }

        if (found.size > 0) {
            println("${found.size} persons found:")
        } else {
            println("No matching people found.")
        }
        return found
}

fun printAllData(data: List<String>) {
    println("=== List of people ===")
    data.forEach { println(it) }
}

fun runSearchEngine(data: List<String>) {
    val index = indexMap(data)

    while (true) {
        printMenu()
        val choice = sin.next().first()
        sin.nextLine()
        println()

        when (choice) {
            '0' -> break
            '1' -> findPersonsInfo(data, index).forEach { println( it ) }
            '2' -> printAllData(data)
            else -> println("Incorrect option! Try again.")
        }
        println()
    }
    println("Bye!")
}

fun indexMap(lines: List<String>): Map<String, List<Int>> {
    val words = lines.map { it.split(' ') }.flatten().toSet()
    val invIndexMap = words.associateWith { mutableListOf<Int>() }
    lines.forEachIndexed { index, line ->
        for (word in words) {
            if (line.contains(word)) {
                invIndexMap[word]?.add(index)
            }
        }
    }
    return invIndexMap
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
