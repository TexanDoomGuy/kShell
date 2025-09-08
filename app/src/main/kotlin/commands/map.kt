package commands


data class Command(
    val description: String,
    val args: Map<String, String>,
    val action: (List<String>) -> Unit,
    val type: String = "builtin"
)

/**
 * A map of all built-in commands.
 */
val commands: Map<String, Command> = mapOf(
    "exit" to Command(
        description = "Exit the program with a specified status code (defaults to 0).",
        args = mapOf("arg" to "Exit code.", "default" to "0"),
        action = { argv ->
            // Parse the expected single numeric argument and call the real exit command
            val code = argv[0].toInt()
            // Call the underlying exit command that expects a numeric code
            Basics().exit(code)
        },
        type = ".Basics"
    ),
    "echo" to Command(
        description = "Print a message to the standard output.",
        args = mapOf("arg" to "message"),
        action = { argv -> Basics().echo(argv[0]) },
        type = ".Basics"
    ),
    "type" to Command(
        description = "Print the type of a command.",
        args = mapOf("arg" to "command"),
        action = { argv ->
            val command: String = argv[0]
            // Call the underlying exit command that expects a numeric code
            Basics().type(command)
        },
        type = ".Basics"
    ),
    "pwd" to Command(
        description = "Print the working directory",
        args = emptyMap(),
        action = { Paths().pwd() },
        type = ".Paths"
    ),
    "ls" to Command(
        description = "List the contents of the working directory",
        args = mapOf("arg" to "path", "default" to path.workingDir),
        action = { argv ->
            Paths().ls(argv[0])
        },
        type = ".Paths"
    )
)