package com.beerus.generator

import java.io.File
import java.io.InputStream

fun traverseFileTree() {
    val systemDir = File("src/template")
    val targetFile = File("src/temp")
    if (!targetFile.exists()) {
        targetFile.mkdir()
    }

    systemDir.copyRecursively(targetFile,true)
    val fileTree: FileTreeWalk = targetFile.walkTopDown()
    fileTree.iterator().forEach {
       //println(it.path)
//        it.copyTo(File(it.path))

        if(it.isFile) {
            val inputStream: InputStream = File(it.path).inputStream()
            val inputString = inputStream.bufferedReader().use { it.readText() }
            //println(inputString)
        }
       if(it.isDirectory) {
            println(it.name)
        }
    }
}

fun main(args: Array<String>) {
    traverseFileTree()
}