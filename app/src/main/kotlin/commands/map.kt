package commands

data class Command(
    val description: String,
    val args: List<String>,
    val action: (List<String>) -> Unit,
    val type: String = "builtin"
)

/**
 * A map of all built-in commands.
 */
public val commands: Map<String, Command> = mapOf(
    "exit" to Command(
        description = "Exit the program with a specified status code (defaults to 0).",
        args = listOf("statusCode"),
        action = { argv ->
            // Parse the expected single numeric argument and call the real exit command
            val code = argv[0].toInt()
            // Call the underlying exit command that expects a numeric code
            Simple().exit(code)
        }
    ),
    "echo" to Command(
        description = "Print a message to the standard output.",
        args = listOf("message"),
        action = { argv -> Simple().echo(argv[0]) }
    ),
    "type" to Command(
        description = "Print the type of a command.",
        args = listOf("command"),
        action = { argv ->
            val command: String = argv[0]
            // Call the underlying exit command that expects a numeric code
            Simple().type(command)
        }
    )
)