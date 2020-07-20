package cs402;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

/**
 *
 * @author NIGHT WOLF
 */
public class RSA 
{
    IOfile x=new IOfile();
    BigInteger p,q,n,e,d,phiN;
    public RSA()
    {
        Random rnd=new Random();
        this.p=BigInteger.probablePrime(512,rnd);
        this.q=BigInteger.probablePrime(512,rnd);
        while(p.intValue()<0)
        {
            this.p=BigInteger.probablePrime(512,rnd);
        }
        while(p.equals(q)||q.intValue()<0)
        {
            this.q=BigInteger.probablePrime(512,rnd);
        }
        this.n=p.multiply(q);
        this.phiN=p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        this.e=BigInteger.probablePrime(1024,rnd);
        while(!phiN.gcd(e).equals(BigInteger.ONE)&&phiN.min(e).equals(e))
        {
            this.e=BigInteger.probablePrime(1024,rnd);
        }
        this.d=e.modInverse(phiN);
    }
    public BigInteger encrypt(String fn,String fn2)
    {
        String m=x.read(fn);
        BigInteger m1=convert(m);
        //BigInteger m1=new BigInteger(m);
        BigInteger c=m1.modPow(e,n);
        x.write(c.toString(), fn2);
        return c; 
    }
    public BigInteger decrypt(String fn,String fn2)
    {
        String c=x.read(fn2);
        BigInteger m=new BigInteger(c).modPow(d,n);
        x.write(m.toString(), fn);
        return m; 
    }
    public BigInteger convert(String m)
    {
        StringBuilder s=new StringBuilder();
        for(int i=0;i<m.length();i++)
        {
            s.append(((int)m.charAt(i))+"");
        }
        m=s.toString();
        BigInteger m1=new BigInteger(m);
        return m1;
    }
    public String reconvert(BigInteger c)
    {
        byte[] chars = c.toByteArray();
        StringBuilder s=new StringBuilder();
        for (int i = 0; i < chars.length; i++) 
        {
            s.append((char)chars[i]);
        }
        return s.toString();
    }
    public static void main(String[] args) 
    {
        RSA x=new RSA();
        
        BigInteger m1=x.convert("abcd");
        BigInteger c=x.encrypt("PlainText.txt","CypherText.txt");
        BigInteger m=x.decrypt("PlainText.txt","CypherText.txt");

    }
}
