package org.prezydium.akkalearning;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class NumbersPrinterActor extends AbstractActor {

    private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    public static Props props() {
        return Props.create(NumbersPrinterActor.class, NumbersPrinterActor::new);
    }

    public static class NumberMsg {

        private final String numberAsString;

        public NumberMsg(String numberAsString) {
            this.numberAsString = numberAsString;
        }
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(NumberMsg.class, number -> {
                    log.info(number.numberAsString);
                })
                .build();
    }
}
