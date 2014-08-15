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
		User user = new User();
		while (true) {
			int id = rand.nextInt(50000000);
			String method = "users.get";
			String parameter = "user_ids=" + id;
			log.log(Level.INFO, "random id set to - " + id);

			InputStreamReader in = request(method, parameter);

			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(in);
			JSONArray jarr = (JSONArray) jsonObject.get("response");
			jsonObject = (JSONObject) jarr.get(0);
			if (jsonObject.containsKey("deactivated")) {
				log.log(Level.INFO, "User deactivated");
				continue;
			}
			
			String name = (String) jsonObject.get("first_name");
			String lastName = (String) jsonObject.get("last_name");
			user.setId(id);
			user.setName(name);
			user.setLastName(lastName);
			log.log(Level.INFO, "user set: id - " + id + " name - " + name + " last name - " + lastName);
			break;
		}

		return user;

	}

	public ArrayList<Integer> getUserFriends() {
		String method = "friends.get";
		String parameter = "order=random";

		return new ArrayList<Integer>();
	}

	public InputStreamReader request(String... args) throws IOException {
		String host = "https://api.vk.com/method/";
		String urlString = host + args[0] + "?" + args[1];
		for (int i = 2; i < args.length; ++i) {
			urlString = urlString + "&" + args[i];
		}
		URL url = new URL(urlString);
		URLConnection conn = url.openConnection();
		InputStreamReader in = new InputStreamReader(conn.getInputStream(),
				"UTF-8");
		return in;
	}

}
