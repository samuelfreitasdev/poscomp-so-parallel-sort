package br.com.sf.so.trab01.collection.sort

import br.com.sf.so.trab01.collection.LinkedList

class ParallelMergeSort : MergeSort() {

    private fun mergeSort(
        node: LinkedList.Node?,
        availableThreads: Int
    ): LinkedList.Node? {

        if (availableThreads <= 1) {
            return mergeSort(node)
        } else {

            if (node?.next == null) {
                return node
            }

            // get the middle of the list
            val middle = getMiddle(node)
            val nextOfMiddle = middle!!.next

            // set the next of middle node to null
            middle.next = null

            var left: LinkedList.Node? = null
            var right: LinkedList.Node? = null

            // Apply mergeSort on left list
            val leftThread = object : Thread() {
                override fun run() {
                    left = mergeSort(node, availableThreads - 1)
                }
            }

            // Apply mergeSort on right list
            val rightThread = object : Thread() {
                override fun run() {
                    right = mergeSort(nextOfMiddle, availableThreads - 1)
                }
            }

            leftThread.start()
            rightThread.start()

            try {
                leftThread.join()
                rightThread.join()
            } catch (uncaughtExceptionHandler: InterruptedException) {
                uncaughtExceptionHandler.printStackTrace()
            }

            // Merge the left and right lists
            return sortedMerge(left, right)
        }
    }

    companion object {
        fun sort(list: LinkedList, availableThreads: Int) {
            list.head = ParallelMergeSort().mergeSort(list.head, availableThreads)
        }
    }

}