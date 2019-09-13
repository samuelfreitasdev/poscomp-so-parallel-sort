package br.com.sf.so.trab01.collection

// Java program to illustrate merge sorted
// of linkedList

class LinkedList {
    internal var head: Node? = null

    // node a, b;
    class Node(var value: Int) {
        var next: Node? = null
    }

    internal fun push(new_data: Int) {
        /* allocate node */
        val newNode = Node(new_data)

        /* link the old list off the new node */
        newNode.next = head

        /* move the head to point to the new node */
        head = newNode
    }

    override fun toString(): String {
        val sb = StringBuffer()

        var headRef = head
        while (headRef != null) {
            sb.append(headRef.value.toString()).append(" ")
            headRef = headRef.next
        }

        return sb.toString()
    }
}