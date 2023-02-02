# OS Command Injection
I created a folder named `USR_NAME` in my Desktop and put a file named `something.txt` into it.
Application asks for the user name then it lookups for the folder named given input in the Desktop folder and prints its content.
This might be a real world scenario where you store user's data and when they want to see their data, you just run some application like this,
get the data and send it to them.

**Scenario 1 (User enters user name):** <br/>
If user enters user name and if there is a file with that name in the Desktop folder, it will read the content of that file and display it.
If not, it will display `Error executing command!` error message.

**Scenario 2 (User enters command with username):** <br/>
What if user enters some malicious command with the user name? <br/>

For example, if user enters `USR_NAME; dir` as the user name, it will execute the command `dir` and display the content of the folder where this application run.

Also user can enter `USR_NAME & ping -n 2 8.8.8.8` and ping the Google's server.


**Scenario 3 (More dangerous payloads):** <br/>
User can enter `USR_NAME & dir & rmdir /Q /S SOME_IMPORTANT_FILE & dir` and delete the important file.

More dangerously, user can enter `USR_NAME & shutdown /s` and after printing the folder content, system will shutdown.
Congratulations, you just let some hacker shutdown your entire server by just executing user inputs and now your users are frustrated and waiting.

## How to prevent this?
Just don't use command executing if you don't really need it but if you really need it, here are some tips:<br/>
- You should always sanitize user inputs before using them in your application. 
- You should create a permission chain to determine which parts of your system will be accessible from which authority level of your application.
- You should create a whitelist of allowed commands and arguments and check user inputs against that whitelist.

For more tips you can visit OWASP's [OS Command Injection](https://owasp.org/www-community/attacks/Command_Injection) page and [defence cheat sheet](https://cheatsheetseries.owasp.org/cheatsheets/OS_Command_Injection_Defense_Cheat_Sheet.html).

# SQL Injection
I'm writing the docs of my work on this topic. This section will be completed soon.