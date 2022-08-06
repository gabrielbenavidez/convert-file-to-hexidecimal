/*****************************
Author : Gabriel Benavidez
Purpose : To convert the given file into hex, binary, and decimal then determine RAM chip number.
*****************************/

import java.io.*;
import java.util.*;

public class FileToHex
{
	public static ArrayList <String> hex = new ArrayList <String>();
	public static ArrayList <String> binary = new ArrayList <String>();
	public static ArrayList <Long> decimal = new ArrayList <Long>();
	public static ArrayList <String> chipArray = new ArrayList <String> ();
	
	
	public void openFile(File f)//opens file 
	{
		String fileToText;
		
		try
		{
			FileReader reader = new FileReader(f);
			BufferedReader buff = new BufferedReader(reader);//create character input stream
			
			
			while((fileToText = buff.readLine()) != null)
			{
            	hex.add(fileToText); // add current line to hex array
			}

			buff.close();
			chipArray.add("chip 0");
			chipArray.add("chip 1");
			chipArray.add("chip 2");
			chipArray.add("chip 3");
			chipArray.add("chip 4");
			chipArray.add("chip 5");
			chipArray.add("chip 6");
			chipArray.add("chip 7");
				
			toBin(hex);//sending hexidecimal to convert to binary
			toDecimal(binary);//sending binary to convert to decimal
			checkChip(decimal);//checking what error the chip is in
		}

		catch(IOException e)
		{
			e.toString();//catches exception if file isn't found
		}
	}  
  
  
	public void toBin(ArrayList <String> hexi)//converts hex to binary
	{
		String bin = "";
		String [] tempArray = new String [hexi.size()];
		tempArray = hexi.toArray(tempArray);
		char character ;
		
		for (int i = 0; i < tempArray.length ;i++)//loops through array
		{
			String binString = "";

			for (int j = 0; j < tempArray[i].length(); j++)//loops through each character
			{
				character = tempArray[i].charAt(j);
				switch (character)
				{
					case '0': bin = "0000"; break;
   					case '1': bin = "0001"; break;
					case '2': bin = "0010"; break;
					case '3': bin = "0011"; break;
					case '4': bin = "0100"; break;
					case '5': bin = "0101"; break;
					case '6': bin = "0110"; break;
  					case '7': bin = "0111"; break;
					case '8': bin = "1000"; break;
					case '9': bin = "1001"; break;
					case 'A': bin = "1010"; break;
					case 'B': bin = "1011"; break;
					case 'C': bin = "1100"; break;
					case 'D': bin = "1101"; break;
					case 'E': bin = "1110"; break;
					case 'F': bin = "1111"; break;
				}
					binString = binString + bin;//adds binary numbers together

			}
					binary.add(i,binString);// adds result to binary array
		}
					
			
	}  

	
	public void toDecimal(ArrayList <String> hexi)//converst binary to decimal
	{
		int x = 0;
		int y = 0;
			
		for(x = 0; x < hexi.size(); x++)// loops through hexi array
		{ 
		
			long sum = 0;
		
				for (y = 0; y < hexi.get(x).length(); y++)//loops through each character
				{
			
					if (hexi.get(x).charAt(y) == '1')
					{
						sum = sum + (long) Math.pow(2,hexi.get(x).length() - 1 - y);//does math to convert to decimal
				
					}

				}
			
			decimal.add(sum);	//adds result to decimal array
		}	
													
	}
		
	
	public void checkChip(ArrayList <Long> deci)// checks what chip has an error
	{
		String chipError = "";
		int index = 0;
		int chipindex = 0;
			
			for (int x = 0; x < deci.size(); x++)// loops through each decimal number
			{
				long number = deci.get(x);
				
					if ( number <= 34359738368L )// conditions for each chip
					{
						chipArray.remove("chip 0");   
					}
					
					else if (number <= 68719476738L )
					{
						chipArray.remove("chip 1"); 
					}
					
					else if ( number <= 103079215108L)
					{
						chipArray.remove("chip 2"); 
					}
					
					else if (number <= 137438953478L)
					{
						chipArray.remove("chip 3");  
					}
					
					else if (number <= 171798691848L)
					{
						chipArray.remove("chip 4");  
					}
					
					else if (number <= 206158430218L)
					{
						chipArray.remove("chip 5");  
					}
					
					else if (number <= 240518168588L)
					{
						chipArray.remove("chip 6");  
					}
					
					else if (number <= 274877906958L)
					{
					 	chipArray.remove("chip 7");   
					}
					else
					{
						chipindex = index;
					}
				index ++;
			}

			for (int i = 0; i < chipArray.size(); i++)
			{
				chipError = chipArray.get(0);	
			}
						
		//prints out results
		System.out.printf("%s %74s", "Error", "Found at");
		System.out.printf("\n%s = %s = %d = %s\n", hex.get(chipindex),  binary.get(chipindex),  decimal.get(chipindex), chipError);
	}


	public static void main(String args[])
    {
        FileToHex fileToHex = new FileToHex();
 		String filename = "RAMerrors8x4";//name of file
		File file = new File (filename);//creating new file
		
		fileToHex.openFile(file);//sending file to open method
    }  

}
    
