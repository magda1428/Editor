import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public interface SaveAble {
	
	public default void SaveAsFile(String filepath,String content){
		
	     try {
	            File newTextFile = new File(filepath);

	            FileWriter fw = new FileWriter(newTextFile);
	            System.out.println(content);
	            System.out.println("nanana");
	            fw.write(content);
	            fw.close();

	        } catch (IOException iox) {
	            //do stuff with exception
	            iox.printStackTrace();
	        }
		
	}
	
	public default void SaveFile(String filepath,String content){
		
	     try {


	            FileWriter fw = new FileWriter(filepath);
	            fw.write(content);
	            fw.close();

	        } catch (IOException iox) {
	            //do stuff with exception
	            iox.printStackTrace();
	        }
		
	}

}
