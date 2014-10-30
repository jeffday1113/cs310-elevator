package lab3;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class Main {
	

	public static  PrintWriter writer ;
	
	public static void main(String[] args) {
		
		try {
			writer = new PrintWriter("Elevator.log", "UTF-8");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(args.length==0) {
			Parser.open();
		} 
		else if(args.length > 1) {
			writer.println("Too many arguments");
		} 
		else if(args[0].equals("p1")) //Test cases for Event Barrier are "hard baked" into test class
		{
			writer.println("Event Barrier:");
		}
		else if(args[0].equals("p2")){ 
		
			Parser.open();
		}
		writer.close();
	}
}
