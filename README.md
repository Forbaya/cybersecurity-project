# cybersecurity-project
This is a simple Spring project to demonstrate five of the [OWASP top ten security flaws of 2017.](https://www.owasp.org/images/7/72/OWASP_Top_10-2017_%28en%29.pdf.pdf)

### Injection
#### Steps to reproducing the security flaw
1. Fill the Name field with anything
2. Fill the Address field with the following

```
Address'); DROP TABLE Signup;
```

This makes the user to first add a valid Signup, but then injecting a query to drop the table, resulting in data loss.
