package worker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import worker.Worker;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

class UnitWorker {

    private final TestLogHandler logHandler = new TestLogHandler();
    private final Logger fizzBuzzLogger = Logger.getLogger(Worker.FizzBuzz.class.getName());

    @BeforeEach
    public void setUpStreams() {
	fizzBuzzLogger.addHandler(logHandler);
        fizzBuzzLogger.setLevel(Level.INFO);
        fizzBuzzLogger.setUseParentHandlers(false);
    }

    @AfterEach
    public void restoreStreams() {
        fizzBuzzLogger.removeHandler(logHandler);
        logHandler.close();
    }

    private static final class TestLogHandler extends Handler {
        private final StringBuilder messages = new StringBuilder();

        @Override
        public void publish(LogRecord record) {
            messages.append(record.getMessage()).append('\n');
        }

        @Override
        public void flush() {
        }

        @Override
        public void close() {
        }

        String getMessages() {
            return messages.toString();
        }

        void reset() {
            messages.setLength(0);
        }
    }

    @Test
    void testFizzBuzzGenerate() {
        // Test with limit = 5
        Worker.FizzBuzz.generate(5);
        Assertions.assertEquals("1\n2\nFizz\n4\nBuzz\n", logHandler.getMessages());
        logHandler.reset();

        // Test with limit = 15
        Worker.FizzBuzz.generate(15);
	Assertions.assertEquals("1\n2\nFizz\n4\nBuzz\nFizz\n7\n8\nFizz\nBuzz\n11\nFizz\n13\n14\nFizzBuzz\n", logHandler.getMessages());
        logHandler.reset();

        // Test with limit = 0
        Worker.FizzBuzz.generate(0);
	Assertions.assertEquals("", logHandler.getMessages());
        logHandler.reset();

        // Test with limit = 1
        Worker.FizzBuzz.generate(1);
	Assertions.assertEquals("1\n", logHandler.getMessages());
        logHandler.reset();
    }

    @Test
    void sample1() {

    }


    @Test
    void sample2() {

    }

    @Test
    void sample3() {

    }

    @Test
    void sample4() {

    }
   @Test
    void sample5() {

    }  
    @Test
    void sample6() {

    }  
    @Test
    void sample7() {

    }  
    @Test
    void sample8() {

    }  
    @Test
    void sample9() {

    }  
    @Test
    void sample10() {

    }  
    @Test
    void sample16() {

    }  
     @Test
    void sample17() {

    }  

 @Test
    void sample18() {

    }  
 @Test
    void sample19() {

    }  
  @Test
    void sample20() {

    }  
}


