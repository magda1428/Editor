import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MenuBar implements ReadAble {
	
	public static String path;
	
	public MenuBar(){
		JMenuBar menuBar=new JMenuBar();
		JMenu fileMenu=new JMenu("File");
		JMenu viewMenu=new JMenu("View");
		JMenuItem newItem=new JMenuItem("New");
		JMenuItem save=new JMenuItem("Save");
		JMenuItem saveAs=new JMenuItem("Save As");
		JMenuItem open=new JMenuItem("Open");
		
		JMenuItem kolor=new JMenuItem("Change color");
		
		
		fileMenu.add(newItem);
		fileMenu.add(open);
		fileMenu.add(save);
		fileMenu.add(saveAs);
		viewMenu.add(kolor);
		menuBar.add(fileMenu);
		menuBar.add(viewMenu);
	}
	

	


}
