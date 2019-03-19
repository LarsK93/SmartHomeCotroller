package de.larskolmetz.smarthomemanager.storage.repositories

import de.larskolmetz.smarthomemanager.core.Util
import de.larskolmetz.smarthomemanager.core.out.HeatingStore
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.io.File
import java.lang.NumberFormatException

@Component
class HeatingRepository : HeatingStore {

    private val log = LoggerFactory.getLogger(this.javaClass)

    @Value("\${heating.mac_addr}")
    val heatingMacAddr: String? = null

    override fun fetchTargetTemperatureAndValve(): Pair<Double, Double>? {
        return try {
            val heatingMacAddr = heatingMacAddr!!
            val status = Util.runCommand("./eq3.exp $heatingMacAddr status")
            log.debug(status)
            val targetTemperature = parseExpResult(status, "Temperature:", "°C")
            val valve = parseExpResult(status, "Valve:", "%")

            Pair(targetTemperature!!, valve!!)
        } catch (e: KotlinNullPointerException) {
            log.error("MAC-Address of heating is not defined or command returned invalid result. MAC: $heatingMacAddr")
            e.printStackTrace()

            null
        }
    }

    fun parseExpResult(cmdOutput: String?, searchStringStart: String, searchStringEnd: String): Double? {
        return try {
            val startIndex = cmdOutput!!.indexOf(searchStringStart) + searchStringStart.length
            val endIndex = cmdOutput.indexOf(searchStringEnd)

            val curr = cmdOutput.substring(startIndex, endIndex).replace("\t", "")

            curr.toDouble()
        }
        catch (e: KotlinNullPointerException) {
            log.error("Heating status could not be fetched: Command did not deliver result.")
            e.printStackTrace()

            null
        }
        catch (e: NumberFormatException) {
            log.error("Heating status could not be fetched: Command result does not contain valid value.")
            e.printStackTrace()

            null
        }
        catch (e: StringIndexOutOfBoundsException) {
            log.error("Heating status could not be fetched: Command result does not contain valid boundaries for value.")
            e.printStackTrace()

            null
        }
    }

}