/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs402;

import java.math.BigInteger;

/**
 *
 * @author NIGHT WOLF
 */
public class PrimeFactorization 
{
    public static void main(String[] args) 
    {
        BigInteger x=BigInteger.ONE;
        BigInteger y=BigInteger.ONE;
        BigInteger r=new BigInteger("3599");
        boolean z=false;
        System.out.println(r);
        while(r.subtract(x).signum()>0)
        {
            x=x.nextProbablePrime();
            y=BigInteger.ONE;
            //System.out.println("x:"+x);
            while(r.subtract(y).signum()>0)
            {
                y=y.nextProbablePrime();
                //System.out.println("y:"+y);
                if(x.multiply(y).equals(r))
                {
                    z=true;
                    break;
                }
            }
            if(z)
            {
                break;
            }
        }
        System.out.println(x+","+y);
    }
    
}
