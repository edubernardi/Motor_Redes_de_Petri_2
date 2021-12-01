package com.company;

import java.util.ArrayList;

public class FEL {
    ArrayList<Evento> eventList = new ArrayList<Evento>();

    public FEL() {
    }

    public void addEvent(Evento evento){
        eventList.add(evento);
    }

    public Evento getNext(){
        if (eventList.isEmpty()){
            return null;
        } else {
            Evento next = eventList.get(0);
            for (Evento e: eventList){
                if (e.getTec() < next.getTec()){
                    next = e;
                }
            }
            eventList.remove(next);
            return next;
        }
    }

    public boolean isEmpty(){
        return eventList.isEmpty();
    }
}

