package ua.pasha.VKHandShake;

import java.io.IOException;

import org.json.simple.parser.ParseException;

public class Test {
	
	public static void main(String[] args) throws IOException, ParseException {
		
		VkApiImpl vk = new VkApiImpl();
		User user = vk.getRandUser();
		System.out.println( user.getId() + user.getName() + user.getLastName());
	}

}
