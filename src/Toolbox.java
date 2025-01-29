import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class Toolbox {

 /**
   * Removes an element from an array of strings at the specified index, padding with nulls at the end.
   *
   * @param array the array of strings to modify
   * @param index the index of the element to remove
   * @throws IllegalArgumentException if the array is null or the index is out of bounds
   */
  // Time Complexity: O(N)
  // Space Complexity: O(1)
  public static void removeElementInPlace(String[] array, int index) {
    if (array == null || index < 0 || index >= array.length) {
      throw new IllegalArgumentException("Array cannot be null and index must be within bounds.");
    }

    for (int i = index; i < array.length - 1; i++) {
      // move the element at the next position to the current position
      array[i] = array[i + 1];
    }
    
    // setting the last element to null
    array[array.length - 1] = null;
  }

  /**
   * Adds an element to an array of strings at a specified location in-place, evicting the last value.
   *
   * @param array the array of strings to modify
   * @param index the index at which to add the new element
   * @param value the value to add
   * @throws IllegalArgumentException if the array is null or the index is out of bounds
   */
  // Time Complexity: O(N)
  // Space Complexity: O(1)
  public static void addElementInPlace(String[] array, int index, String value) {
    if (array == null || index < 0 || index >= array.length) {
      throw new IllegalArgumentException("Array cannot be null and index must be within bounds.");
    }

    for (int i = array.length - 1; i > index; i--) {
      // shift each element one position to the right
      array[i] = array[i - 1];
    }
    
    // inserting the new value at the specified index
    array[index] = value;
  }

  /**
   * Finds the tail of a singly linked list given the head.
   *
   * @param head the head node of the singly linked list
   * @return the tail node of the list, or null if the list is empty
   * @throws IllegalArgumentException if the head is null
   */
  // Time Complexity: O(N)
  // Space Complexity: O(1)
  public static SingleNode findTail(SingleNode head) {
    if (head == null) {
      throw new IllegalArgumentException("Head cannot be null.");
    }

    // starting with the head of the singly linked list
    while (head.next != null) {
      head = head.next;  // move the 'head' pointer to the next node in the list
    }

    return head;
  }

  /**
   * Finds the head of a doubly linked list given the tail.
   *
   * @param tail the tail node of the doubly linked list
   * @return the head node of the list, or null if the list is empty
   * @throws IllegalArgumentException if the tail is null
   */
  // Time Complexity: O(N)
  // Space Complexity: O(1)
  public static DoubleNode findHead(DoubleNode tail) {
    if (tail == null) {
      throw new IllegalArgumentException("Tail cannot be null.");
    }

    // traverse the list backwards using the 'prev' reference
    while (tail.prev != null) {
      tail = tail.prev; // moving to the previous node in the list
    }

    return tail;
  }

  /**
   * Counts the occurrences of values in a linked list.
   *
   * @param head the head node of the linked list
   * @return a map where the keys are the values in the list, and the values are the counts of occurrences
   * @throws IllegalArgumentException if the head is null
   */
  // Time Complexity: O(N)
  // Space Complexity: O(N)
  public static Map<Integer, Integer> countOccurrences(SingleNode head) {
    if (head == null) {
      throw new IllegalArgumentException("Head cannot be null.");
    }

    // creating a HashMap to store the count of each value in the linked list
    Map<Integer, Integer> map = new HashMap<>();
    
    // traversing the linked list until the end (head is null)
    while (head != null) {
        // get the current value (head.value) and update its count in the map
        // map.getOrDefault() retrieves the current count, or 0 if not found
        // then increment the count by 1 and put it back in the map
        map.put(head.data, map.getOrDefault(head.data, 0) + 1); 

        // move to the next node in the linked list
        head = head.next;
    }
    
    // return the map containing the counts of all values in the linked list
    return map;
  }

  /**
   * Removes a node from a doubly linked list.
   *
   * @param node the node to remove
   * @throws IllegalArgumentException if the node is null
   */
  // Time Complexity: O(1)
  // Space Complexity: O(1)
  public static void removeNode(DoubleNode node) {
    if (node == null) {
      throw new IllegalArgumentException("Node cannot be null.");
    }
    
     // update the previous node's next reference, if any
    if (node.prev != null) {
      node.prev.next = node.next;
    }

    // update the next node's previous reference, if any
    if (node.next != null) {
      node.next.prev = node.prev;
    }
  }

  /**
   * Finds the nth element in a singly linked list.
   *
   * @param head the head node of the singly linked list
   * @param n the index of the element to find (0-based)
   * @return the nth node, or null if the index is out of bounds
   * @throws IllegalArgumentException if the head is null or n is negative
   */
  // Time Complexity: O(N)
  // Space Complexity: O(1)
  public static SingleNode findNthElement(SingleNode head, int n) {
    if (head == null || n < 0) {
      throw new IllegalArgumentException("Head cannot be null and n cannot be negative.");
    }

    // traverse the list until we reach the nth element
    while (n > 0 && head != null) {
      head = head.next; // move to the next node
      n--; // decrease the index
    }

    // return the nth node, or null if out of bounds
    return head;
  }

  /**
   * Inserts a new node into a singly linked list given a pointer to a node in the middle of the list.
   *
   * @param node the node before which the new node is to be inserted
   * @param newNode the new node to insert
   * @throws IllegalArgumentException if either node or newNode is null
   */
  // Time Complexity: O(1)
  // Space Complexity: O(1)
  public static void insertNode(SingleNode node, SingleNode newNode) {
    if (node == null || newNode == null) {
      throw new IllegalArgumentException("Node and newNode cannot be null.");
    }

    // insert newNode after node
    newNode.next = node.next; // set newNode's next to node's next
    node.next = newNode; // set node's next to newNode, inserting it into the list
  }

  /**
   * Rotates a queue to the left by the specified number of positions in-place.
   * 
   * The first k elements of the queue are moved to the end, preserving the order
   * of all elements.
   *
   * Example:
   * Given a queue [1, 2, 3, 4, 5] and k = 2, the result will be [3, 4, 5, 1, 2].
   *
   * @param queue the queue to rotate
   * @param k the number of positions to rotate to the left
   * @throws IllegalArgumentException if the queue is null or k is negative
   */
  // Time Complexity: O(N)
  // Space Complexity: O(1)
  public static void rotateQueueLeft(Queue<Integer> queue, int k) {
    if (queue == null || k < 0) {
      throw new IllegalArgumentException("Queue cannot be null and k cannot be negative.");
    }
    
    // rotate the queue k times
    for (int i = 0; i < k; i++) {
      // remove the first element and add it to the end of the queue
      Integer temp = queue.poll(); // poll removes the head of the queue
      queue.offer(temp); // offer adds the element to the end of the queue
    }
  }

  /**
   * Checks if a string has balanced parentheses using a stack.
   * 
   * A string is considered to have balanced parentheses if each opening parenthesis
   * '(' has a corresponding closing parenthesis ')', and the parentheses are correctly nested.
   *
   * Example:
   * - Input: "(()())" -> Returns true
   * - Input: "(()" -> Returns false
   * - Input: ")" -> Returns false
   *
   * @param input the string to check
   * @return true if the string has balanced parentheses, false otherwise
   * @throws IllegalArgumentException if the input string is null
   */
  // Time Complexity: O(N)
  // Space Complexity: O(N)
  public static boolean hasBalancedParentheses(String input) {
    if (input == null) {
      throw new IllegalArgumentException("Input string cannot be null.");
    }

    // creating a stack to store opening parentheses
    Stack<Character> stack = new Stack<>();

    // loop through each character in the string
    for (char ch : input.toCharArray()) {
      // if the character is an opening parenthesis, push it onto the stack
      if (ch == '(') {
        stack.push(ch);
      }
      // if the character is a closing parenthesis, check the stack
      else if (ch == ')') {
        // If the stack is empty, we have a mismatch (no opening parenthesis)
        if (stack.isEmpty()) {
          return false;
        }
        // pop the matching opening parenthesis from the stack
        stack.pop();
      }
    }

    // If the stack is empty, the parentheses are balanced; otherwise, they are not
    return stack.isEmpty();
  }

}