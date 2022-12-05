package model.observerPattern;



import model.EnemyComposite;
import model.Shooter;
import model.EnemyComposite.Event;

public interface Subject {
   void addListener(Observer o);
   void removeListener(Observer o);
   void notifyObserver(EnemyComposite.Event event);
   


}
