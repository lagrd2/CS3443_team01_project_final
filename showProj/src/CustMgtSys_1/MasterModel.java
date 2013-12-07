package CustMgtSys_1;

/**
 * @author Robert
 * For better structure, a master method was created. It handles all information about 
 * a given database selection. All files, directories and data associated with them are kept here
 * 
 */

public class MasterModel {
	
	
	public introviewController intro;
	public DirModel Dirmodel;
	public CustomerController CustControl;
	
	public MasterModel(){
		this.intro = new introviewController();
		this.Dirmodel = new DirModel();
	}
	
	public introviewController getintroviewControl(){
		return intro;
	}
	public DirModel getFileModel(){
		return Dirmodel;
	}
	public CustomerController getCustomerController(){
		return CustControl;
	}
	public void setintroviewControl(introviewController intro){
		this.intro = intro;
	}
	public void setFileModel(DirModel filemodel){
		this.Dirmodel = filemodel;
	}
	public void setCustomerController(CustomerController CustControl){
		this.CustControl = CustControl;
	}
	
}
