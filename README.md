# OS Command Injection
For this section please first see `src/OSCommandInjection.java` file.<br/>
I created a folder named `USR_NAME` in my Desktop and put a file named `something.txt` into it.
Application asks for the username then it looks up for the folder named given input in the Desktop folder and prints its content.
This might be a real world scenario where you store user's data and when they want to see their data, you just run some application like this,
get the data and send it to them.

**Scenario 1 (User enters user name):** <br/>
If user enters username and if there is a file with that name in the Desktop folder, it will read the content of that folder and display it.
If not, it will display `Error executing command!` error message.

**Scenario 2 (User enters command with username):** <br/>
What if user enters some malicious command with the username? <br/>

For example, if user enters `USR_NAME; dir` as the username, it will execute the command `dir` and display the content of the folder where this application run.

Also, user can enter `USR_NAME & ping -n 2 8.8.8.8` and ping the Google's server.


**Scenario 3 (More dangerous payloads):** <br/>
User can enter `USR_NAME & dir & rmdir /Q /S SOME_IMPORTANT_FILE & dir` and delete the important file.

More dangerously, user can enter `USR_NAME & shutdown /s` and after printing the folder content, system will shut down.
Congratulations, you just let some hacker shutdown your entire server by just executing user inputs and now your users are frustrated and waiting.

## How to prevent this?
Just don't use command executing if you don't really need it but if you really need it, here are some tips:<br/>
- You should always sanitize user inputs before using them in your application. 
- You should create a permission chain to determine which parts of your system will be accessible from which authority level of your application.
- You should create a whitelist of allowed commands and arguments and check user inputs against that whitelist.

For more tips you can visit OWASP's [OS Command Injection](https://owasp.org/www-community/attacks/Command_Injection) page and [defence cheat sheet](https://cheatsheetseries.owasp.org/cheatsheets/OS_Command_Injection_Defense_Cheat_Sheet.html).

# SQL Injection
For this section please first see `src/SQLInjection.java` file.<br/>
There are three SQL Injection types mainly:
- SQL Injection based on 1=1 is always true.
- SQL Injection based on 'c'='c' is always false.
- SQL Injection based on batched statements.

You can see the details of these types and their applications in the `src/SQLInjection.java` file.

## How to prevent this?
- You should always sanitize user inputs before using them in your application.
- You should use parameterized queries instead of string concatenation.
- You should use stored procedures or prepared statements instead of dynamic queries.
- You should use a whitelist of allowed query parts and check user inputs against that whitelist.

For more tips you can visit OWASP's [SQL Injection](https://owasp.org/www-community/attacks/SQL_Injection) page and [defence cheat sheet](https://cheatsheetseries.owasp.org/cheatsheets/SQL_Injection_Prevention_Cheat_Sheet.html).