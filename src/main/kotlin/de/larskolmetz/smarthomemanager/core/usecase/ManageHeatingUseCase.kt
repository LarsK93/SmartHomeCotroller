package de.larskolmetz.smarthomemanager.core.usecase

import de.larskolmetz.smarthomemanager.core.domain.HeatingData
import de.larskolmetz.smarthomemanager.core.`in`.HeatingService
import de.larskolmetz.smarthomemanager.core.out.HeatingStore
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class ManageHeatingUseCase(val heatingStore: HeatingStore) : HeatingService {

    private val log = LoggerFactory.getLogger(this.javaClass)

    override fun getTargetTemperatureAndValve(): HeatingData {
        return try {
            val (targetTemperature, valve) = heatingStore.fetchTargetTemperatureAndValve()!!

            HeatingData(targetTemperature, valve)
        } catch (e: KotlinNullPointerException) {
            log.error("HeatingStore returned invalid target temperature and valve values.")
            e.printStackTrace()

            HeatingData(null, null)
        }
    }

}
