package cs402;
import java.util.*;
import java.io.*;
public class Keygen
{
    String Key;
    String [] keys;
    int [] shift;
    PrintWriter out;
    Scanner in;
    public Keygen(){}
    public Keygen(String k)
    {
        Key=k;
        int [] shift={1 ,1 ,2 ,2 ,2, 2, 2 ,2 ,1 ,2 ,2 ,2 ,2 ,2 ,2 ,1};
        this.shift=shift;
        keys=new String[16];
    }
    public void Pc1()
    {
        String k="";
        int [] pc1={57 ,49 ,41 ,33 ,25 ,17 ,9,1 ,58 ,50 ,42, 34 ,26 ,18,10 ,2 ,59 ,51 ,43 ,35 ,27,19 ,11, 3 ,60 ,52 ,44 ,36,63 ,55 ,47 ,39 ,31 ,23 ,15,7 ,62 ,54 ,46 ,38 ,30 ,22,14 ,6 ,61 ,53 ,45 ,37 ,29,21 ,13 ,5 ,28 ,20 ,12 ,4};
        for(int i=0;i<pc1.length;i++)
        {
            k+=Key.charAt(pc1[i]-1);
        }
        Key=k;
    }
    public String Shift(String k,int s)
    {
        String m1=k.substring(0,s);
        String m2=k.substring(s);
        k=m2+m1;
        return k;
    }
    public String Pc2(String k1)
    {
        String k="";
        int [] pc2={14 ,17 ,11 ,24, 1 ,5 ,3 ,28,15 ,6 ,21 ,10, 23 ,19 ,12 ,4,26 ,8 ,16 ,7 ,27 ,20 ,13 ,2,41, 52 ,31 ,37 ,47 ,55 ,30 ,40,51 ,45 ,33 ,48 ,44 ,49 ,39 ,56,34, 53 ,46 ,42 ,50 ,36 ,29 ,32};
        for(int i=0;i<pc2.length;i++)
        {
            k+=k1.charAt(pc2[i]-1);
        }
       return k;
    }
    public void KeyGeneration()
    {
        String l="",r="";
        Pc1();
        l=Key.substring(0, 28);
        r=Key.substring(28);
        for(int i=0;i<keys.length;i++)
        {
           l=Shift(l,shift[i]);
           r=Shift(r,shift[i]);
           keys[i]=l+r;
          /*System.out.println("C"+(i)+":"+l);
            System.out.println("D"+(i)+":"+r);
           System.out.println();*/
        }
        for(int j=0;j<keys.length;j++)
        {
            keys[j]=Pc2(keys[j]);
         //System.out.println("K "+(j+1)+":"+keys[j]);
        }
    }
      public  String read(String s)
    {
        String m = "";
        try{
            in = new Scanner(new File(s));
            while (in.hasNextLine()) {
                m = m + in.nextLine();
            }
           in.close();
        }
        catch(IOException e){
            System.out.println(e);
        }
      return m;
    }
    public void write(String m, String s)
    {
        try{
            out = new PrintWriter(m);
            out.write(s);
            out.close();
        }
        catch(IOException e){
            System.out.println("Error");
        }
    }
}
