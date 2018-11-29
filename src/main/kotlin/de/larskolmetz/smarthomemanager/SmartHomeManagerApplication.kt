package de.larskolmetz.smarthomemanager

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class SmartHomeManagerApplication

fun main(args: Array<String>) {
    SpringApplication.run(SmartHomeManagerApplication::class.java, *args)
}