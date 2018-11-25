package de.larskolmetz.smarthomecontroller.core

import java.io.File
import java.io.IOException
import java.util.concurrent.TimeUnit

class Util {

    companion object {
        fun runCommand(cmd: String,
                       workingDir: File = File("."),
                       timeoutAmount: Long = 60,
                       timeoutUnit: TimeUnit = TimeUnit.SECONDS): String? {
            return try {
                ProcessBuilder(*cmd.split("\\s".toRegex()).toTypedArray())
                    .directory(workingDir)
                    .redirectOutput(ProcessBuilder.Redirect.PIPE)
                    .redirectError(ProcessBuilder.Redirect.PIPE)
                    .start().apply {
                        waitFor(timeoutAmount, timeoutUnit)
                    }.inputStream.bufferedReader().readText()
            } catch (e: IOException) {
                e.printStackTrace()
                null
            }
        }
    }

}