//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.*;
import java.io.*;
import java.lang.Math;
	//import java.util.Scanner;
	public  class ReadFile
	{
		public static void main(String[] args)
		{try {
			
		// Making instance of the class to read file from the location
			BufferedReader reader=new BufferedReader(new FileReader("F:\\Java Programs\\java lab Assignment 1\\HW1-data.txt"));
			
			//Making instance of the class to write file from the location
			FileWriter g_file=new FileWriter("F:\\Java Programs\\java lab Assignment 1\\HW1-output-XXXXX.txt");
			BufferedWriter g_write=new BufferedWriter(g_file);
			//Writing the initial lines
			String init="Stdnt Id  Ex  ------- Assignments --------   Tot  Mi  Fin  CL  Pts  Pct  Gr";
			String int2="--------  --  ----------------------------   ---  --  ---  --  ---  ---  --";
			g_write.write(init);
			g_write.newLine();
			//newLine() to go to the next line
			g_write.write(int2);
			g_write.newLine();
			//Creating an array to stor count of grades 0-A, 1-B....
			 int grades[]=new int[5];
			 
			String line=reader.readLine();//Reading file as a string line by line
			//While all the lines of the file are not read
			while(line!=null)
			{
					System.out.println(line);
					              
					     //converting string to an object(integers) to perform computations
						
			            Read2 obj=convertStringtoObj(line,grades);
			             //Converting the computed object to string so as to write on file
			             String output=convertToString(obj);
			             g_write.write(output);
			             g_write.newLine();
			             
			             line=reader.readLine();
	        }
			//To find avg percentage
			  int a=  Read2.totpts/Read2.count;
			  //Writing average percentage to output file
			  	String str1="Average total percent of all students= "+Integer.toString(a);
		         g_write.write(str1);
		          g_write.newLine();
		          g_write.newLine();
		      //Writing grades to output file
			      str1="Number of A's= "+ Integer.toString(grades[0]);
		          g_write.write(str1);
		          g_write.newLine();
		          str1="Number of B's= "+ Integer.toString(grades[1]);
		          g_write.write(str1);
		          g_write.newLine();
		          str1="Number of C's= "+ Integer.toString(grades[2]);
		          g_write.write(str1);
		          g_write.newLine();
		          str1="Number of D's= "+ Integer.toString(grades[3]);
		          g_write.write(str1);
		          g_write.newLine();
		          str1="Number of E's= "+ Integer.toString(grades[4]);
		          g_write.write(str1);
		          g_write.newLine();
		          g_write.newLine();
		      //Writing maximum pts to output file
		          str1="Maximum points= "+ Integer.toString(Read2.maxpts);
		          g_write.write(str1);
		          g_write.newLine();
		          
		         //Closing both reader and writer
		          reader.close(); 
		          g_write.close();
		
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	
		}
	
	
	public static Read2 convertStringtoObj(String line,int[] grade)
	{
		
		String []str=line.split("\\s+");
		//Whenever we encounter one or more white spaces the string is split
		int i=0;//int count=14;
		Read2 obj=new Read2();
		//creating a new object
			
		obj.id=str[0];
		
			//parseInt - to convert string to integer
		obj.ex=Integer.parseInt(str[1]);
			
			for(i=2;i<=11;i++)
				{

				 obj.asign[i-2]=Integer.parseInt(str[i]);
				}
			
			
				obj.mi=Integer.parseInt(str[12]);
			
			//Giving all the integers values
				obj.fi=Integer.parseInt(str[13]);
			
			
				obj.cl=Integer.parseInt(str[14]);
				int sum=0;
				//Doing computation here
				for(int j=0;j<10;j++)
					sum+=obj.asign[j];
				obj.tot=sum;
				obj.pts=obj.tot+obj.mi+obj.fi+obj.cl+obj.ex;
				//To maintain maximum count
				if(Read2.maxpts<obj.pts)
					Read2.maxpts=obj.pts;
				//Rounding off percentage
				float percent=obj.pts*100/420;
				obj.pct=(Math.round(percent));
				(Read2.totpts)+=obj.pct;
				//Assigning grades and counting them
				if(obj.pct>=90)
					{
					obj.gr='A';grade[0]++;//(Read2.numa)++;
					}
					
				else if(obj.pct<=89 && obj.pct>=78)
					{
					obj.gr='B'; grade[1]++; //(Read2.numb)++;
					}
					
				else if(obj.pct<=77 && obj.pct>=62)
					{
					obj.gr='C';grade[2]++;//(Read2.numc)++;
					
					}
				else if(obj.pct<=61 && obj.pct>=46)
					{obj.gr='D';grade[3]++;//(Read2.numd)++;
					}
				else if(obj.pct>=45)
					{obj.gr='E';grade[4]++;//(Read2.nume)++;
					}
					
			
			
			
				(Read2.count)++;
		//Returning object
		return obj;
	}
	
	
	static String convertToString(Read2 obj)
	{
		//Converting the object to string
		String line="";
		String str=obj.id;
		//Finding length and then appending 0 if needed in id
		int si=str.length();
		while(si<8)
			str= '0'+str;
		line+=str;
		line+="  ";
		str=Integer.toString(obj.ex);
		line+=str;
		line+="  ";
		//For array
		for(int i=0;i<10;i++)
		{
			str=Integer.toString(obj.asign[i]);
			if(str.length()==1)
				str=" "+str;
			line=line+str+" ";
		}
		line+=" ";
		str=Integer.toString(obj.tot);

		line+=str;
		line+="  ";
		str=Integer.toString(obj.mi);
		line+=str;
		line+="   ";
		str=Integer.toString(obj.fi);
		line+=str;
		line+="  ";
		//Checking for allignment
		str=Integer.toString(obj.cl);
		if(str.length()==1)
			str=" "+str;
		line+=str;
		line+="  ";
		str=Integer.toString(obj.pts);
		line+=str;
		line+="   ";
		str=Integer.toString(obj.pct);
		line+=str;
		line+="  ";
		//str=obj.gr;
		line+=" "+obj.gr;
		line+="  ";
		return line;	
		//Finally returning the string made out of object
	}
	}