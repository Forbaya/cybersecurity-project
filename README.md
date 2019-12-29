# cybersecurity-project
This is a simple Spring project to demonstrate five of the [OWASP top ten security flaws of 2017.](https://www.owasp.org/images/7/72/OWASP_Top_10-2017_%28en%29.pdf.pdf)



### A1:2017 - Injection
#### Steps to reproducing the security flaw
1. Login with username `admin` and password `admin`.
2. Go to the `/form` page.
3. Fill the Name field with anything.
4. Fill the Address field with `Address'); DROP TABLE Signup; -- -`
5. Press Submit.

This makes the user to first add a valid Signup, but then injecting a query to drop the table, resulting in data loss.

#### How to fix
Get rid of the `DatabaseManager` class and use make a new POST-endpoint to the `SignupController` to submit the form.

### A3:2017 - Sensitive Data Exposure
#### Steps to reproducing the security flaw
1. Login with username `admin` and password `admin`.
2. go to the `/form` page.
3. Fill the Name field with `Name',(SELECT Username, Password FROM User WHERE Id=1))-- -`
4. Press Submit.

This adds a Signup with the address field populated with sensitive information.

#### How to fix
Same as fixing the injection problem.

### A5:2017 - Broken Access Control
#### Steps to reproducing the security flaw
1. Login with username `Pekka` and password `salasana123`.
2. Go to the `/signups` page, which should only be working for `ROLE_ADMIN` users.

#### How to fix
Change the `configure`-method in `SecurityConfiguration.java` to the following
```
@Override
protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
            .antMatchers("/signups").hasAuthority("ROLE_ADMIN")
            .anyRequest().authenticated().and()
            .formLogin().permitAll();
}
```
Now the `/signups` page is only working for `ROLE_ADMIN` users.


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