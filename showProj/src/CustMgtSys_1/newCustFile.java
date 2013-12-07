package CustMgtSys_1;
import java.io.*;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
@SuppressWarnings("serial")
public class newCustFile extends JFrame{

/**
* @author modified by Robert
*
*/
        


/**
* Fig. 17.20 and 17.21: FileDemonstration.java.
* Demonstrating JFileChooser.
* @author Deitel & Associates, Inc.
*/
        private File name;
        // set up GUI
        public newCustFile(MasterModel MM) {
                while(true){
                        try{
                                name = getFile();
                        }catch(FileNotFoundException e){
                                System.out.println(e.getMessage());
                                if(e.getMessage() == "cancel"){
                                        return;
                                }
                        }finally{
                                if(name != null) break;
                        }
                }
                File path = new File(name.getAbsolutePath());
                String path1 = path.getAbsolutePath();
                if(!path1.toLowerCase().endsWith(".data"))
                {
                    path = new File(path1 + ".data");
                }
                        //System.out.println("path " +path);
                        String folder = path.getName();
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
                        File folder1 = new File(folder);
                        //System.out.println("folder1 " + folder1);
                        File auth1 = new File(auth);
                        //System.out.println("auth1 " + auth1);
                        File customers1 = new File(customers);
                        //System.out.println("customers1 " + customers1);
                        File transactions1 = new File(transactions);
                        //System.out.println("transactions1 " + transactions1);
                        CreateStuff(path,files);
                        MM.Dirmodel.setpath(path);
                        MM.Dirmodel.setfolder(folder1);
                        MM.Dirmodel.setauth(auth1);
                        MM.Dirmodel.setcustomers(customers1);
                        MM.Dirmodel.settransactions(transactions1);
                        MM.Dirmodel.setAccountsFile(accounts1);
                        
                        MM.intro.killIntro();
                
                new CreateLoginWindowINTRO(MM);
                
        }

        // allow user to specify filename
        private File getFile() throws FileNotFoundException{
         // display file dialog, so user can choose file or directory to open
         JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        FileNameExtensionFilter customerfilter = new FileNameExtensionFilter("Customer data files (*.data)", "data");
            fileChooser.setFileFilter(customerfilter);
            fileChooser.setDialogTitle("Create new customer file");

         int result = fileChooser.showOpenDialog(this);
         File fileName = fileChooser.getSelectedFile(); // get File
        
         // if user clicked Cancel button on dialog, return
        if (result == JFileChooser.CANCEL_OPTION)
         throw new FileNotFoundException("cancel");
        
         // display error if invalid
         if ((fileName == null) || (fileName.getName().equals(""))) {
         JOptionPane.showMessageDialog(this, "Invalid Name","Invalid Name", JOptionPane.ERROR_MESSAGE);
         throw new FileNotFoundException("Invalid name:" + fileName);
         }
         if (fileName.exists()){
                 JOptionPane.showMessageDialog(this, "File already exists","File already exists",JOptionPane.ERROR_MESSAGE);
                 throw new FileNotFoundException("Invalid name:" + fileName);
         }
         return fileName;
        } // end method getFile
        
        public static void CreateFile(File path) {
                    String name = path.getName();
                    name = name.substring(0, name.length()-4);
                    String p = path.getPath();
                    path.mkdir();
                    File cust = new File(p, name + ".cust");
                    File trans = new File(p, name + ".trans");
                    File usr = new File(p, name + ".pass");
                    try {
                            cust.createNewFile();
                            trans.createNewFile();
                            usr.createNewFile();
                    } catch (IOException e) {
                            e.printStackTrace();
                    }
            }
        
        public static void CreateStuff(File path, String name){
                path.mkdir();
                String p = path.getAbsolutePath();
                File cust = new File(p, name + ".cust");
                    File trans = new File(p, name + ".trns");
                    File usr = new File(p, name + ".pass");
                    File acct = new File(p, name + ".acct");
                    try {
                            cust.createNewFile();
                            trans.createNewFile();
                            usr.createNewFile();
                            acct.createNewFile();
                    } catch (IOException e) {
                            e.printStackTrace();
                    }
                
        }
}