package com.centaurean.jetflow.sim.geometry;

/**
 * jetFlow
 * guillaume
 * 01/03/13 14:45
 */
public interface Point<C, P, V> {
    public C coordinates();

    public P translation(V v);

    public double distance(P p);

    @Override
    public boolean equals(Object o);
}