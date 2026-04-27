class ElectronicDevice:
    def __init__(self, brand: str, model: str):
        self.brand = brand
        self.model = model
    
    def turn_on(self) -> None:
        print("Device is turning on")
    
    def turn_off(self) -> None:
        print("Device is turning off")

class HealthDevice:
    def __init__(self, brand: str, model: str):
        self.brand = brand
        self.model = model
    
    def measure_heart_rate(self) -> None:
        print("Measuring heart rate")


# TODO: Create a SmartWatch class that inherits from both ElectronicDevice and HealthDevice
class SmartWatch(ElectronicDevice, HealthDevice):
    pass
# Do not modify the code below
smart_watch = SmartWatch("Apple", "Watch Series 6")
smart_watch.turn_on()
smart_watch.measure_heart_rate()
smart_watch.turn_off()
