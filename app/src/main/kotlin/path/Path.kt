package path
import okio.Path.Companion.toPath
import okio.FileSystem
import okio.Path
// We aren't using java.io.File because we're planning to compile to native later



/**
 * Initialize the PATH environment variable into a list of directories.
 */
fun pathInit(): List<String> {
    val pathVariable = System.getenv("PATH")
    @Suppress("SystemGetProperty")
    val pathSeparator = System.getProperty("path.separator") ?: ":" // We aren't assuming anything because Windows
    if (pathVariable == null) {                                     // uses a different separator character, I think.
        println("WARNING: PATH environment variable not found.")    // we're ignoring the warning because we have to
        return emptyList()                                          // use libraries that work with Kotlin/Native.
    }
    return pathVariable.split(pathSeparator)
}

/**
 * Scan the directories in the PATH variable and return a map of filenames to their full paths.
 * If a path is a file, it is added directly. If it is a directory, all files in that directory are added.
 * Avoid using this function directly. Use the pathFiles variable instead, so we aren't hogging a shit ton of memory
 * since the map returned is really long.
 */
fun pathFilesInit(paths: List<String>): Map<String, Path> {
    val files = mutableMapOf<String, Path>()
    for (path in paths) {
        val fs = FileSystem.SYSTEM
        val p = path.toPath()
        if (fs.metadataOrNull(p)?.isDirectory == true) {
            val listedPath = fs.list(p)
            val listed = listedPath.associateBy { it.name }
            files.putAll(listed)
        } else {
            files[p.name] = p
        }
    }
    return files
}

/**
 * A map of filenames to their full paths from the PATH variable.
 */
val pathFiles = pathFilesInit(pathInit())