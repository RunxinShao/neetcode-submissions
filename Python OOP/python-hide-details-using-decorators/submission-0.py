class SuperHero:
    def __init__(self, name: str, health: int, power_level: int):
        self.name = name
        # TODO: Add the private attributes
        self.__health = health
        self.__power_level = power_level
    # TODO: Add the getter and setter methods
    @property
    def health(self):
        return self.__health
    @property
    def power_level(self):
        return self.__power_level
    @health.setter
    def health(self, health:int):
        if health > 100:
            print("You can't set the health to more than 100")
        elif health < 0:
            print("You can't set the health to less than 0")
        else:
            self.__health = health
    @power_level.setter
    def power_level(self, power_level):
        if power_level > 10:
            print("You can't set the power level to more than 10")
        elif power_level < 1 :
            print("You can't set the power level to less than 1")
        else:
            self.__power_level = power_level




# Don't change the following code
super_hero = SuperHero("Batman", 80, 9)

print(super_hero.health) # this should print 80
super_hero.health = 110 # this should print You can't set the health to more than 100

print(super_hero.power_level) # this should print 9
super_hero.power_level = 100 # this should print You can't set the power level to more than 10
super_hero.power_level = 0 # this should print You can't set the power level to less than 1


# TODO: print the hero's attributes 
print(f"{super_hero.name} has {super_hero.health} health and {super_hero.power_level} power level")

