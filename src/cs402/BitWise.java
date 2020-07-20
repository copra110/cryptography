package CS402;
import java.io.*;
import java.util.Scanner;
public class BitWise {
    public static void main(String args[]) throws IOException
    {
        File file = new File("TestBitwise.txt");
        if(file.exists())
        {
            System.out.print("File already exists !");
            System.exit(0);
        }
        Scanner in = new Scanner(System.in );
        PrintWriter output = new PrintWriter(file);
        System.out.print("Insert the First Binary number :");
        int x=in.nextInt();
        System.out.println();
        System.out.print("Insert the Second Binary number :");
        int y=in.nextInt();
        output.println(x);
        output.print(y);
        output.close();
    }

}
