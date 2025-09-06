package commands
data class Command(
    val description: String,
    val args: List<String>,
    val action: (List<String>) -> Unit,
    val type: String = "builtin"
)
val commands: Map<String, Command> = mapOf(
    "exit" to Command(
        description = "Exit the program with a specified status code (defaults to 0).",
        args = listOf("statusCode"),
        action = { argv ->
            var code: Int? = 0
            // Parse the expected single numeric argument and call the real exit command
            code = argv[0].toIntOrNull()
            if (code == null) {
                println("Usage: exit \$statusCode")
                return@Command
            }
            // Call the underlying exit command that expects a numeric code
            Simples().exit(code)
        }
    ),
    "echo" to Command(
        description = "Print a message to the standard output.",
        args = listOf("message"),
        action = { argv -> Simples().echo(argv[0]) }
    ),
    "type" to Command(
        description = "Print the type of a command.",
        args = listOf("command"),
        action = { argv ->
            var command: String? = ""
            if (argv.isEmpty()) {
                println("Usage: type \$command")
                return@Command
            }
            command = argv[0]
            // Call the underlying exit command that expects a numeric code
            Simples().type(command)
        }
    )
)