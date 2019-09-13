package br.com.sf.so.trab01

import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import kotlin.random.Random

class AppDataGenerator {

    fun generate(entriesAmount: Int) {
        println("Generating data...")
        for (i in 0..10) {
            buildData(i, entriesAmount)
        }
        println("Finished.")
    }

    private fun buildData(fileIndex: Int, entriesAmount: Int) {
        val filename = "input/arquivo$fileIndex.txt";

        println("Build file '$filename'.")

        val file = File(filename);
        if (file.exists()) {
            file.delete()
        }

        val fileWriter = FileWriter(filename)
        val bufferWriter = BufferedWriter(fileWriter)

        for (i in 0..entriesAmount) {
            val number = Random.nextInt(0, 1_000_000)
            bufferWriter.write(number.toString())
            bufferWriter.newLine()
        }

        bufferWriter.flush()
        fileWriter.flush()

        bufferWriter.close()
        bufferWriter.close()
    }

}

fun main() {
    AppDataGenerator().generate(1_000)
}