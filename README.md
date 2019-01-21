# Firewall
My Solution for Illumino coding challenge.

All the security rules were stored in a Hashset of SecurityRules object which made the search more efficient.
By overriding the equals method and hashcode method I could compare the test input with the firewall rules using contains method.
If the input was present in the security rules then firewall would allow the rule to pass.

I tested the solution on given input examples and other additional inputs.
