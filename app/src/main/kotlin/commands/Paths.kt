package commands

import path.isValidDirectory
import path.resetWorkingDirFiles
import path.workingDir
import path.workingDirFiles
import java.io.File

class Paths {
    fun pwd() {
        println(workingDir)
    }
    fun rescan() {
        resetWorkingDirFiles()
    }

    fun ls(path: String) {
        if (path != "The current working directory") { // We're checking for that instead of the actual
            try {                                      // working dir because we want a readable help command.
                val dirFiles = File(path).list() ?: run {
                    println("cannot access '$path': No such file or directory")
                    return
                }
                println(dirFiles.joinToString("\n"))
                return
            } catch (e: Exception) {
                println("cannot access '$path': No such file or directory")
                // todo add a flag to print more verbose errors
                // or do the opposite and add a flag to throw the error instead. That would be comedic.
                return
            }
        }
        println(workingDirFiles)
    }

    fun cd(path: String) {

        if (path == "..") {
            workingDir = File(workingDir).parent ?: workingDir
            resetWorkingDirFiles()
            return
        }
        workingDir = if (isValidDirectory(path)) {
            path
        }
        else if (isValidDirectory("${workingDir}/$path")) {
            "${workingDir}/$path"
        } else {
            println("Cannot access '$path': No such file or directory")
            return
        }
        resetWorkingDirFiles()
    }
}