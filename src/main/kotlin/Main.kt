val commands = listOf<String>("exit")

fun invalidCommand(command: String): Boolean {
    if (command !in commands) {
        println("${command}: command not found")
        return true
    }
    return false
}

fun input(prompt: String): String {
    var input: String
    do {
        print(prompt)
        input = readln()
    } while (invalidCommand(input))
    return input
}

fun main() {
    input("$ ")
}
