package de.larskolmetz.smarthomecontroller

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class SmartHomeControllerApplication

fun main(args: Array<String>) {
    SpringApplication.run(SmartHomeControllerApplication::class.java, *args)
}