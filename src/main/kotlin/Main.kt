import commands.*

fun runCommand(command: String) {
    val splitCommand = command.split(" ")
    val functionName = splitCommand[0]
    val args = splitCommand.drop(1)
    val commandInfo = commands[functionName] ?: return

    if (args.size != commandInfo.args.size) {
        println("Usage: $functionName ${commandInfo.args.joinToString(" ")}")
        return
    }
    // Execute the strongly typed action
    commandInfo.action(args)
}

fun invalidCommand(command: String): Boolean {
    if (command.isEmpty()) {return true} // Ignore empty commands
    val splitCommand = command.split(" ")
    val functionName = splitCommand[0]
    if (functionName !in commands) {
        println("${command}: command not found")
        return true
    }
    return false
}

fun main() {
    var input: String
    do {
        print("$ ")
        input = readln()
        if (invalidCommand(input)) continue
        runCommand(input)
    } while (true)
}
