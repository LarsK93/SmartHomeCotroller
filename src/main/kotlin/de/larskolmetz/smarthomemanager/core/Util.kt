package de.larskolmetz.smarthomemanager.core

import org.slf4j.LoggerFactory
import java.io.*
import java.nio.file.*
import java.util.concurrent.TimeUnit


class Util {

    companion object {

        private val log = LoggerFactory.getLogger(Util::class.java)

        fun runCommand(cmd: String,
                       workingDir: File = File("."),
                       timeoutAmount: Long = 60,
                       timeoutUnit: TimeUnit = TimeUnit.SECONDS): String? {
            return ProcessBuilder(*cmd.split("\\s".toRegex()).toTypedArray())
                .directory(workingDir)
                .redirectOutput(ProcessBuilder.Redirect.PIPE)
                .redirectError(ProcessBuilder.Redirect.PIPE)
                .start().apply {
                    waitFor(timeoutAmount, timeoutUnit)
                }.inputStream.bufferedReader().readText()
        }

        fun installEq3() {
            exportResource("eq3.exp")
        }

        private fun exportResource(resourceName: String) {
            log.debug(File(".").absolutePath)
            val stream =  Util::class.java.classLoader.getResourceAsStream(resourceName)
            Files.copy(stream, Paths.get("", resourceName).toAbsolutePath(), StandardCopyOption.REPLACE_EXISTING)
            runCommand("chmod +x $resourceName")
        }

    }

}