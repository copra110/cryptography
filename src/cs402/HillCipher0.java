package cs402;
import java.util.Scanner;
/**
 *
 * @author Deel
 */
public class HillCipher0
{
    static Scanner in=new Scanner(System.in);
    static String Message,Cipher="",ke;
    static int key[][]=new int[2][2];//{{1,1},{2,2}};//new int [2][2];
    static double keyInverse[][]=new double [2][2];
    static int a[][]=new int[2][2];
    static int c[][]=new int [2][2];
    static int choose=0;
    public static void main(String args[])
    {

        ReadInput();
        CorrectLength();
       DisplayKey();
        if(choose==1)
        Encryption();
        else if(choose==2)
        Encryption();
        System.out.println("Result is : "+Cipher);
    }
     static void ReadInput()
    {
         System.out.print("Please enter Message : ");
     Message=in.next();
     System.out.print("Please enter key : ");
    // ke=in.next();
    for(int i=0;i<2;i++)
         for(int j=0;j<2;j++)
     key[i][j]=in.nextInt();
     /*key[0][0]=ke.charAt(0);
           key[0][1]=ke.charAt(1);
             key[1][0]=ke.charAt(2);
             key[1][1]=ke.charAt(3);*/
             System.out.print("To Encrypt press \'1\' To Decrypt press \'2\' : ");
             choose=in.nextInt();
    }
     static void CorrectLength()
    {
         if(Message.length()%2!=0)
             Message+='x';
     }

    private static void Encryption()
    {
        for(int m=0;m<Message.length();m+=2)
        {
            a[0][1]=a[1][1]=0;
            a[0][0]=(int)Message.charAt(m)-97;
            a[1][0]=(int)Message.charAt(m+1)-97;
            for (int i = 0; i < 2; i++)
                {
                    for (int j = 0; j < 2; j++)
                     {
                        c[i][j] = 0;
                            for (int k = 0; k <2; k++)
                                
                                    c[i][j]=c[i][j]+ key[i][k] *a[k][j];
                                
                     }
                }
            Cipher+=(char)((c[0][0]%26)+97);
            Cipher+=(char)((c[1][0]%26)+97);
            

        }


    }

    private static void Decryption()
    {
      CalculateKeyInverse();
        for(int m=0;m<Message.length();m+=2)
            {
                a[0][1]=a[1][1]=0;
                a[0][0]=(int)Message.charAt(m)-97;
                a[1][0]=(int)Message.charAt(m+1)-97;
                    for (int i = 0; i < 2; i++)
                        {
                            for (int j = 0; j < 2; j++)
                                {
                                    c[i][j] = 0;
                                    for (int k = 0; k <2; k++)
                                        {
                                            c[i][j] += keyInverse[i][k] *a[k][j];
                                        }
                                }
                        }
                Cipher+=(char)((c[0][0]%26)+97);
                Cipher+=(char)((c[1][0]%26)+97);
            }

    }
    static void CalculateKeyInverse()
    {
        double x=1/((key[0][0]*key[1][1])-(key[0][1]*key[1][0]));
        System.out.println("X="+x);
        keyInverse[0][0]=x*key[1][1];
        keyInverse[0][1]=-x*key[0][1];
        keyInverse[1][0]=-x*key[1][0];
        keyInverse[1][1]=x*key[0][0];
    }
    static void DisplayKey()
    {
        System.out.println("Matrix of key is:");
    for(int i=0;i<2;i++)
    {
        for(int j=0;j<2;j++)
            System.out.print(key[i][j]);
        System.out.println();
        }
    
    }
    static void DisplayInverse()
    {
    for(int i=0;i<2;i++)
    {
        for(int j=0;j<2;j++)
            System.out.print(keyInverse[i][j]);
        System.out.println();
        }
    System.out.println();
    }
}