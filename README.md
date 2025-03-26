This tool analyzes your WowCombatLog.txt and extracts some useful informations
(Example: http://klarasprudel.atwebpages.com/schmetterlingsbrigade/3_14_2025_Naxxramas_v033_schmetterlingsbrigade.html)

You really need to have SuperwowcombatLogger: https://github.com/pepopo978/SuperWowCombatLogger?tab=readme-ov-file
and also: https://github.com/balakethelock/SuperWoW
Otherwise a lot of useful informations (like sunder armor) will be missing in your log

At the moment you better use a clean wowcombatlog, so only with the raid you wanted to analyze,
if you have a very huge combatlog with a lot of raid over several days, the result would be broken i guess.
I start afresh log with every raid.

Download the jar (for example TWowLogAnalyzer-0.3.3.jar)file, 
or grab the whole sources and build it yourself.
with: maven clean package
Then after a successfull build you find it in yourFolder called something like: /target/TWowLogAnalyzer-0.3.3-jar-with-dependencies.jar

!!!You need to have at least java 17 installed!!!

Java 1.8 what is offered from oracle is not matching!

YOu could get a free open java here: https://www.openlogic.com/openjdk-downloads
If you only want to let it run you need a JRE; if you want to change code and build you need a JDK

If you only want to start the tool via doubleclick, download the jre msi installer
![image](https://github.com/user-attachments/assets/759b39a6-17f1-4e70-8b4d-f781f4ac1967)

Another alternative is to download a open jdk (>17) from here: 
https://jdk.java.net/archive/
Unzip it and configure your path variable, that it will find this downloaded java.
But if you not familar with those stuff choose the msi installer from openlogic for exampe (as described above)

And then download my package, unzipp it and double click the TwowLogAnalyzer-versionjar



If you have Java installed (at least java 17) u can run it by doubleclick (if java is connected to open a jar)
or you hit a cmd shell and enter "java -jar NameOfTHeFile.jar"

After this a GUI opens:

![image](https://github.com/user-attachments/assets/51f03d55-0249-4604-b129-b3a65f8044a0)


Here you select ur combatlog and hit "Start"

![image](https://github.com/user-attachments/assets/655eeb43-6bc7-4942-9a9b-7a33906ec274)

Depending of your CPU and the Raid this can take a while ...few seconds up to 2/3 minutes,
but the GUI is telling u what at the moment is calulated....

If everything is done, it generates a html page in ur user space and opens ur system browser
with that page: 

![image](https://github.com/user-attachments/assets/c0dd1f71-c327-4a51-8be1-a73cb4e9d54b)

