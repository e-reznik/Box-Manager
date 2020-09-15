package model;

public interface Coefficients {

    public final double COEENDURANCE = 0.4;
    public final double COESTRENGHT = 0.1;
    public final double COEMOTIVATION = 0.1;
    public final double COEABILITY = 0.2;
    public final double COEIMPACT = 0.2;

    public final double COELUCK = 0.1; // Luck factor, will be multiplied by a random number between 0 and 9

    public final double COEMOTIVATIONATTACK = 0.1; // Increase of motivation after successfull attack
    public final double COEMOTIVATIONDEFENSE = 0.1; // Increase of motivation after defense attack

    public final double COEENDURANCEATTACK = 0.1; // Decrease of endurance after attack
    public final double COEENDURANCEDEFENSE = 0.05; // Decrease of endurance after defense
}
