class Hero:
    def __init__(self, name: str, base_power: int, attributes: dict):
        self.name = name
        self.base_power = base_power
        self.attributes = attributes
    
    # TODO: Add calculate_effective_power static method here
    @staticmethod
    def calculate_effective_power(base_power:int, attributes:dict) ->int:
        attribute_bonus = sum(attributes.values()) / len(attributes)

        return round(base_power*(1+attribute_bonus),1)



# Don't change the following code
base_power1 = 8
attributes1 = {'strength': 7, 'speed': 6, 'intelligence': 8}

power1 = Hero.calculate_effective_power(base_power1, attributes1)
print(f"Base Power: {base_power1}")
print(f"Attributes: {attributes1}")
print(f"Effective Power: {power1}\n")

base_power2 = 6
attributes2 = {'strength': 4, 'speed': 5, 'intelligence': 6}

power2 = Hero.calculate_effective_power(base_power2, attributes2)
print(f"Base Power: {base_power2}")
print(f"Attributes: {attributes2}")
print(f"Effective Power: {power2}")
