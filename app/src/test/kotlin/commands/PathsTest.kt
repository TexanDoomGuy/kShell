package commands

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream


class PathsTest {
    @Test
    fun pwd(){
        val originalOut = System.out
        val outContent = ByteArrayOutputStream()
        try {
            System.setOut(PrintStream(outContent, true))
            Paths().pwd()

        } finally {
            System.setOut(originalOut)
        }
        val output = outContent.toString()
        assertEquals(System.getProperty("user.dir")+"\n", output)
    }
    @Test
    fun ls(){
        val originalOut = System.out
        val outContent = ByteArrayOutputStream()
        try {
            System.setOut(PrintStream(outContent, true))
            Paths().ls(System.getProperty("user.dir"))
        } finally {
            System.setOut(originalOut)
        }
        val output = outContent.toString()
        assertEquals(java.io.File(System.getProperty("user.dir")).list()!!.joinToString("\n")+"\n", output)
    }
}