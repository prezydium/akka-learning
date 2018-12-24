package org.prezydium.akkalearning;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;

import java.util.Random;

public class RandomBinaryNumberActor extends AbstractActor {

    private final ActorRef numberPrinterActorRef;
    private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    public static Props props(ActorRef numberPrinterActorRef) {
        return Props.create(RandomBinaryNumberActor.class, () -> new RandomBinaryNumberActor(numberPrinterActorRef));
    }

    public static class RandomBinaryActorTrigger {

        public RandomBinaryActorTrigger() {

        }
    }

    public RandomBinaryNumberActor(ActorRef numberPrinterActorRef) {

        this.numberPrinterActorRef = numberPrinterActorRef;
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(RandomBinaryActorTrigger.class, randomBinaryActorTrigger -> {
                    log.info("Generating number");
                    numberPrinterActorRef.tell(new NumbersPrinterActor.NumberMsg(generateRandomBinaryNumberAsString()), getSelf());
                })
                .build();
    }

    private String generateRandomBinaryNumberAsString() {
        String result = Integer.toBinaryString(new Random().nextInt(10000));
        result = ("00000000000000" + result).substring(result.length());
        return result;
    }
}