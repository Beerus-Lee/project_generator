package com.beerus.generator

import java.io.File
import java.io.InputStream

fun traverseFileTree() {
    val systemDir = File("src/template")
    val targetFile = File("src/temp")
    val fileTree: FileTreeWalk = systemDir.walkTopDown()
    fileTree.iterator().forEach {
        println(it.name+"   "+it.path)
        val targetDirectoryPath = it.path.replace("template","temp").replace("demo","beerus")
        if(it.isFile) {
            val inputStream: InputStream = File(it.path).inputStream()
            val inputString = inputStream.bufferedReader().use { it.readText() }
            File(targetDirectoryPath).bufferedWriter().use{ out-> out.write(inputString) }
        }

       if(it.isDirectory) {
           val targetDir = File(targetDirectoryPath)
            if(!targetDir.exists()) {
                targetDir.mkdir()
            }
        }

    }
}

fun main(args: Array<String>) {
    traverseFileTree()
}