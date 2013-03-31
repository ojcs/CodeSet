package sep.util.time;

import java.util.HashMap;
import java.util.Map;

public class Timer {
	private final Map<String, Long> map = new HashMap<>();
	
	public void begin(String name) {
		map.put(name + "_start", System.nanoTime());
	}
	
	public void end(String name) {
		map.put(name + "_end", System.nanoTime());
	}
	
	public void remove(String name) {
		map.remove(name + "_start");
		map.remove(name + "_end");
	}
	
	/** @return 单位ns */
	public long get(String name) {
		if (map.containsKey(name + "_start") && map.containsKey(name + "_end")) {
			final long end = map.get(name + "_end").longValue();
			final long start = map.get(name + "_start").longValue();
			return start - end;
		} else {
			return -1;
		}
	}
}
