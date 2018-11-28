package de.larskolmetz.smarthomecontroller.core.out

interface HeatingStore {

    fun fetchTargetTemperatureAndValve(): Pair<Double?, Double?>?

}