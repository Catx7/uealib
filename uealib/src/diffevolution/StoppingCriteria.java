package diffevolution;

import diffevolution.Context;
import core.Generation;

public class StoppingCriteria implements core.StoppingCriteria{

	public StoppingCriteria() {
		
	}
	
	public boolean isSatisfied(Generation g) {
		Context ctx = Context.getInstance();
		if(ctx.getCount() == 0)
			return true;
		int c = ctx.getCount();
		ctx.setCount(--c);
		return false;
	}
}
