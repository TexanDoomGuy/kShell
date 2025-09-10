package path
import java.io.File
import java.nio.file.Files
import java.nio.file.LinkOption
import java.nio.file.Path

/**
 * Initialize the PATH environment variable into a list of directories.
 */
fun pathInit(): List<String> {
    val pathVariable = System.getenv("PATH")
    val pathSeparator = File.pathSeparator ?: ":" // We aren't assuming anything because Windows
    if (pathVariable == null) {                   // uses a different separator character, I think.
        println("WARNING: PATH environment variable not found.")
        return emptyList()
    }
    return pathVariable.split(pathSeparator)
}

/**
 * Scan the directories in the PATH variable and return a map of filenames to their full paths.
 * If a path is a file, it is added directly. If it is a directory, all files in that directory are added.
 * Avoid using this function directly. Use the pathFiles variable instead, so we aren't hogging a shit ton of memory
 * since the map returned is really long.
 */
fun pathFilesInit(paths: List<String>): Map<String, File> {
    val files = mutableMapOf<String, File>()
    for (path in paths) {
        if (path.isBlank()) {
            println("WARNING: blank path found in PATH environment variable. Do we have a PATH env?")
        }
        val f = File(path)
        if (f.isDirectory) {
            val listed = f.listFiles() ?: continue
            for (child in listed) {
                if (child.isFile) {
                    files[child.name] = child
                }
            }
        } else if (f.isFile) {
            files[f.name] = f
        }
    }
    return files
}

fun isValidDirectory(path: String, followSymlinks: Boolean = true): Boolean {
    if (path.isBlank()) return false
    val raw = Path.of(path)
    // Resolve relative paths from the filesystem root (e.g., "src" -> "/src")
    val p = if (raw.isAbsolute) {
        raw.normalize()
    } else {
        Path.of("/").resolve(raw).normalize()
    }

    val isDir = if (followSymlinks) {
        Files.isDirectory(p) // follows symlinks
    } else {
        Files.isDirectory(p, LinkOption.NOFOLLOW_LINKS)
    }

    return isDir
}

/**
 * A map of filenames to their full paths from the PATH variable.
 */
val pathFiles = pathFilesInit(pathInit())