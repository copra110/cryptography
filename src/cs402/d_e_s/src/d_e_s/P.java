
package d_e_s;
public class P
{
    int Permutation[]={16,7,20,21,29,12,28,17,
    				   1,15,23,26,5,18,31,10,
    				   2,8,24,14,32,27,3,9,
    				   19,13,30,6,22,11,4,25};

    public P(int x[],int m[])
    {        
        for(int i=0;i<32;i++)
        {
        	m[i]=x[Permutation[i]-1];
        }

    }
}
