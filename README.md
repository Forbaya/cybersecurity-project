# cybersecurity-project
This is a simple Spring project to demonstrate five of the [OWASP top ten security flaws of 2017.](https://www.owasp.org/images/7/72/OWASP_Top_10-2017_%28en%29.pdf.pdf)



### A1:2017 - Injection
#### Steps to reproducing the security flaw
1. Go to the `/form` page.
2. Fill the Name field with anything.
3. Fill the Address field with the following.

```
Address'); DROP TABLE Signup;
```

This makes the user to first add a valid Signup, but then injecting a query to drop the table, resulting in data loss.

### A7:2017 - Cross-Site Scripting (XSS)
#### Steps to reproducing the security flaw
1. Go to the `/form` page.
2. Fill the Name field with anything.
3. Fill the Address field with the following `<script>alert("This is an XSS attack!");</script>`
4. Go to the `/signups` page.

The alert script should run.

#### How to fix it
The `signup.html` has the following
```
<td data-th-utext="${signup.address}"></td>
```

Since we are using the Thymeleaf's `data-th-utext` the text will be unescaped and the alert script will run. To fix this we can change it to
```
<td data-th-text="${signup.address}"></td>
```

### A9:2017 - Using Components with Known Vulnerabilities
#### Steps to reproducing the security flaw
1. Run the following command `mvn dependency:check check`, this will generate a report of the vulnerabilities found.
2. Go to the `/target` directory and open `dependency-check-report.html`

At the time of writing this the project has 14 vulnerable dependencies with a total of 87 vulnerabilities found.

#### How to fix it
The vulnerabilities can be fixed by updating all the dependencies.