package cs402;
/**
 *
 * @author NIGHT WOLF
 */
public class Sbox 
{
    public static void main(String []args)
    {
        byte k[]={0,0,0,0,0,0,0,1,0,0,1,0,0,0,1,1,0,1,0,0,0,1,0,1,0,1,1,0,0,1,1,1,1,0,0,0,1,0,0,1,1,0,1,0,0,0,0,0,0};
        Sbox x=new Sbox();
        byte r[]=x.sboxs(k);
        for(int i=0;i<r.length;i++)
        {
            System.out.print(r[i]+",");
        }
    }
    int s1[][]={{14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7},
        {0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8},
        {4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0},
        {15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13}};
    int s2[][]={{15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10},
        {3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5},
        {0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15},
        {13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9}};
    int s3[][]={
        {10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8},
        {13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1},
        {13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7},
        {1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12}};
    int s4[][]={{7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15},
        {13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9},
        {10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4},
        {3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14}};
    int s5[][]={{2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9},
        {14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6},
        {4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14},
        {11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3}};
        
    int s6[][]={{12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11},
        {10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8},
        {9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6},
        {4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13}};
        
  int s7[][]={{4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1},
        {13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6},
        {1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2},
        {6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12}};
        
  int s8[][]={{13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7},
        {1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2},
        {7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8},
        {2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11}};
  public byte[] sboxs(byte x[])
  {
      byte r[]=new byte[32];
      for(int i=1;i<8;i++)
      {
          switch (i){
                  case 1:
                  {
                      int row=x[5]*2+x[0];
                      int coulumn=8*x[4]+4*x[3]+2*x[2]+x[1];
                      int t=s1[row][coulumn];
                      String b=Integer.toBinaryString(t);
                      if(b.length()<4)
                      {
                          for(int j=0;j<4-b.length()+1;j++)
                          {
                              b="0"+b;
                          }
                      }
                      r[0]=(byte)Integer.parseInt(b.charAt(0)+"");
                      r[1]=(byte)Integer.parseInt(b.charAt(1)+"");
                      r[2]=(byte)Integer.parseInt(b.charAt(2)+"");
                      r[3]=(byte)Integer.parseInt(b.charAt(3)+"");
                  }
                  case 2:
                  {
                      int row=x[11]*2+x[6];
                      int coulumn=8*x[10]+4*x[9]+2*x[8]+x[7];
                      int t=s2[row][coulumn];
                      String b=Integer.toBinaryString(t);
                      if(b.length()<4)
                      {
                          for(int j=0;j<4-b.length()+1;j++)
                          {
                              b="0"+b;
                          }
                      }
                      r[4]=(byte)Integer.parseInt(b.charAt(0)+"");
                      r[5]=(byte)Integer.parseInt(b.charAt(1)+"");
                      r[6]=(byte)Integer.parseInt(b.charAt(2)+"");
                      r[7]=(byte)Integer.parseInt(b.charAt(3)+"");
                  }
                  case 3:
                  {
                      int row=x[17]*2+x[12];
                      int coulumn=8*x[10]+4*x[9]+2*x[8]+x[7];
                      int t=s3[row][coulumn];
                      String b=Integer.toBinaryString(t);
                      if(b.length()<4)
                      {
                          for(int j=0;j<4-b.length()+2;j++)
                          {
                              b="0"+b;
                          }
                      }
                      r[8]=(byte)Integer.parseInt(b.charAt(0)+"");
                      r[9]=(byte)Integer.parseInt(b.charAt(1)+"");
                      r[10]=(byte)Integer.parseInt(b.charAt(2)+"");
                      r[11]=(byte)Integer.parseInt(b.charAt(3)+"");
                  }
                  case 4:
                  {
                      int row=x[23]*2+x[18];
                      int coulumn=8*x[22]+4*x[21]+2*x[20]+x[19];
                      int t=s4[row][coulumn];
                      String b=Integer.toBinaryString(t);
                      if(b.length()<4)
                      {
                          for(int j=0;j<4-b.length()+1;j++)
                          {
                              b="0"+b;
                          }
                      }
                      r[12]=(byte)Integer.parseInt(b.charAt(0)+"");
                      r[13]=(byte)Integer.parseInt(b.charAt(1)+"");
                      r[14]=(byte)Integer.parseInt(b.charAt(2)+"");
                      r[15]=(byte)Integer.parseInt(b.charAt(3)+"");
                  }
                  case 5:
                  {
                      int row=x[29]*2+x[24];
                      int coulumn=8*x[28]+4*x[27]+2*x[26]+x[25];
                      int t=s5[row][coulumn];
                      String b=Integer.toBinaryString(t);
                      if(b.length()<4)
                      {
                          for(int j=0;j<4-b.length()+1;j++)
                          {
                              b="0"+b;
                          }
                      }
                      r[16]=(byte)Integer.parseInt(b.charAt(0)+"");
                      r[17]=(byte)Integer.parseInt(b.charAt(1)+"");
                      r[18]=(byte)Integer.parseInt(b.charAt(2)+"");
                      r[19]=(byte)Integer.parseInt(b.charAt(3)+"");
                  }
                  case 6:
                  {
                      int row=x[35]*2+x[30];
                      int coulumn=8*x[34]+4*x[33]+2*x[32]+x[31];
                      int t=s6[row][coulumn];
                      String b=Integer.toBinaryString(t);
                      if(b.length()<4)
                      {
                          for(int j=0;j<4-b.length()+1;j++)
                          {
                              b="0"+b;
                          }
                      }
                      r[20]=(byte)Integer.parseInt(b.charAt(0)+"");
                      r[21]=(byte)Integer.parseInt(b.charAt(1)+"");
                      r[22]=(byte)Integer.parseInt(b.charAt(2)+"");
                      r[23]=(byte)Integer.parseInt(b.charAt(3)+"");
                  }
                  case 7:
                  {
                      int row=x[41]*2+x[36];
                      int coulumn=8*x[40]+4*x[39]+2*x[38]+x[37];
                      int t=s7[row][coulumn];
                      String b=Integer.toBinaryString(t);
                      if(b.length()<4)
                      {
                          for(int j=0;j<4-b.length()+1;j++)
                          {
                              b="0"+b;
                          }
                      }
                      r[24]=(byte)Integer.parseInt(b.charAt(0)+"");
                      r[25]=(byte)Integer.parseInt(b.charAt(1)+"");
                      r[26]=(byte)Integer.parseInt(b.charAt(2)+"");
                      r[27]=(byte)Integer.parseInt(b.charAt(3)+"");
                  }
                  case 8:
                  {
                      int row=x[47]*2+x[40];
                      int coulumn=8*x[40]+4*x[39]+2*x[38]+x[37];
                      int t=s8[row][coulumn];
                      String b=Integer.toBinaryString(t);
                      if(b.length()<4)
                      {
                          for(int j=0;j<4-b.length()+2;j++)
                          {
                              b="0"+b;
                          }
                      }
                      r[28]=(byte)Integer.parseInt(b.charAt(0)+"");
                      r[29]=(byte)Integer.parseInt(b.charAt(1)+"");
                      r[30]=(byte)Integer.parseInt(b.charAt(2)+"");
                      r[31]=(byte)Integer.parseInt(b.charAt(3)+"");
                  }
          }
      }
      return r;
  }
}
