package Poker.util;
// Very quick implementation of the pair class.
// Sadly no library contains a decent implementation and this has been done to maintain compatibility with the existing code
public class Pair<K, V>
{
    public Pair(K fst, V snd) {
        this.fst = fst;
        this.snd = snd;
    }
    public K fst;
    public V snd;
}
