package CustMgtSys_1;


/**
 * 
 * @author amartine & ofalcon
 * 
 *         This is the structure for The MVC for the MainWindow classes.
 *         MainWindowView, MainWindowModel, & MainWindowController
 * 
 */
public class MainWindow {


	public MainWindow(String user, CustomerController database, boolean flag,
			MasterModel MM) {
		MainWindowModel model = new MainWindowModel(database);
		MainWindowView view = new MainWindowView(user, model, MM, flag);
		MainWindowController controller = new MainWindowController(view, model,
				MM);

		view.registerControllers(controller);

	}
}