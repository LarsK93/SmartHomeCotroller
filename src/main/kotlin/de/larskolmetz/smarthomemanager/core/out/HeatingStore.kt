package de.larskolmetz.smarthomemanager.core.out

interface HeatingStore {

    fun fetchTargetTemperatureAndValve(): Pair<Double?, Double?>?

}