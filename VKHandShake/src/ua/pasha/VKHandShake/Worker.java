package ua.pasha.VKHandShake;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.simple.parser.ParseException;

public class Worker {

	private static Logger log = Logger.getLogger(VkApiImpl.class.getName());

	private final int TRACEDEEPNESS = 10;
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
		for( int traceElem : trace){
			result.add(vk.getUser(traceElem));
		}
		return result;

	}

	private void doTrace(int curId, int aimId) throws IOException, ParseException {
		if (trace.size() >= TRACEDEEPNESS || isFind) {
			return;
		}
		trace.add(curId);

		for (int i : vk.getFriendList(curId)) {
			if (i == aimId) {
				isFind = true;
				log.log(Level.INFO, "trace finded");
			}
			if( isFind){
				log.log(Level.INFO, "search stopped cause of find");
				return;
			}
			if( trace.size() <= TRACEDEEPNESS){
				doTrace(i, aimId);
			}
		}
		trace.remove(trace.size() - 1);

	}

}
