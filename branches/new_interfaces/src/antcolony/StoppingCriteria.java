package antcolony;

import core.Generation;
import antcolony.Context;

public class StoppingCriteria implements core.StoppingCriteria{

	public StoppingCriteria() {
		
	}
	
	public boolean isSatisfied(Generation g) {
		Context con = Context.getInstance();
		if(con.getCount() == 0)
			return true;
		int c = con.getCount();
		con.setCount(--c);
		return false;
	}
}
