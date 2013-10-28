package algocw;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class MainGui extends JFrame {
	private JList listFrom, listTo;
	
	public MainGui(ActionListener al, MetroMap prague) {
		super("Prague Metro");
		
		JPanel panel = new JPanel();
	       getContentPane().add(panel);

	       panel.setLayout(new FlowLayout());
	      
	       listFrom = new JList(prague.getAllStationsAsArray()); 
	       listFrom.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
	       listFrom.setLayoutOrientation(JList.HORIZONTAL_WRAP);
	       listFrom.setVisibleRowCount(-1);
	       
	       // From station
	       panel.add(new JLabel("From station:"));	    
	       JScrollPane fromScroller = new JScrollPane(listFrom);
	       fromScroller.setPreferredSize(new Dimension(180, 120));
	       panel.add(fromScroller);
	       
	       listTo = new JList(prague.getAllStationsAsArray()); 
	       listTo.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
	       listTo.setLayoutOrientation(JList.HORIZONTAL_WRAP);
	       listTo.setVisibleRowCount(-1);
	       
	       // To station
	       panel.add(new JLabel("To station:"));
	       JScrollPane toScroller = new JScrollPane(listTo);
	       toScroller.setPreferredSize(new Dimension(180, 120));
	       panel.add(toScroller);
	       
	       JButton quitButton = new JButton("Search");
	       quitButton.addActionListener(al);
	       panel.add(quitButton);
	       
	       setTitle("Prague Tube Planner");
	       setSize(250, 350);
	       setLocationRelativeTo(null);
	       setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public JList getListFrom() {
		return this.listFrom;
	}
	
	public JList getListTo() {
		return this.listTo;
	}
}
