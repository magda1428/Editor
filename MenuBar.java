import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.StringJoiner;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MenuBar extends JMenuBar implements ReadAble,SaveAble {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String filePath;
	private String fileName;
	private JTextArea textArea;
	public JMenuBar menuBar;
	
	public MenuBar(JTextArea mytextArea){
		
		menuBar=new JMenuBar();
		JMenu fileMenu=new JMenu("File");
		JMenuItem save=new JMenuItem("Save");
		JMenuItem saveAs=new JMenuItem("Save As");
		JMenuItem open=new JMenuItem("Open");
		textArea=mytextArea;
		
		//dodanie skrotow klawiaturowych
		KeyStroke ctrlO = KeyStroke.getKeyStroke(KeyEvent.VK_O , Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask());
		KeyStroke ctrlS = KeyStroke.getKeyStroke(KeyEvent.VK_S , Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask());
		KeyStroke ctrlD = KeyStroke.getKeyStroke(KeyEvent.VK_D , Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask());
        //set the accelerator
        open.setAccelerator(ctrlO);
        save.setAccelerator(ctrlS);
        saveAs.setAccelerator(ctrlD);

		
		
		fileMenu.add(open);
		fileMenu.add(save);
		fileMenu.add(saveAs);
		menuBar.add(fileMenu);

		
		open.addActionListener(e->//domyslny katalog
		
		{
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Geek Files *.geek", "geek");
		JFileChooser chooser=new JFileChooser();
		chooser.setFileFilter(filter);	
		
		
			while(true){
				int option=chooser.showOpenDialog(Main.editor);
				
				if(option==JFileChooser.APPROVE_OPTION){//jesli wybrano sciezke
					filePath=chooser.getSelectedFile().getPath();
					
					if (filePath.split("\\.").length==1){
						StringJoiner joiner = new StringJoiner("");
		    	 		joiner.add(filePath);
		    	 		joiner.add(".geek");
		    	 		filePath=joiner.toString();
					}
					
					boolean exists = (new File(filePath)).exists();
					if (option==JFileChooser.CANCEL_OPTION){
						break;
					}
					
					if (exists) {
					   if(!filePath.endsWith(".geek")){
						    String error = "You can select only geek files";
				            JOptionPane.showMessageDialog(this, error, "Wrong type of file", JOptionPane.INFORMATION_MESSAGE);
							chooser=new JFileChooser();
							chooser.setFileFilter(filter);
							chooser.showOpenDialog(Main.editor);
					   }
					   else{
					   try {
							String content=readFile(filePath);	
							textArea.setText(content);
							break;
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					   }
				   
					}
					else{
						String error = "Directory doesn't exist";
			            JOptionPane.showMessageDialog(this, error, "Wrong type of file", JOptionPane.INFORMATION_MESSAGE);
						chooser=new JFileChooser();
						chooser.setFileFilter(filter);
						chooser.showOpenDialog(Main.editor);
					}
					
				}
				else{
					break;
				}
		}
		});
		
		saveAs.addActionListener(e->//domyslny katalog
		{
			saveAsF();
		
		});
		
		save.addActionListener(e->//domyslny katalog
		{
			try{
				SaveFile(filePath,textArea.getText());
			}
			catch(java.lang.NullPointerException e1){
				saveAsF();
				
			}
		});
		setVisible(true);
	}
	
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public void saveAsF(){
		
		FileNameExtensionFilter filter = new FileNameExtensionFilter("*.geek", "geek");
		JFileChooser chooser=new JFileChooser();
		chooser.setFileFilter(filter);
		int option = chooser.showSaveDialog(Main.editor);
		
		switch (option){
		case JFileChooser.APPROVE_OPTION:	
			
			try{
				setFilePath(chooser.getSelectedFile().getPath());
				setFileName(chooser.getSelectedFile().getName());
				SaveAsFile(filePath,textArea.getText());
				//Main.editor.setTitle("Magda Geek "+fileName);
				}
			catch(java.lang.NullPointerException b) {
				String error = "Error, Please save file as geek file";
	            JOptionPane.showMessageDialog(this, error, "Wrong type of file", JOptionPane.INFORMATION_MESSAGE);
	            chooser = new JFileChooser();
	            chooser.showSaveDialog(Main.editor);	
				chooser.setFileFilter(filter);	
			}
			break;
		case JFileChooser.ERROR_OPTION:
			System.out.println("error");
			break;
		}
		
	}

}
