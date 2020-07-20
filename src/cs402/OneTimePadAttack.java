package cs402;
/**
 *
 * @author NIGHT WOLF
 */
public class OneTimePadAttack 
{
    String cyphers[];
    String [][] results;
    IOfile x=new IOfile();
    public void read()
    {
        String raw=x.read("CypherText.txt");
        cyphers=raw.split("ciphertext");
        results=new String[cyphers.length][cyphers.length];
        /*for(int i=0;i<cyphers.length;i++)
        {
            for(int j=0;j<cyphers.length;j++)
            {
                results[i][j]="";
            }
        }*/
    }
    public String XORToHexa(String c1,String c2)
    {
        byte a[]=new byte[41];
        byte a1[]=hexStringToByteArray(c1);
        byte a2[]=hexStringToByteArray(c2);
        //System.out.println(c1.length()+" "+a1.length);
        //System.out.println(c2.length()+" "+a2.length);
        for(int i=0;i<41;i++)
        {
            a[i]=(byte)(a1[i]^a2[i]);
        }
        StringBuilder hex = new StringBuilder();
        for (int i = 0; i < a.length; i++)
        {
            hex.append((char)a[i]);
            //hex.append(Integer.toHexString((int) a[i]));
        }
        return hex.toString();
    }
    public static byte[] hexStringToByteArray(String s) 
    {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) 
        {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)+ Character.digit(s.charAt(i+1), 16));
        }
    return data;
    }
    public boolean check(String s,char c)
    {
        return s.indexOf(c)!=-1;
    }
    public int[][] places(String c[][])
    {
        int places[][]=new int[c.length][41];
        String chars="qwertyuioplkjhgfdsazxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
        for(int i=0;i<c.length;i++)
        {
            for(int j=0;j<c[i].length;j++)
            {
                if(i!=j)
                {
                    for(int z=0;z<c[i][j].length();z++)
                    {
                        if(check(chars,c[i][j].charAt(z)))
                        {
                            for(int k=0;k<c.length;k++)
                            {
                                if(k!=j&&check(chars,c[k][j].charAt(k)))
                                {
                                    places[i][z]++;
                                }
                            }
                        }
                    }
                }
            }
        }
        return places;
    }
    public String[] wirteKeyResult(int p[][])
    {
        String result[]=new String[p.length];
        byte temp[];
        for(int i=0;i<p.length;i++)
        {
            for(int j=0;j<p[i].length;j++)
            {
                if(p[i][j]>=10)
                {
                    temp=hexStringToByteArray(cyphers[i]);
                    result[i]+=(char)(temp[i]^' ');
                }
                else
                {
                    result[i]+="-";
                }
            }
        }
        return result;
    }
    public static void main(String args[])
    {
        OneTimePadAttack x=new OneTimePadAttack();
        x.read();
        for(int i=0;i<x.cyphers.length;i++)
        {
            for(int j=0;j<x.cyphers.length;j++)
            {
                if(i!=j)
                {
                    x.results[i][j]=x.XORToHexa(x.cyphers[i],x.cyphers[j]);
                    //System.out.println("cypher "+i+" XOR cypher "+j);
                    if(j%11==0)
                    {
                        System.out.println("cypher "+i+" XOR cypher "+j);
                    }
                    System.out.println(x.results[i][j].replaceAll("[\n\r]", ""));
                }
            }
        }
        System.out.println("candidte key places");
        //find placse starts here
        int p[][]=x.places(x.results);
        for(int i=0;i<p.length;i++)
        {
            for(int j=0;j<p[i].length;j++)
            {
                System.out.print(p[i][j]+",");
            }
            System.out.println("");
        }
        //key candidates
        System.out.println("key candidates");
        String m[]=x.wirteKeyResult(p);
        for(int i=0;i<m.length;i++)
        {
            System.out.println(m[i]);
        }
        //System.out.println("aktar mn keda ab2a bahry");
    }
}