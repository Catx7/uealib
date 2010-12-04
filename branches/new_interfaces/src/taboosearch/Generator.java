package taboosearch;

import java.util.Vector;

public class Generator implements core.Generator<LazyGeneration> {

	/*@SuppressWarnings("unchecked")
	public LazyGeneration getNext(core.Generation g) {
		assert g.size() == 1;
		
		Solution s = ((Generation)g).get(0);
		Vector<Move> moves = this.getAllMoves(s.length());
		LazyGeneration res = new LazyGeneration();
		
		for (Move m : moves) {
			res.add(new SolutionDiff(s, m));
		}
		
		return res;
	}*/
	
	/**
	 * Возвращает все сочетания по два из натурального ряда от 1 до n
	 * @param n
	 */
	private Vector<Move> getAllMoves(int n) {
		Vector<Move> result = new Vector<Move>();
		for ( int i = 1; i < n - 1; ++i )
			for ( int j = i + 1; j < n; ++j )
				result.add(new Move(i, j));
		
		return result;	
	}

	@Override
	public LazyGeneration getNext(LazyGeneration g) {
		assert g.size() == 1;
		
		Solution s = g.get(0).castToSolution();
		Vector<Move> moves = this.getAllMoves(s.length());
		LazyGeneration res = new LazyGeneration();
		
		for (Move m : moves) {
			res.add(new LazySolution(s, m));
		}
		
		return res;
	}

}
