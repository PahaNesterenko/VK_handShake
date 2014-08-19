package ua.pasha.VKHandShake;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.simple.parser.ParseException;

public class Worker {

	private static Logger log = Logger.getLogger(VkApiImpl.class.getName());

	private final int TRACEDEEPNESS = 6;
	public static VkApiImpl vk = new VkApiImpl(); 
	ArrayList<Integer> trace = new ArrayList<Integer>();
	boolean isFind = false;

	public ArrayList<User> getTwoUsers() throws IOException, ParseException {
		ArrayList<User> list = new ArrayList<User>();
		list.add(vk.getRandUser());
		log.log(Level.INFO, "first users set");
		list.add(vk.getRandUser());
		log.log(Level.INFO, "second user set");
		return list;
	}

	public ArrayList<User> searchTrace(ArrayList<User> list) throws IOException, ParseException {
		int initId = list.get(0).getId();
		int aimId = list.get(1).getId();
		log.log(Level.INFO, "serch started");
		doTrace(initId, aimId);
		log.log(Level.INFO, "serch finished");

		ArrayList<User> result = new ArrayList<User>();
		for (int traceElem : trace) {
			result.add(vk.getUser(traceElem));
		}
		return result;

	}

	private void doTrace(int curId, int aimId) throws IOException, ParseException {
		trace.add(curId);

		ArrayList<Integer> friendList = vk.getFriendList(curId);

		if (friendList.contains(aimId)) {
			log.log(Level.INFO, "User " + aimId + " finded at " + trace.size()
					+ " level");
			isFind = true;
			return;
		}

		if (!isFind && (trace.size() <= TRACEDEEPNESS)) {
			for (int i : friendList) {
				doTrace(i, aimId);
				if (isFind) {
					return;
				}
			}
		}
		trace.remove(trace.size() - 1);
	}

}
