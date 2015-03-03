package funcons.core.experiments;

import funcons.entities.Env;
import funcons.entities.Value;

public class EnvDeclaration implements Declaration {
	private Env<Value> env;

	public EnvDeclaration(Env<Value> env){
		this.env = env;
	}
	
	public Env<Value> getEnv(){
		return this.env;
	}
}
