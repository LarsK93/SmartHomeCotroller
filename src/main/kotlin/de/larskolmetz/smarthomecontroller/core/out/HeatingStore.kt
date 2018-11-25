package de.larskolmetz.smarthomecontroller.core.out

interface HeatingStore {

    fun fetchTargetTemperature(): Double?

}