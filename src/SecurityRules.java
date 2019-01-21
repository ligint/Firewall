
public class SecurityRules {

	private String direction;
	private String protocol;
	private int port;
	private long ip_address;

	public SecurityRules(String direction, String protocol, int port, String ip_address) {
		this.direction = direction;
		this.protocol = protocol;
		this.port = port;
		this.ip_address = Long.parseLong(ip_address.replaceAll("\\.", ""));
	}

	public SecurityRules(String direction, String protocol, int port, long ip_address) {
		this.direction = direction;
		this.protocol = protocol;
		this.port = port;
		this.ip_address = ip_address;
	}

	public SecurityRules(String direction, String protocol, String port, long ip_address) {
		this.direction = direction;
		this.protocol = protocol;
		this.port = Integer.parseInt(port);
		this.ip_address = ip_address;
	}

	public SecurityRules(String direction, String protocol, String port, String ip_address) {
		this.direction = direction;
		this.protocol = protocol;
		this.port = Integer.parseInt(port);
		this.ip_address = Long.parseLong(ip_address.replaceAll("\\.", ""));
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof SecurityRules))
			return false;

		SecurityRules sr = (SecurityRules) o;
		return direction.equalsIgnoreCase(sr.direction) && protocol.equalsIgnoreCase(sr.protocol) && port == sr.port
				&& ip_address == sr.ip_address;
	}

	@Override
	public String toString() {
		return this.direction + ", " + this.protocol + ", " + Integer.toString(this.port) + ", "
				+ Long.toString(this.ip_address);
	}

	public int hashCode() {
		long hash = 31 * (this.ip_address + this.port + this.direction.hashCode() + this.protocol.hashCode()); // get
																												// unique
																												// key
																												// from
																												// all
																												// the
																												// components
		return Long.valueOf(hash).hashCode();
	}

}
