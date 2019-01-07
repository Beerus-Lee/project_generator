package com.beerus.generator

import java.io.File
import java.io.InputStream

object ConfigUtils {
    private val properties: HashMap<String, String> = initConfigProperties()

    private fun initConfigProperties(): HashMap<String, String> {
        println("start init config properties")
        val propertyMap: HashMap<String, String> = HashMap()
        val inputStream: InputStream = File("src/main/resources/config.properties").inputStream()
        inputStream.bufferedReader().useLines { lines ->
            lines.forEach {
                val row = it.split("=")
                propertyMap.put(row[0].trim(), row[1].trim())
            }
        }
        return propertyMap
    }

    fun getProperties(key: String): String? {
        return properties.get(key)?.replace("""/""","""/""")
    }


}

