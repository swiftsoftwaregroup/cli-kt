package com.swiftsoftwaregroup.clikt

import kotlinx.cli.*
import java.io.File

enum class Language {
    en, es, bg
}

fun readNameFromFile(file: File): String = file.readText().trim()

fun generateGreeting(name: String, language: Language): String {
    return when (language) {
        Language.en -> "Hello, $name!"
        Language.es -> "¡Hola, $name!"
        Language.bg -> "Здравей, $name!"
    }
}

class GreetCommand : Subcommand("greet", "Greet a user based on a name in a file") {
    private val file by argument(ArgType.String, description = "File containing the name")
    private val language by option(
        type = ArgType.Choice<Language>(),
        shortName = "l",
        fullName = "language",
        description = "Language for greeting (en, es, bg)"
    ).default(Language.en)

    override fun execute() {
        val name = readNameFromFile(File(file))
        val greeting = generateGreeting(name, language)
        println(greeting)
    }
}

fun main(args: Array<String>) {
    val parser = ArgParser("cli-kt")
    parser.subcommands(GreetCommand())
    parser.parse(args)
}