import java.awt.BorderLayout;
//import javax.swing.UIManager;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class MyEditor extends JFrame {
	
	//***************************************************//
	private static final long serialVersionUID = 1L;
	private JTextArea textArea = new JTextArea(5, 20);

	//**************************************************//



	public MyEditor(){
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		setTitle("Magda GEEK");
		ImageIcon img = new ImageIcon("C:/Users/Magdalena/workspace/EdytorTekstowy/src/titleIcon.png");
		setIconImage(img.getImage());
		
		//UIManager.put("InternalFrame.activeTitleBackground", new ColorUIResource(Color.RED ));
		//UIManager.put("InternalFrame.activeTitleForeground", new ColorUIResource(Color.BLUE));
		//UIManager.put("InternalFrame.titleFont", new Font("Dialog", Font.BOLD, 11));

		JScrollPane scrollPane = new JScrollPane(textArea); 
		textArea.setEditable(true);
		scrollPane.setPreferredSize(new Dimension(700,500));
		MenuBar mb = new MenuBar(textArea);
		setJMenuBar(mb.menuBar);
		add(scrollPane,BorderLayout.CENTER);
		ToolBar tb = new ToolBar(textArea);
		add(tb.toolbar ,BorderLayout.NORTH);
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
		repaint();
		setLocationRelativeTo(null);
		setVisible(true);//NA KONCU!!!
	}
	
	
	public JTextArea getTextArea() {
		return textArea;
	}

	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}
	
}
