### This is a test project
#### It does not represent the actual Bank

Let's assume that there is a Bank that has some money and there are clients that want to take credits.

How to test:
1. Run the application
2. Create a simple GET request from the browser  
   GET http://localhost:8080/amount   
   This will show the actual money available in the Bank
3. To take a credit use another GET request  
   GET http://localhost:8080/credit?client=Stark&amount=500
4. If this does not work and the Bank does not give a credit try to change the client's name
