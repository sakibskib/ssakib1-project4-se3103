package model.observerPattern;

import model.Shooter;

public interface Subject {
   void addListener(Observer o);
   void removeListener(Observer o);
   //void notifyObserver(Shooter.Event event);
   


}
