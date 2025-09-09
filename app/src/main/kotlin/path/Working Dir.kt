package path

var workingDir: String = System.getProperty("user.dir") ?: ("home/${System.getProperty("user.name")}")

var workingDirFiles: List<String> = emptyList()

fun resetWorkingDirFiles(){
    workingDirFiles = java.io.File(workingDir).list()?.toList() ?: emptyList()
}


// todo: change directories, and handle shorthands like ~ (home directory)