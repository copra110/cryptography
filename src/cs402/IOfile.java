package cs402;
import java.io.*; 
/**
 *
 * @author NIGHT WOLF
 */
public class IOfile 
{
    String s="";
    public IOfile(){}
    String read(String fn)
    {
        try
        {
            File file= new File(fn);
            java.util.Scanner input = new java.util.Scanner(file); 
            while (input.hasNext())
            {
                s= input.next();
            }
            input.close();
        }
        catch(IOException e)
        {}
        return s;
    }
    void write(String s,String fn)
    {
        try
        {
            PrintWriter key = new PrintWriter(fn);
            key.println(s);
            key.close();
        }
        catch(IOException e)
        {}
    }
    void append(String s,String fn)
    {
        try
        {
            String c=read(fn);
            PrintWriter key = new PrintWriter(fn);
            key.println(c);
            key.println(s);
            key.close();
        }
        catch(IOException e)
        {}
    }
    public static void main(String[] args) 
    {
        
        IOfile x=new IOfile();
        String z=x.read("PlainText.txt");
        String z1=x.read("Key.txt");
        System.out.println(z+","+z1);
    }
    int reinterpret(char c)
    {
        int x=0;
        char y='a';
        for(int i=0;i<=26;i++)
        {
            if(c==(char)(y+i))
            {
                x=i;
            }
        }
        return x;
    }
    char interpret(int x)
    {
        char c='a';
        for(int i=0;i<=25;i++)
        {
            if(x==i)
            {
                c=(char)(c+i);
            }
        }
        return c ;
    }
}