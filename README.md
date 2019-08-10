# MeBankCC

This README file explains how to use HothFamilyTree application. This file also specifies minimum system requirements needed to run the application.

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
<li> Once the file is converted to bean, the checks are perfromed based on the given input. firstly, based on 'from' and 'to' transactions are added to a list and any reversal transaction is omitted. Then, accountid is checked in the list and based on send or receive of the amount, balance is calculated. </li>
</ul>

<hr>

