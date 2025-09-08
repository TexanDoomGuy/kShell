package path

val workingDir: String = System.getProperty("user.dir") ?: ("home/${System.getProperty("user.name")}")

val workingDirFiles: List<String> = java.io.File(workingDir).list()?.toList() ?: emptyList()
// todo: change directories, and handle shorthands like ~ (home directory)