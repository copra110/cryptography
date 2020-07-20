package cs402;

import java.math.BigInteger;

/**
 *
 * @author NIGHT WOLF
 */
public class FrequencyAttack 
{
    public static void main(String[] args) 
    {
        IOfile x=new IOfile();
        String cipher=x.read("CypherText.txt");
        char a[]={'e','a','t','o','n','h','i','s','r','d','l','c','u','m','f','w','g','b','p','v','k','j','x'};
        StringBuilder c=new StringBuilder();
        for(int i=0;i<cipher.length();i++)
        {
            if(c.toString().indexOf(cipher.charAt(i))==-1)
            {
                c.append(cipher.charAt(i));
            }
        }
        double p[]=new double[c.length()];
        for(int i=0;i<p.length;i++)
        {
            int count=0;
            for(int j=0;j<cipher.length();j++)
                {
                    if(c.charAt(i)==cipher.charAt(j))
                    {
                        count++;
                    }
                }
            p[i]=((double)count/cipher.length())*100;
        }
        InsertionSort( p,c);
        for(int i=0;i<p.length;i++)
        {
            System.out.println(c.toString().charAt(i)+" "+p[i]);
        }
        System.out.println(c);
        for(int i=0;i<a.length;i++)
        {
            System.out.print(a[i]);
        }
        System.out.println("");
        for(int i=0;i<c.length();i++)
        {
            cipher=cipher.replace(c.charAt(i)+"", a[i]+"");
        }
        x.write(cipher, "PlainText.txt");
        System.out.println(cipher);
    }
    public static void countDupplicate(String cipher,String c)
    {
        int count;
        for(int i=0;i<c.length();i++)
        {
            count=0;
            for(int j=0;j<cipher.length()-1;j++)
            {
                if(cipher.charAt(j)==cipher.charAt(j+1)&&cipher.charAt(j)==c.charAt(i))
                {
                    count++;
                }
            }
            System.out.println(c.charAt(i)+""+c.charAt(i)+" "+count);
        }
    }
    public static void InsertionSort( double [ ] num,StringBuilder c)
    {
        int j;
        double key;
        char x;
        int i;
        for (j = 1; j < num.length; j++)
        {
           key = num[ j ];
           x=c.charAt(j);
           for(i = j - 1; (i >= 0) && (num[ i ] < key); i--)
           {
               num[ i+1 ] = num[ i ];
               c.setCharAt(i+1, c.charAt(i));
           }
           num[ i+1 ] = key;
           c.setCharAt(i+1, x);
        }
    }
}