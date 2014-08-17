package ua.pasha.VKHandShake;

import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.parser.ParseException;

public class Test {
	
	public static void main(String[] args) throws IOException, ParseException {
		
		
		Worker work = new Worker();
		ArrayList<User> twoUsers = work.getTwoUsers();
		
		ArrayList<User> list = work.searchTrace(twoUsers);
		for( User i : list){
		System.out.println( i);
		}
		
		
		
	}

}
