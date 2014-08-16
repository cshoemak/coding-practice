package testpkg;

import java.util.Date;
import java.util.Stack;

public class HanoiCs
{
  private Stack<Integer>[] stacks;

  public HanoiCs(Stack<Integer>[] stacks)
  {
    if (stacks.length != 3 || stacks[0] == null || stacks[1] == null || stacks[2] == null)
    {
      throw new IllegalArgumentException("An array of 3 stacks with non-null values required.");
    }
    this.stacks = stacks;
  }
  
  public Stack[] moveTower()
  {
    moveTowerRight(stacks[0].size());
    return stacks;
  }

  private void moveTowerRight(int n)
  {
    if (n < 1)
    {
      throw new IllegalArgumentException("n must be greater than or equal to 1.");
    }
    if (n == 1)
    {
      slide(0, 1);
      slide(1, 2);
    }
    else
    {
      moveTowerRight(n - 1);
      slide(0, 1);
      moveTowerLeft(n - 1);
      slide(1, 2);
      moveTowerRight(n - 1);
    }
  }

  private void moveTowerLeft(int n)
  {
    if (n < 1)
    {
      throw new IllegalArgumentException("n must be greater than or equal to 1.");
    }
    if (n == 1)
    {
      slide(2, 1);
      slide(1, 0);
    }
    else
    {
      moveTowerLeft(n - 1);
      slide(2, 1);
      moveTowerRight(n - 1);
      slide(1, 0);
      moveTowerLeft(n - 1);
    }
  }

  private void slide(int from, int to)
  {
    if (stacks[from].isEmpty() || (!stacks[to].isEmpty() && stacks[from].peek().intValue() > stacks[to].peek().intValue()))
    {
      throw new RuntimeException("Violated the rules of Hanoi. The volcano will now errupt.");
    }
    stacks[to].push(stacks[from].pop());
  }

  private static void printStacks(Stack[] stacks)
  {
    for (int i = 0; i < stacks.length; ++i)
    {
      boolean first = true;
      while (!stacks[i].isEmpty())
      {
        if (first)
        {
          System.out.print(i + ": ");
        }
        System.out.print((first ? "" : ",") + stacks[i].pop());
        first = false;
      }
    }
  }

  public static void main(String[] args)
  {
    Stack[] stacks = new Stack[] { new Stack<Integer>(), new Stack<Integer>(), new Stack<Integer>() };
    Stack[] stacks2 = new Stack[] { new Stack<Integer>(), new Stack<Integer>(), new Stack<Integer>() };
    for (int i = 16; i >= 0; --i)
    {
      stacks[0].push(new Integer(i));
      stacks2[0].push(new Integer(i));
    }
    System.out.println("before: ");
    printStacks(stacks2);
    Long start = new Date().getTime();
    System.out.println("\nStart: " + start);
    Stack[] movedStacks = new HanoiCs(stacks).moveTower();
    Long end = new Date().getTime();
    System.out.println("End: " + end);
    System.out.println("Total time: " + (end - start) + "ms");
    System.out.println("\nafter: ");
    printStacks(movedStacks);
  }
}
