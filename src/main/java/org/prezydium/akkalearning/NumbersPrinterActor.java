package org.prezydium.akkalearning;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class NumbersPrinterActor extends AbstractActor {

    private static int count = 0;

    private static long startTime;
    private static long endTime;

    private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    public static Props props() {
        return Props.create(NumbersPrinterActor.class, () -> new NumbersPrinterActor(System.currentTimeMillis()));
    }

    public static class NumberMsg {

        private final String numberAsString;

        public NumberMsg(String numberAsString) {
            this.numberAsString = numberAsString;
        }

        public String getNumberAsString() {
            return numberAsString;
        }
    }

    public NumbersPrinterActor(long start) {
        startTime = start;
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(NumberMsg.class, number -> {
                    log.info(number.numberAsString);
                    count++;
                    if (count >= 50000) {
                        endTime = System.currentTimeMillis();
                        log.info("Processed " + count + " print messages in: " + (endTime - startTime));
                    }
                })
                .build();
    }

    @Override
    public void postStop() throws Exception {
        endTime = System.currentTimeMillis();
        log.info("Poststop!: " + (endTime - startTime));
    }
}
