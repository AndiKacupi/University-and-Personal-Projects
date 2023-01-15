package mainengine;

public class MainEngineFactory {

	public IMainEngine createMainEngine(String engineType) {
		if(engineType.equals("MainEngine")){
			myclas m = new myclas();
			return m;
		}
		else
			return null;
	}

}