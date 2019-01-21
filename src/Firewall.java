import java.io.*;
import java.util.*;

public class Firewall {
	static HashSet<SecurityRules> securityrules = new HashSet<SecurityRules>();

	public Firewall(String file) {

		try (BufferedReader buffer = new BufferedReader(new FileReader(file))) {

			String line;
			while ((line = buffer.readLine()) != null) {
				// System.out.println("Started Reading");

				String firewallRules[] = line.split(",");

				if (firewallRules[2].contains("-")) {
					// System.out.println("1st If");
					String portRanges[] = firewallRules[2].split("-");
					int firstPort = Integer.parseInt(portRanges[0]);
					int lastPort = Integer.parseInt(portRanges[1]);
					int portRange = lastPort - firstPort;

					if (firewallRules[3].contains("-")) {
						// System.out.println("2nd If");
						String ipAddressRange[] = firewallRules[3].split("-");
						long fistIPaddr = Long.parseLong(ipAddressRange[0].replaceAll("\\.", ""));
						long lastIPaddr = Long.parseLong(ipAddressRange[1].replaceAll("\\.", ""));
						long ipRange = lastIPaddr - fistIPaddr;

						for (int i = 0; i <= portRange; i++) {
							for (int j = 0; j <= ipRange; j++) {
								SecurityRules newRule = new SecurityRules(firewallRules[0], firewallRules[1],
										firstPort + i, fistIPaddr + j);
								securityrules.add(newRule);
							}
						}

					} else {
						// System.out.println("1st else");
						for (int i = 0; i <= portRange; i++) {
							SecurityRules newRule = new SecurityRules(firewallRules[0], firewallRules[1], firstPort + i,
									firewallRules[3]);
							securityrules.add(newRule);
						}
					}
				}

				else {
					// System.out.println("No dash 1");
					if (firewallRules[3].contains("-")) {
						String ipAddressRange[] = firewallRules[3].split("-");
						long fistIPaddr = Long.parseLong(ipAddressRange[0].replaceAll("\\.", ""));
						long lastIPaddr = Long.parseLong(ipAddressRange[1].replaceAll("\\.", ""));
						long ipRange = lastIPaddr - fistIPaddr;

						for (int j = 0; j <= ipRange; j++) {
							SecurityRules newRule = new SecurityRules(firewallRules[0], firewallRules[1],
									firewallRules[2], fistIPaddr + j);
							securityrules.add(newRule);
						}
					} else {
						// System.out.println("No dash 2");
						SecurityRules newRule = new SecurityRules(firewallRules[0], firewallRules[1], firewallRules[2],
								firewallRules[3]);
						securityrules.add(newRule);

					}

				}

			}
			// System.out.println("Rules");
			// securityrules.forEach(System.out::println);
		}

		catch (FileNotFoundException e) {
			System.out.println("Cannot find file " + file);
		} catch (Exception e) {
			System.out.println("Exeption occured " + e.getMessage());
		}
	}

	public void acceptPacket(String direction, String protocol, int port, String ip_address) {
		SecurityRules input = new SecurityRules(direction, protocol, port, ip_address);
		// System.out.println(input);
		if (securityrules.contains(input)) {
			System.out.println("Rule is accepted" + "(" + input.toString() + ")");
		} else {
			System.out.println("No such rule present" + "(" + input.toString() + ")");
		}

	}

	public static void main(String[] args) {

		Firewall fw = new Firewall("network.csv");
		fw.acceptPacket("inbound", "tcp", 80, "192.168.1.2");
		fw.acceptPacket("inbound", "udp", 53, "192.168.2.1");
		fw.acceptPacket("outbound", "tcp", 10234, "192.168.10.11");
		fw.acceptPacket("inbound", "tcp", 81, "192.168.1.2");
		fw.acceptPacket("inbound", "udp", 24, "52.12.48.92");

	}

}
