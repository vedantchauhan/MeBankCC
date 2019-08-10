package com.main;

import com.bean.TransactionRecord;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Main class with a private function transactionOperations to perform the operations.
 *
 * @author Vedant Chauhan
 */
public class Transactions {

    public static void main(String [] args) {

        try {
            Scanner sc = new Scanner(System.in);
            // Providing menu options for continuous functionality
            System.out.println("Menu: \n 1. For input check \n 2. exit");
            while(sc.hasNextLine()) {
                String choice = sc.nextLine();
                if (choice.equals("1")) {
                    System.out.println("Enter following inputs:");
                    System.out.println("accountId:");
                    String accountIdInput = sc.nextLine();
                    System.out.println("from (in format- DD/MM/YYYY HH:mm:ss):");
                    String fromIn = sc.nextLine();
                    System.out.println("to (in format- DD/MM/YYYY HH:mm:ss):");
                    String toIn = sc.nextLine();

                    // If the inputs are not empty then proceed
                    if(!accountIdInput.isEmpty() && !fromIn.isEmpty() && !toIn.isEmpty()) {
                        // trim any whitespaces
                        String accountId = accountIdInput.trim();
                        String fromInput = fromIn.trim();
                        String toInput = toIn.trim();

                        // convert string to date
                        SimpleDateFormat format = new SimpleDateFormat("DD/MM/YYYY HH:mm:ss");
                        Date from = format.parse(fromInput);
                        Date to = format.parse(toInput);

                        Transactions newTransaction = new Transactions();
                        if(args.length == 0) {
                            throw new Exception("File not found");
                        }
                        newTransaction.transactionOperations(args[0], accountId, from, to);
                    } else{
                        throw new Exception("Not a valid input");
                    }
                } else if (choice.equals("2")) {
                    sc.close();
                    System.exit(0);
                } else {
                    System.out.println("Wrong choice! Choose again.");
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }


    }

    /**
     * Perform CSV file reading and operations on the file
     *
     * @param file
     * @param accountId
     * @param from
     * @param to
     */
    private void transactionOperations(String file, String accountId, Date from, Date to){

        try {

            // filereader with CSV file as a parameter.
            FileReader readCSV = new FileReader(file);

            List<TransactionRecord> dateCheck= new ArrayList<>();
            List<TransactionRecord> revTransaction= new ArrayList<>();

            // converting csv to TransactionRecord bean by skipping the header
            CsvToBean<TransactionRecord> csvToBean = new CsvToBeanBuilder(readCSV)
                    .withType(TransactionRecord.class)
                    .withSeparator(',')
                    .withSkipLines(1)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            Iterator<TransactionRecord> transactionRecordIterator = csvToBean.iterator();
            SimpleDateFormat format = new SimpleDateFormat("DD/MM/YYYY HH:mm:ss");

            // performing the first check based on the date and time and the reversal transactions by storing them in dateCheck and revTransaction lists
            while (transactionRecordIterator.hasNext()) {
                TransactionRecord csvRecord = transactionRecordIterator.next();


                Date record = format.parse(csvRecord.getCreateAt());
                if(record.after(from) && record.before(to)){
                    dateCheck.add(csvRecord);
                }

                if(csvRecord.getTransactionType().contains("REVERSAL")){
                    revTransaction.add(csvRecord);
                }
            }

            //removing reversal transactions from datecheck transactions even it is outside the time frame
            for(TransactionRecord record: dateCheck){
                for(TransactionRecord reversal: revTransaction){
                    if(reversal.getRelatedTransaction().contains(record.getTransactionId())){
                        dateCheck.remove(record);
                    }
                }
            }


            List<TransactionRecord> finalCheck = new ArrayList<>();
            //getting all the transactions with accountID
            for(TransactionRecord recordWithAccountName: dateCheck){
                if(recordWithAccountName.getFromAccountId().contains(accountId) || recordWithAccountName.getToAccountId().contains(accountId)){
                    finalCheck.add(recordWithAccountName);
                }
            }

            // provides no. of transactions
            int count = 0;
            double sendertotal = 0.0;
            double receivertotal = 0.0;
            // provides the balance
            double balance = 0.0;
            if(finalCheck.size() == 0){
                System.out.println("AccountID does not exist in the transaction records.");
            }
            // performing final check for accountID's balance
            for(TransactionRecord record : finalCheck){
                double amount = Double.parseDouble(record.getAmount());

                // accountid was sender
                if(record.getFromAccountId().contains(accountId)){
                    sendertotal = sendertotal - amount;
                }
                // accountid was receiver
                if(record.getToAccountId().contains(accountId)){
                    receivertotal = receivertotal + amount;
                }

                // getting total
                balance = sendertotal + receivertotal;
                count++;
            }

            System.out.println("Relative balance for the period is: $" + balance);
            System.out.println("Number of transactions included is:" + count);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}