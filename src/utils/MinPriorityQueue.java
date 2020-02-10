package utils;

public class MinPriorityQueue<T extends Comparable<T>> {

  /**
   * Class variables
   **/
  public T queue[];

  /**
   * Creates an empty queue.
   */
  public MinPriorityQueue() {
    // TODO implement the constructor
    queue = new T[3];
  }

  /**
   * Returns the number of elements currently in the queue.
   */
  public int size() {
    return queue.length;
  }

  /**
   * Adds elem to the queue.
   */
  public void add(T elem) {
    // TODO implement this method
    if (queue.length == 0) {
      queue[1] = elem;
    } else {
      queue[size()] = elem;
    }
  }

  /**
   * Removes, and returns, the element at the front of the queue.
   */
  public T remove() {
    // TODO implement this method
    return null;
  }

  /**
   * Returns true if the queue is empty, false otherwise.
   */
  public boolean isEmpty() {
    if (queue.length == 0)
      return true;
    return false;
  }
}
