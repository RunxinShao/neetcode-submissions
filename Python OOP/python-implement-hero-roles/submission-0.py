from abc import ABC, abstractmethod

# TODO: Create the Attacker, Defender, and Healer interfaces

class Attacker(ABC):
    @abstractmethod
    def attack(self):
        pass
class Defender(ABC):
    @abstractmethod
    def defend(self):
        pass
class Healer(ABC):
    @abstractmethod
    def heal(self):
        pass

# Don't modify the following code
class Knight(Attacker, Defender, Healer):
    def __init__(self, name: str) -> None:
        self.name = name
    
    def attack(self) -> None:
        print(f"{self.name} attacks with sword!")
        
    def defend(self) -> None:
        print(f"{self.name} raises shield!")
        
    def heal(self) -> None:
        print(f"{self.name} uses healing potion!")

hero = Knight("Sir Galahad")
hero.attack()  
hero.defend()  
hero.heal()
