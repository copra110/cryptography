package cs402;

import java.math.BigInteger;

/**
 *
 * @author NIGHT WOLF
 */
public class HellCipherAttack 
{
    public static void main(String args[])
    {
        IOfile x=new IOfile();
        String plaintext="gjfi";
        String cciphertext="xzpo";
        int plain[][]={{x.reinterpret(plaintext.charAt(0)),x.reinterpret(plaintext.charAt(1))},{x.reinterpret(plaintext.charAt(2)),x.reinterpret(plaintext.charAt(3))}};
        int cipher[][]={{x.reinterpret(cciphertext.charAt(0)),x.reinterpret(cciphertext.charAt(1))},{x.reinterpret(cciphertext.charAt(2)),x.reinterpret(cciphertext.charAt(3))}};
        int IP[][]=inverse(plain);
        int key[][]=product(IP,cipher);
        System.out.println("K =");
        for(int i=0;i<2;i++)
        {
            for(int j=0;j<2;j++)
            {
                System.out.print(key[i][j]+" ");
            }
            System.out.println("");
        }
    }
    public static int[][] inverse(int a[][])
    {
        BigInteger m=new BigInteger("26");
        BigInteger det=new BigInteger(Integer.toString(((a[0][0]*a[1][1])-(a[0][1]*a[1][0])))).mod(m);
        BigInteger Idet=det.modInverse(m);
        int result[][]=new int [2][2];
        result[0][0]=Idet.intValue()* a[1][1];
        result[0][1]=Idet.intValue()* (-1)*a[0][1];
        result[1][0]=Idet.intValue()* (-1)*a[1][0];
        result[1][1]=Idet.intValue()* a[0][0];
        for(int i=0;i<result.length;i++)
        {
            for(int j=0;j<result[i].length;j++)
            {
                BigInteger value=new BigInteger(Integer.toString(result[i][j])).mod(m);
                result[i][j]=value.intValue();
            }
        }
        return result;
    }
    public static int[][] product(int a[][],int b[][])
    {
        BigInteger m=new BigInteger("26");
        int r[][]=new int[2][2];
        r[0][0]=(a[0][0]*b[0][0]+a[0][1]*b[1][0]);
        r[1][0]=(a[0][0]*b[0][1]+a[0][1]*b[1][1]);
        r[0][1]=(a[1][0]*b[0][0]+a[1][1]*b[1][0]);
        r[1][1]=(a[1][0]*b[0][1]+a[1][1]*b[1][1]);
        for(int i=0;i<r.length;i++)
        {
            for(int j=0;j<r[i].length;j++)
            {
                BigInteger value=new BigInteger(Integer.toString(r[i][j])).mod(m);
                r[i][j]=value.intValue();
            }
        }
        return r;
    }
}