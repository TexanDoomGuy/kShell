import commands.*
import path.*


fun main() {
    println("Setting up...")
    resetWorkingDirFiles()
    println("workingDirFiles set probably.")
    println("Scanned ${pathFiles.size} files in ${pathInit().size} paths in the PATH env.")
    println("\n\nWelcome to KYS! Enter 'help' for a list of commands.\n")
    var input: String
    do {
        println("╭─${workingDir}")
        print("╰─[${System.getProperty("user.name")}@${System.getProperty("os.name")}]❯ ")
        input = readlnOrNull() ?: continue
        val (commandName, args) = tokenize(input)?: continue
        if (pathFiles[commandName] != null && commands[commandName] == null) {
            runExternalCommand(commandName,args)
            continue
        }
        if (commands[commandName] != null) {
            runCommand(input)
        } else {
            println("${commandName}: command not found")
        }
    } while (true)
}


