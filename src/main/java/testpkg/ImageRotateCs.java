package testpkg;

public class ImageRotateCs
{
  public void rotate90(byte[][][] mat, int level)
  {
    if (mat == null || mat.length != mat[0].length || level > mat.length / 2)
    {
      throw new IllegalArgumentException("non-null, nxn matrix required with appropriate level (intially pass 0).");
    }
    int n = mat.length;
    int length = n - 2 * level;
    if (length > 1)
    {
      for (int i = 0; i < length - 1; ++i)
      {
        byte[] top = mat[level][level + i];
        byte[] right = mat[level + i][n - 1 - level];
        byte[] bottom = mat[n - 1 - level][n - 1 - level - i];
        byte[] left = mat[n - 1 - level - i][level];
        mat[level][level + i] = left;
        mat[level + i][n - 1 - level] = top;
        mat[n - 1 - level][n - 1 - level - i] = right;
        mat[n - 1 - level - i][level] = bottom;
      }
      rotate90(mat, level + 1);
    }
  }

  public static void printMat(byte[][][] mat)
  {
    for (int i = 0; i < mat.length; ++i)
    {
      StringBuffer sb = new StringBuffer();
      for (int j = 0; j < mat[0].length; ++j)
      {
        sb.append((j == 0 ? "" : "\t") + (int) mat[i][j][0]);
      }
      System.out.println(sb.toString());
    }
  }

  public static void constructMat(byte[][][] mat, byte[][] values)
  {
    int valueIncrementer = 0;
    for (int i = 0; i < mat.length; ++i)
    {
      for (int j = 0; j < mat[0].length; ++j)
      {
        mat[i][j] = values[valueIncrementer++];
      }
    }
  }

  public static void main(String[] args)
  {
    byte[][][] mat = new byte[1][1][1];
    mat[0][0][0] = 0x05;
    new ImageRotateCs().rotate90(mat, 0);
    //    printMat(mat);
    mat = new byte[5][5][5];
    constructMat(mat, new byte[][] { { 0x01 }, { 0x02 }, { 0x03 }, { 0x04 }, { 0x05 }, { 0x06 }, { 0x07 }, { 0x08 }, { 0x09 },
                                    { 0x0a }, { 0x0b }, { 0x0c }, { 0x0d }, { 0x0e }, { 0x0f }, { 0x10 }, { 0x11 }, { 0x12 },
                                    { 0x13 }, { 0x14 }, { 0x15 }, { 0x16 }, { 0x17 }, { 0x18 }, { 0x19 } });
    printMat(mat);
    new ImageRotateCs().rotate90(mat, 0);
    System.out.println("----------------------");
    printMat(mat);
  }
}
