importPackage(javax.swing);
//Can't tell why this gives error...
//importPackage(io.endeios.example.javascripts);

myobj = {
		
		getBean : function() {
			var retVal =new Packages.io.endeios.example.javascripts.ABean();//lets stick with java's long namespaces
			retVal.setName("javascript");
			retVal.setNumber(101);
			retVal.setReady(true);
			return retVal;
		},
		
		
		isActive:function(){
			return true;
		},
		
		
		getNum:function(){
			return 101;
		},
		
		serviceableResult:function(){
			var listOfStrings = service.getStrings();
			//print("List is "+listOfStrings+"\n");
			var retString = "";
			/*java like iteration */
			for(var i =0;i<listOfStrings.size();i++){
				var part = listOfStrings.get(i);
				//print("partString is "+part+"\n");
				retString = retString + part;
			}
			//print("retString is "+retString+"\n");
			return retString;
		}
};

function gimmeMyObject(){
	return myobj;
}

/**
 * RHino returns last object as result!
 * 
 * the other way around could have been creating a function that returns an object with desired implementations, and then
 * invoke the function and put an interface to the resulting object on java side
 * 
 *
 * https://www.informit.com/guides/content.aspx?g=java&seqNum=562
 * leveraging on this!
 * */
myobj;