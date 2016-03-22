/* Assignment for Security module
 * Jonathan Riordan
 * C13432152
 */

package ie.dit;

import java.math.BigInteger;
import java.util.Scanner;

public class RSA {
	
	
	public static BigInteger[] RSA_Keys = null;
	static byte[] encrypted;
	static BigInteger E;
	static BigInteger D;
	static BigInteger N;

	

	public RSA() {
		PrimeGenerator generator = new PrimeGenerator();
		RSA_Keys = new BigInteger[3];
		RSA_Keys = generator.keys();
		
		N = RSA_Keys[0];
		D = RSA_Keys[1];
		E = RSA_Keys[2];
	}
	
	public void main(String[] args) {

		RSA rsa = new RSA();
		Scanner scanner = new Scanner(System.in);
		PrimeGenerator generator = new PrimeGenerator();
		RSA_Keys = new BigInteger[3];
		
		RSA_Keys = generator.keys();
		
		System.out.println("Public key N: " + RSA_Keys[0]);
		System.out.println("Bit Length of key N: " + RSA_Keys[0].bitLength());
		
		System.out.println("Private key D: " + RSA_Keys[1]);
		System.out.println("Bit Length of key D: " + RSA_Keys[1].bitLength());
		
		System.out.println("Public conponent E: " + RSA_Keys[2]);
		System.out.println("Bit Length of E: " + RSA_Keys[2].bitLength());
		
		N = RSA_Keys[0];
		D = RSA_Keys[1];
		E = RSA_Keys[2];
		
		
		System.out.println("Enter in message");
		String plaintext = scanner.nextLine();
		

	//	encrypted = rsa.encyptFunction(plaintext.getBytes());

	
		byte[] decypted = rsa.decryptFunction(encrypted);
		System.out.println("String in bytes " + bytesToString(plaintext.getBytes()));
		System.out.println("Decrypting Bytes: " + bytesToString(decypted));
        System.out.println("Decrypted String: " + new String(decypted));
        
        System.out.println("\n\n\n\n\n");
	
	}
	

	

	public static byte[] decryptFunc(BigInteger decryptedMessageBig) {		
		return decryptedMessageBig.modPow(RSA_Keys[1], RSA_Keys[0]).toByteArray();
	}
	

	private byte[] decryptFunction(byte[] message) {
		return (new BigInteger(message)).modPow(RSA_Keys[1], RSA_Keys[0]).toByteArray();
	}
	

	
	public static BigInteger encyptFunction(BigInteger message)
	{
		RSA rsa = new RSA();
		PrimeGenerator generator = new PrimeGenerator();
		RSA_Keys = new BigInteger[3];
		
		RSA_Keys = generator.keys();
		
		N = RSA_Keys[0];
		D = RSA_Keys[1];
		E = RSA_Keys[2];
		
		
		return message.modPow(RSA_Keys[2], RSA_Keys[0]);
	}
	
	public static byte[] decryptionFunction(BigInteger encrypted)
	{
	
		return encrypted.modPow(D, N).toByteArray();
	}
	
	public static byte[] decryptionFileFunction(BigInteger encrypted, BigInteger D, BigInteger N)
	{
	
		return encrypted.modPow(D, N).toByteArray();
	}
	 
	public static String bytesToString(byte[] message)
    {
		String test = "";
	    for (byte b : message)
	    {
	    	test += Byte.toString(b);
	    }
	    return test;
	}
}