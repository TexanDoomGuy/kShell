package commands

import kotlin.system.exitProcess


/**
 * Simple, builtin commands that don't fit having their own class
 */
public class Simple {
    fun exit(statusCode: Int = 0) {
        println("Exiting...")
        exitProcess(statusCode)
    }

    fun echo(message: String) {
        println(message)
    }
    fun type(command: String) {
        val cmd = commands[command]
        var type = cmd?.type ?: "unknown"
        if (type == "builtin") {
            type += ("." + this::class.simpleName)
        }
        println("$command is a $type shell command")
    }

}