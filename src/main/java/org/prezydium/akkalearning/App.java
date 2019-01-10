package org.prezydium.akkalearning;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import org.prezydium.akkalearning.noactor.RandomBinaryNumberNoActor;

import java.util.logging.Level;
import java.util.logging.Logger;


public class App {

    private static Logger logger = Logger.getLogger(App.class.getName());

    public static void main(String[] args) {
        App app = new App();
        if (args.length == 0) {
            app.useActorSystem();
        } else {
            app.noActorSystem();
        }
        System.out.println("###################");
    }

    private void useActorSystem() {
        System.out.println("Hello Akka!");
        long startTime = System.currentTimeMillis();
        ActorSystem actorSystem = ActorSystem.create("prezydiumActorSystem");

        final ActorRef printerActor = actorSystem.actorOf(NumbersPrinterActor.props(), "printerActor");
        final ActorRef randomBinaryActorOne = actorSystem.actorOf(RandomBinaryNumberActor.props(printerActor), "One");
        final ActorRef randomBinaryActorTwo = actorSystem.actorOf(RandomBinaryNumberActor.props(printerActor), "Two");
        final ActorRef randomBinaryActorThree = actorSystem.actorOf(RandomBinaryNumberActor.props(printerActor), "Three");
        final ActorRef randomBinaryActorFour = actorSystem.actorOf(RandomBinaryNumberActor.props(printerActor), "Four");
        final ActorRef randomBinaryActorFive = actorSystem.actorOf(RandomBinaryNumberActor.props(printerActor), "Five");

        for (int i = 0; i < 10000; i++) {
            randomBinaryActorOne.tell(new RandomBinaryNumberActor.RandomBinaryActorTrigger(), ActorRef.noSender());
            randomBinaryActorTwo.tell(new RandomBinaryNumberActor.RandomBinaryActorTrigger(), ActorRef.noSender());
            randomBinaryActorThree.tell(new RandomBinaryNumberActor.RandomBinaryActorTrigger(), ActorRef.noSender());
            randomBinaryActorFour.tell(new RandomBinaryNumberActor.RandomBinaryActorTrigger(), ActorRef.noSender());
            randomBinaryActorFive.tell(new RandomBinaryNumberActor.RandomBinaryActorTrigger(), ActorRef.noSender());
        }
        long endTime = System.currentTimeMillis();
        logger.log(Level.INFO, "Main with actor system finished in: " + (endTime - startTime));
    }

    private void noActorSystem() {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 50000; i++) {
            RandomBinaryNumberNoActor.generateRandomBinaryNumberAsString();
        }
        long endTime = System.currentTimeMillis();
        logger.log(Level.INFO, "No actor finished in: " + (endTime - startTime));
    }
}
