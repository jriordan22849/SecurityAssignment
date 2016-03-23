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

	// Generate new keys.
	public RSA() {
		PrimeGenerator generator = new PrimeGenerator();
		RSA_Keys = new BigInteger[3];
		RSA_Keys = generator.keys();
		
		N = RSA_Keys[0];
		D = RSA_Keys[1];
		E = RSA_Keys[2];
	}
	
	
	// Method to create new keys and encrypt message and return a biginteger.
	public static BigInteger encyptFunction(BigInteger message)
	{
		PrimeGenerator generator = new PrimeGenerator();
		RSA_Keys = new BigInteger[3];
		
		RSA_Keys = generator.keys();
		
		N = RSA_Keys[0];
		D = RSA_Keys[1];
		E = RSA_Keys[2];
		
		
		return message.modPow(RSA_Keys[2], RSA_Keys[0]);
	}
	
	// Decrypt biginteger and return byte array
	public static byte[] decryptionFunction(BigInteger encrypted)
	{
	
		return encrypted.modPow(D, N).toByteArray();
	}
	
	// method to decrypt file, the encrypted message and keys are passed in and are used to decrypt.
	public static byte[] decryptionFileFunction(BigInteger encrypted, BigInteger D, BigInteger N)
	{
	
		return encrypted.modPow(D, N).toByteArray();
	}
}