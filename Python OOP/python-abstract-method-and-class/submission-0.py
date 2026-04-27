from abc import ABC, abstractmethod

class PaymentCard(ABC):
    def __init__(self, card_number: str, balance: float):
        self.card_number = card_number
        self.balance = balance

    # TODO: Implement the process_payment method
    @abstractmethod
    def process_payment(self, amount:float) -> str:
        pass

# TODO: Implement the DebitCard class
class DebitCard(PaymentCard):
    def process_payment(self,amount:float):
        if self.balance < amount:
            return "Insufficient funds"
        else:
            self.balance -= amount
            return "Payment successful"

# TODO: Implement the CreditCard class
class CreditCard(PaymentCard):
    def process_payment(self,amount:float):
        self.balance -= amount
        return "Payment successful"
        


# Don't modify the code below
debit_card = DebitCard("1234", 100.0)  # Card with $100 balance
credit_card = CreditCard("5678", 100.0) # Card with $100 balance

# Test debit card
print(debit_card.process_payment(50.0))  
print(debit_card.balance)                
print(debit_card.process_payment(100.0))  
print(debit_card.balance)    

# Test credit card
print(credit_card.process_payment(50.0))   
print(credit_card.balance)                
print(credit_card.process_payment(100.0))  
print(credit_card.balance)    
