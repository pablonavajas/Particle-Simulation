package utils;

public class MinPriorityQueue<T extends Comparable<T>> {

  private Object[] queue;
  final int INITIAL_SIZE = 2;
  private int nextEmpty;

  /**
   * Creates an empty queue.
   */
  public MinPriorityQueue() {
    queue = new Object[INITIAL_SIZE];
    nextEmpty = 1;
  }

  /**
   * Returns the number of elements currently in the queue.
   */
  public int size() {
    return nextEmpty-1;
  }

  private void compareToParent(int childPos) {
    if (childPos <= 1) {
      if (childPos < 1) {
        throw (new IndexOutOfBoundsException("Went below 1"));
      } return;
    }

    int parentPos = childPos / 2;
    T child = (T) queue[childPos];
    T parent = (T) queue[parentPos];
    if (child.compareTo(parent) > 0) {
      queue[parentPos] = child;
      queue[childPos] = parent;
      compareToParent(parentPos);
    }
  }

  private void extendQ(){
    Object[] newQ = new Object[queue.length * 2];
    System.arraycopy(queue, 0, newQ, 0, queue.length);
    queue = newQ;
  }

  /**
   * Adds elem to the queue.
   */
  public void add(T elem) {
    if (queue.length <= nextEmpty) {
      extendQ();
    }

    queue[nextEmpty] = elem;
    int index = nextEmpty;
    compareToParent(index);
    nextEmpty++;
  }

  private void compareToChildren(int parentPos){
    T parent = (T) queue[parentPos];

    int leftChildPos = parentPos * 2;
    if (queue[leftChildPos] == null) {
      return;
    }
    T leftChild = (T) queue[leftChildPos];

    int rightChildPos = parentPos * 2 + 1;
    if (queue[rightChildPos] == null) {
      if (leftChild.compareTo(parent) > 0) {
        queue[parentPos] = leftChild;
        queue[leftChildPos] = parent;
      }
      return;
    }

    T rightChild = (T) queue[rightChildPos];
    int toSwapPos = leftChildPos;
    if (rightChild.compareTo(leftChild) > 0) {
      toSwapPos = rightChildPos;
    }
    T childToSwap = (T) queue[toSwapPos];
    if (childToSwap.compareTo(parent) > 0) {
      queue[parentPos] = childToSwap;
      queue[toSwapPos] = parent;
      compareToChildren(toSwapPos);
    }
  }

  /**
   * Removes, and returns, the element at the front of the queue.
   */
  public T remove() {
    if (nextEmpty == 1) {
      return null;
    }

    T elem = (T) queue[1];

    nextEmpty = nextEmpty - 1;
    queue[1] = queue[nextEmpty];
    queue[nextEmpty] = null;
    compareToChildren(1);
    return elem;
  }

  /**
   * Returns true if the queue is empty, false otherwise.
   */
  public boolean isEmpty() {
    return nextEmpty == 1;
  }
}
