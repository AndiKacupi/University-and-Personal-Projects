package datamodel;



public class IResultFactory {
	public IResult createResult(String name) {
		if(name.equals("database")){
			database m = new database();
			return m;
		}
		else if(name.equals("MeasurementRecord")){
			MeasurementRecord m = new MeasurementRecord();
			return m;
		}
		else
			return null;
	}

}