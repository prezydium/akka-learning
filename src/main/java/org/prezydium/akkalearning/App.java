package org.prezydium.akkalearning;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import org.prezydium.akkalearning.noactor.RandomBinaryNumberNoActor;


public class App {
    public static void main(String[] args) {
        System.out.println("Hello Akka!");
        App app = new App();
        app.useActorSystem();
        System.out.println("###################");
        app.noActorSystem();
    }

    private void useActorSystem() {
        long startTime = System.currentTimeMillis();
        ActorSystem actorSystem = ActorSystem.create("prezydiumActorSystem");

        final ActorRef printerActor = actorSystem.actorOf(NumbersPrinterActor.props(), "printerActor");
        final ActorRef randomBinaryActorOne = actorSystem.actorOf(RandomBinaryNumberActor.props(printerActor), "randomBinaryActorOne");
        final ActorRef randomBinaryActorTwo = actorSystem.actorOf(RandomBinaryNumberActor.props(printerActor), "randomBinaryActorTwo");
        final ActorRef randomBinaryActorThree = actorSystem.actorOf(RandomBinaryNumberActor.props(printerActor), "randomBinaryActorThree");

        randomBinaryActorOne.tell(new RandomBinaryNumberActor.RandomBinaryActorTrigger(), ActorRef.noSender());
        randomBinaryActorOne.tell(new RandomBinaryNumberActor.RandomBinaryActorTrigger(), ActorRef.noSender());
        randomBinaryActorOne.tell(new RandomBinaryNumberActor.RandomBinaryActorTrigger(), ActorRef.noSender());

        randomBinaryActorTwo.tell(new RandomBinaryNumberActor.RandomBinaryActorTrigger(), ActorRef.noSender());
        randomBinaryActorTwo.tell(new RandomBinaryNumberActor.RandomBinaryActorTrigger(), ActorRef.noSender());
        randomBinaryActorTwo.tell(new RandomBinaryNumberActor.RandomBinaryActorTrigger(), ActorRef.noSender());

        randomBinaryActorThree.tell(new RandomBinaryNumberActor.RandomBinaryActorTrigger(), ActorRef.noSender());
        randomBinaryActorThree.tell(new RandomBinaryNumberActor.RandomBinaryActorTrigger(), ActorRef.noSender());
        randomBinaryActorThree.tell(new RandomBinaryNumberActor.RandomBinaryActorTrigger(), ActorRef.noSender());
        long endTime = System.currentTimeMillis();

        System.out.println("Main with actor system finished in: " + (endTime - startTime));
    }

    private void noActorSystem() {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 9; i++) {
            RandomBinaryNumberNoActor.generateRandomBinaryNumberAsString();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("No actor finished in: " + (endTime - startTime));
    }
}
