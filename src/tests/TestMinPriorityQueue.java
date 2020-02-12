package tests;

import utils.MinPriorityQueue;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class TestMinPriorityQueue {

  MinPriorityQueue<Integer> queue = new MinPriorityQueue();

  @Test
  public void initializedQueueisOfSizeZero(){
    assertThat(queue.size(),is(0));
  }

  @Test
  public void thereIsOneElementInArrayWhenOneElementAdded(){
    queue.add(20);
    assertThat(queue.size(), is(1));
  }

  @Test
  public void queueHasTheCorrectSize(){
    queue.add(15);
    queue.add(10);
    queue.add(11);

    assertThat(queue.size(), is(3));
  }

  @Test
  public void elementsAddedAndRemovedFromCorrectPositions(){
    queue.add(20);

    queue.add(15);
    queue.add(11);

    queue.add(10);
    queue.add(4);
    queue.add(8);
    queue.add(6);
    queue.add(25);

    assertThat(queue.size(), is(8));
    assertThat(queue.remove(), is(4));
    assertThat(queue.size(), is(7));
  }

  @Test
  public void arrayShouldBeEmpty(){
    assertThat(queue.isEmpty(), is(true));
  }


}
