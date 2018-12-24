package org.prezydium.akkalearning;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

public class App {
    public static void main(String[] args) {
        System.out.println("Hello Akka!");
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
    }
}
