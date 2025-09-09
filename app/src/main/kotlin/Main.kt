import commands.*
import path.*

private const val ARG_KEY: String = "arg"
private const val DEFAULT_KEY: String = "default"


/**
 * run a command from a user input
 */

private fun tokenize(input: String): Pair<String, List<String>>? {
    val tokens = input.trim().split(Regex("\\s+")).filter { it.isNotEmpty() }
    if (tokens.isEmpty()) return null
    val commandName = tokens.first()
    val args = if (tokens.size > 1) tokens.drop(1) else emptyList()
    return commandName to args
}
private fun runCommand(command: String) {
    val (commandName, args) = tokenize(command) ?: return
    val commandInfo = commands[commandName] ?: return

    val defaultArg = commandInfo.args[DEFAULT_KEY]
    val argName = commandInfo.args[ARG_KEY]

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
private fun invalidCommand(command: String): Boolean {
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
    resetWorkingDirFiles()
    println("workingDirFiles set probably.")
    println("Scanned ${pathFiles.size} files in ${pathInit().size} paths in the PATH env.")
    println("I don't know why we're scanning for those, the shell does not support running external commands.")
    println("\n\nWelcome to KYS! Enter 'help' for a list of commands.\n")
    var input: String
    do {
        println("╭─${workingDir}")
        print("╰─[${System.getProperty("user.name")}@${System.getProperty("os.name")}]❯ ")
        val line = readlnOrNull() ?: continue
        input = line
        if (invalidCommand(input)) continue
        runCommand(input)
    } while (true)
}

