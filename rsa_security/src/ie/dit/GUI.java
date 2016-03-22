/* Assignment for Security module
 * Jonathan Riordan
 * C13432152
 */

package ie.dit;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.Color;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;

import java.io.BufferedWriter;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.PrintWriter;







import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import java.awt.Font;

import javax.swing.JFileChooser;

import java.awt.Choice;
import java.math.BigInteger;

import javax.swing.JTextField;


public class GUI extends JPanel implements ActionListener{

	/**
	 * 
	 */
	
	public RSA rsa;
	public static BigInteger[] RSA_Keys;
	
	BigInteger message, encrypted, decrypted;
	BigInteger publicKey, privateKey;
	byte[] bytes, decryptedbyte, decryptFile;
	
	FileReader reader = null;
	FileReader encryptedReader = null;
	
	BufferedReader in;
	BufferedReader emcryptedIn;
	File file, pk;
	
	boolean wrtieToFile = false;
	
	JLabel keyLabel;
	JButton chooseFile;
	JButton fileDecrypt;
	JButton clearButton;
	
	
	// boolean to write to the file
	boolean writeToFile = false;
	boolean fileToOpen = false;


	public static void main(String[] args) {
		createAndShowGUI();
	}
	
	
	JTextArea plaintextArea;
	JTextArea cyphertextArea;
	private JTextField PKey;
	
