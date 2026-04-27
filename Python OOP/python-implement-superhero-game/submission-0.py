from typing import override


class Hero:
    def __init__(self, name: str, power_level: int, health: int):
        self.name = name
        self.power_level = power_level
        self.health = health
    
    def use_power(self) -> str:
        return f"{self.name} uses their power!"


# TODO: Implement the FlightHero and StrengthHero classes
class FlightHero(Hero):
    def __init__(self, name: str, power_level: int, health: int,flight_speed:int):
        super().__init__(name,  power_level,health)
        self.flight_speed = flight_speed

    @override
    def use_power(self) -> str:
        return f"{self.name} flies at {self.flight_speed} mph!"
class StrengthHero(Hero):
    def __init__(self, name: str, power_level: int, health: int,lifting_capacity:int):
        super().__init__(name,  power_level,health)
        self.lifting_capacity = lifting_capacity
    @override
    def use_power(self) -> str:
        return f"{self.name} lifts {self.lifting_capacity} pounds!"



# Don't change the code below
flight_hero = FlightHero("Superman", 10, 100, 1000)
strength_hero = StrengthHero("Hulk", 10, 100, 1000)

print(flight_hero.name)
print(flight_hero.power_level)
print(flight_hero.health)
print(flight_hero.flight_speed)
print(flight_hero.use_power())

print(strength_hero.name)
print(strength_hero.power_level)
print(strength_hero.health)
print(strength_hero.lifting_capacity)
print(strength_hero.use_power())
