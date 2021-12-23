
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author HT170305
 */
public class ArrayExam {
    
    public static void main(String args[]) throws IOException {
 /*       //Exam 1
         String[] num = new String[10];
         
        for (int i = 0; i < num.length; i++) {
            //System.out.println(i);
            if ( i % 2 == 1 )
            {
                num[i]= ""+i;
            }else{
                num[i]= "";
            }

        }
        for (int i = 0; i < num.length; i++) {
        
            if (!num[i].equals(""))
            {
                System.out.println(" odd number="+ num[i]);
            }

        }
        
        //Exam 2
        int[] m = new int[1000];
        for (int i = 0; i < m.length; i++) {
            m[i]=i;
        }
        
        //Exam 3
        Scanner scan = new Scanner( System.in );
        System.out.println(" Entry number=");
        int val = scan.nextInt();
        Double[] a = new Double[val];
        for (int i = 0; i < a.length; i++) {
            
            scan = new Scanner( System.in );
            System.out.println(" Entry "+i+"=");
            Double value = scan.nextDouble();
           // Double d=Double.parseDouble(br.readLine());
            a[i]=value;
        }
        
        for (int i = 0; i < a.length; i++) {
             System.out.println("value ="+ a[i]);
        }
        
        //Exam 4
         int[] x = {7,9,-1,4,12,9,3,2,-7,2,1,9,-15,24,11,13};
         int odd=0;
         int even=0;
        for (int i = 0; i < x.length; i++) {
        //System.out.println(i);
            if ( i % 2 == 1 )
            {
            even++;
            }else{
            odd++;
            }

        }
        System.out.println(" odd number="+ odd);
        System.out.println(" even number="+ even);
        
     */   
        //Exam 5
        Scanner scan = new Scanner( System.in );
        System.out.println(" Entry number=");
        int mm = scan.nextInt();
        int nn = scan.nextInt();
        
        
        int[][] mmnn = new int[mm][nn];
        for (int i = 0; i < mm; i++) {
 
            for (int j = 0; j < nn; j++) {
                scan = new Scanner( System.in );
                System.out.println(" Entry number  "+i+" X "+j+"=");
                int vx = scan.nextInt(); 
                mmnn[i][j]=vx;
            }
        }
        
        scan = new Scanner( System.in );
        System.out.println(" Entry number  to serach=");
        int s = scan.nextInt();
        if(s>mm-1){
            System.out.println(" Row not Match");
        }else{
            System.out.println(" Result");
            for (int j = 0; j < nn; j++) {
                System.out.println(" Entry number  "+mm+" X "+j+"="+mmnn[s][j]);
            }
         }
    
        
    }
}
