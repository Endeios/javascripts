function echo(something){
	return something;
}

function test(){
	return true;
}

/**
 * POJSO (plain old javascript object)
 * */
function MyObject(service){
	
	print("Begin decl\n");
	
	this.getBean = function() {
		var retVal =new Packages.io.endeios.example.javascripts.ABean();//lets stick with java's long namespaces
		retVal.setName("javascript");
		retVal.setNumber(101);
		retVal.setReady(true);
		return retVal;
	},
	
	
	this.isActive=function(){
		return true;
	},
	
	
	this.getNum=function(){
		return 101;
	},
	
	this.serviceableResult=function(){
		var listOfStrings = service.getStrings();
		//print("List is "+listOfStrings+"\n");
		var retString = "";
		/*java like iteration */
		for(var i =0;i<listOfStrings.size();i++){
			var part = listOfStrings.get(i);
			//print("partString is "+part+"\n");
			/**
			 * @param service
			 * @returns {MyObject}
			 */
			retString = retString + part;
		}
		//print("retString is "+retString+"\n");
		return retString;
	};
	print("end decl\n");
}

/**
 * This is my factory !
 * */
function gimmeMyObject(service){
	return new MyObject(service);
};

//we dont want to mess up results do we?
false;