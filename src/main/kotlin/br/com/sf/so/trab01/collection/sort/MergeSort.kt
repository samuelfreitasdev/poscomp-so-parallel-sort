package br.com.sf.so.trab01.collection.sort

import br.com.sf.so.trab01.collection.LinkedList
import java.util.concurrent.RecursiveAction

open class MergeSort {

    internal fun sortedMerge(a: LinkedList.Node?, b: LinkedList.Node?): LinkedList.Node? {
        var result: LinkedList.Node? = null
        /* Base cases */
        if (a == null)
            return b
        if (b == null)
            return a

        /* Pick either a or b, and recur */
        if (a.value <= b.value) {
            result = a
            result.next = sortedMerge(a.next, b)
        } else {
            result = b
            result.next = sortedMerge(a, b.next)
        }
        return result
    }

    internal fun mergeSort(head: LinkedList.Node?): LinkedList.Node? {
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
        val left = mergeSort(head)

        // Apply mergeSort on right list
        val right = mergeSort(nextOfMiddle)

        // Merge the left and right lists
        return sortedMerge(left, right)
    }

    // Utility function to get the middle of the linked list
    internal fun getMiddle(h: LinkedList.Node?): LinkedList.Node? {
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
        fun sort(list: LinkedList) {
            list.head = MergeSort().mergeSort(list.head)
        }
    }

}
