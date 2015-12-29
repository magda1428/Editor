import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Listeners implements ReadAble {
	
	public static String content;
	
	Listeners(JMenuItem save,JMenuItem saveAs,JMenuItem open){
	
	open.addActionListener(e->//domyslny katalog
	
	{
	FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "geek");
	JFileChooser chooser=new JFileChooser();
	chooser.setFileFilter(filter);
	//chooser.showOpenDialog(MyEditor.this);
	
	int option=chooser.showOpenDialog(null);
	
	if(option==JFileChooser.APPROVE_OPTION){//jesli wybrano sciezke
		System.out.println(chooser.getSelectedFile());//wypisuje sciezke
	    try {
			content=readFile(chooser.getSelectedFile().getPath());	
			MyEditor ed=new MyEditor();
			
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
	}	
	});
	
	saveAs.addActionListener(e->//domyslny katalog
	{
	FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "geek");
	JFileChooser chooser=new JFileChooser();
	//chooser.showSaveDialog(MyEditor.this);	
	chooser.setFileFilter(filter);

	});
	}

}
