package cs402;
/**
 *
 * @author NIGHT WOLF
 */
public class SAES 
{
    IOfile x;
    IOfile y;
    String p;
    String k;
    String state[][];
    String key[][];
    String [][] sBox={{"9","4","a","b"},{"d","1","8","5"},{"6","2","0","3"},{"c","e","f","7"}};
    String [][] iSBox={{"a","5","9","b"},{"1","7","8","f"},{"6","0","2","3"},{"c","4","d","e"}};
    public SAES(String s)
    {
       
        this.x=new IOfile();
        //this.y=new IOfile();
        this.p=x.read(s);
        System.out.println(p);
        //System.out.println(p);
        this.k=x.read("Key.txt");
        //System.out.println(k);
        this.state=new String[2][2];
        this.key=new String[2][2];
        int z=0;
        int z1=0;
        for(int i=0;i<state.length;i++)
        {
            for(int j=0;j<state[i].length;j++)
            {
                state[j][i]=p.charAt(z++)+"";
                key[j][i]=k.charAt(z1++)+"";
            }
        }
    }
    void ipsub()
    {
        state[0][0]=binaryToHex(iSup(hexToBinary(state[0][0])));
        state[0][1]=binaryToHex(iSup(hexToBinary(state[0][1])));
        state[1][0]=binaryToHex(iSup(hexToBinary(state[1][0])));
        state[1][1]=binaryToHex(iSup(hexToBinary(state[1][1])));
    }
    String iSup(String s)
    {
        String result="";
        String a[]=new String [2];
        int index[]=new int[2];
        int r=0;
        for(int i=0;i<a.length;i++)
        {
            a[i]=s.substring(r, r+2);
            //System.out.println(a[i]);
            r+=2;
            index[i]=Integer.parseInt(a[i], 2);
        }
        result+=hexToBinary(iSBox[index[0]][index[1]]);
        return result;
    }
    
