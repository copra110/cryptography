package cs402;
public class DES
{
    String  pr;
    String pl;
    String [] prnext;
    String [] plnext;
    Keygen key;
    Desround dr;
    public DES(String p,String k)
    {
        key=new Keygen(k);
        //System.out.println(k);
        key.KeyGeneration();
        p=IP(p);
        pl=p.substring(0,32);
        pr=p.substring(32);
        dr=new Desround(pr);
        plnext=new String[16];
        prnext=new String[16];
    }
    //***********************************
    public String DES_En()
    {
        String c="";
        plnext[0]=pr;
       // System.out.println("PR :"+pr);
        //System.out.println("PL :"+pl);
        pr=dr.Exp(pr);
      //  System.out.println("After Expantion "+pr);
        pr=Xor(pr,key.keys[0]);
       // System.out.println("After XOR"+pr);
       // System.out.println("with key :"+key.keys[0]);
        pr=dr.Sbox(pr);
       // System.out.println("After Sbox:"+pr);
        pr=dr.Pe(pr);
       // System.out.println("Permention :"+pr);
        prnext[0]=Xor(pl,pr);
        //System.out.println("After 1st Round "+pr);
      //  System.out.println("PRnext :"+prnext[0]);
       // System.out.println("PLnext :"+plnext[0]);
        for(int i=1;i<prnext.length;i++)
        {
            plnext[i]=prnext[i-1];
           // System.out.println("\n"+plnext[i]);
            prnext[i-1]=dr.Exp(prnext[i-1]);
           // System.out.println("After Expantion "+prnext[i].length());
            prnext[i-1]=Xor(prnext[i-1],key.keys[i]);
            prnext[i-1]=dr.Sbox(prnext[i-1]);
            prnext[i-1]=dr.Pe(prnext[i-1]);
            prnext[i]=Xor(plnext[i-1],prnext[i-1]);
         //   System.out.println(prnext[i]+"\n");
        }
       // System.out.println(prnext[15]+"\n"+plnext[15]);
        c=IPI(prnext[15]+""+plnext[15]);
        return c;
    }
    //***********************************
    public String DES_Dec()
    {
        String c="";
        plnext[0]=pr;
       // System.out.println("PR :"+pr);
        //System.out.println("PL :"+pl);
        pr=dr.Exp(pr);
      //  System.out.println("After Expantion "+pr);
        pr=Xor(pr,key.keys[15]);
       // System.out.println("After XOR"+pr);
       // System.out.println("with key :"+key.keys[0]);
        pr=dr.Sbox(pr);
       // System.out.println("After Sbox:"+pr);
        pr=dr.Pe(pr);
       // System.out.println("Permention :"+pr);
        prnext[0]=Xor(pl,pr);
        //System.out.println("After 1st Round "+pr);
      //  System.out.println("PRnext :"+prnext[0]);
       // System.out.println("PLnext :"+plnext[0]);
        for(int i=1;i<prnext.length;i++)
        {
            plnext[i]=prnext[i-1];
           // System.out.println("\n"+plnext[i]);
            prnext[i-1]=dr.Exp(prnext[i-1]);
           // System.out.println("After Expantion "+prnext[i].length());
            prnext[i-1]=Xor(prnext[i-1],key.keys[prnext.length-i-1]);
            prnext[i-1]=dr.Sbox(prnext[i-1]);
            prnext[i-1]=dr.Pe(prnext[i-1]);
            prnext[i]=Xor(plnext[i-1],prnext[i-1]);
         //   System.out.println(prnext[i]+"\n");
        }
       // System.out.println(prnext[15]+"\n"+plnext[15]);
        c=IPI(prnext[15]+""+plnext[15]);
        return c;
    }
    //**************************************
    public String IP(String p) {
         String ps = "";
        int[] ip = {58 ,50 ,42 ,34 ,26 ,18 ,10 ,2
,60 ,52 ,44 ,36 ,28 ,20 ,12 ,4
,62 ,54 ,46 ,38, 30, 22 ,14 ,6
,64 ,56 ,48 ,40 ,32 ,24 ,16 ,8
,57, 49, 41 ,33 ,25 ,17, 9 ,1
,59 ,51 ,43 ,35 ,27 ,19 ,11 ,3
,61 ,53 ,45 ,37 ,29 ,21 ,13, 5
,63 ,55 ,47 ,39 ,31 ,23 ,15 ,7};
        for (int i = 0; i < ip.length; i++) {
            ps += p.charAt(ip[i] - 1);
        }
        return ps;
    }
    //*************************************************

    public String IPI(String p)
    {
        String ps = "";
        int[] ipi = {40, 8 ,48 ,16 ,56 ,24 ,64 ,32
,39, 7 ,47 ,15 ,55 ,23 ,63 ,31
,38, 6 ,46 ,14 ,54 ,22 ,62 ,30
,37, 5 ,45 ,13 ,53 ,21 ,61 ,29
,36 ,4 ,44 ,12 ,52 ,20 ,60 ,28
,35 ,3 ,43 ,11 ,51 ,19 ,59, 27
,34 ,2 ,42 ,10 ,50 ,18 ,58 ,26
,33 ,1 ,41 ,9 ,49 ,17 ,57 ,25};
        for(int i=0;i<ipi.length;i++)
        {
            ps +=p.charAt(ipi[i]-1);
        }
        return ps;
    }
    //**********************************
    public String Xor(String s1,String s2)
    {
        String s="";
        for(int i=0;i<s1.length();i++)
        {
            if(s1.charAt(i)==s2.charAt(i))
                s+="0";
            else
                s+="1";
        }
        return s;
    }
    public static void main(String args[])
    {
        DES d=new DES("0000000100100011010001010110011110001001101010111100110111101111","0001001100110100010101110111100110011011101111001101111111110001");
        System.out.println("Cipher :- "+d.DES_En());
        System.out.println("Plain :-"+d.DES_Dec());
    }
}
