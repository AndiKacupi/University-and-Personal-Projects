package dataload;



public class ILoadFactory {
	
	/**
 	 * Creates different types of loaders based on the input parameter
 	 * 
 	 * @param className: type of loader
 	 * @param empPath a String with the path of the file with employees
 	 * @param dishPath a String with the path of the file with dishes
 	 * @param orderPath a String with the path of the file with orders
 	 * @return an IFullDataLoader loader to load all three files
 	 */
	public ILoadFactory createFullDataLoader(String className, String empPath, String dishPath, String orderPath){
		if(className.equals("FullDataLoader"))
			return new ILoadFactory();
		
		System.out.println("If the code got up to here, you passed a wrong argument to the FullDataLoader Factory");
		return null;
	}

}

