package cs402;
import java.util.Scanner;
public class Maintest
{
    
    public void Maintest()
    {
        Keygen T=new Keygen();
        String k=T.read("Key.txt");
        System.out.println("Key : "+k+"\n");
       T=new Keygen(k);
       T.KeyGeneration();
      }
    public static void main(String args[])
    {
        Maintest t=new Maintest();
        t.Maintest();
    }
}
