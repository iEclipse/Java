public class UI {

	protected void intro() {
		System.out.println("Secret Message V2");
		System.out.println("===================");
	}
	protected void menu() {
		System.out.println("1) Encrypt\n2) Decrypt\n3) Notes\n4) Quit\n");
		System.out.print("Command: ");
	}
	protected void encryptOpt() {
		System.out.println("\nEncrypt");
		System.out.println("=========");
		System.out.println("1) Create New Message\n2) Encrypt Text File\n3) Back\n");
		System.out.print("Command: ");
	}
	protected void decryptOpt() {
		System.out.println("\nDecrypt");
		System.out.println("=========");
		getFileName();
	}
	protected void getFileName(){
		System.out.print("Enter Filename: ");
	}
	protected void getMessage(){
		System.out.println("\nEnter a message (Type \"/end\" and press enter to save.)");
		System.out.println("--------------------------------------------------------");
	}
	protected void write(){
		System.out.println("Message saved to current directory.\n");
	}
	protected void beginMessage(){
		System.out.println("\n\n<-------------------- Begin Message -------------------->");	
	}
	protected void endMessage(){
		System.out.println("\n<--------------------- End Message --------------------->\n\n");
	}
	protected void error1(){
		System.out.println("Error: That is an invalid action.\n");
	}
	protected void quit(){
		System.out.println("Terminated.");
	}
	protected void blank(){
		System.out.println();
	}
	protected void notes(){
		System.out.println("\n1) Do not try to encrypt the same file twice.");
		System.out.println("2) Encrypting an existing file will overwrite the original.");
		System.out.println("4) Editing a pre-encrypted file and decrypting will not work.");
		System.out.println("   (Recommended: Create new files before encryption.)");
		System.out.println("5) Messages that are decrypted will NOT be saved.");
		System.out.println("6) Different program versions cannot read each other's messages.\n");
	}
}