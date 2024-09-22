import java.util.Scanner;

class BankAccount {
    String name;
    String username;
    String password;
    String accountNo;
    float balance = 100000f;
    int transactions = 0;
    String transactionHistory = "";

    public void register() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your Name: ");
        this.name = sc.nextLine();
        System.out.println("Enter your Username: ");
        this.username = sc.nextLine();
        System.out.println("Enter your Password: ");
        this.password = sc.nextLine();
        System.out.println("Enter your Account Number: ");
        this.accountNo = sc.nextLine();
        System.out.println("Registration Successful. Please login to your Bank account");
    }

    public boolean login() {
        boolean isLogin = false;
        Scanner sc = new Scanner(System.in);
        while (!isLogin) {
            System.out.println("Enter your username: ");
            String inputUsername = sc.nextLine();
            if (inputUsername.equals(username)) {
                System.out.println("Enter your password: ");
                String inputPassword = sc.nextLine();
                if (inputPassword.equals(password)) {
                    System.out.println("Login Successful");
                    isLogin = true;
                } else {
                    System.out.println("Incorrect Password! Please try again");
                }
            } else {
                System.out.println("User not found");
            }
        }
        return isLogin;
    }

    public void withdraw() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter amount to withdraw: ");
        float amount = sc.nextFloat();
        if (balance >= amount) {
            transactions++;
            balance -= amount;
            System.out.println(amount + " Rs is debited from your account.");
            transactionHistory += amount + " Rs withdrawn\n";
        } else {
            System.out.println("Insufficient Balance...");
        }
    }

    public void deposit() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter amount to deposit: ");
        float amount = sc.nextFloat();
        if (amount <= 10000f) {
            transactions++;
            balance += amount;
            System.out.println(amount + " Rs deposited in your account.");
            transactionHistory += amount + " Rs deposited\n";
        } else {
            System.out.println("Sorry, one-time limit for deposit is 10000 Rs.");
        }
    }

    public void transfer() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Recipient's name: ");
        String recipient = sc.nextLine();
        System.out.println("Enter the amount to transfer: ");
        float amount = sc.nextFloat();
        if (balance >= amount) {
            if (amount <= 50000f) {
                transactions++;
                balance -= amount;
                System.out.println("Successfully Transferred " + amount + " Rs to " + recipient);
                transactionHistory += amount + " Rs transferred to " + recipient + "\n";
            } else {
                System.out.println("Transfer limit exceeded (50000 Rs).");
            }
        } else {
            System.out.println("Insufficient Balance...");
        }
    }

    public void checkBalance() {
        System.out.println("Available Balance: " + balance + " Rs");
    }

    public void showTransactionHistory() {
        if (transactions == 0) {
            System.out.println("No transactions made yet.");
        } else {
            System.out.println("\nTransaction History:\n" + transactionHistory);
        }
    }
}

public class Main {
    public static int takeIntegerInput(int limit) {
        int input = 0;
        boolean flag = false;
        while (!flag) {
            try {
                Scanner sc = new Scanner(System.in);
                input = sc.nextInt();
                flag = true;

                if (input < 1 || input > limit) {
                    System.out.println("Choose a number between 1 and " + limit);
                    flag = false;
                }
            } catch (Exception e) {
                System.out.println("Enter only an integer value.");
                flag = false;
            }
        }
        return input;
    }

    public static void main(String[] args) {
        System.out.println("\n************************************Welcome TO Axis Bank");
        System.out.println("\n1. Register \n2. Exit");
        System.out.println("Choose one option: ");
        int choice = takeIntegerInput(2);

        if (choice == 1) {
            BankAccount account = new BankAccount();
            account.register();
            while (true) {
                System.out.println("\n1. Login \n2. Exit");
                System.out.println("Enter your choice: ");
                int ch = takeIntegerInput(2);
                if (ch == 1) {
                    if (account.login()) {
                        System.out.println("\n***************************Welcome Back " + account.name + "***************************");
                        boolean isFinished = false;
                        while (!isFinished) {
                            System.out.println("\n1. Withdraw \n2. Deposit \n3. Transfer \n4. Check Balance \n5. Transaction History \n6. Exit");
                            System.out.println("Enter your choice: ");
                            int c = takeIntegerInput(6);
                            switch (c) {
                                case 1:
                                    account.withdraw();
                                    break;
                                case 2:
                                    account.deposit();
                                    break;
                                case 3:
                                    account.transfer();
                                    break;
                                case 4:
                                    account.checkBalance();
                                    break;
                                case 5:
                                    account.showTransactionHistory();
                                    break;
                                case 6:
                                    isFinished = true;
                                    break;
                            }
                        }
                    }
                } else {
                    System.exit(0);
                }
            }
        } else {
            System.exit(0);
        }
    }
}
