package com.github.martinfrank.simpleciv.game.advisor;

public abstract class Advice<T> {

    private final Priority priority;

    public enum Priority {LOW, MEDIUM, HIGH, EMERGENCY}

    public enum Military {RANGED, MELEE, DEFENDER}

    public enum Economy {INCOME, BALANCE;} //Balance = Guthaben


    public Advice(Priority priority) {
        this.priority = priority;
    }

    public Priority getPriority() {
        return priority;
    }

    public abstract T getAdvice();

    public static Advice<Military> militaryAdvice(final Priority priority, final Military military) {
        return new Advice<>(priority) {

            @Override
            public Military getAdvice() {
                return military;
            }
        };

    }

    public static Advice<Economy> economy(final Priority priority, final Economy economy) {
        return new Advice<>(priority) {

            @Override
            public Economy getAdvice() {
                return economy;
            }
        };

    }

}
