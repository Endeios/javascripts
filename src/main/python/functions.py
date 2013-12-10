from io.endeios.example.javascripts import ABean, TestInterface
from java.lang import Long
def myecho(a):
	return a

def greet():
	return "Hello Python"

class MyClass(TestInterface):
	def __init__(self):
		pass
	def getBean(self):
			retVal =ABean() 
			retVal.setName("python")
			retVal.setNumber(101)
			retVal.setReady(True)
			return retVal
		
		
	def isActive(self):
			return True
		
		
	def getNum(self):
			# NOTE Long.parseLong("101") returns a java.math.BigIntger on java side...
			return Long(101)
		
	def serviceableResult(self):
			listOfStrings = service.getStrings()
			print("List is "+listOfStrings.toString()+"\n")
			retString = ""
			for part in listOfStrings:
				print("partString is "+part+"\n")
				retString = retString + part
				
			print("retString is "+retString+"\n")
			return retString



def gimmeMyObject():
	return MyClass()
