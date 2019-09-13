package br.com.sf.so.trab01

import br.com.sf.so.trab01.collection.LinkedList
import br.com.sf.so.trab01.collection.sort.ParallelMergeSort
import org.junit.Assert
import org.junit.Test
import java.time.Duration
import java.time.Instant
import kotlin.random.Random


internal class ParallelMergeSortTest {

    @Test
    fun test() {
        for (availableThreads in 0..20) {
            println("Threads number: $availableThreads - Elapsed time: ${sort(availableThreads)}")
        }
    }

    private fun sort(threads: Int): Long {

        val li = LinkedList()
        for (i in 1..1_000) {
            li.push(Random.nextInt(0, 1_000_000))
        }

        // Apply merge Sort
        val start = Instant.now()
        ParallelMergeSort.sort(li, threads)
        val end = Instant.now()
        val timeElapsed = Duration.between(start, end).toMillis()

        var node = li.head
        while (node != null) {
            node.next?.run {
                Assert.assertTrue(node!!.value <= this.value)
            }
            node = node.next
        }

        return timeElapsed
    }

}