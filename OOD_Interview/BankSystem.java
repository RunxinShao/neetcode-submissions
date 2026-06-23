//TIP 要<b>运行</b>代码，请按 <shortcut actionId="Run"/> 或
// 点击装订区域中的 <icon src="AllIcons.Actions.Execute"/> 图标。
class BankAccount {
    private int customerId;
    private String name;
    private int balance;

    public BankAccount(int customerId, String name, int balance) {
        this.customerId = customerId;
        this.name = name;
        this.balance = balance;
    }

    public int getBalance() {
        return this.balance;
    }

    public void deposit(int amount) {
        this.balance += amount;
    }

    public void withdraw(int amount) {
        this.balance -= amount;
    }
}
class BankTeller {
    private int id;

    public BankTeller(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
abstract class Transaction{
    private int customerID;
    private int tellerID;
    public Transaction(int customerID,int tellerID){
        this.customerID = customerID;
        this.tellerID = tellerID;
    }

    public int getTellerID() {
        return tellerID;
    }

    public int getCustomerID() {
        return customerID;
    }
    public abstract String getTransactionDescription();
}
class OpenAccount extends Transaction{
    public OpenAccount(int customerID, int tellerID) {
        super(customerID, tellerID);
    }

    @Override
    public String getTransactionDescription() {
        return "Teller" + getTellerID()+ "opened an account for" + getCustomerID();
    }


}
class Deposit extends Transaction{
    private int amount;
    public Deposit(int customerID, int tellerID,int amount) {
        super(customerID, tellerID);
        this.amount = amount;
    }

    @Override
    public String getTransactionDescription() {
        return "Teller " + getTellerID() + " deposited " + amount + " to account " + getCustomerID();
    }
}
class Withdrawal extends Transaction{
    private int amount;
    public Withdrawal(int customerID, int tellerID,int amount) {
        super(customerID, tellerID);
        this.amount = amount;
    }

    @Override
    public String getTransactionDescription() {
        return "Teller " + getTellerID() + " withdrawed " + amount + " from account " + getCustomerID();
    }
}

class BankSystem {
    // kinda like a persistence layer and also simulate an actual database,
    // store all the account and transactions,
    // also check for the contraints
    private List<BankAccount> accounts; // hashmap is better
    private List<Transaction> transactions;

    public BankSystem(List<BankAccount> accounts, List<Transaction> transactions) {
        this.accounts = accounts;
        this.transactions = transactions;
    }

    public BankAccount getAccount(int customerID) {
        return accounts.get(customerID);
    }

    public List<BankAccount> getAccounts() {
        return this.accounts;
    }

    public List<Transaction> getTransactions() {
        return this.transactions;
    }
    public int openAccount( int tellerID, String name){
        int customerID = this.accounts.size();
        BankAccount b = new BankAccount(customerID,name,tellerID);
        this.accounts.add(b);

        Transaction t = new OpenAccount(customerID,tellerID);
        transactions.add(t);
        return customerID;
    }

    public void deposit(int customerID, int tellerID, int amount) {
        BankAccount account = this.getAccount(customerID);
        account.deposit(amount);
        Transaction d = new Deposit(customerID, tellerID, amount);
        transactions.add(d);
    }

    public void withdrawal(int customerID, int tellerID, int amount) {
        BankAccount account = this.getAccount(customerID);
        if (account.getBalance() >= amount) {
            account.withdraw(amount);
            Transaction w = new Withdrawal(customerID, tellerID, amount);
            transactions.add(w);
        }else{
            throw new IllegalStateException("insufficiant balance");
        }
    }

}

/**
 * A headquarter Bank will be made up of multiple BankBranch objects,
 * and a single BankSystem which will be the central store for customer accounts and transactions.
 * Note that a customer could transact with multiple branches,
 * so we need to store their information in the BankSystem.
 *
 * A BankBranch will be responsible
 * for performing transactions on behalf of customers via available BankTellers.
 * We also add methods for cash to be collected from
 * and provided to the BankBranch (via the headquarter Bank).
 */
class BankBranch{
    private int cash;
    private List<BankTeller> tellers;
    String name;
    BankSystem bankSystem;
    Random rand = new Random();
    public BankBranch(BankSystem bankSystem, String name, int cashOnHand){
        this.name = name;
        this.cash = cashOnHand;
        this.bankSystem = bankSystem;
        tellers = new ArrayList<>();
    }

    public void addTeller(BankTeller teller){
        tellers.add(teller);
    }
    public BankTeller getAvailableTeller(){
        return this.tellers.get(rand.nextInt(this.tellers.size()));

    }
    public void deposit(int customerId, int amount){
        bankSystem.deposit(customerId,this.getAvailableTeller().getId(),amount);
    }
    public void withdrawal(int customerId, int amount){
        
    }

}

/**
 * The headquarter Bank will be responsible for managing all BankBranches,
 * as well as collecting cash from each branch.
 * For convenience, we also add a method to print all transactions that have taken place.
 */
class HeadBank{
    //
    public HeadBank(BankSystem bankSystem){

    }
}









void main() {


}
