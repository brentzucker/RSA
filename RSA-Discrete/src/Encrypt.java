//have all values
//p, q, n, d
//read in from file
import java.io.*;
import java.util.Scanner;
import java.math.BigInteger;
import java.util.Random;

public class Encrypt 
{
	public static void main(String[] args)throws IOException
	{
		File file = new File("GeneratedValues.txt");
		PrintWriter printWriter = new PrintWriter("EncryptedFile.txt");
		Scanner file_scan = new Scanner(file);
		Scanner scan = new Scanner(System.in);
		Random rnd = new Random();
		
		//read in p
		BigInteger p = file_scan.nextBigInteger();
		//System.out.println("p: "+p);
		
		//read in q
		BigInteger q = file_scan.nextBigInteger();
		//System.out.println("q: "+q);
		
		//read in n
		BigInteger n = file_scan.nextBigInteger();
		//System.out.println("n: "+n);
		printWriter.println(n);
		
		//read in e
		BigInteger e = file_scan.nextBigInteger();
		
		//read in d
		// private part of the key is d
		BigInteger d = file_scan.nextBigInteger();
		//System.out.println("d: "+d);
		printWriter.println(d);
		
		file_scan.close();
		
		boolean word = false;
		boolean number = false; 
		
		while(!word && !number)
		{
			System.out.println("Type word to encrypt a word OR number to encrypt a number");
			String input_choice = scan.next();
			
			if(input_choice.equalsIgnoreCase("word"))
				word = true; 
			
			if(input_choice.equalsIgnoreCase("number"))
				number = true; 
		}
		
		// T = block to be encrypted
		if(number)
		{
			System.out.println("What number do you want to encrypt?");
			BigInteger T = scan.nextBigInteger();
			scan.close();
			
			//tell decrypt that its just decyrpting 1 number
			printWriter.println("0");
			
			BigInteger encrypted = T.modPow(e, n);
			System.out.println("encrypted: "+encrypted);
			
			printWriter.println(encrypted);
		}
		
		if(word)
		{
			System.out.println("What word do you want to encrypt?");
			String word_T = scan.next();
			scan.close();
			
			//tells decrypting its decrypting this many letters
			printWriter.println(word_T.length());
			
			BigInteger[] T_arr = new BigInteger[word_T.length()];
			
			//encrypts each letter in the word
			for(int i=0; i<T_arr.length; i++)
			{
				long ascii_value = (long)word_T.charAt(i);
				T_arr[i] = BigInteger.valueOf(ascii_value);
				
				// public key is the par of values (n,e) T is encoded by computer T^e mod n
				BigInteger encrypted = T_arr[i].modPow(e, n);
				//System.out.println(word_T.charAt(i)+" encrypted: "+encrypted);
				printWriter.println(encrypted);
			}
		}

		printWriter.close();
	}
}
