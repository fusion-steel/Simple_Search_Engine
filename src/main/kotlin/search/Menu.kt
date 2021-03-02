package search

enum class Menu(private val description: String) {
    EXIT("Exit"),
    FIND("Find a person"),
    PRINT("Print all people");

    override fun toString(): String {
        return "$ordinal. $description"
    }
}
