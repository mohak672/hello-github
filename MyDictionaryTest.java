import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MyDictionaryTest {

    @Mock
    Map<String,String> wordMap;
    //create mock object of Map<String,String> wordMap

    @InjectMocks
    MyDictionary myDictionary = new MyDictionary();
    //MyDictionary class contains wordMap(instance variable)
    //@InjectMocks will fetch mock object of Map<String,String> wordMap into myDictionary

    @Test
    public void whenUseInjectMocksAnnotation_thenCorrect() {
        when(wordMap.get("walk")).thenReturn("run");
        assertEquals("run",myDictionary.getMeaning("walk"));
        //myDictionary.getMeaning() is internally using wordMap.get()

        //Mockito doesn't support injecting mocks into spy
        //If we want to use a mock with a spy, we can manually inject the mock through a constructor
        //instead of using the annotation, we can now create the spy manually:

        //withSpy
        MyDictionary myDictionaryWithSpy = spy(new MyDictionary(wordMap));
        when(wordMap.get("eat")).thenReturn("sleep");
        assertEquals("sleep",myDictionaryWithSpy.getMeaning("eat"));
    }
}