package br.com.sf.so.trab01.data

import br.com.sf.so.trab01.collection.LinkedList
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.util.concurrent.Semaphore


class DataReader(
    filename: String,
    private val list: LinkedList,
    private val semaphore: Semaphore
) : Thread() {

    private val file = File(filename)
    private val fr = FileReader(file)
    private val br = BufferedReader(fr)

    override fun run() {
        do {
            val value = br.readLine()
            value?.run {
                try {
                    semaphore.acquire()
                    list.push(value.toInt())
                } finally {
                    semaphore.release()
                }
            }
        } while (value != null)
    }

}