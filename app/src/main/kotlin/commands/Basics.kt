package commands

import path.*
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
        val cmd = commands[command]?.action ?: {
            TODO("This is where we implement external commands, but there are none yet!")
        }
        val type = commands[command]?.type ?: "external"
        println("$command is a $type shell command")
    }
}
class Paths {
    fun pwd() {
        println(workingDir)
    }
    fun ls(path: String) {
        if (path != workingDir) {
            try {
                val dirFiles = java.io.File(path).list()?.toList() ?: emptyList()
                println(dirFiles)
                return
            } catch (e: Exception) {
                println("ls: cannot access '$path': No such file or directory")
                // todo add a flag to print more verbose errors
                // or do the opposite and add a flag to throw the error instead. That would be comedic.
                return
            }
        }
        println(workingDirFiles)
    }

    fun cd(path: String) {
//        if
        // change the working directory to the specified path
        // handle ~ (home directory), and relative paths
        // update the workingDir variable
        // update the wokingDirFiles variable,
        // this is a stub for now
        println("cd command is not implemented yet.")
    }
}