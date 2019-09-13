package br.com.sf.so.trab01

import br.com.sf.so.trab01.collection.LinkedList
import br.com.sf.so.trab01.collection.sort.ParallelMergeSort
import br.com.sf.so.trab01.data.DataReader
import java.time.Duration
import java.time.Instant
import java.util.*
import java.util.concurrent.Semaphore

class App {

    private val semaphore = Semaphore(1)
    private val list = LinkedList()

    fun run(availableThreads: Int): Long {

        println("================================")
        println("Using $availableThreads on sort thread pool")
        println()

        println("Reading files.")
        readFiles()
        println("Read ${list.size} numbers")

        println("Starting list sort")
        val start = Instant.now()
        ParallelMergeSort.sort(list, availableThreads)
        val end = Instant.now()
        val timeElapsed = Duration.between(start, end).toMillis()
        println("The sorting takes $timeElapsed Milliseconds.")
        println("================================")
        println()

        return timeElapsed
    }

    private fun readFiles() {
        for (i in 0..FILES_NUMBER) {
            val thread = DataReader("input/arquivo$i.txt", list, semaphore)
            thread.start()
            thread.join()
        }
    }

    companion object {
        const val FILES_NUMBER = 10
    }
}

//-Xmx1G -Xms1G -Xss1G
fun main() {

    println("Starting...")

//    println("Should generate files(S or N)? ")
//    if (readLine().equals("S", true)) {
//        print("Type the entries amount per file: ")
//        val amount = readLine()
//
//        AppDataGenerator().generate(amount!!.toInt())
//    }

    val reportData = ArrayList<Pair<Int, Long>>()
    for (availableThreads in 0..13) {
        reportData.add(Pair(availableThreads, App().run(availableThreads)))
    }

    println("REPORT\n")
    println(String.format("%10s | %s ", "Threads", "Duration"))
    for (data in reportData) {
        println(String.format("%10d | %5d ms ", data.first, data.second))
    }

}