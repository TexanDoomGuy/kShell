import commands.*
import path.pathFiles
import path.pathInit

/**
 * run a command from a user input
 */
fun runCommand(command: String) {
    val splitCommand = command.split(" ")
    val functionName = splitCommand[0]
    val args = splitCommand.drop(1)
    val commandInfo = commands[functionName] ?: return

    if (args.size != commandInfo.args.size) {
        println("Usage: $functionName ${commandInfo.args.joinToString(" ")}")
        return
    }
    commandInfo.action(args)
}

/**
 * Returns true if the command is invalid, and prints an error message.
 * If the input is empty, it is also considered invalid, but without an error message.
 */
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
    println("Setting up...")
    println("Scanned ${pathFiles.size} files in ${pathInit().size} paths.")
    var input: String
    do {
        println("╭─/Temp/Working/Directory")
        print("╰─[${System.getProperty("user.name")}@${System.getProperty("os.name")}]❯ ")
        val line = readlnOrNull() ?: continue
        input = line
        if (invalidCommand(input)) continue
        runCommand(input)
    } while (true)
}
