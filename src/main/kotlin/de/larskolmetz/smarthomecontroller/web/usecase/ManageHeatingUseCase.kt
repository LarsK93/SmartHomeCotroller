package de.larskolmetz.smarthomecontroller.web.usecase

import de.larskolmetz.smarthomecontroller.core.domain.HeatingData
import de.larskolmetz.smarthomecontroller.core.`in`.HeatingService
import de.larskolmetz.smarthomecontroller.core.out.HeatingStore
import org.springframework.stereotype.Component

@Component
class ManageHeatingUseCase(val heatingStore: HeatingStore) : HeatingService {

    override fun getTargetTemperature(): HeatingData {
        return HeatingData(heatingStore.fetchTargetTemperature())
    }

}
