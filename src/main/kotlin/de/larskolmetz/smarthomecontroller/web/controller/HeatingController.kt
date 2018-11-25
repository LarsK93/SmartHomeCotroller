package de.larskolmetz.smarthomecontroller.web.controller

import de.larskolmetz.smarthomecontroller.core.domain.HeatingData
import de.larskolmetz.smarthomecontroller.core.`in`.HeatingService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
class HeatingController(val heatingService: HeatingService) {

    @GetMapping("/heating/targettemperature")
    @ResponseBody
    fun getTargetTemperature(): ResponseEntity<HeatingData> {
        val data = heatingService.getTargetTemperature()
        if (data.targetTemperature != null) {
            return ResponseEntity(data, HttpStatus.ACCEPTED)
        }
        else {
            return ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

}