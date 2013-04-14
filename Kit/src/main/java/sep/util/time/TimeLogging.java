package sep.util.time;

import java.util.HashMap;
import java.util.Map;

public class TimeLogging {
	private static class TimePair {
		long begin;
		long end;
		
		long difference() {
			return end - begin;
		}
	}
	
	private final Map<String, TimePair> map = new HashMap<>();
	
	public void begin(String name) {
		get(name).begin = System.nanoTime();
	}
	
	public void end(String name) {
		get(name).end = System.nanoTime();
	}
	
	private TimePair get(String name) {
		if (map.containsKey(name)) {
			return map.get(name);
		} else {
			return map.put(name, new TimePair());
		}
	}
	
	/** @return 单位ns */
	public long getResult(String name) {
		if (map.containsKey(name)) {
			return get(name).difference();
		} else {
			return -1;
		}
	}
	
	public void remove(String name) {
		map.remove(name);
	}
}
