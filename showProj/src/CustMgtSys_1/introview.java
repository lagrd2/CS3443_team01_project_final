package CustMgtSys_1;

import java.awt.*;
import javax.swing.*;

/**
 * @author Robert Structure&design
 * @author amartine GUI
 * 
 *         The window is the first window to be viewed upon running our program.
 *         greets user and displays program logo. gives the user the option to
 *         create a new data file or open an existing file.
 */
@SuppressWarnings({ "serial", })
public class introview extends JFrame {

	private JButton New;
	private JButton open;

	public introview() {
		super("MyCMS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 300);
		JLabel background = new JLabel(new ImageIcon("MyCMSLogo1.JPG"));
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - getHeight()) / 2);

		New = new JButton("New");
		New.setBounds(100, 210, 100, 35);
		add(New);
		open = new JButton("Open");
		open.setBounds(200, 210, 100, 35);
		add(open);

		add(background);
		background.setLayout(new BorderLayout());

		Font font = new Font("SansSerif", Font.BOLD, 16);
		JLabel label = new JLabel("Welcome to ", JLabel.CENTER);
		label.setFont(font);
		background.add(label, BorderLayout.NORTH);

		JLabel about = new JLabel("MyCMS copyright 2013", JLabel.CENTER);
		about.setFont(font);
		background.add(about, BorderLayout.SOUTH);

		setLocation(x, y);

		setVisible(true);
		setResizable(false);

	}

	public void register(introviewController controller) {
		open.addActionListener(controller);
		New.addActionListener(controller);
	}

	public void pullThePlug() {
		setVisible(false);
	}

	public void plugBackIn() {
		setVisible(true);
	}

}