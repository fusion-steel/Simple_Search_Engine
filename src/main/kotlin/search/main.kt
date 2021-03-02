package search

import java.util.Scanner

val sin = Scanner(System.`in`)

fun parseData(): MutableList<String> {
    println("Enter the number of people:")
    val n = sin.nextInt()
    sin.nextLine()
    println("Enter all people:")
    val people = mutableListOf<String>()

    repeat(n) {
        val toAdd = sin.nextLine()
        people.add(toAdd)
    }
    println()
    return people
}

fun printMenu() {
    println("=== Menu ===")
    Menu.values().filter { it.name != "EXIT" }
        .forEach { println(it) }
    println(Menu.EXIT)
}

fun findPersonsInfo(people: List<String>): List<String> {
    println("Enter a name or email to search all suitable people.")
    val searchFor = sin.nextLine()
    val found = mutableListOf<String>()
    for (person in people) {
        if (person.contains(searchFor, true)) {
            found.add(person)
        }
    }
    return found
}

fun printAllPeople(people: List<String>) {
    println("=== List of people ===")
    people.forEach { println(it) }
}

fun main() {
    val people = parseData()
    while (true) {
        printMenu()
        val choice = sin.nextInt()
        sin.nextLine()
        println()

        when (choice) {
            0 -> break
            1 -> findPersonsInfo(people).forEach { println( it ) }
            2 -> printAllPeople(people)
            else -> println("Incorrect option! Try again.")
        }
        println()
    }
    println("Bye!")
}
