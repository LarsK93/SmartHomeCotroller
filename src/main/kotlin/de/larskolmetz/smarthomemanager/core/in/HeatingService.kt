package de.larskolmetz.smarthomemanager.core.`in`

import de.larskolmetz.smarthomemanager.core.domain.HeatingData

interface HeatingService {

    fun getTargetTemperatureAndValve(): HeatingData

}
