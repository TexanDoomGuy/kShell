package commands

import kotlin.system.exitProcess


/**
 * Simple, builtin commands that don't fit having their own class
 */
class Basics {
    fun exit(statusCode: Int = 0) {
        println("Exiting...")
        exitProcess(statusCode)
    }

    fun echo(message: String) {
        println(message)
    }
    fun type(command: String) {
        val type = commands[command]?.type ?: "external"
        println("$command is a ${if (type != "external") "builtin" else ""}$type shell command")
    }
    fun help(command: String) {

        // Maybe sort these by type? We have types.
        if (command == "all") {
            println("""
                |kShell version ${globals.version}
                |
                |Use help $command to get help for a specific built-in command
                |Available built-in commands:""".trimMargin())
            for ((name, command) in commands) {
                println("$name: ${command.description}")
            }
        }
        else {
            val cmd = commands[command] ?: run {
                println("Command $command not found")
                return
            }
            println("$command: ${cmd.description}\nusage: $command ${cmd.usage}")
        }
    }
}
