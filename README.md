# HTML-Tag-Checking-App
USE:
      
This Java program checks whether an HTML file has wrong tagging and informs the user about it.

INFO:
      
- It checks for errors like: Wrong tag format (e.g. <HEAD ), Wrong tag closing order (e.g. <HEAD> <BODY> </HEAD> </BODY>)
      
- It only works for simple HTML tags (e.g. not working on "<a href="...>", "<BODY BGCOLOR=...>") 
  open and close tags must have the same word inside them.


HOW TO RUN:
- Intellij: 
                
You have to pass the HTML file as an argument on the main method:
                    
Run -> Edit Configurations -> + -> Application -> Give a name -> Give main class name (TagMatching) -> Give program arguments (test_html_file.html) -> OK
      
File "test_html_file.html" has to be in project file (outside of src file)
     
- CMD:
                
"test_html_file.html" file must be in src folder -> Go to java files folder through cmd navigation ( cd ) -> javac *.java -> java TagMatching test_html_file.html
 
 
 ![Screenshot_5](https://user-images.githubusercontent.com/48126722/182365485-2bf7cbf4-9d64-429c-a2ae-70b37f3377af.png)


![Screenshot_6](https://user-images.githubusercontent.com/48126722/182365492-499be1c6-960b-4f6d-a62c-3486f991197c.png)



Running on: jdk-11.0.11
