def greet()
	return "Hello JRuby!"
end

# main reference https://github.com/jruby/jruby/wiki/CallingJavaFromJRuby
require 'java'
require 'logger'
logger = Logger.new(STDOUT)
logger.level = Logger::DEBUG
logger.debug("Debug")
logger.info("Info")
logger.warn("Warn")

#these does not work, io is not in the standard import scope
#java_import io.endeios.example.javascripts.ABean
#java_import io.endeios.example.javascripts.TestInterface

ABean = Java::IoEndeiosExampleJavascripts::ABean
#TestInterface = Java::IoEndeiosExampleJavascripts::TestInterface

logger.info("ABean is "+ABean.java_class.to_s)

class MyClass# < Java::IoEndeiosExampleJavascripts::TestInterface
	include Java::IoEndeiosExampleJavascripts::TestInterface#io.endeios.example.javascripts.TestInterface
	
	def initialize
	end

	def getBean
		a = ABean.new
		a.setName("jruby")
		a.setNumber(101)
		a.setReady(true)
		return a
	end

	def isActive
		return true
	end

	def getNum
		return 101
	end

	def serviceableResult
		listOfStrings = $service.getStrings()
		result = ""
		listOfStrings.each { |element| result = result+element }
		puts "Result is #{result}"
		return result
	end
end
#
$myobj = MyClass.new
logger.info($myobj.java_class.to_s)
logger.info("===END===")

def gimmeMyObject()
	return MyClass.new
end
