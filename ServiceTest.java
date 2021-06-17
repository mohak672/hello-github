import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ServiceTest {

    @Mock
    Database databaseMock;

    @Test
    public void testQuery(){
        //assertNull(databaseMock);
        //assertNull() will be failed, because databaseMock is holding the object of Database
        assertNotNull(databaseMock);
        when(databaseMock.isAvailable()).thenReturn(true);
        //whenever isAvailable() is called it will return true
        Service service = new Service(databaseMock);
        boolean check = service.query("select * from table_name");
        //service.query() internally call isAvailable() of database object
        //so check will have true
        assertTrue(check);
    }

    @InjectMocks
    Service service;

    @Test
    public void ensureMockitoReturnsTheConfiguredValue(){
        when(databaseMock.getUniqueId()).thenReturn(42);
        assertEquals("Using database with id: 42",service.toString());
    }
}