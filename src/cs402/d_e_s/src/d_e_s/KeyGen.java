
package d_e_s;
public class KeyGen 
{	
	 int PC1[]=new int [56];
	 int PC2[]=new int [48];

	 int Array1[]=new int [64];
	 int Array2[]=new int [56];
	
	 int [] left = new int [28];
	 int [] right = new int [28];
	
	 int subkeys[][]=new int [16][48];
	
	 int round[]={1,1,2,2,2,2,2,2,1,2,2,2,2,2,2,1};
	
	public KeyGen(int k[])
	{
	        for(int i=0;i<64;i++)
	        {
	        	Array1[i]=k[i];	        	
	        }
	        
	    	PermutedChoiceOne(); 
	        Split(); 			
	        GenerateKey();		  
	        
    }
	
	public void PermutedChoiceOne()
    {
        int P[]={57,49,41,33,25,17,9,
        		 1,58,50,42,34,26,18,
        		 10,2,59,51,43,35,27,
        		 19,11,3,60,52,44,36,
        		 63,55,47,39,31,23,15,
        		 7,62,54,46,38,30,22,
        		 14,6,61,53,45,37,29,
        		 21,13,5,28,20,12,4};

        for(int i=0;i<56;i++)
        {
           PC1[i]=Array1[P[i]-1];
        }
    }
	
	public void Split()
    {
		for(int i=0;i<28;i++)
		{
			left[i]=PC1[i];
		}
		
		for(int j=28;j<56;j++)
		{
			right[j-28]=PC1[j];
		}
       
    }
	
	 public void GenerateKey()
	 {
             System.out.print("c0"+": ");
	    	    for(int k=0;k<left.length;k++)
                    {
                        System.out.print(left[k]);
                    }
                    System.out.println("");
                    System.out.print("D0"+": ");
	    	    for(int k=0;k<right.length;k++)
                    {
                        System.out.print(right[k]);
                    }
                    System.out.println("");
	       for(int i=1;i<17;i++)
	       {
                   Shift(left,round[i-1]);        
	    	   Shift(right,round[i-1]);   
	    	    System.out.print("c"+i+": ");
	    	    for(int k=0;k<left.length;k++)
                    {
                        System.out.print(left[k]);
                    }
                    System.out.println("");
                    System.out.print("D"+i+": ");
	    	    for(int k=0;k<right.length;k++)
                    {
                        System.out.print(right[k]);
                    }
                    System.out.println("");
	    	    for(int t=0;t<28;t++)
	    		{
	    			Array2[t]=left[t];
	    		}
	    		
	    		for(int j=28;j<56;j++)
	    		{
	    			Array2[j]=right[j-28];
	    		}
	    		
	            PermutedChoiceTwo();
	            for(int k=0;k<48;k++)
	            { 
	            	subkeys[i-1][k]=PC2[k];
	            }   
	        }
               for(int i=0;i<subkeys.length;i++)
               {
                   System.out.print("K"+(i+1)+": ");
                   for(int j=0;j<subkeys[i].length;j++)
                   {
                       System.out.print(subkeys[i][j]);
                   }
                   System.out.println("");
               }
    }
	 
	public void Shift(int x[],int roundNumber)
    {
         int m1,m2;
         if(roundNumber==1)
         {
            m1=x[0];
            for(int i=1;i<x.length;i++)
            {
                x[i-1]=x[i];
            }
             x[x.length-1]=m1;
         }
         
         if(roundNumber==2)
         {
             m1=x[0];
             m2=x[1];
             for(int i=2;i<x.length;i++)
             {
                 x[i-2]=x[i];
             }
             x[x.length-1]=m2;
             x[x.length-2]=m1;
         }
    }
	    
	public void PermutedChoiceTwo()
    {
        	int P[]={14,17,11,24,1,5,3,28,
            		 15,6,21,10,23,19,12,4,
            		 26,8,16,7,27,20,13,2,
            		 41,52,31,37,47,55,30,40,
            		 51,45,33,48,44,49,39,56,
            		 34,53,46,42,50,36,29,32};
            
            for(int i=0;i<48;i++)
            {
                
                PC2[i]=Array2[P[i]-1];
            }
            
     }

	public void DisplaySubkeys()
	{
		for(int i=0;i<16;i++)
		{
			int h=i+1;
			System.out.print("K"+h+" = ");

			for(int j=0;j<48;j++)
            {
				if(j%6==0)
				{
		            System.out.print(" "+subkeys[i][j]);
				}
				else
				{
					System.out.print(subkeys[i][j]);
				}
			}
			
			System.out.println("");
        }
	}
}