package com.beerus.generator

import java.io.File
import java.io.InputStream
import java.io.IOException

/**
 * 生成目标文件
 */
fun generateFileTree() {
    val systemDir = File(ConfigUtils.getProperties("template_dir"))
    val fileTree: FileTreeWalk = systemDir.walkTopDown()
    fileTree.iterator().forEach {
        val targetDirectoryPath = it.path
                .replace(ConfigUtils.getProperties("template_dir")!!, ConfigUtils.getProperties("target_dir")!!)
                .replace("""${'$'}{project_name}""", ConfigUtils.getProperties("project_name")!!)
                .replace("""${'$'}{module_name}""", ConfigUtils.getProperties("module_name")!!)
                .replace("""com\company""", ConfigUtils.getProperties("group")!!.replace(".","""\"""))
        if (it.isFile) {
            val inputStream: InputStream = File(it.path).inputStream()
            val inputString = inputStream.bufferedReader().use { it.readText() }
            val targetContent = inputString
                    .replace("""${'$'}{group}""", ConfigUtils.getProperties("group")!!)
                    .replace("""${'$'}{project_name}""", ConfigUtils.getProperties("project_name")!!)
                    .replace("""${'$'}{module_name}""", ConfigUtils.getProperties("module_name")!!)
                    .replace("com.company", ConfigUtils.getProperties("group")!!)
            File(targetDirectoryPath).bufferedWriter().use { out -> out.write(targetContent) }
        }

        if (it.isDirectory) {
            val targetDir = File(targetDirectoryPath)
            if (!targetDir.exists()) {
                targetDir.mkdir()
            }
        }
    }
    println("generate project template finished!!")
    open_directory(ConfigUtils.getProperties("target_dir")!!)

}

/**
 * 打开文件目录
 */
fun open_directory(folderObj: Any) {
    folderObj?.let {
        var file: File?

        if (folderObj is String) {
            file = File(folderObj)
        } else {
            file = folderObj as File
        }
        if (!file.exists()) {
            return
        }
        var runtime: Runtime? = null
        try {
            runtime = Runtime.getRuntime()
            val systemType = System.getProperty("os.name")
            if (systemType.startsWith("Windows")) {
                runtime!!.exec("cmd /c start explorer " + file.absolutePath)
            } else if (systemType.startsWith("Mac")) {
                val cmdPrefix: String
                if (file.isDirectory) {
                    cmdPrefix = "open "
                } else {
                    cmdPrefix = "open -R "
                }
                runtime!!.exec(cmdPrefix + file.absolutePath)
            } else {
                runtime!!.exec("nautilus " + file.absolutePath)
            }
        } catch (ex: IOException) {
            ex.printStackTrace()
        } finally {
            runtime?.runFinalization()
        }
    }
}

