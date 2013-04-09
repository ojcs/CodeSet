package sep.util.net.ipseeker;

public final class IPLocation {
	static final IPLocation Unknown = new IPLocation("未知国家", "未知地区");

	private final String area;
	private final String country;

	IPLocation(String country, String area) {
		if ((country != null && !country.isEmpty()) && (area != null && !area.isEmpty())) {
			this.country = country;
			// 如果为局域网，纯真IP地址库的地区会显示CZ88.NET,这里把它去掉
			this.area = area.equals("CZ88.NET") ? "局域网" : area;
		} else {
			throw new NullPointerException();
		}
	}

	public String getArea() {
		return area;
	}
	
	public String getCountry() {
		return country;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + area.hashCode();
		result = prime * result + country.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IPLocation other = (IPLocation) obj;
		if (area == null) {
			if (other.area != null)
				return false;
		} else if (!area.equals(other.area))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return area + '-' + country;
	}

	static IPLocation of(String country, String area) {
		if ((country != null && !country.isEmpty()) && (area != null && !area.isEmpty())) {
			return new IPLocation(country, area);
		} else {
			return Unknown;
		}
	}
}