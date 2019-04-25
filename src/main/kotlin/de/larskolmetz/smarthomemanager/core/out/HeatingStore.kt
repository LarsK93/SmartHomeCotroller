package de.larskolmetz.smarthomemanager.core.out

interface HeatingStore {

    fun fetchTargetTemperatureAndValve(tryCount: Int = 0): Pair<Double?, Double?>?

}