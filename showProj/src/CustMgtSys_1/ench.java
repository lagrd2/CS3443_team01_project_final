package CustMgtSys_1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ench {
	/**
	 * ench class simple encrypting and dencrypt methods
	 * 
	 * @author Alex
	 */
	// encryption key
	private static int mkey = -5;

	public ench() {
	}

	/**
	 * encrypts input string
	 * 
	 * @param inLine
	 *            string to encrypt
	 * @return new encrypted string
	 */
	public static String encrypt(String inLine) {
		int key = mkey;
		String c;
		int n;
		char[] w;
		// takes string
		c = inLine;
		// converts string to char array
		w = c.toCharArray();
		// alters char with key
		for (int i = 0; i < w.length; i++) {
			n = w[i] + key;
			// replaces char with encrypted char
			w[i] = ((char) n);
		}
		return new String(w);
	}

	/**
	 * dencrypts string
	 * 
	 * @param inLine
	 *            string to dencrypt
	 * @return dencrypted string
	 */
	public static String dencrypt(String inLine) {
		int key = mkey;
		String d;
		int m;
		char[] x;
		// takes string
		d = inLine;
		// converts string to char array
		x = d.toCharArray();
		// alters char with key
		for (int i = 0; i < x.length; i++) {
			m = x[i] - key;
			// replaces char with correct char
			x[i] = ((char) m);
		}
		return new String(x);
	}

	/**
	 * open files
	 * 
	 * @return output output string array of file
	 * @throws IOException
	 */
	public static ArrayList<CustomerModel> opener(MasterModel master)
			throws IOException {
		// string holders
		String line = null;
		String s;
		String[] parts;
	

		ArrayList<CustomerModel> output = new ArrayList<CustomerModel>();
		BufferedReader br = new BufferedReader(new FileReader(master.Dirmodel.getCustomersFile()));
		// loop until end of file
		while ((line = br.readLine()) != null) {
			// dencrypt file
			s = dencrypt(line);
			// split into parts
			parts = s.split("\\|");
			output.add(new CustomerModel(parts[1], parts[2], parts[3],
					parts[4], parts[5], parts[6], parts[7],
					null));
			
		}
		// return array
		br.close();
		return output;
	}

	/**
	 * transactions opener
	 * 
	 * @param file
	 * @param pass
	 * @return
	 * @throws IOException
	 */
	public static ArrayList<TransactionsModel> TransOpener(MasterModel master) throws IOException {
		// string holders
		String line = null;
		String s;
		String[] parts;
		ArrayList<TransactionsModel> output = new ArrayList<TransactionsModel>();
		;
		BufferedReader br = new BufferedReader(new FileReader(master.Dirmodel.getTransactionsFile()));
		// loop until end of file
		while ((line = br.readLine()) != null) {
			// dencrypt file
			s = dencrypt(line);
			// split into parts
			parts = s.split("\\|");
			output.add(new TransactionsModel((parts[1]), (parts[2]),
					(parts[3]), (parts[4]), (parts[5]), (parts[6]), (parts[0])));
		}
		// return array
		br.close();
		return output;
	}
	/**
	 * open accountnumbers
	 * @param file
	 * @param pass
	 * @return
	 * @throws IOException
	 */
	public static ArrayList<String> AccountOpener(MasterModel master) throws IOException {
		// string holders
		String line = null;
		String s;
		ArrayList<String> output = new ArrayList<String>();
		;
		BufferedReader br = new BufferedReader(new FileReader(master.Dirmodel.getAccountsFile()));
		// loop until end of file
		
		while ((line = br.readLine()) != null) {
			// dencrypt file
			s = dencrypt(line);
			output.add(s);
		}
		// return array
		br.close();
		return output;
		
		
	}

	/**
	 * save customer Array and trans array
	 * @param file
	 * @param inLine
	 * @param trans
	 * @param pass
	 */
	public static void saving(MasterModel master) {
		// storage string
		String s;
		// create file
		try {
			BufferedWriter output = new BufferedWriter(new FileWriter(
					master.Dirmodel.getCustomersFile()));
			// loops until end
			for (int i = 0; i < master.CustControl.getCustomers().size(); i++) {
				// add |s
				s = "" + i + "|";
				s = s + master.CustControl.getCustomers().get(i).getFirst() + "|";
				s = s + master.CustControl.getCustomers().get(i).getLast() + "|";
				s = s + master.CustControl.getCustomers().get(i).getPhone() + "|";
				s = s + master.CustControl.getCustomers().get(i).getEmail() + "|";
				s = s + master.CustControl.getCustomers().get(i).getAddress() + "|";
				s = s + master.CustControl.getCustomers().get(i).getCity() + "|";
				s = s + master.CustControl.getCustomers().get(i).getZip() + "|";
				// encrypt and write to file
				output.write(encrypt(s));
				// inputs new line to file
				output.write("\n");
			}
			// close file
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			BufferedWriter output = new BufferedWriter(new FileWriter(master.Dirmodel.getTransactionsFile()));
			// loops until end
			for (int i = 0; i < master.CustControl.getTransactions().size(); i++) {
				s = "" + master.CustControl.getTransactions().get(i).getAccountNumber() + "|";
				s = s + master.CustControl.getTransactions().get(i).getTransactionNumber()+ "|";
				s = s + master.CustControl.getTransactions().get(i).getDate() + "|";
				s = s + master.CustControl.getTransactions().get(i).getSKU() + "|";
				s = s + master.CustControl.getTransactions().get(i).getItem() + "|";
				s = s + master.CustControl.getTransactions().get(i).getPrice() + "|";
				s = s + master.CustControl.getTransactions().get(i).getPaymentMethod() + "|";
				// encrypt and write to file
				output.write(encrypt(s));
				// inputs new line to file
				output.write("\n");
			}
			// close file
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			BufferedWriter output = new BufferedWriter(new FileWriter(master.Dirmodel.getAccountsFile()));
			// loops until end
			for (int i = 0; i < master.CustControl.getCustomers().size(); i++) {
				// add |s
				s = "" + master.CustControl.getCustomers().get(i).getAccount();
				// encrypt and write to file
				output.write(encrypt(s));
				// inputs new line to file
				output.write("\n");
			}
			// close file
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * checks login against user passwords
	 * 
	 * @param user
	 *            user name
	 * @param password
	 *            user password
	 * @return boolean array
	 * @throws IOException
	 */
	public static boolean[] logincheck(String user, String password,MasterModel master)
			throws IOException {
		// string to store file line
		String line = null;
		// array to return
		boolean[] account = { false, false };
		// open file/buffered reader
		BufferedReader br = new BufferedReader(new FileReader(master.Dirmodel.getAuthFile()));
		// scan file line by line until end
		while ((line = br.readLine()) != null) {
			// if match and admin change both to true
			if (encrypt((user + password + "0")).equals(line)) {
				account[0] = true;
				account[1] = true;
			}
			// if match but not admin only mark as valid
			if (encrypt((user + password + "1")).equals(line)) {
				account[0] = true;
			}
		}
		br.close();
		// return
		return account;
	}

	/**
	 * add a new user
	 * 
	 * @param user
	 *            name
	 * @param password
	 * @param admin
	 *            admin status
	 * @throws IOException
	 */
	public static void addaccount(String user, String password, boolean admin, MasterModel master)
			throws IOException {
		// create apendable writer
		BufferedWriter output = new BufferedWriter(new FileWriter(master.Dirmodel.getAuthFile(),
				true));
		// set correct string
		String A;
		if (admin == true) {
			A = encrypt(user + password + "0");
		} else {
			A = encrypt(user + password + "1");
		}
		// add line to file
		output.write(A);
		output.newLine();
		output.close();
	}

	/**
	 * delete account
	 * 
	 * @param user
	 *            name
	 * @param password
	 * @param admin
	 *            admin status
	 * @throws IOException
	 */
	public static boolean deleteaccount(String user, String password, boolean admin, MasterModel master)
			throws IOException {
		boolean success = false;
		// create tmp
		File tmp = File.createTempFile("tmp", "");
		// create writer and reader
		BufferedWriter output = new BufferedWriter(new FileWriter(tmp));
		BufferedReader br = new BufferedReader(new FileReader(master.Dirmodel.getAuthFile()));
		// strings for holding lines
		String line = null;
		String DA;
		// set to correct check string
		if (admin == true) {
			DA = encrypt((user + password + "0"));
		} else {
			DA = encrypt((user + password + "1"));
		}
		// loop through file skip matching line
		while ((line = br.readLine()) != null) {
			if (!line.equals(DA)) {
				output.write(line);
				output.newLine();
			}
			if(line.equals(DA)){
				success = true;
			}
		}
		// close
		br.close();
		output.close();
		// delete and rename files
		File oldFile = new File(master.Dirmodel.getAuthFile().getPath()); // needed to get the path ALEX!!!!!!!!
		if(oldFile.delete()){
			tmp.renameTo(oldFile);
		}
		return success;
	}
}