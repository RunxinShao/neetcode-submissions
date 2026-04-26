class StoreItem:
    def __init__(self,name:str, price:int):
        self.name = name  # Add: name, price
        self.price = price

chips = StoreItem("Chips", 1.99) # Don't modify this line

# TODO: Access the attributes of the chips object and display them

print(chips.name)
print(chips.price)
