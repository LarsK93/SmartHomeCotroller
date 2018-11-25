package de.larskolmetz.smarthomecontroller.storage.repositories

import de.larskolmetz.smarthomecontroller.core.Util
import de.larskolmetz.smarthomecontroller.core.out.HeatingStore
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.io.File

@Component
class HeatingRepository : HeatingStore {

    private val log = LoggerFactory.getLogger(this.javaClass)

    @Value("\${heating.mac_addr}")
    val heatingMacAddr: String? = null

    override fun fetchTargetTemperature(): Double? {
        if (heatingMacAddr != null) {
            val status = Util.runCommand("./eq3.exp $heatingMacAddr status", File("src/main/resources"))
            log.debug(status)
            return null
        }
        else {
            log.error("MAC-Address of heating is not defined.")
            return null
        }
    }

}