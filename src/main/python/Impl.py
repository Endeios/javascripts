from io.endeios.example.javascripts import ABean, TestInterface

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
			return long(101)
		
	def serviceableResult(self):
			listOfStrings = service.getStrings()
			print("List is "+listOfStrings.toString()+"\n")
			retString = ""
			for part in listOfStrings:
				print("partString is "+part+"\n")
				retString = retString + part
				
			print("retString is "+retString+"\n")
			return retString
			

myobj = MyClass()
#if __name__ == "__main__":
if __name__ == "__builtin__":
	print "The builtin name is executes"
	MyClass()

