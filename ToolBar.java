import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.font.TextAttribute;
import java.util.Map;


import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ToolBar extends JToolBar {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox <String> fonty = new JComboBox<String>(fontsList());
	private String kind;
	private static int counter_k;
	private static int counter_b;
	private static int counter_p;
	private JTextArea textArea;
	public JToolBar toolbar;
	
	public ToolBar(JTextArea mytextArea){
		
		toolbar = new JToolBar();
		toolbar.setRollover(true);   

		JButton bold=new JButton("G");
		JButton kursywa=new JButton("/");
		JButton podkreslenie=new JButton("_");
		JButton fontColorChooser=new JButton("FC");
		JButton bckGColorChooser=new JButton("BGC");
		textArea=mytextArea;
		
		/*
		ImageIcon img1 = new ImageIcon(path);
		ImageIcon img2 = new ImageIcon(path
		ImageIcon img3 = new ImageIcon(path);
		 bold.setIcon(img1);
		 kursywa.setIcon(img2);
		 podkreslenie.setIcon(img3);
		 */
		
		
		
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
				textArea.setFont(new Font(kind,textArea.getFont().getStyle(),textArea.getFont().getSize()));//Font.decode(kind)
				
			}
		});
		
		fontColorChooser.addActionListener(e->//domyslny katalog
		
		{
			Color newColor = JColorChooser.showDialog(
	                this,
	                "Choose Font Color",
	                textArea.getForeground());
			if (newColor != null) {
			    textArea.setForeground(newColor);
			}
			
		});
		
		bckGColorChooser.addActionListener(e->//domyslny katalog
		
		{
			Color newColor = JColorChooser.showDialog(
	                Main.editor,
	                "Choose Background Color",
	                textArea.getBackground());
			if (newColor != null) {
			    textArea.setBackground(newColor);
			}
			
		});
		
		bold.addActionListener(e->//domyslny katalog
		{
			counter_b++;
			counter_b=counter_b%2;
			Font f = textArea.getFont();
			if (counter_b!=0){
				textArea.setFont(f.deriveFont(f.getStyle()|Font.BOLD));
			}
			else{
				textArea.setFont(textArea.getFont().deriveFont(f.getStyle()&~Font.BOLD));
			}
		});
		
		kursywa.addActionListener(e->//domyslny katalog
		{
			counter_k++;
			counter_k=counter_k%2;
			Font f = textArea.getFont();
			if (counter_k!=0){
				textArea.setFont(f.deriveFont(f.getStyle()|Font.ITALIC));
			}
			else{
				textArea.setFont(f.deriveFont(f.getStyle()&~Font.ITALIC));
			}
		});
		  
		podkreslenie.addActionListener(e->//domyslny katalog
		{
			counter_p++;
			counter_p=counter_p%2;
			Font f = textArea.getFont();
			@SuppressWarnings("unchecked")
			Map<TextAttribute, Integer> attributes = (Map<TextAttribute, Integer>) f.getAttributes();
			if (counter_p!=0){
				attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
			}
			else{
				attributes.put(TextAttribute.UNDERLINE, -1);
			}
			textArea.setFont(f.deriveFont(attributes));
		});
		
		setVisible(true);
	}
	
	public String[] fontsList(){
		String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		return fonts;	
	}
	
	private JSpinner Spinner(){
		  //font size
	      SpinnerModel spinnerModel =
	         new SpinnerNumberModel(10, //initial value
	            6, //min
	            100, //max
	            2);//step
	      JSpinner spinner = new JSpinner(spinnerModel);
	      spinner.addChangeListener(new ChangeListener() {
	         public void stateChanged(ChangeEvent e) {
	           int currentSize = (int)spinner.getValue();
	           textArea.setFont(textArea.getFont().deriveFont(textArea.getFont().getStyle(),(float) currentSize));
	           textArea.setVisible(true);
	         }
	      });
	      return spinner;  
	   } 

}
