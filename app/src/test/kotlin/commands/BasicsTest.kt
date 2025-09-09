package commands

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class BasicsTest {

    /*@Test
    fun exit() {
//      I'm not sure how to test this since exit() terminates the JVM.
//      No JVM means no more testing.
    }*/

    @Test
    fun echo() {
        val originalOut = System.out
        val outContent = ByteArrayOutputStream()
        try {
            System.setOut(PrintStream(outContent, true))
            Basics().echo("hello world")
        } finally {
            System.setOut(originalOut)
        }
        val output = outContent.toString()
        assertEquals("hello world\n", output)
    }

    @Test
    fun type() {
        val originalOut = System.out
        val outContent = ByteArrayOutputStream()
        try {
            System.setOut(PrintStream(outContent, true))
            Basics().type("type")
        } finally {
            System.setOut(originalOut)
        }
        val output = outContent.toString()
        assertEquals("type is a builtin.Basics shell command\n", output)
    }

    @Test
    fun help() {
        val originalOut = System.out
        val outContent = ByteArrayOutputStream()
        try {
            System.setOut(PrintStream(outContent, true))
            Basics().help("help")
        } finally {
            System.setOut(originalOut)
        }
        val output = outContent.toString()
        assertEquals("help: Display help information about a command.\n" +
                "usage: help <command> default: <all>\n", output)
    }

}