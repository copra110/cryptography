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
public class KeyGeneration 
{
    int pc1[]={57 ,49, 41, 33, 25, 17, 9,1 ,58 ,50 ,42 ,34 ,26 ,18,10 ,2 ,59, 51 ,43 ,35 ,27,19 ,11 ,3, 60, 52, 44, 36,63, 55, 47, 39, 31, 23, 15,7, 62, 54, 46, 38, 30, 22,14, 6, 61, 53, 45, 37, 29,21, 13, 5 ,28 ,20 ,12, 4};
    int pc2[]={14, 17, 11, 24, 1, 5,3, 28, 15, 6 ,21 ,10,23, 19, 12, 4 ,26, 8,16, 7, 27, 20, 13, 2,41, 52, 31 ,37 ,47 ,55,30 ,40 ,51 ,45 ,33 ,48,44 ,49, 39, 56 ,34 ,53,46 ,42 ,50 ,36 ,29 ,32};
    int shift[]={1,1,2,2,2,2,2,2,2,2,1,2,2,2,2,2,2,1};
    byte keys[][];
    public static void main(String[] args) 
    {
        KeyGeneration x=new KeyGeneration();
        for(int i=0;i<x.keys.length;i++)
        {
            for(int j=0;j<x.keys[i].length;j++)
            {
                System.out.print(x.keys[i][j]+",");
            }
            System.out.println("");
        }
    }
    public KeyGeneration()
    {
        String M="89abcdef";
        byte[] bytes = M.getBytes();
        StringBuilder binary = new StringBuilder();
        for (byte b : bytes)
        {
            int val = b;
            for (int i = 0; i < 8; i++)
            {
                binary.append((val & 128) == 0 ? 0 : 1);
                val <<= 1;
            }
        }
        binary.append(' ');
        String s = binary.toString();
        System.out.println(s+" "+s.length());
        byte k[]=new byte[64];
        for(int i=0;i<s.length()-1;i++)
        {
            k[i]=(byte)(Integer.parseInt(s.charAt(i)+""));
        }
        keys=KeyGeneration(k);
        
    }
    public byte[][]KeyGeneration(byte key[])
    {
        byte keys[][]=new byte[16][48];
        byte tKey []=new byte[48];
        byte bKey []=new byte[56];
        byte pcKey[]=pc1(key);
        byte lk[]=new byte[28];
        byte rk[]=new byte[28];
        for(int i=0;i<pcKey.length;i++)
        {
            if(i<lk.length)
            {
                lk[i]=pcKey[i];
            }
            else
            {
                rk[i%28]=pcKey[i];
            }
        }
        for(int i=0;i<16;i++)
        {
            leftshife(i,lk);
            leftshife(i,rk);
            for(int k=0;k<55;k++)
            {
                if(k<lk.length)
                {
                    bKey[k]=lk[k];
                }
                else
                {
                    bKey[k]=rk[k%28];
                }
            }
            tKey=pc2(bKey);
            keys[i]=tKey;
        }
        return keys;
    }
    public byte[] pc1(byte key[])
    {
        byte kpc[]=new byte[56];
        for(int i=0;i<kpc.length;i++)
        {
            kpc[i]=key[pc1[i]-1];
        }
        return kpc;
    }
    public byte[] pc2(byte key[])
    {
        byte kpc[]=new byte[48];
        for(int i=0;i<kpc.length;i++)
        {
            kpc[i]=key[pc2[i]-1];
        }
        return kpc;
    }
    public void leftshife(int i,byte a[])
    {
        byte temp1;
        byte temp2;
        int s=shift[i];
        if(s==1)
        {
            temp1=a[0];
            for(int j=0;j<a.length-1;j++)
            {
                a[j]=a[j+1];
            }
            a[a.length-1]=temp1;
        }
        else
        {
            temp1=a[0];
            temp2=a[1];
            for(int j=0;j<a.length-2;j++)
            {
                a[j]=a[j+1];
            }
            a[a.length-1]=temp2;
            a[a.length-2]=temp1;
        }
    }
}