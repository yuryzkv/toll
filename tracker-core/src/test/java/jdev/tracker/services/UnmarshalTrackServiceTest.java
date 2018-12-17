package jdev.tracker.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import javax.xml.stream.XMLStreamException;
import java.text.ParseException;
import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:Test.properties")
public class UnmarshalTrackServiceTest {

    @Value("${unmarshalTrack.prop}")
    String pathTrack;


    @Test
    public void unmarshal() throws XMLStreamException, ParseException {
        UnmarshalTrackService service = new UnmarshalTrackService();
        service.pathTrack = this.pathTrack;
        service.unmarshal();
        assertNotNull(service.landMarks);

    }
}