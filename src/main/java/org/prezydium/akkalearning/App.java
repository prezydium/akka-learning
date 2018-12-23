package org.prezydium.akkalearning;

import akka.actor.ActorSystem;

public class App {
    public static void main(String[] args) {
        System.out.println("Hello Akka!");
        ActorSystem actorSystem = ActorSystem.create("prezydiumActorSystem");
    }
}