	public GUI(BorderLayout borderLayout) {
		setBackground(Color.LIGHT_GRAY);
		
		
		JPanel textFieldPanel = new JPanel(new BorderLayout());
		textFieldPanel.setBounds(463, 46, 0, 0);
		JPanel encryptPanel = new JPanel(new BorderLayout());
		JPanel decryptPanel = new JPanel(new BorderLayout());
		
		textFieldPanel.add(encryptPanel, BorderLayout.NORTH);
		textFieldPanel.add(decryptPanel, BorderLayout.SOUTH);
		setLayout(null);
		
		
		//Components that go in the textFieldPanel
		plaintextArea = new JTextArea(5, 35);
		plaintextArea.setBounds(78, 46, 422, 82);
		plaintextArea.setTabSize(15);
		add(plaintextArea);
		plaintextArea.setLineWrap(true);
		plaintextArea.setBorder(BorderFactory.createLineBorder(Color.black));
		add(textFieldPanel);
		
		JButton encrypt = new JButton("Encrypt");
		encrypt.setBounds(300, 266, 91, 29);
		//encrypt.setHorizontalAlignment(SwingConstants.TRAILING);
		add(encrypt);
		encrypt.setBackground(Color.LIGHT_GRAY);
		encrypt.setActionCommand("encrypt");
		encrypt.addActionListener(this);
		
		
		cyphertextArea = new JTextArea(5, 35);
		cyphertextArea.setBounds(78, 172, 422, 82);
		cyphertextArea.setTabSize(15);
		add(cyphertextArea);
		cyphertextArea.setLineWrap(true);
		cyphertextArea.setBorder(BorderFactory.createLineBorder(Color.black));
		
		JButton decrypt = new JButton("Decrypt");
		decrypt.setBounds(407, 266, 93, 29);
		//decrypt.setHorizontalAlignment(SwingConstants.RIGHT);
		add(decrypt);
		decrypt.setActionCommand("decrypt");
		decrypt.addActionListener(this);
		
		JLabel lblEnterMessage = new JLabel("Enter Message");
		lblEnterMessage.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		lblEnterMessage.setBounds(186, 5, 183, 29);
		add(lblEnterMessage);
		
		JLabel lblMessageInBytes = new JLabel("Encrypted Message");
		lblMessageInBytes.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		lblMessageInBytes.setBounds(167, 140, 242, 29);
		add(lblMessageInBytes);
		
		JLabel lblPublicKey = new JLabel("Public Key:");
		lblPublicKey.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		lblPublicKey.setBounds(36, 307, 153, 29);
		add(lblPublicKey);
		
	
		keyLabel = new JLabel(" Public key for RSA");
		keyLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		keyLabel.setBackground(Color.LIGHT_GRAY);
		keyLabel.setBounds(36, 346, 525, 16);
		add(keyLabel);
		
		chooseFile = new JButton("Choose File To Encrpyt");
		chooseFile.setBounds(36, 455, 182, 29);
		add(chooseFile);
		chooseFile.setActionCommand("Choose_File_Encrypt");
		chooseFile.addActionListener(this);
		
		JLabel lblPrivatekey = new JLabel("PrivateKey:");
		lblPrivatekey.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		lblPrivatekey.setBounds(36, 374, 153, 29);
		add(lblPrivatekey);
		
		PKey = new JTextField();
		PKey.setBounds(36, 409, 525, 28);
		add(PKey);
		PKey.setColumns(10);
		
		fileDecrypt = new JButton("Choose File To Decrypt");
		fileDecrypt.addActionListener(this);
		fileDecrypt.setActionCommand("File_Decrypt");
		fileDecrypt.setBounds(379, 455, 182, 29);
		add(fileDecrypt);
		
		clearButton = new JButton("Clear");
		clearButton.addActionListener(this);
		clearButton.setBackground(Color.LIGHT_GRAY);
		clearButton.setActionCommand("Clear_Button");
		clearButton.setBounds(74, 266, 91, 29);
		add(clearButton);
	}
	
	
	public static void createAndShowGUI() {
		JFrame frame = new JFrame("GUI");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		GUI contentPane = new GUI(new BorderLayout());
		contentPane.setOpaque(true); 
		contentPane.setPreferredSize(new Dimension(600,500));
		
		frame.setContentPane(contentPane);
		frame.setResizable(true);
		frame.setLocation(450, 200);
		
		
		//Display the Window
		frame.pack();
		frame.setVisible(true);
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
	
	public void encryptFile() throws IOException {
		JFileChooser file = new JFileChooser();
		int result = file.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
		    File selectedFile = file.getSelectedFile();
		    System.out.println("Selected file: " + selectedFile.getAbsolutePath());
		    
		     selectedFile = file.getSelectedFile();
		      //makes sure files can be processed before proceeding
		      if (selectedFile.canRead() && selectedFile.exists())
		      {
		    	  try {
					reader = new FileReader(selectedFile);
					 in = new BufferedReader(reader);
					    
					    // Read text from file
					    String textFromFile = in.readLine();
					    System.out.println(textFromFile);
					    
					    plaintextArea.setText(textFromFile);
					    
					    // set boolean to true to write to file.
					    writeToFile = true;

				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		      }
		
		    
		}	
	}
	
	// method to write encrypted message to file and private key to file.
	public void fileWritter(BigInteger encrypted) {
		BufferedWriter writer, pkwriter;
		file = new File("Encrypted.txt");
		pk = new File("PrivateKeys.txt");
		try {
			writer = new BufferedWriter(new FileWriter(file));
			pkwriter = new BufferedWriter(new FileWriter(pk));
			
			// write the encrypted message to file
			writer.write(encrypted.toString());
			// close file.
			writer.close();
			
			
			// Write keys to file.
			pkwriter.write(RSA.RSA_Keys[1].toString());
			pkwriter.write("\n");
			pkwriter.write(RSA.RSA_Keys[0].toString());
			
			pkwriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void decryptFile()  {
		String KeyD = null;
		String KeyN = null;
		String encryptedString = null;
		JFileChooser file = new JFileChooser();
		int result = file.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
		    File selectedFile = file.getSelectedFile();
		    System.out.println("Selected file: " + selectedFile.getAbsolutePath());
		    
		     selectedFile = file.getSelectedFile();
		      //makes sure files can be processed before proceeding
		      if (selectedFile.canRead() && selectedFile.exists())
		      {
		    	  try {
					reader = new FileReader("PrivateKeys.txt");
					encryptedReader = new FileReader(selectedFile);
					in = new BufferedReader(reader);
					emcryptedIn = new BufferedReader(encryptedReader);
					
					for(int i = 0; i < 2; i ++) {
						if(i == 0) {
							KeyD = in.readLine();
						}
						if(i == 1) {
							KeyN = in.readLine();
						}
						
					}
					
					encryptedString = emcryptedIn.readLine();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		      }
		      
		      System.out.println("KeyD: " + KeyD);
		      System.out.println("KeyN: " + KeyN);
		      System.out.println("Encrypted: " + encryptedString);
		      
		      encrypted = new BigInteger(encryptedString);
		      publicKey = new BigInteger(KeyN);
		      privateKey = new BigInteger(KeyD);
		      
		      decryptFile = RSA.decryptionFileFunction(encrypted,privateKey, publicKey);
		      
		      System.out.println("Decrypting Bytes: " + bytesToString(decryptFile));
		      System.out.println("Decrypted String: " + new String(decryptFile) + "\n\n");
		      
		      
		      // Update GUI
		      plaintextArea.setText(new String(decryptFile));
		      cyphertextArea.setText(encryptedString);
		      keyLabel.setText(KeyN);
		      PKey.setText(KeyD);
		}	
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
				
		if ("encrypt".equals(e.getActionCommand())) {
			System.out.println("Emcryption command");
			
			String plaintext = plaintextArea.getText();
			bytes = plaintext.getBytes();
			message = new BigInteger(bytes);
			encrypted = RSA.encyptFunction(message);
			
			System.out.println("plaintext: " + plaintext.getBytes());
			System.out.println("Message: " + message);
			System.out.println("Encrypted message: " + encrypted);
			System.out.println("Public Key: " + RSA.N);
			System.out.println("Private Key: " + RSA.D);
			System.out.println("Modulous: " + RSA.E + "\n\n");
			
			// set public key in GUI
			keyLabel.setText(RSA.N.toString());
			
			// set cyphertext area in GUI
			plaintextArea.setText("");
			cyphertextArea.setText(encrypted.toString());
			
			// statement to check whter or not to write to a file.
			if(writeToFile) {
				try {
					fileWritter(encrypted);
				} catch(Exception e2) {
					e2.printStackTrace();
				}
				writeToFile = !writeToFile;
			}
		} 
				
		
		// if the decyption button is pressed
		if("decrypt".equals(e.getActionCommand())){ 
			System.out.println("Decryption command");
			String ciphertext = cyphertextArea.getText();
			
			decrypted = new BigInteger(ciphertext);
			decryptedbyte = RSA.decryptionFunction(decrypted);
			
			System.out.println("Decrypting Bytes: " + bytesToString(decryptedbyte));
	        System.out.println("Decrypted String: " + new String(decryptedbyte) + "\n\n");
	        
	        plaintextArea.setText(new String(decryptedbyte));


		}
		
		
		// if the user clicks the choose file button, load the file and display in the text area.
		if("Choose_File_Encrypt".equals(e.getActionCommand())) {
			System.out.println("Encrypt file command");
						
			if(e.getSource() == chooseFile) {
				
				try {
					encryptFile();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		}
		
		//if the user selects to decrypt a file
		if("File_Decrypt".equals(e.getActionCommand())) {
			System.out.println("Decrypt file command");
			
			if(e.getSource() == fileDecrypt) {
				plaintextArea.setText("");
				decryptFile();
			}
		}
		
		if("Clear_Button".equals(e.getActionCommand())) {
			System.out.println("Clear Command");
			if(e.getSource() == clearButton) {
				plaintextArea.setText(" ");
				cyphertextArea.setText(" ");
			}
		}
	}
}
