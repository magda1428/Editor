import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringJoiner;

public interface SaveAble {
	
	public default void SaveAsFile(String filepath,String content){
		
	     try {
	    	 	if (!filepath.endsWith(".geek")){
	    	 		StringJoiner joiner = new StringJoiner("");
	    	 		joiner.add(filepath);
	    	 		joiner.add(".geek");
	    	 		filepath=joiner.toString();

	    	 	}

	    	 	File newTextFile = new File(filepath);
	            FileWriter fw = new FileWriter(newTextFile);
	            fw.write(content);
	            fw.close();

	        } catch (IOException iox) {
	            //do stuff with exception
	            iox.printStackTrace();
	        }
		
	}
	
	public default void SaveFile(String filepath,String content){
		
	     try {	
	    	   if (!filepath.endsWith(".geek")){
	    	 		StringJoiner joiner = new StringJoiner("");
	    	 		joiner.add(filepath);
	    	 		joiner.add(".geek");
	    	 		filepath=joiner.toString();

	    	 	}


	            FileWriter fw = new FileWriter(filepath);
	            fw.write(content);
	            fw.close();

	        } catch (IOException iox) {
	            //do stuff with exception
	            iox.printStackTrace();
	        }
		
	}

}
