package CS402;
import java.io.*;
import java.util.Scanner;
public class BitWiseTest
{
    public static void main(String args[]) throws IOException
    {
        File file= new File("TestBitwise.txt");
        Scanner input = new Scanner(file);
        while(input.hasNext())
        {
            int a = input.nextInt();
            int b = input.nextInt();
            System.out.println(a&b);
            System.out.println(a|b);
            System.out.println(a^b);
            System.out.println(Integer.toBinaryString(~a));
            System.out.println(Integer.toBinaryString(a<<2));
            System.out.println(Integer.toBinaryString(a>>2));
            System.out.println(Integer.toBinaryString(a>>>2));
        }
        input.close();
        int x=(int)('1');
        System.out.println(x);
    }
}
