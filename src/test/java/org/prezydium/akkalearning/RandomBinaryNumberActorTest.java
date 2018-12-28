package org.prezydium.akkalearning;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.testkit.javadsl.TestKit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class RandomBinaryNumberActorTest {

    private ActorSystem actorSystem;

    @Before
    public void setUp() {
        actorSystem = ActorSystem.create("mockedActorSystem");
    }

    @After
    public void teardown() {
        TestKit.shutdownActorSystem(actorSystem);
    }


    @Test
    public void shouldGenerateNumberMsgWithStringField() {
        //given
        TestKit testKit = new TestKit(actorSystem);
        ActorRef actorRef = actorSystem.actorOf(RandomBinaryNumberActor.props(testKit.getRef()));
        //when
        actorRef.tell(new RandomBinaryNumberActor.RandomBinaryActorTrigger(), testKit.getRef());
        NumbersPrinterActor.NumberMsg result = testKit.expectMsgClass(NumbersPrinterActor.NumberMsg.class);
        //then
        assertNotNull(result.getNumberAsString());
    }

    @Test
    public void shouldGenerateNumberMsgAlwaysWithSameLength() {
        //given
        TestKit testKit = new TestKit(actorSystem);
        ActorRef actorRef = actorSystem.actorOf(RandomBinaryNumberActor.props(testKit.getRef()));
        //when
        actorRef.tell(new RandomBinaryNumberActor.RandomBinaryActorTrigger(), testKit.getRef());
        NumbersPrinterActor.NumberMsg result1 = testKit.expectMsgClass(NumbersPrinterActor.NumberMsg.class);
        actorRef.tell(new RandomBinaryNumberActor.RandomBinaryActorTrigger(), testKit.getRef());
        NumbersPrinterActor.NumberMsg result2 = testKit.expectMsgClass(NumbersPrinterActor.NumberMsg.class);
        //then
        assertEquals(result1.getNumberAsString().length(), result2.getNumberAsString().length());
    }

}