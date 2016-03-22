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
	private static int bitSize = 128;
	
	PrimeGenerator() {
		
		p = generatePrime(divisor);
		q = generatePrime(divisor);
//		p = BigInteger.probablePrime(bitSize, r);
//		q = BigInteger.probablePrime(bitSize, r);
		
		n = p.multiply(q);
		
		z = (p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE)));
		
		divisor = 2;
		e = generatePrime(divisor);
//		e = BigInteger.probablePrime(bitSize, r);
		
        while (z.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(z) < 0)
        {
            e.add(BigInteger.ONE);
        }
        d = e.modInverse(z);			
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
	
	

