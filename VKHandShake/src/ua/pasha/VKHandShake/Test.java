package ua.pasha.VKHandShake;

import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.parser.ParseException;

public class Test {
	
	public static void main(String[] args) throws IOException, ParseException {
		
		
		Worker work = new Worker();
		ArrayList<User> twoUsers = new ArrayList<User>();
		User u1 = new User();
		User u2 = new User();
		u1.setId(220142247);
		u2.setId(248897092); 
		twoUsers.add(u1);
		twoUsers.add(u2);
		
		
		ArrayList<User> list = work.searchTrace(twoUsers);
		for( User i : list){
		System.out.println( i);
		}
		
	}

}
