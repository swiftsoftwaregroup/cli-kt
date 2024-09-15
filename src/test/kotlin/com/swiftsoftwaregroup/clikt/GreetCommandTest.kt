package com.swiftsoftwaregroup.clikt

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.io.TempDir
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.PrintStream
import java.nio.file.Path
import kotlinx.cli.ArgParser

@DisplayName("GreetCommand Tests")
class GreetCommandTest {

    private lateinit var outputStream: ByteArrayOutputStream
    private lateinit var errorStream: ByteArrayOutputStream
    private val originalOut = System.out
    private val originalErr = System.err

    @BeforeEach
    fun setUp() {
        outputStream = ByteArrayOutputStream()
        errorStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))
        System.setErr(PrintStream(errorStream))
    }

    @AfterEach
    fun tearDown() {
        System.setOut(originalOut)
        System.setErr(originalErr)
    }

    @Nested
    @DisplayName("generateGreeting function")
    inner class GenerateGreetingTests {
        @Test
        @DisplayName("should generate correct greetings for different languages")
        fun testGenerateGreeting() {
            assertEquals("Hello, Alice!", generateGreeting("Alice", Language.en))
            assertEquals("¡Hola, Carlos!", generateGreeting("Carlos", Language.es))
            assertEquals("Здравей, Ivan!", generateGreeting("Ivan", Language.bg))
        }
    }

    @Nested
    @DisplayName("readNameFromFile function")
    inner class ReadNameFromFileTests {
        @Test
        @DisplayName("should read name correctly from file")
        fun testReadNameFromFile(@TempDir tempDir: Path) {
            val file = File(tempDir.toFile(), "name.txt")
            file.writeText("John")
            assertEquals("John", readNameFromFile(file))
        }
    }

    @Nested
    @DisplayName("GreetCommand execution")
    inner class GreetCommandExecutionTests {
        @Test
        @DisplayName("should greet in English by default")
        fun testGreetCommandExecution(@TempDir tempDir: Path) {
            val file = File(tempDir.toFile(), "name.txt")
            file.writeText("Maria")

            val parser = ArgParser("test")
            val greetCommand = GreetCommand()
            parser.subcommands(greetCommand)
            parser.parse(arrayOf("greet", file.absolutePath))

            assertEquals("Hello, Maria!\n", outputStream.toString())
        }

        @Test
        @DisplayName("should greet in Spanish when specified")
        fun testGreetCommandWithSpanishLanguage(@TempDir tempDir: Path) {
            val file = File(tempDir.toFile(), "name.txt")
            file.writeText("Carlos")

            val parser = ArgParser("test")
            val greetCommand = GreetCommand()
            parser.subcommands(greetCommand)
            parser.parse(arrayOf("greet", "-l", "es", file.absolutePath))

            assertEquals("¡Hola, Carlos!\n", outputStream.toString())
        }

        @Test
        @DisplayName("should greet in Bulgarian when specified")
        fun testGreetCommandWithBulgarianLanguage(@TempDir tempDir: Path) {
            val file = File(tempDir.toFile(), "name.txt")
            file.writeText("Ivan")

            val parser = ArgParser("test")
            val greetCommand = GreetCommand()
            parser.subcommands(greetCommand)
            parser.parse(arrayOf("greet", "--language", "bg", file.absolutePath))

            assertEquals("Здравей, Ivan!\n", outputStream.toString())
        }

        @Test
        @DisplayName("should handle non-existent file")
        fun testGreetCommandWithNonExistentFile() {
            val parser = ArgParser("test")
            val greetCommand = GreetCommand()
            parser.subcommands(greetCommand)
            parser.parse(arrayOf("greet", "nonexistent.txt"))

            assertTrue(errorStream.toString().contains("Error: File not found"))
        }
    }
}