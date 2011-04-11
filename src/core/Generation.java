package core;

import java.util.List;

/**
 * Используется для хранения некоторого множества (или поколения) решений.
 */
public interface Generation<S extends Solution> extends List<S> {}
