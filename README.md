# MeBankCC

This README file explains how to use MeBank coding challenge application. This file also specifies minimum system requirements needed to run the application.

<hr>

Project Description:

The implementation target is to develop a system analyzing financial transaction records.
A transaction record describes transferring money from one account to another
account. As such, each transaction record will have the following fields: <br>
transactionId, fromAccountId, toAccountId, createAt, amount, relatedTransaction.

The system takes a CSV file containing a list of records and returns a relative account balance.

<hr>

Approach:
<ul>
<li>The project is Java maven project with Java 8 or higher.</li>
<li>
The project is simple contains two packages main and a TransactionRecord bean.
</li>
<li>
In relation to the packages there is an input file called transactions.csv which contains transaction records.
</li>
<li> The program has a straight forward approach of taking the CSV files and using CsvToBean to read the file line by line and convert it into bean.</li>
<li> Once the file is converted to bean, the checks are perfromed based on the given input. Firstly, based on 'from' and 'to' transactions are added to a list and any reversal transaction is omitted. Then, accountid is checked in the list and based on send or receive of the amount, balance is calculated. </li>
</ul>

<hr>

System Requirements:
    <ul>
        <li>System must have Java8 or higher version.</li>
        <li>Maven pom.xml contains source and target JDK 1.8 </li>
        <li>System must comply minimum requirements specified for JVM.</li>
    </ul>
    
<hr>

Libraries included:
    <ul>
        <li> opencsv-4.1: To read CSV files</li>
    </ul>    
<hr>

Running in an IDE:

If you want to run the application in an IDE, such as Intellij IDEA, you should be able to import the entire project into the IDE. Alternatively,
you can copy entire source files into existing java maven project, add dependencies into maven pom.xml and then use /src/main/java/com/main/Transactions.java to run application.

<hr>

Run using JAR file:

The MeBankCodingChallenge.jar file is in /out/artifacts/MeBankCodingChallenge_jar directory. Use following command to execute the jar:<br>
<i>java -jar MeBankCodingChallenge.jar <path-to-transactions.csv-file></i>

Remember to have the Java version above 8 or higher.

<hr>

Execution instructions and test cases:

When you execute the application, following prompt occurs: <br>
<i>
Menu:
 1. For input check
 2. exit </i>
 
Provide your response. Application will continue to prompt for inputs and will show results. Type '2' to exit. With '1' response, below test cases area attached: <br>
<i> 
1
Enter following inputs:
accountId:
ACC334455
from (in format- DD/MM/YYYY HH:mm:ss):
20/10/2018 12:00:00
to (in format- DD/MM/YYYY HH:mm:ss):
20/10/2018 19:00:00
Relative balance for the period is: $-25.0
Number of transactions included is:1
1
Enter following inputs:
accountId:
ACC778899
from (in format- DD/MM/YYYY HH:mm:ss):
20/10/2018 12:00:00
to (in format- DD/MM/YYYY HH:mm:ss):
20/10/2018 19:00:00
Relative balance for the period is: $30.0
Number of transactions included is:2
1
Enter following inputs:
accountId:
ACC998877
from (in format- DD/MM/YYYY HH:mm:ss):
20/10/2018 12:00:00
to (in format- DD/MM/YYYY HH:mm:ss):
20/10/2018 19:00:00
Relative balance for the period is: $-5.0
Number of transactions included is:1
2
</i>
<br>
Remember the values are case-sensitive, if case is not followed, the output will not be the one desired.

<hr>


Assumptions:
    <ul>
        <li>Input file and records are all in a valid format including a header.</li>
        <li>Transaction are recorded in order.</li>
        <li>Date format should be DD/MM/YYYY HH:mm:ss. </li>
    </ul>
