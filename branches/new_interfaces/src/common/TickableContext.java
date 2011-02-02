package common;

public interface TickableContext extends core.Context {
	public void tick();
	public int getTicks();
}
