package commons;
import java.util.*;

public class RegExMap<K, V> extends HashMap<K, V> {

	@Override
	public V get(Object key) {

		String keyAsString = key.toString();
		String regExKey = null;
		Iterator it = keySet().iterator();
		while (it.hasNext() && (regExKey == null)) {
			String keyFromSet = it.next().toString();
			if (keyAsString.matches(keyFromSet)) 
				regExKey = keyFromSet;
		}
		return (regExKey == null) ? null : super.get(regExKey);

	}

	@Override
	public boolean containsKey(Object key) {
		String keyAsString = key.toString();
		Iterator it = keySet().iterator();
		Boolean result = null;
		
		while (it.hasNext() && (result == null)) {
			String keyFromSet = it.next().toString();
			if (keyAsString.matches(keyFromSet)) {
				result = true;
				break;
			}
		}
		return (result == null) ? false : true;
	}
}
