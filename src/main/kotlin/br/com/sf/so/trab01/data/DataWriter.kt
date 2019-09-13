package br.com.sf.so.trab01.data

import br.com.sf.so.trab01.collection.LinkedList
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter

class DataWriter(
    private val filename: String,
    private val list: LinkedList
) {

    fun write() {
        val file = File(filename)
        if (file.exists()) {
            file.delete()
        }

        val fw = FileWriter(filename)
        val bw = BufferedWriter(fw)

        var headRef = list.head
        while (headRef != null) {
            bw.write(headRef.value.toString())
            bw.newLine()

            headRef = headRef.next
        }

        bw.flush()
        fw.flush()

        bw.close()
        fw.close()
    }
}