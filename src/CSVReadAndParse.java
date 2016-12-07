import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReadAndParse {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub

		
		CSVFileParser parse = new CSVFileParser();
		List<String[]> data = parse.run("/home/sun/birthday.csv");
		
		for(String[] element : data) {
			for (String el : element){
	       		  System.out.print(el + ", ");
			}
			System.out.println();
		}
		
		//parse.read("/home/sundarina/birthday.csv");
		List<Title> people = parse.userInfo("/home/sun/birthday.csv");
		for(Title p : people){
		System.out.print(p.id + ", ");
		System.out.print(p.name + ", ");
		System.out.println(p.age);
		}
	}
}
