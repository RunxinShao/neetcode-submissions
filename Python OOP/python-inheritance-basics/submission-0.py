class SmartDevice:
    def __init__(self, name: str):
        self.name = name

# TODO: Implement the SmartLight class
class SmartLight(SmartDevice):
    def turn_on(self):
        print(f"{self.name} is turned on")
    def turn_off(self):
        print(f"{self.name} is turned off")

# Don't change the code below
device = SmartLight("Smart Light")
device.turn_on()
device.turn_off()
