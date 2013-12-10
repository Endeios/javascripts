# main reference https://github.com/jruby/jruby/wiki/CallingJavaFromJRuby
require 'logger'
logger = Logger.new(STDOUT)
logger.level = Logger::DEBUG
logger.debug("Debug")
logger.info("Info")
logger.warn("Warn")

require 'java'
#theese does not work, io is not in the standard inmport scope
#java_import io.endeios.example.javascripts.ABean
#java_import io.endeios.example.javascripts.TestInterface

ABean = Java::IoEndeiosExampleJavascripts::ABean
TestInterface = Java::IoEndeiosExampleJavascripts::TestInterface

logger.info("ABean is "+ABean.java_class.to_s)

class MyClass < Java::IoEndeiosExampleJavascripts::TestInterface
	def initialize
	end

	def getBean
		a = ABean.new
		a.setName("ruby")
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
	end
end

myobj = MyClass.new
logger.info("===END===")
