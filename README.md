#  Secure Expense Vault

> A Java-based Secure Ledger and CLI Expense Tracker built as a Capstone Project.

##  Overview
**Secure Expense Vault** is a localized, offline financial tracking application. While many beginner-level expense trackers store data in plain text, this project addresses the real-world problem of data privacy. It acts as a secure ledger that requires user authentication and scrambles all persistent data using a custom encryption layer, ensuring your financial records remain completely private on your local machine.

This project was developed to demonstrate the practical application of core software engineering principles, moving beyond basic scripts into a fully functional, resilient application.

##  Key Features
* ** Password Authentication:** The application state is locked behind a Master Password.
* ** Full CRUD Operations:** Users can seamlessly Create, Read, and Delete individual expense records.
* ** Smart Analytics:** Automatically aggregates data and generates real-time spending summaries grouped by category.
* ** Secure Data Persistence:** Saves records permanently to a local file (`data/vault.txt`).
* ** Built-in Encryption:** Utilizes an XOR cipher for File I/O operations, rendering the raw text file unreadable to unauthorized users.
* ** Resilient Input Handling:** Includes `Scanner` validation loops to prevent application crashes from invalid user inputs (e.g., typing letters instead of numbers).

##  Tech Stack & Architecture
* **Language:** Java (JDK 11+)
* **Paradigm:** Object-Oriented Programming (OOP)
* **Core Concepts Applied:**
  * **Encapsulation:** Protected data models (`Expense.java`).
  * **Collections Framework:** Efficient data management using `ArrayList` for storage and `HashMap` for $O(1)$ category aggregation.
  * **File I/O:** Streamlined reading/writing using `BufferedReader` and `PrintWriter`.

### Technical Highlight: Custom Delimiter Parsing
To prevent data corruption from user-generated input (such as entering a description containing commas), the application implements a custom double-semicolon (`;;`) delimiter for its CSV storage logic. This ensures robust data serialization and deserialization regardless of the text the user inputs.

##  How to Set Up and Run

### Prerequisites
* **Java Development Kit (JDK)** installed on your system.
* A terminal or command prompt.

### Installation
1. Clone this repository to your local machine:
   ```bash
   git clone [https://github.com/shikhasingh-0508/SecureExpenseVault.git](https://github.com/shikhasingh-0508/SecureExpenseVault.git)
Navigate into the project directory:

Bash
cd SecureExpenseVault
Compile the Java source files:

Bash
javac src/*.java -d .
Usage
Start the application by running:

Bash
java Main
Security Key:
When prompted upon initialization, enter the default Master Password to unlock the vault:

s_singh05

Navigating the Application
Once authenticated, use the number keys to navigate the interactive menu:

Add Expense: Enter the date, category (e.g., Food, Transport), amount, and a brief description.

View All: Displays a decrypted, numbered ledger of all your saved expenses.

Category Summary: Generates a real-time report showing total money spent per category.

Delete Expense: Enter the ID number of an expense to permanently remove it from the vault.

Lock & Exit: Securely triggers the File I/O save sequence and closes the application.

Project Structure
Plaintext
SecureExpenseVault/
├── data/
│   └── vault.txt             # Auto-generated encrypted data file
├── src/
│   ├── Expense.java          # Data model and string formatting
│   ├── ExpenseManager.java   # Core logic, HashMap analytics, and File I/O
│   ├── Security.java         # Authentication and XOR encryption algorithms
│   └── Main.java             # Entry point and CLI user interface
└── README.md                 # Project documentation
Developed by Shikha Singh
