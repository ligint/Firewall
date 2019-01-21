# Firewall
My Solution for Illumino coding challenge.

All the security rules were stored in a Hashset of SecurityRules object which made the search more efficient.
By overriding the equals method and hashcode method I could compare the test input with the firewall rules using contains method.
If the input was present in the security rules then firewall would allow the rule to pass.

I tested the solution on given input examples and other additional inputs.


Test Cases:
Rule is accepted(inbound, tcp, 80, 19216812)
Rule is accepted(inbound, udp, 53, 19216821)
Rule is accepted(outbound, tcp, 10234, 1921681011)
No such rule present(inbound, tcp, 81, 19216812)
No such rule present(inbound, udp, 24, 52124892)
