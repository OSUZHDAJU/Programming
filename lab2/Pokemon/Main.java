package Pokemon;

import Attack.*;
import ru.ifmo.se.pokemon.*;

import javax.swing.text.TabableView;

public class Main {
    public static void main(String[] args) {
        Battle ringMMA = new Battle();
        Aerodactyl aerodactyl = new Aerodactyl("Боец",35);
        Wooper wooper = new Wooper("Боец", 12);
        Quagsire quagsire = new Quagsire("Боец",20);
        Trapinch trapinch = new Trapinch("Боец",1);
        Vibrara vibrara = new Vibrara("Боец",62);
        Flygon flygon = new Flygon("Боец",68);
        ringMMA.addAlly(vibrara);
        ringMMA.addFoe(quagsire);
        ringMMA.go();

    }
}
