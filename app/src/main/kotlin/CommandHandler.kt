
import commands.commands
import path.workingDir
import java.io.File

/**
 * Tokenize a user input into a command name and arguments.
 * Returns:
 * Null if the input is empty.
 * Otherwise, a pair of the command name and arguments.
 */

fun tokenize(input: String): Pair<String, List<String>>? {
    val tokens = input.trim().split(Regex("\\s+")).filter { it.isNotEmpty() }
    if (tokens.isEmpty()) return null
    val commandName = tokens.first()
    val args = if (tokens.size > 1) tokens.drop(1) else emptyList()
    return commandName to args
}

/**
 * run an external command from a user input
 */
fun runExternalCommand(command: String,args: List<String> = emptyList()) {
    try {
        val processBuilder = ProcessBuilder(command, *args.toTypedArray())
        processBuilder.directory(File(workingDir))
//        processBuilder.redirectErrorStream(true) // merge stderr into stdout
        processBuilder.inheritIO() // stream child output directly to our console

        val process: Process = processBuilder.start()

        // Wait for the process to exit while it streams output live
        process.waitFor()
    } catch (e: Exception) {
        println(e.message)
    }

    // FIXME: We can get output, but anything using input is just not ran? Why????
    // Also, write some new tests
}


/**
 * run a command from a user input
 */
fun runCommand(command: String) {
    val (commandName, args) = tokenize(command) ?: return
    val commandInfo = commands[commandName] ?: return
    val defaultArg = commandInfo.args["default"]
    val argName = commandInfo.args["arg"]

    if (args.isEmpty()) {
        if (defaultArg != null) {
            commandInfo.action(listOf(defaultArg))
            return
        }
        println("Usage: $commandName ${argName ?: ""}".trim())
        return
    }

    commandInfo.action(args)
}

/**
 * Returns true if the command is invalid, and prints an error message.
 * If the input is empty, it is also considered invalid, but without an error message.
 */

// We do our own invalid command check in MainKt, this is useless now.
// We may need it later, so we're just commenting it out for now.
//fun invalidCommand(command: String): Boolean {
//    val splitCommand = command.split(" ")
//    val functionName = splitCommand[0]
//    if (functionName !in commands) {
//        println("${command}: command not found")
//        return true
//    }
//    return false
//}