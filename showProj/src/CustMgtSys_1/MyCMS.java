
package CustMgtSys_1;

public class MyCMS {

	/**
     * @author Robert
     * This is where the main method is housed. Starts the program, initializes windows, and 
     * initializes the master model.
     * @param args
     */
	
    public static void main(String[] args) {
    	MasterModel MM = new MasterModel();
    	introview iv = new introview();
    	introviewController ivc = new introviewController();
    	iv.register(ivc);
    	ivc.register(iv);
    	MM.intro = ivc;
    	ivc.registerMaster(MM);
    }
}
   
