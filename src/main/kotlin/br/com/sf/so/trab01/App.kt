package br.com.sf.so.trab01

import br.com.sf.so.trab01.collection.LinkedList
import br.com.sf.so.trab01.collection.sort.ParallelMergeSort
import java.util.concurrent.TimeUnit
import kotlin.random.Random

//-Xmx1G -Xms1G -Xss1G
fun main() {

    val li = LinkedList()

    for (i in 1..10_000) {
        li.push(Random.nextInt(0, 1_000_000))
    }

    println("Linked List without sorting is :")
    println(li.toString())

    // Apply merge Sort
//    MergeSort.sort(li)
    val start = System.currentTimeMillis()
    ParallelMergeSort.sort(li, 0)
    val end = System.currentTimeMillis()

    println(TimeUnit.MILLISECONDS.toSeconds(end - start))

    print("\n Sorted Linked List is: \n")
    println(li.toString())
}