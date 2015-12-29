import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.font.TextAttribute;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MyEditor extends JFrame implements ReadAble,SaveAble {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String filePath;
	private JTextArea textArea = new JTextArea(5, 20);
	private JComboBox <String> fonty = new JComboBox<String>(fontsList());
	private String kind;
	private JToolBar toolbar;
	
	public MyEditor(){
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setTitle("Magda GEEK");
		JScrollPane scrollPane = new JScrollPane(textArea); 
		textArea.setEditable(true);
		scrollPane.setPreferredSize(new Dimension(700,500));
		setJMenuBar(makeMenuBar());
		add(scrollPane,BorderLayout.CENTER);
		add(makeToolBar(),BorderLayout.NORTH);
		
		
		addWindowListener(new java.awt.event.WindowAdapter() {
			
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        if (JOptionPane.showConfirmDialog(MyEditor.this, 
		            "Are you sure to close this window?", "", 
		            JOptionPane.YES_NO_OPTION,
		            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
		            System.exit(0);
		        }
		    }
		});
		pack();
		setLocationRelativeTo(null);
		setVisible(true);//NA KONCU!!!
	}
	
  private JToolBar makeToolBar(){
	  
	toolbar = new JToolBar();
	toolbar.setRollover(true);   

	JButton bold=new JButton("G");
	JButton kursywa=new JButton("/");
	JButton podkreslenie=new JButton("_");
	JButton fontColorChooser=new JButton("FC");
	JButton bckGColorChooser=new JButton("BGC");
	
	
/*	ImageIcon img1 = new ImageIcon(path);
	ImageIcon img2 = new ImageIcon(path
	ImageIcon img3 = new ImageIcon(path);
	 bold.setIcon(img1);
	 kursywa.setIcon(img2);
	 podkreslenie.setIcon(img3);*/
	 
	
	
	
	toolbar.add(fonty);
	toolbar.add(kursywa);
	toolbar.add(podkreslenie);
	toolbar.add(bold);
	toolbar.add(Spinner());
	toolbar.add(fontColorChooser);
	toolbar.add(bckGColorChooser);
	

	
	fonty.addItemListener (new ItemListener () {


		@Override
		public void itemStateChanged(ItemEvent f1) {
			// TODO Auto-generated method stub
			kind=fonty.getSelectedItem().toString();
			textArea.setFont(Font.decode(kind));
		}
	});
	
	fontColorChooser.addActionListener(e->//domyslny katalog
	
	{
		Color newColor = JColorChooser.showDialog(
                MyEditor.this,
                "Choose Font Color",
                textArea.getForeground());
		if (newColor != null) {
		    textArea.setForeground(newColor);
		}
		
	});
	
	bckGColorChooser.addActionListener(e->//domyslny katalog
	
	{
		Color newColor = JColorChooser.showDialog(
                MyEditor.this,
                "Choose Background Color",
                textArea.getBackground());
		if (newColor != null) {
		    textArea.setBackground(newColor);
		}
		
	});
	
	bold.addActionListener(e->//domyslny katalog
	{
		/*if (Font.BOLD == 1){
			textArea.setFont(editF.deriveFont(editF.getStyle() ^ Font.BOLD));
		}
		else{
		textArea.setFont(editF.deriveFont(editF.getStyle() ^ Font.BOLD));
		}*/
	});
	
	kursywa.addActionListener(e->//domyslny katalog
	{
		textArea.setFont(textArea.getFont().deriveFont(Font.ITALIC));
	});
	  
	podkreslenie.addActionListener(e->//domyslny katalog
	{
		//textArea.setFont(textArea.getFont().deriveFont(TextAttribute.UNDERLINE));
	});
	  
	return toolbar;
	  
  }
	
	
   private JMenuBar makeMenuBar(){
		
		JMenuBar menuBar=new JMenuBar();
		JMenu fileMenu=new JMenu("File");
		JMenuItem save=new JMenuItem("Save");
		JMenuItem saveAs=new JMenuItem("Save As");
		JMenuItem open=new JMenuItem("Open");
		
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
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "geek");
		JFileChooser chooser=new JFileChooser();
		chooser.setFileFilter(filter);
		chooser.showOpenDialog(MyEditor.this);
		
		int option=chooser.showOpenDialog(null);
		
		if(option==JFileChooser.APPROVE_OPTION){//jesli wybrano sciezke
			System.out.println(chooser.getSelectedFile());//wypisuje sciezke
			   try {
				    filePath= chooser.getSelectedFile().getPath();
					String content=readFile(filePath);	
					textArea.setText(content);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			
		}	
		});
		
		saveAs.addActionListener(e->//domyslny katalog
		{
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "geek");
		JFileChooser chooser=new JFileChooser();
		chooser.showSaveDialog(MyEditor.this);	
		chooser.setFileFilter(filter);
		setFilePath(chooser.getSelectedFile().getPath());
		SaveAsFile(filePath,textArea.getText());
		
		

		});
		
		save.addActionListener(e->//domyslny katalog
		{
			SaveFile(filePath,textArea.getText());
		});
		return menuBar;
		
	}


	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	public String[] fontsList(){
		String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		return fonts;	
	}
	
	  private JSpinner Spinner(){
	
	      SpinnerModel spinnerModel =
	         new SpinnerNumberModel(10, //initial value
	            6, //min
	            100, //max
	            2);//step
	      JSpinner spinner = new JSpinner(spinnerModel);
	      spinner.addChangeListener(new ChangeListener() {
	         public void stateChanged(ChangeEvent e) {
	           int currentSize = (int)spinner.getValue();
	           System.out.println(currentSize);
	           textArea.setFont(textArea.getFont().deriveFont((float) currentSize));
	           textArea.setVisible(true);
	         }
	      });
	      return spinner;  
	   } 
	  




}
