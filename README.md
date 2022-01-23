# Expense-Tracker-App
Expense tracker application for adding expenses, dues and settling dues based on different implementations

# Steps to run application on your local system:

- Clone the app using git clone <URL>
- Load the project in IntelliJ IDEA 
- Start the application from the main method.
- In-memory database is visible at http://localhost:8080/h2-console. Username: sa, Password: password
- All APIs available at http://localhost:8080

## Few assumptions made:
- By default application will use Latest Repayment first algorithm to settle all dues.
- User can either settle all dues (provided he has sufficient balance) or cant settle the dues.
- In memory database is used for simplicity
- No test cases written due to time constraints. 
- API versioning not yet done.