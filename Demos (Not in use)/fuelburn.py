cruisespeed = int(input("Enter the cruise speed in knots: "))
fuelcapacity = int(input("Enter the fuel capacity in gallons: "))
airplanerange = int(input("Enter the airplane range in nautical miles: "))
consumptionpernauticalmile = fuelcapacity / airplanerange
fuelburnrate = consumptionpernauticalmile * cruisespeed
print("The fuel burn rate is", fuelburnrate, "gallons per hour.")