    void shift()
    {
        String temp=state[1][0];
        state[1][0]=state[1][1];
        state[1][1]=temp;
    }
   void addRoundKey(String w1,String w2)
    {
        String rkey[][]=new String[2][2];
        rkey[0][0]=w1.charAt(0)+"";
        rkey[1][0]=w1.charAt(1)+"";
        rkey[0][1]=w2.charAt(0)+"";
        rkey[1][1]=w2.charAt(1)+"";
        
        for(int i=0;i<state.length;i++)
        {
            for(int j=0;j<state[i].length;j++)
            {
                state[i][j]=binaryToHex(xor(hexToBinary(state[i][j]),hexToBinary(rkey[i][j])));
            }
        }
    }
    String [] keyGen(String key[][])
    {
        String w[]=new String [6];
        w[0]=key[0][0]+key[1][0];
        w[1]=key[0][1]+key[1][1];
        w[2]=binaryToHex(xor(hexToBinary(w[0]),xor("10000000",ksub(rot(hexToBinary(w[1]))))));
        w[3]=binaryToHex(xor(hexToBinary(w[2]),hexToBinary(w[1])));
        w[4]=binaryToHex(xor(hexToBinary(w[2]),xor("00110000",ksub(rot(hexToBinary(w[3]))))));
        w[5]=binaryToHex(xor(hexToBinary(w[4]),hexToBinary(w[3])));
        System.out.println(w[0]);
        System.out.println(w[1]);
        System.out.println(w[2]);
        System.out.println(w[3]);
        System.out.println(w[4]);
        System.out.println(w[5]);
        return w;
    }
    void psub()
    {
        state[0][0]=binaryToHex(sup(hexToBinary(state[0][0])));
        state[0][1]=binaryToHex(sup(hexToBinary(state[0][1])));
        state[1][0]=binaryToHex(sup(hexToBinary(state[1][0])));
        state[1][1]=binaryToHex(sup(hexToBinary(state[1][1])));
    }
    String sup(String s)
    {
        String result="";
        String a[]=new String [2];
        int index[]=new int[2];
        int r=0;
        for(int i=0;i<a.length;i++)
        {
            a[i]=s.substring(r, r+2);
            //System.out.println(a[i]);
            r+=2;
            index[i]=Integer.parseInt(a[i], 2);
        }
        result+=hexToBinary(sBox[index[0]][index[1]]);
        return result;
    }
    String ksub(String s) 
    {
        //System.out.println("psub"+s);
        String result="";
        String a[]=new String [4];
        int index[]=new int[4];
        int r=0;
        for(int i=0;i<a.length;i++)
        {
            a[i]=s.substring(r, r+2);
            r+=2;
            index[i]=Integer.parseInt(a[i], 2);
        }
        for(int i=0;i<4;i+=2)
        {
            result+=hexToBinary(sBox[index[i]][index[i+1]]);
        }
        //System.out.println("rpsub"+result);
        return result;
    }
    String rot(String s)
    {
        //System.out.println("rot"+s);
        String temp=s.substring(0,4);
        String r="";
        r+=s.substring(4,8);
        r=r+temp;
        //System.out.println("rrot"+r);
        return r;
    }
    String xor(String s1,String s2)
    {
        String a="";
        for(int i=0;i<s1.length();i++)
        {
            a+=s1.charAt(i)^s2.charAt(i);
        }
        return a;
    }
    String hexToBinary(String hex) 
    {
        String result="";
        for(int i=0;i<hex.length();i++)
        {
            int k = Integer.parseInt(hex.charAt(i)+"", 16);
            String bin = Integer.toBinaryString(k);
            while(bin.length()<4)
            {
                bin="0"+bin;
            }
            result+= bin;
        }
        return result;
    }
    String binaryToHex(String s)
    {
        String r=Integer.toHexString(Integer.parseInt(s,2));
        return r;
    }
    void mix()
    {
        String result[][]=new String[2][2];
        Generator x=new Generator();
        result[0][0]=binaryToHex(xor(hexToBinary(state[0][0]),x.multi(hexToBinary(state[1][0]),hexToBinary("4"))));
        result[0][1]=binaryToHex(xor(hexToBinary(state[0][1]),x.multi(hexToBinary(state[1][1]), hexToBinary("4"))));
        result[1][0]=binaryToHex(xor(x.multi(hexToBinary(state[0][0]), hexToBinary("4")),hexToBinary(state[1][0])));
        result[1][1]=binaryToHex(xor(x.multi(hexToBinary(state[0][1]), hexToBinary("4")),hexToBinary(state[1][1])));
        state=result;
    }
    void imix()
    {
        String result[][]=new String[2][2];
        Generator x=new Generator();
        result[0][0]=binaryToHex(xor(x.multi(hexToBinary(state[0][0]),hexToBinary("9")),x.multi(hexToBinary(state[1][0]),hexToBinary("2"))));
        result[0][1]=binaryToHex(xor(x.multi(hexToBinary(state[0][1]),hexToBinary("9")),x.multi(hexToBinary(state[1][1]), hexToBinary("2"))));
        result[1][0]=binaryToHex(xor(x.multi(hexToBinary(state[0][0]), hexToBinary("2")),x.multi(hexToBinary(state[1][0]),hexToBinary("9"))));
        result[1][1]=binaryToHex(xor(x.multi(hexToBinary(state[0][1]), hexToBinary("2")),x.multi(hexToBinary(state[1][1]),hexToBinary("9"))));
        state=result;
    }
    static void decrypt()
    {
        SAES x=new SAES("CypherText.txt");
        String wkey[]=x.keyGen(x.key);
        System.out.println("-------------------------0");
        for(int i=0;i<x.state.length;i++)
        {
            for(int j=0;j<x.state.length;j++)
            {
                System.out.print(x.state[i][j]);
            }
            System.out.println("");
        }
        System.out.println("-------------------------1");
        x.addRoundKey( wkey[4], wkey[5]);
        for(int i=0;i<x.state.length;i++)
        {
            for(int j=0;j<x.state.length;j++)
            {
                System.out.print(x.state[i][j]);
            }
            System.out.println("");
        }
        System.out.println("-------------------------2");
        x.shift();
        for(int i=0;i<x.state.length;i++)
        {
            for(int j=0;j<x.state.length;j++)
            {
                System.out.print(x.state[i][j]);
            }
            System.out.println("");
        }
        System.out.println("-------------------------2");
        x.ipsub();
        for(int i=0;i<x.state.length;i++)
        {
            for(int j=0;j<x.state.length;j++)
            {
                System.out.print(x.state[i][j]);
            }
            System.out.println("");
        }
        System.out.println("-------------------------3");
        x.addRoundKey( wkey[2], wkey[3]);
        for(int i=0;i<x.state.length;i++)
        {
            for(int j=0;j<x.state.length;j++)
            {
                System.out.print(x.state[i][j]);
            }
            System.out.println("");
        }
        System.out.println("-------------------------4");
        x.imix();
        for(int i=0;i<x.state.length;i++)
        {
            for(int j=0;j<x.state.length;j++)
            {
                System.out.print(x.state[i][j]);
            }
            System.out.println("");
        }
        System.out.println("-------------------------5");
        x.shift();
        for(int i=0;i<x.state.length;i++)
        {
            for(int j=0;j<x.state.length;j++)
            {
                System.out.print(x.state[i][j]);
            }
            System.out.println("");
        }
        System.out.println("-------------------------6");
        x.ipsub();
        for(int i=0;i<x.state.length;i++)
        {
            for(int j=0;j<x.state.length;j++)
            {
                System.out.print(x.state[i][j]);
            }
            System.out.println("");
        }
        System.out.println("-------------------------7");
        x.addRoundKey(wkey[0], wkey[1]);
        String d="";
        for(int i=0;i<x.state.length;i++)
        {
            for(int j=0;j<x.state.length;j++)
            {
                d+=x.state[j][i];
                System.out.print(x.state[i][j]);
            }
            System.out.println("");
        }
        x.x.write(d,"PlainText.txt");
    }
    static void encrypt()
    {
        SAES x=new SAES("PlainText.txt");
        String wkey[]=x.keyGen(x.key);
                System.out.println("-------------------------0");
        for(int i=0;i<x.state.length;i++)
        {
            for(int j=0;j<x.state.length;j++)
            {
                System.out.print(x.state[i][j]);
            }
            System.out.println("");
        }
        System.out.println("-------------------------1");
        x.addRoundKey( wkey[0], wkey[1]);
        for(int i=0;i<x.state.length;i++)
        {
            for(int j=0;j<x.state.length;j++)
            {
                System.out.print(x.state[i][j]);
            }
            System.out.println("");
        }
        System.out.println("-------------------------2");
        x.psub();
        for(int i=0;i<x.state.length;i++)
        {
            for(int j=0;j<x.state.length;j++)
            {
                System.out.print(x.state[i][j]);
            }
            System.out.println("");
        }
        System.out.println("-------------------------2");
        x.shift();
        for(int i=0;i<x.state.length;i++)
        {
            for(int j=0;j<x.state.length;j++)
            {
                System.out.print(x.state[i][j]);
            }
            System.out.println("");
        }
        System.out.println("-------------------------3");
        x.mix();
        for(int i=0;i<x.state.length;i++)
        {
            for(int j=0;j<x.state.length;j++)
            {
                System.out.print(x.state[i][j]);
            }
            System.out.println("");
        }
        System.out.println("-------------------------4");
        x.addRoundKey( wkey[2], wkey[3]);
        for(int i=0;i<x.state.length;i++)
        {
            for(int j=0;j<x.state.length;j++)
            {
                System.out.print(x.state[i][j]);
            }
            System.out.println("");
        }
        System.out.println("-------------------------5");
        x.psub();
        for(int i=0;i<x.state.length;i++)
        {
            for(int j=0;j<x.state.length;j++)
            {
                System.out.print(x.state[i][j]);
            }
            System.out.println("");
        }
        System.out.println("-------------------------6");
        x.shift();
        for(int i=0;i<x.state.length;i++)
        {
            for(int j=0;j<x.state.length;j++)
            {
                System.out.print(x.state[i][j]);
            }
            System.out.println("");
        }
        System.out.println("-------------------------7");
        x.addRoundKey(wkey[4], wkey[5]);
        String d="";
        for(int i=0;i<x.state.length;i++)
        {
            for(int j=0;j<x.state.length;j++)
            {
                d+=x.state[j][i];
                System.out.print(x.state[i][j]);
            }
            System.out.println("");
        }
        x.x.write(d, "CypherText.txt");
    }
    public static void main(String args[])
    {
        encrypt();
        decrypt();
    }
}
