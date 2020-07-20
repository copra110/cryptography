/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs402;
/**
 *
 * @author NIGHT WOLF
 */
public class Generator 
{
    static String [][] sBox={{"9","4","a","b"},{"d","1","8","5"},{"6","2","0","3"},{"c","e","f","7"}};
    String g[]={"0001","0010","0100","1000","0011","0110","1100","1011","0101","1010","0111","1110","1111","1101","1001"};
    public String sum(String s1,String s2)
    {
        String a="";
        for(int i=0;i<4;i++)
        {
            a+=(s1.charAt(i)-'0')^(s2.charAt(i)-'0');
        }
        return a;
    }
    public String multi(String s1,String s2)
    {
        if(s1.equals("0000")||s2.equals("0000"))
        {
            return "0000";
        }
        else
        {
            int x=search(s1);
            int y=search(s2);
            int r=((x+y)%15);
            return g[r];
        }
    }
    public String inverse(String s)
    {
        if(!s.equals("0000"))
        {
            for(int i=0;i<g.length;i++)
            {
                if(multi(s, g[i]).equals("0001"))
                {
                    return g[i];
                }
            }
        }
        return "no inverse for such String";
    }
    
    public String inverse1(String s){
        if(!s.equals("0000"))
        {
            int i = search(s);
            i=15-i;
            
            return g[(i%15)];
        }
        return "no inverse for such String";
    
    
    }
    private int search(String s)
    {
        for(int i=0;i<g.length;i++)
        {
            if(s.equals(g[i]))
            {
                return i;
            }
        }
        return -1;
    }
    public static void main(String args[])
    {
        System.out.println((int)'0');
        System.out.println((int)'1');
    }
}
