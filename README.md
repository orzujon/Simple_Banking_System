# Simple_Banking_System

In this project I have created a console based application where users can create a unique card with pin generated for them. They have a personal account where they can check their balance, deposit money, transfer money to another account, and delete an account. In this project I have used JDBC API for SQLite so the memory of the banking system will not be wiped off once the application is closed. 

Initial page of the applcation looks like this. In this they will be gived 3 options. Based on their choice the application completes the task: 
<img width="291" alt="Screenshot 2022-11-28 at 11 00 35" src="https://user-images.githubusercontent.com/100639316/204314841-8d730879-42de-4605-bdc0-b89423756119.png">
 
 Create an account implements few strategies. The BIN code of the card number is fixed as 400000 and next 9 digit numbers are account number where it is unique and followed by Check Sum (based on Luhn Algorythm); 
 
 Each card is varified by Luhn algorythm for the security purposes
 
 <img width="347" alt="Screenshot 2022-11-28 at 11 00 23" src="https://user-images.githubusercontent.com/100639316/204315649-5bb46181-e0b3-455e-a6ec-bd87a96f5ba2.png">
 
The Luhn algorithm is used to validate a credit card number or other identifying numbers, such as Social Security. Luhn algorithm, also called the Luhn formula or modulus 10, checks the sum of the digits in the card number and checks whether the sum matches the expected result or if there is an error in the number sequence. After working through the algorithm, if the total modulus 10 equals zero, then the number is valid according to the Luhn method.

While the algorithm can be used to verify other identification numbers, it is usually associated with credit card verification. The algorithm works for all major credit cards.

<img width="416" alt="Screenshot 2022-11-28 at 11 00 09" src="https://user-images.githubusercontent.com/100639316/204315912-c316cf2a-be33-4da6-9e0a-cdb475fe79b3.png">

If the user asks for Balance, you should read the balance of the account from the database and output it into the console.

Add income item should allow us to deposit money to the account.

Do transfer item should allow transferring money to another account. You should handle the following errors:

- If the user tries to transfer more money than he/she has, output: Not enough money!
- If the user tries to transfer money to the same account, output the following message: You can't transfer money to the same account!
- If the receiver's card number doesn’t pass the Luhn algorithm, you should output: Probably you made a mistake in the card number. Please try again!
- If the receiver's card number doesn’t exist, you should output: Such a card does not exist.
- If there is no error, ask the user how much money they want to transfer and make the transaction.
- If the user chooses the Close an account item, you should delete that account from the database.
<img width="351" alt="Screenshot 2022-11-28 at 10 59 59" src="https://user-images.githubusercontent.com/100639316/204316354-6185e4dd-7528-4142-ad33-e08564e71460.png">

<img width="435" alt="Screenshot 2022-11-28 at 10 59 45" src="https://user-images.githubusercontent.com/100639316/204316373-1033c3ce-7e91-4384-bda6-0653dd98eef1.png">

Some of the optimization can be performed in the application. Features such as: Account Details, personalizing the account adding the name and details of the user, giving them an option of changing the pin code. 

<img width="307" alt="Screenshot 2022-11-28 at 10 59 31" src="https://user-images.githubusercontent.com/100639316/204316685-0933db08-0e0d-4469-bb4b-e12588b64ba6.png">
