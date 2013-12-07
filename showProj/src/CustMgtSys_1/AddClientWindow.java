package CustMgtSys_1;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.*;
import javax.swing.text.MaskFormatter;


/**
 * 
 * @author amartine in collab with Johncwimberly
 * 
 * The purpose of this class is to display a window that will accept information to add a client to our ArrayList of customers.
 * It creates fields for First name, Last name, phone, email, address, city and zip.
 * 
 * takes in the MainWindowModel, MainWindowView, and MainWindowController to update client table and reset table listener
 *
 */


public class AddClientWindow extends JFrame{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField firstNameField;
    private JTextField lastNameField;
    private JFormattedTextField phoneField;
    private JTextField emailField;
    private JTextField addressField;
    private JTextField cityField;
    private JTextField zipField;
    
    private MainWindowModel model;
    private MainWindowView view;
    private MainWindowController listen;
    
    
    public AddClientWindow(final MainWindowModel model, final MainWindowView view, final MainWindowController listen){
            super("MyCMS");
    
            this.model = model;
            this.view = view;
            this.listen = listen;
            MaskFormatter mask = null;
            try {
                    mask = new MaskFormatter("(###) ###-####");
                    mask.setPlaceholderCharacter('x');
            } catch (ParseException e) {
                    e.printStackTrace();
            }     
            
            firstNameField = new JTextField(15);
            lastNameField = new JTextField(15);
            phoneField = new JFormattedTextField(mask);
            emailField = new JTextField(15);
            addressField = new JTextField(15);
            cityField = new JTextField(15);
            zipField = new JTextField(15);
            
            String[] labels = {"First Name: ", "Last Name: ", "Phone Number: ", "Email: ", "Address: ", "City: ", "Zip: "};
        int numPairs = labels.length;
        final JButton addButton;
                final JButton cancelButton;
 
        //Create and populate the panel.
        JPanel p = new JPanel(new SpringLayout());
       
        for (int i = 0; i < numPairs; i++) {
            JLabel l = new JLabel(labels[i], JLabel.TRAILING);
            p.add(l);
            
            switch(i){
                    case 0:
                            l.setLabelFor(firstNameField);
                            p.add(firstNameField);
                            break;
                    case 1:
                            l.setLabelFor(lastNameField);
                            p.add(lastNameField);
                            break;
                    case 2:
                            l.setLabelFor(phoneField);
                            p.add(phoneField);
                            break;
                    case 3:
                            l.setLabelFor(emailField);
                            p.add(emailField);
                            break;
                    case 4:
                            l.setLabelFor(addressField);
                            p.add(addressField);
                            break;
                    case 5:
                            l.setLabelFor(cityField);
                            p.add(cityField);
                            break;
                    case 6:
                            l.setLabelFor(zipField);
                            p.add(zipField);
                            break;
            
            
            }//switch
        }//for
    
        addButton = new JButton("Add");
        p.add(addButton);
       
        cancelButton = new JButton("Cancel");
        p.add(cancelButton);
       
 
        //Lay out the panel. args (panel, rows, columns, initial x, initial y,
        //horizontal spacing, vertical spacing
        SpringUtilities.makeCompactGrid(p, numPairs+1, 2, 6, 6, 6, 6);
 
        //Create and set up the window.
        final JFrame frame = new JFrame("Add Client");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
 
        //Set up the content pane.
        p.setOpaque(true); //content panes must be opaque
        frame.setContentPane(p);
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
        //set windows start location to center of screen
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
        
            
        
        addButton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                        if((firstNameField.getText().equals("")) || (lastNameField.getText().equals("")) || (phoneField.getValue().equals("")) || (emailField.getText().equals(""))
                                    || (addressField.getText().equals("")) || (cityField.getText().equals("")) || (zipField.getText().equals(""))){
                                JOptionPane.showMessageDialog(addButton, "All Fields must be filled");
                        } else {
                                        model.getDatabase().addCustomer(firstNameField.getText(), lastNameField.getText(), (String) phoneField.getValue(), emailField.getText(),
                                                        addressField.getText(), cityField.getText(), zipField.getText());
                                        
                                        int size = model.getDatabase().getCustomers().size();
                                        
                                        JOptionPane.showMessageDialog(addButton, "Successfully added new Client " + firstNameField.getText() + " " + lastNameField.getText()
                                        								+ " New Account Number: " + model.getDatabase().getCustomers().get(size-1).getAccount());
                                        view.updateStatus(firstNameField.getText() + " " + lastNameField.getText() + " has been added to the data file");
                                        model.getDatabase().printCustomers();
                                        view.updateTable(model.getDatabase().getCustomers());
                                        view.restoreListListener(listen);
                                        frame.dispose();
                        }
                }
        } );
        
        cancelButton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                        JOptionPane.showMessageDialog(cancelButton, "Add client canceled");
                        frame.dispose();
                }
        });
        
        
        
        
                
    }

}