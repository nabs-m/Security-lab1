/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crypto;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.Cipher;
import java.security.Key;

import java.security.NoSuchAlgorithmException;
import java.security.InvalidKeyException;
import java.security.InvalidAlgorithmParameterException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

import sun.misc.BASE64Encoder;

/**
 * @author Joe Prasanna Kumar
 * This program provides the following cryptographic functionalities
 * 1. Encryption using AES
 * 2. Decryption using AES
 * 
 * High Level Algorithm :
 * 1. Generate a DES key (specify the Key size during this phase) 
 * 2. Create the Cipher 
 * 3. To Encrypt : Initialize the Cipher for Encryption
 * 4. To Decrypt : Initialize the Cipher for Decryption
 * 
 * 
 */

public class SymmetricEncrypt {
			
		String strDataToEncrypt = new String();
		String strCipherText = new String();
		String strDecryptedText = new String();
		static KeyGenerator keyGen;
		private static String strHexVal = "0123456789abcdef";

		public static SecretKey getSecret(){
		/**
		 *  Step 1. Generate an AES key using KeyGenerator
		 *  		Initialize the keysize to 128 
		 * 
		 */
			
			try{
				keyGen = KeyGenerator.getInstance("AES");
				keyGen.init(128);

				}
					
			catch(Exception exp)
			{
				System.out.println(" Exception inside constructor " +exp);
			}
			
			SecretKey secretKey = keyGen.generateKey();
			return secretKey;
		}
		
		/**
		 *  Step2. Create a Cipher by specifying the following parameters
		 * 			a. Algorithm name - here it is AES
		 */
		
		
		public byte[] encryptData(byte[] byteDataToEncrypt, Key secretKey, String Algorithm) {
			byte[] byteCipherText = new byte[200];
			
			try {
			Cipher aesCipher = Cipher.getInstance(Algorithm);
		
		/**
		 *  Step 3. Initialize the Cipher for Encryption 
		 */
			if(Algorithm.equals("AES")){
				aesCipher.init(Cipher.ENCRYPT_MODE,secretKey,aesCipher.getParameters());
				}
				else if(Algorithm.equals("RSA/ECB/OAEPWithSHA1AndMGF1Padding")){
				aesCipher.init(Cipher.ENCRYPT_MODE,secretKey);
				} 
				
		/**
		 *  Step 4. Encrypt the Data
		 *  		1. Declare / Initialize the Data. Here the data is of type String
		 *  		2. Convert the Input Text to Bytes
		 *  		3. Encrypt the bytes using doFinal method 
		 */
		byteCipherText = aesCipher.doFinal(byteDataToEncrypt); 
		strCipherText = new BASE64Encoder().encode(byteCipherText);

			}
			
			catch (NoSuchAlgorithmException noSuchAlgo)
			{
				System.out.println(" No Such Algorithm exists " + noSuchAlgo);
			}
			
				catch (NoSuchPaddingException noSuchPad)
				{
					System.out.println(" No Such Padding exists " + noSuchPad);
				}
			
					catch (InvalidKeyException invalidKey)
					{
						System.out.println(" Invalid Key " + invalidKey);
					}
					
					catch (BadPaddingException badPadding)
					{
						System.out.println(" Bad Padding " + badPadding);
					}
					
					catch (IllegalBlockSizeException illegalBlockSize)
					{
						System.out.println(" Illegal Block Size " + illegalBlockSize);
						illegalBlockSize.printStackTrace();
					}
					catch (Exception exp)
					{
						exp.printStackTrace();
					}
					
		return byteCipherText;
		}
		/**
		 *  Step 5. Decrypt the Data
		 *  		1. Initialize the Cipher for Decryption 
		 *  		2. Decrypt the cipher bytes using doFinal method 
		 */
		
		public byte[] decryptData(byte[] byteCipherText, Key secretKey, String Algorithm) {
			byte[] byteDecryptedText = new byte[200];
						
			try{	
		Cipher aesCipher = Cipher.getInstance(Algorithm);
		if(Algorithm.equals("AES")){
		aesCipher.init(Cipher.DECRYPT_MODE,secretKey,aesCipher.getParameters());
		}
		else if(Algorithm.equals("RSA/ECB/OAEPWithSHA1AndMGF1Padding")){
		aesCipher.init(Cipher.DECRYPT_MODE,secretKey);
		} 
		
		byteDecryptedText = aesCipher.doFinal(byteCipherText);
		strDecryptedText = new String(byteDecryptedText);
			}
		
		catch (NoSuchAlgorithmException noSuchAlgo)
		{
			System.out.println(" No Such Algorithm exists " + noSuchAlgo);
		}
		
			catch (NoSuchPaddingException noSuchPad)
			{
				System.out.println(" No Such Padding exists " + noSuchPad);
			}
		
				catch (InvalidKeyException invalidKey)
				{
					System.out.println(" Invalid Key " + invalidKey);
					invalidKey.printStackTrace();
				}
				
				catch (BadPaddingException badPadding)
				{
					System.out.println(" Bad Padding " + badPadding);
					badPadding.printStackTrace();
				}
				
				catch (IllegalBlockSizeException illegalBlockSize)
				{
					System.out.println(" Illegal Block Size " + illegalBlockSize);
					illegalBlockSize.printStackTrace();
				}
				
				catch (InvalidAlgorithmParameterException invalidParam)
				{
					System.out.println(" Invalid Parameter " + invalidParam);
				}
	
		return byteDecryptedText;
		}
                
}