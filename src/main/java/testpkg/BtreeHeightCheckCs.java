package testpkg;

public class BtreeHeightCheckCs
{
  private class Node
  {
    Node left = null;
    Node right = null;
    int data;

    Node(int data)
    {
      this.data = data;
    }
  }

  private class BtreeWrapper
  {
    int height = 0;
    boolean balanced = true;
  }

  public BtreeWrapper checkBalance(Node rootNode)
  {
    if (rootNode == null)
    {
      throw new IllegalArgumentException("rootNode must be non-null");
    }
    BtreeWrapper leftResult = new BtreeWrapper();
    if (rootNode.left != null)
    {
      leftResult = checkBalance(rootNode.left);
    }
    BtreeWrapper rightResult = new BtreeWrapper();
    if (rootNode.right != null)
    {
      rightResult = checkBalance(rootNode.right);
    }
    BtreeWrapper rootResult = new BtreeWrapper();
    rootResult.height = Math.max(leftResult.height, rightResult.height) + 1;
    rootResult.balanced = leftResult.balanced && rightResult.balanced && 
                          Math.abs(leftResult.height - rightResult.height) <= 1;
    return rootResult;
  }

  public static void main(String[] args)
  {
    BtreeHeightCheckCs hc = new BtreeHeightCheckCs();
    Node root = hc.new Node(1);
    Node two = hc.new Node(2);
    Node three = hc.new Node(3);
    BtreeWrapper result = hc.checkBalance(root);
    System.out.println("Single node tree:\n\theight: " + result.height + "\n\tbalanced: " + result.balanced);
    root.left = two;
    result = hc.checkBalance(root);
    System.out.println("Two node tree:\n\theight: " + result.height + "\n\tbalanced: " + result.balanced);
    root.left.right = three;
    result = hc.checkBalance(root);
    System.out.println("Three node unbalanced tree:\n\theight: " + result.height + "\n\tbalanced: " + result.balanced);
  }
}
