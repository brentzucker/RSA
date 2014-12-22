import java.util.Scanner;
import java.math.BigInteger;
import java.util.Random;
import java.io.*;

public class Generate 
{
	public static BigInteger encode_key; 
	public static BigInteger decode_key;
	public static BigInteger mod_key;
	
	public static void main(String[] args)throws IOException
	{
		PrintWriter printWriter = new PrintWriter("GeneratedValues.txt");
		Scanner scan = new Scanner(System.in);
		//BigInteger e = new BigInteger("5");
		
		//ask how big how many bits you want the key to be
		System.out.println("How many bits do you want the key to be? - 330 is 100 digits");
		int bits_input = Integer.parseInt(scan.next());
		
		//1.) generate 2 large prime numbers p&q at random 
		Random rnd = new Random();
		BigInteger p = BigInteger.probablePrime(bits_input, rnd);
		printWriter.println(p);
		BigInteger q = BigInteger.probablePrime(bits_input, rnd);
		printWriter.println(q);
		
		System.out.println("p "+p+"\n"+"q: "+q);
		
		//n = p*q
		BigInteger n = p.multiply(q);
		printWriter.println(n);
		
		System.out.println("n: "+n);
		
		//3.) compute euler phi, eu_phi = (p-1)(q-1)
		
		BigInteger eu_phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));

		//divide eu_phi by gcd of p-1, q-1
		//int t = ((p-1)*(q-1)) / gcd(p-1, q-1)
		BigInteger t = eu_phi.divide(p.subtract(BigInteger.ONE).gcd(q.subtract(BigInteger.ONE)));

		
		//4.) choose e 1<e<eu_phi where e and eu_phi are relatively prime ... gcd(e, eu_phi) == 1
		
		int e_bit_count = 0;
		while(e_bit_count < 2)
		{
			e_bit_count = rnd.nextInt(p.bitCount()) + 1;
		}

		BigInteger e = BigInteger.probablePrime(e_bit_count, rnd);
		printWriter.println(e);
		
		//5.) linear combination of e and eu_phi      d*e + f*eu_phi = 1 or d*e = 1 mod eu_phi
		//check that modInverse doesn't throw an error
		BigInteger d = new BigInteger("0"); 
		while(d.equals(BigInteger.ZERO))
		{
			try
			{
				d = e.modInverse(t);
			}
			catch(ArithmeticException a_e)
			{
				System.out.println(" if m ≤ 0, or this BigInteger has no multiplicative inverse mod m (that is, this BigInteger is not relatively prime to m).");
			}
		}
		
		printWriter.println(d);
		printWriter.close();
		
	}

}
