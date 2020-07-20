/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package d_e_s;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author NIGHT WOLF
 */
public class Decryption_Test {

    static int keyArray[]=new int [64];
	static int mesArray[]=new int [64];

	static int Array64[]=new int [64];
        static int Array32[]=new int [32];
	
        static int Left[][]=new int[17][32];
        static int Right[][]=new int[17][32];
    
        static FunctionFE round[]=new FunctionFE[16]; 
    
    static int FB[]=
    	{
            40,8,48,16,56,24,64,32,
            39,7,47,15,55,23,63,31,
            38,6,46,14,54,22,62,30,
            37,5,45,13,53,21,61,29,
            36,4,44,12,52,20,60,28,
            35,3,43,11,51,19,59,27,
            34,2,42,10,50,18,58,26,
            33,1,41,9,49,17,57,25
                     
    	};
    
    public static void main(String []args) throws FileNotFoundException 
    {
    	ReadKey();       
    	ReadCipher();   
    	
    	IP IP=new IP(mesArray,Array64);  
    
        
    	for(int i=0;i<64;i++)
    	{   if(i<32)
            	Left[0][i]=Array64[i];   
        	else
        		Right[0][i-32]=Array64[i]; 
    	}
    	
    	round();  
    }
    public static void round() throws FileNotFoundException
    {
    	
    	KeyGen KG=new KeyGen(keyArray); 
    	
    	
    	for(int r=0;r<=15;r++)
    	{
    		round[r]=new FunctionFE(KG.subkeys,Right,Array32 ,15-r,r); 
      
    		
    		for(int i=0;i<32;i++)
    		{
    			if(Array32[i]==Left[r][i])
    				Right[r+1][i]=0;
    			else
    				Right[r+1][i]=1;
    			
    			Left[r+1][i]=Right[r][i];
    		}
    	
    	}
    	
    	
    	for(int i=0;i<64;i++)
    		if(i<32)
    			Array64[i]=Right[16][i];
    		else
    			Array64[i]=Left[16][i-32];
     
    	int[] Result=new int[64];
    	FinalPermitation(Array64, Result);
    
    }
    
    public static void FinalPermitation (int x[],int result[]) throws FileNotFoundException
    {
        for(int i=0;i<64;i++)
            result[i]=x[FB[i]-1];
        
       PrintMessage(result);
      
    }  	  
    

    public static void PrintMessage(int[] result) throws FileNotFoundException
    {
    	PrintWriter output = new PrintWriter(new FileOutputStream("OriginalMessage.txt"));
    	String q="";
    	for(int u=0;u<64;u=u+4)
    	{
    		String s=""+result[u]+result[u+1]+result[u+2]+result[u+3];
    		int x=Integer.parseInt(s, 2);
    		q=Integer.toHexString(x);
    		output.print(q);
    	}
    	output.close(); 
    }
	
    

 public static  void ReadKey() throws FileNotFoundException 
 {
	 int q,c=0;
    File file =new File("KeyGeneration.txt") ;
    Scanner input=new Scanner(file);

    String a=input.next();
    int KeyArray[]=new int [a.length()];
    String m,k;
    
    for(int i=0;i<a.length();i++)
    {
           KeyArray[i]=Integer.parseInt(a.charAt(i)+"",16);
       
            m= Integer.toBinaryString(KeyArray[i]);
            
            if(m.length()!=4)
            {
            	for(int j=m.length();j<4;j++)
            	{
            		k="0";
            		String temp=m;
            		m=k+temp;
            	}

            }
            	for( q=0;q<4;q++)
            	{
            		if(m.charAt(q)=='0')
            		{
            			keyArray[c]=0;c++;
            		}

            		else
            		{  
            			keyArray[c]=1;c++;
            		}
            	}
    } 
  
}
 
	 public static  void ReadCipher() throws FileNotFoundException 
	 {
		  	int q,c=0;
		    File file =new File("CipherMessage.txt") ;
		    Scanner input=new Scanner(file);

		    String a=input.next();
		    int mesArray1[]=new int [a.length()];
		    String n,l;
		    
		    for(int i=0;i<a.length();i++)
		        {
		           mesArray1[i]=Integer.parseInt(a.charAt(i)+"",16);
		            n= Integer.toBinaryString(mesArray1[i]);
		            
		            if(n.length()!=4){
		            for(int j=n.length();j<4;j++)
		            {
		            	  l="0";
		                  String temp=n;
		                  n=l+temp;
		            }

		            }
		            for( q=0;q<4;q++)
		            {

		              if(n.charAt(q)=='0')
		              { mesArray[c]=0;c++;}

		              else
		              { mesArray[c]=1;c++;}
		            }
		    }
		  
 }
}
