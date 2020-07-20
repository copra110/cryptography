
package d_e_s;
public class FunctionFE
{
     int EPermutation[]=
     {
                 32,    1,   2,    3,   4,    5,
                  4,    5,   6,    7,   8,    9,
                  8,    9,  10,   11,   12,   13,
                 12,   13,  14,   15,   16,   17,
                 16,   17,  18,   19,   20,   21,
                 20,   21,  22,   23,   24,   25,
                 24,   25,  26,   27,   28,   29,
                 28,   29,  30,   31,   32,   1
      };
     
     int Expansion_Result[]=new int [48];
     int XOR[]=new int [48];
     int Sbox[]=new int[32];
  
    public FunctionFE(int key[][],int Right[][],int Final[],int k,int r)
    {
    	 
        for(int i=0;i<48;i++)
            Expansion_Result[i]=Right[r][EPermutation[i]-1];
        
        
        for(int i=0;i<48;i++)
            if(Expansion_Result[i]==key[k][i])   
                XOR[i]=0;
            else
                XOR[i]=1;
        
        
        SBOX B=new SBOX(XOR,Sbox);
        P p=new P(Sbox, Final);
    }
}
