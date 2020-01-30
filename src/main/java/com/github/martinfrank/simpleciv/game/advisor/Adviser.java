package com.github.martinfrank.simpleciv.game.advisor;

import java.util.List;

public interface Adviser<T> {

    List<Advice<T>> getAdvices();

}
