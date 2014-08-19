package ua.pasha.VKHandShake;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class VkApiImpl {

	private static Logger log = Logger.getLogger(VkApiImpl.class.getName());

	public User getRandUser() throws IOException, ParseException {

		Random rand = new Random();
		User user;
		do {
			user = getUser(rand.nextInt(50000000));
		}while (user.isDeactivated() || user.getName().equals("DELETED"));
		return user;
	}
	
	public User getUser(int id) throws IOException, ParseException{
		User user = new User();
		String method = "users.get";
		String parametr = "user_ids=" + id;
		
		InputStreamReader in = request(method, parametr);
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject) jsonParser.parse(in);
		JSONArray jarr = (JSONArray) jsonObject.get("response");
		jsonObject = (JSONObject) jarr.get(0);
		if (jsonObject.containsKey("deactivated")) {
			log.log(Level.INFO, "User deactivated");
			user.setDeactivated(true);
		}

		String name = (String) jsonObject.get("first_name");
		String lastName = (String) jsonObject.get("last_name");
		user.setId(id);
		user.setName(name);
		user.setLastName(lastName);
		log.log(Level.INFO, "user set: id - " + id + " name - " + name
				+ " last name - " + lastName);
		in.close();
		return user;
		
	}

	public ArrayList<Integer> getFriendList(int id) throws IOException, ParseException{
		
		String method = "friends.get";
		String parametr1 = "user_id=" + id;
		//String parametr2 = "order=random";

		InputStreamReader in = request(method, parametr1);
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject) jsonParser.parse(in);
		JSONArray jarr = (JSONArray) jsonObject.get("response");
		ArrayList<Integer> list = new ArrayList<Integer>();
		try{
		for (Object o : jarr) {
			list.add((int) (long) o);
		}
		}catch(NullPointerException e){
			log.log(Level.INFO, "NPE!!!!");
		} 
		log.log(Level.INFO, "Friend list from user " + id
				+ " odtained. There are " + list.size() + " friends");
		in.close();
		return list;
	}

	public InputStreamReader request(String... args) throws IOException {
		String host = "https://api.vk.com/method/";
		String urlString = host + args[0] + "?" + args[1];
		for (int i = 2; i < args.length; ++i) {
			urlString = urlString + "&" + args[i];
		}
		URL url = new URL(urlString);
		URLConnection conn = url.openConnection();
		InputStreamReader in = null;
		do{
			try{
				in = new InputStreamReader(conn.getInputStream(), "UTF-8");
			}catch(IOException e){
				
			} 
		}while(in == null);
		
		return in;
	}

}
