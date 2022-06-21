----------------------Solutions setup -----------------------------------
1. Install Testng from Help-> Eclipse MarketPlace
2. Install Cucumber plugin from Help-> Eclipse Marketplace
3. Right click on project and update as maven project for any jar related issues

----------------------------Grid Setup-----------------------------------------------------------------------
gridrun=yes will run the code in grid 

1. Download standalone jarfile from : http://selenium-release.storage.googleapis.com/index.html?path=3.141/
And copy the standalone file in c driver or any prefered drive. make sure to update the following command to run the script in grid

2. Run this command to setup the Hub from command prompt: 
  --> java -jar C:\selenium-server-standalone-3.141.59.jar -role hub

3. Now, run nodes command to register node for hub
Node 1 setup-> 
java "-Dwebdriver.chrome.driver=C:\chromedriver.exe" -jar C:\selenium-server-standalone-3.141.59.jar -role node -nodeConfig C:\node1.json


Node 2 setup ->
java "-Dwebdriver.chrome.driver=C:\chromedriver.exe" -jar C:\selenium-server-standalone-3.141.59.jar -role node -nodeConfig \node2.json



Once the test case is executed then extent report can be auto generated in folder: Reports


---------------Local execution----------------------------------------------------
gridrun=no

when gridrun set to no or any value apart from yes will not run in grid 
Here user can run the code from src/test/java --> runner (package) -->MyRunner.java right click and run as testng
Or
User can run from testng.xml file either from eclipse or from command prompt. Ie, go to project location and run the command = mvn test --> this will run maven test




------Git Repo--------------------
My git repo to download the code: 
https://github.com/manoj12419/AmazonProject.git