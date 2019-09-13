package br.com.sf.so.trab01.collection.sort

import br.com.sf.so.trab01.collection.LinkedList
import java.util.concurrent.RecursiveTask

class ParallelMergeSort(
    private val node: LinkedList.Node?,
    private val availableThreads: Int
) : RecursiveTask<LinkedList.Node?>() {

    override fun compute(): LinkedList.Node? {
        if (availableThreads <= 1) {
            return sort(node)
        } else {
            if (node?.next == null) {
                return node
            }

            // get the middle of the list
            val middle = getMiddle(node)
            val nextOfMiddle = middle!!.next

            // set the next of middle node to null
            middle.next = null

            val left = ParallelMergeSort(node, availableThreads - 1)
            val right = ParallelMergeSort(nextOfMiddle, availableThreads - 1)

            left.fork()

            return merge(left.join(), right.compute())
        }
    }

    private fun sort(head: LinkedList.Node?): LinkedList.Node? {
        // Base case : if head is null
        if (head?.next == null) {
            return head
        }

        // get the middle of the list
        val middle = getMiddle(head)
        val nextOfMiddle = middle!!.next

        // set the next of middle node to null
        middle.next = null

        // Apply mergeSort on left list
        val left = sort(head)

        // Apply mergeSort on right list
        val right = sort(nextOfMiddle)

        // Merge the left and right lists
        return merge(left, right)
    }

    private fun merge(left: LinkedList.Node?, right: LinkedList.Node?): LinkedList.Node? {
        val result: LinkedList.Node?
        /* Base cases */
        if (left == null)
            return right
        if (right == null)
            return left

        /* Pick either left or right, and recur */
        if (left.value <= right.value) {
            result = left
            result.next = merge(left.next, right)
        } else {
            result = right
            result.next = merge(left, right.next)
        }
        return result
    }

    // Utility function to get the middle of the linked list
    private fun getMiddle(h: LinkedList.Node?): LinkedList.Node? {
        // Base case
        if (h == null)
            return h

        var slow = h
        var fast = h.next

        // Move fastptr by two and slow ptr by one
        // Finally slowptr will point to middle node
        while (fast != null) {
            fast = fast.next
            if (fast != null) {
                slow = slow!!.next
                fast = fast.next
            }
        }
        return slow
    }

    companion object {
        fun sort(list: LinkedList, availableThreads: Int) {
            list.head = ParallelMergeSort(list.head, availableThreads).compute()
        }
    }

}