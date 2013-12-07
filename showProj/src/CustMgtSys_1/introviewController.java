package CustMgtSys_1;


/**
 * @author Robert
 *
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class introviewController implements ActionListener {
	
	private introview intro;
	private MasterModel MM;
	
	@Override
	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();
		if(command.equals("New")){
			//open new Customer file creation window 
			newCustFile newf = new newCustFile(MM);
			newf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		} else if(command.equals("Open")){
			//open window to select customer file to open
	        OpenCustFile openf = new OpenCustFile(MM);
	        openf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		} else {
			//????
			System.err.println("Error: in IntroviewController");
			System.err.println("Command recieved was neither \"new\" nor \"open\"");
		}
		
	}
	public void register(introview intro){
		this.intro = intro;
	}
	public void killIntro(){
		intro.pullThePlug();
	}
	public void rebirthIntro(){
		intro.plugBackIn();
	}
	public void registerMaster(MasterModel MM){
		this.MM = MM;
	}
}