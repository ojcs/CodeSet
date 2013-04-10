package sep.util.net.ipseeker;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Hashtable;
import java.util.Map;

public class IPSeekerCache extends IPSeeker {
	private final Map<byte[], IPLocation> cache = new Hashtable<>();
	
	public IPSeekerCache(Path path) throws IOException {
		super(path);
	}

	@Override
	public IPLocation getLocation(byte... address) {
		if (cache.containsKey(address)) {
			return cache.get(address);
		} else {
			IPLocation location = super.getLocation(address);
			cache.put(address, location);
			return location;
		}
	}
}
