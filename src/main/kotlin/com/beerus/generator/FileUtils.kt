package com.beerus.generator

import java.io.File

fun traverseFileTree() {
    val systemDir = File("src/template")
    val targetFile = File("src/temp")
    if (!targetFile.exists()) {
        targetFile.mkdir()
    }

    systemDir.copyRecursively(targetFile,true)
    val fileTree: FileTreeWalk = targetFile.walk()
    fileTree.filter { it.isFile }.forEach {
        println(it.path)
//        it.copyTo(File(it.path))
    }

}

fun main(args: Array<String>) {
    traverseFileTree()
}