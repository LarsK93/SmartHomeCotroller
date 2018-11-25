package de.larskolmetz.smarthomecontroller.core.`in`

import de.larskolmetz.smarthomecontroller.core.domain.HeatingData

interface HeatingService {

    fun getTargetTemperature(): HeatingData

}
