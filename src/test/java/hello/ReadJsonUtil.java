package hello;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class ReadJsonUtil {

	public ReadJsonUtil() {
	}
	
	public static String read_JSON_File(String file) throws IOException {
		//READ JSON FILE --->
		
		File read_file = new File(file);
		String read_jsonString = FileUtils.readFileToString(read_file, "UTF-8");
		System.out.println("\n \n \n \n " + read_jsonString.toString());
		return read_jsonString;
	}
}