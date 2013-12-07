package CustMgtSys_1;
import java.io.*;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
@SuppressWarnings("serial")
public class OpenCustFile extends JFrame{

	
/**
 * @author modified by Robert
 *
 */


/**
* Fig. 17.20 and 17.21: FileDemonstration.java.
* Demonstrating JFileChooser.
* @author Deitel & Associates, Inc.
*/
	private File name = null;
	// set up GUI
	public OpenCustFile(MasterModel MM) {
		while(true){
			try{
				name = getFile();
			}catch(FileNotFoundException e){
				if(e.getMessage() == "cancel"){
					return;
				}
			}finally{
				if(name != null) break;
			}
		}
		File path = new File(name.getAbsolutePath());
		//System.out.println("path " +path);
		String folder = name.getName();
		//System.out.println("folder " + folder);
		String files = folder.substring(0, folder.length() - 5);
		//System.out.println("files " + files);
		String auth = path.getPath() + "/" + files + ".pass";
		//System.out.println("auth " + auth);
		String customers = path.getPath() + "/" + files + ".cust";
		//System.out.println("customers " + customers);
		String transactions = path.getPath() + "/" + files + ".trns";
		String accounts = path.getPath() + "/" + files + ".acct";
		File accounts1 = new File(accounts);
		//System.out.println("transactions " + transactions);
		File folder1 = new File(path.getPath() + "/" + folder);
		//System.out.println("folder1 " + folder1);
		File auth1 = new File(auth);
		//System.out.println("auth1 " + auth1);
		File customers1 = new File(customers);
		//System.out.println("customers1 " + customers1);
		File transactions1 = new File(transactions);
		//System.out.println("transactions1 " + transactions1);
		if(!(path.exists()) || !(auth1.exists()) || !(customers1.exists()) || !(transactions1.exists())){
			JOptionPane.showMessageDialog(this, "Invalid or Malformed File","Invalid or Malformed File", JOptionPane.ERROR_MESSAGE);
			return;
		}
		MM.Dirmodel.setpath(path);
		MM.Dirmodel.setfolder(folder1);
		MM.Dirmodel.setauth(auth1);
		MM.Dirmodel.setcustomers(customers1);
		MM.Dirmodel.settransactions(transactions1);
		MM.Dirmodel.setAccountsFile(accounts1);
		
		MM.intro.killIntro();
		new LoginWindow(MM);
			
	}

	// allow user to specify file name
	private File getFile() throws FileNotFoundException{
	    // display file dialog, so user can choose file or directory to open
	    JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        FileNameExtensionFilter customerfilter = new FileNameExtensionFilter("Customer data files (*.data)", "data");
            fileChooser.setFileFilter(customerfilter);
            fileChooser.setDialogTitle("Open customer data file");

	    int result = fileChooser.showOpenDialog(this);
	    File fileName = fileChooser.getSelectedFile(); // get File
	    
	    // if user clicked Cancel button on dialog, return
        if (result == JFileChooser.CANCEL_OPTION)
	        throw new FileNotFoundException("cancel");
        
	    // display error if invalid
	    if ((fileName == null) || (fileName.getName().equals("")) || !(fileName.exists())) {
	        JOptionPane.showMessageDialog(this, "Invalid Name","Invalid Name", JOptionPane.ERROR_MESSAGE);
	        throw new FileNotFoundException("Invalid name:" + fileName);
	    }
	    return fileName;
	} // end method getFile
}