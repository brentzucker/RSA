import java.util.Scanner;
import java.io.*;
import java.math.BigInteger;

public class Decrypt 
{
	public static void main(String[] args)throws IOException
	{
		File encrypted_file = new File("EncryptedFile.txt");
		Scanner file_scan = new Scanner(encrypted_file);
		
		//read n
		BigInteger n = file_scan.nextBigInteger();
		
		//read d
		//private part of the key is d
		BigInteger d = file_scan.nextBigInteger();
		
		//read in how many lines / number or word
		int lines = file_scan.nextInt();
		
		//recieved message: =T^e mod n
		//decoded by: =(T^e mod n)^d mod n 
		//=(T^e)^d mod n
		//T^e mod n with d*e = 1 mod eu_phi

		if(lines == 0)
		{
			//read in encrypted code
			BigInteger encrypted = file_scan.nextBigInteger();
			
			file_scan.close();
			
			//decrypt
			// decode T^d mod n
			BigInteger decrypted = encrypted.modPow(d, n);
			
			System.out.println("decrypted: "+decrypted);
		}
		else
		{
			String output = "";
			for(int i=0; i<lines; i++)
			{
				//read in encrypted code
				BigInteger encrypted = file_scan.nextBigInteger();

				//decrypt
				// decode T^d mod n
				BigInteger decrypted = encrypted.modPow(d, n);
				
				int c = decrypted.intValue();
				
				String letter = ""+(char)c;
				
				output += letter;
				
				System.out.println(letter+" decrypted: "+decrypted);
			}
			
			System.out.println("message decrypted: "+output);
		}
		
		
		file_scan.close();
		
	}
}
