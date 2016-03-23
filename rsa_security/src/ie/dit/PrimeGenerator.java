/* Assignment for Security module
 * Jonathan Riordan
 * C13432152
 */

package ie.dit;

import java.math.BigInteger;
import java.security.SecureRandom;


public class PrimeGenerator {
	int divisor = 1;
	BigInteger p, q, n, k, d, z, e;
	private SecureRandom r = new SecureRandom();
	private static SecureRandom r2 = new SecureRandom();
	private static int bitSize = 128;
	BigInteger gcd;
	
	PrimeGenerator() {
		
		p = generatePrime(divisor);
		q = generatePrime(divisor);

		
		n = p.multiply(q);
		
		z = (p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE)));
		
		divisor = 2;
		e = generatePrime(divisor);
		
		gcd = GCD(z,e);
		
		while(gcd.compareTo(BigInteger.ONE) > 0 && e.compareTo(z) < 0) {
			e.add(BigInteger.ONE);
		}

        //d = modInverseFunction(e,z);
        d = e.modInverse(z);			
	}
	
//	public static BigInteger modInverseFunction(BigInteger e, BigInteger z) {
//		int comp;
//		BigInteger x = new BigInteger("0");
//		BigInteger y = new BigInteger("1");
//		BigInteger p = new BigInteger("1");
//		BigInteger q = new BigInteger("0");
//		BigInteger quot, temp;
//		
//		while(!z.equals(BigInteger.ZERO)) {
//			quot = e.divide(z);
//			temp = e;
//			z = temp.mod(z);
//			
//			temp = x;
//			x = p.subtract((quot.multiply(x)));
//
//			
//			y = q.subtract((quot.multiply(y)));
//
//		}
//		
//		return z;
//	}
	
	// method to get gcd of two numbers.
	public static BigInteger GCD(BigInteger z, BigInteger e) {

		int compare;
		
		if(z.equals(BigInteger.ZERO)) {
			return e;
		}
		
		BigInteger temp = z.mod(e);
		z = e;
		e = temp;
		
		while(!e.equals(BigInteger.ZERO)) {
			temp = z.mod(e);
			z = e;
			e = temp;
		}

// 		
//		while(!e.equals(BigInteger.ZERO)) {
//			compare = z.compareTo(e);
//			if(compare == 1) {
//				z = z.subtract(e);
//			} 
//			else {
//				e = e.subtract(z);
//			}
//		}
		
		
		return z;
	}
	
	public static void main(String[] args) {
		System.out.println("GCD");
		
//		//BigInteger a = BigInteger.probablePrime(bitSize, r2);
		BigInteger a = new BigInteger("7");
//		//BigInteger b = BigInteger.probablePrime(bitSize, r2);
		BigInteger b = new BigInteger("20");
		
		
		
		//BigInteger gcd = GCD(a,b);
		//BigInteger md = modInverseFunction(a,b);
		System.out.println("A: " + a);
		System.out.println("B: " + b);
		//System.out.println("mod: " + md);
	}
		
	// Method to create a biginteger. A random number the size if the bit size is stored in the 
	// primeNum variable.This varaible is then passed to the isPrime method to check if it is prime.
	private BigInteger generatePrime(int divisor) {
		boolean prime = true;
		BigInteger primeNum = null;
		
		while(prime == true) {
			primeNum = new BigInteger(bitSize / divisor, r);
			//System.out.println(primeNum.toString());
			
			if(numberPrime(primeNum)) {
				break;
			}
			
		}
		return primeNum;
	}



	// Checks to see if the number is prime with a certainity of 1. 
	// if it is, return true
	// else it return false.
	private boolean numberPrime(BigInteger primeNum) {
		if(primeNum.isProbablePrime(1)) {
			return true;
		}
		else {
			return false;
		}
	}



	// the keys for the RSA for the program
	public BigInteger[] keys() {
		BigInteger[] keys = new BigInteger[3];
		// public key n
		keys[0] = n;
		
		// private key d
		keys[1] = d;
		
		// public key component
		keys[2] = e;
		
		return keys;
	}
		

}
	
	

