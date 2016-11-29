package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {

	LLNode<E> head;
	LLNode<E> tail;
	private int size;

	/** Create a new empty LinkedList */
	MyLinkedList() {
		head = new LLNode<>(null);
		tail = new LLNode<>(null);
		head.next = tail;
		tail.prev = head;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element) {
		LLNode<E> newNode = new LLNode<>(element, tail);
		tail.prev.next = newNode;
		tail.prev = newNode;

		size++;

		return true;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) {
		return getNodeAtIndex(index).data;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element) {
	    checkNullElement(element);
		checkIndexInBounds(index);

		LLNode<E> nodeAtIndex = getNodeAtIndex(index);
		LLNode<E> newNode = new LLNode<>(element, nodeAtIndex);
		nodeAtIndex.prev.next = newNode;
		nodeAtIndex.prev = newNode;

		size++;
	}

	/** Return the size of the list */
	public int size() {
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) {
	    checkIndexInBounds(index);

		LLNode<E> nodeAtIndex = getNodeAtIndex(index);
		nodeAtIndex.prev.next = nodeAtIndex.next;
		nodeAtIndex.next.prev = nodeAtIndex.prev;

		size--;

		return nodeAtIndex.data;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) {
	    checkNullElement(element);
	    checkIndexInBounds(index);

	    LLNode<E> nodeAtIndex = getNodeAtIndex(index);
	    E oldValue = nodeAtIndex.data;
	    nodeAtIndex.data = element;

		return oldValue;
	}

    private LLNode<E> getNodeAtIndex(int index) {
        checkIndexInBounds(index);

        int currentIndex = 0;
        LLNode<E> currentNode = head.next;
        while (currentIndex != index) {
            currentNode = currentNode.next;
            currentIndex++;
        }

        return currentNode;
    }

    private void checkNullElement(E element) {
        if (element == null) {
            throw new NullPointerException();
        }
    }

    private void checkIndexInBounds(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
    }
}

class LLNode<E>
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

    LLNode(E e) {
        this.data = e;
        this.prev = null;
        this.next = null;
    }

	LLNode(E e, LLNode<E> nextNode) {
		this.data = e;

		this.next = nextNode;
		this.prev = nextNode.prev;
	}
}
