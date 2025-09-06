package commands

import kotlin.system.exitProcess

class Simples {
    fun exit(statusCode: Int = 0) {
        println("Exiting...")
        exitProcess(statusCode)
    }

    fun echo(message: String) {
        println(message)
    }
    fun type(command: String) {
        val cmd = commands[command]
        val type = cmd?.type ?: "unknown"
        println("$command is a $type shell command")
    }

}