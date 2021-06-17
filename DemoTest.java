import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.AdditionalAnswers.*;

@ExtendWith(MockitoExtension.class)
//this will allow to use Mock annotations @Mock, @Spy etc.
//without this Mock annotations will not work
//and object created with @Mock, @Spy will throw nullPointerException
//manually created mock will not throw error (mock(ArrayList.class))
class DemoTest {

    @Test
    public void whenNotUseMockAnnotation_thenCorrect() {
        ArrayList<String> mockList = mock(ArrayList.class);
        //mock() will create the object of ArrayList
        //so mockList reference variable is not null

        assertNotNull(mockList);
        //as mockList is holding ArrayList object, above assertNotNull will be passed (mockList is not null)

        mockList.add("one");
        mockList.size();
        mockList.size();

        verify(mockList,times(1)).add("one");
        verify(mockList,times(2)).size();
        //verify() will check if particular call is made how many times, it will not modify the list (list size will remain 0)

        assertEquals(0, mockList.size());
        //check if list size is 0 or not

        when(mockList.size()).thenReturn(100);
        //when .size() is called return 100
        assertEquals(100, mockList.size());
        //here .size() is called so it will return 100, assertEquals() will check this 100 with expected 100
    }

    @Mock
    List mockList;
    //@Mock will create the object of ArrayList
    //so mockList reference variable is not null
    //here we are not providing the type, so any type will be added(String,Integer)

    @Test
    public void whenUseMockAnnotation_thenMockIsInjected(){
        mockList.add("two");
        verify(mockList,atLeast(1)).add("two");

        when(mockList.size()).thenReturn(5);
        assertEquals(5,mockList.size());
    }

    @Test
    public void whenNotUseSpyAnnotation_thenCorrect(){
        List<String> spyList = spy(new ArrayList<>());
        //alternate
        //List<String> list = new ArrayList<>();
        //List<String> spyList = spy(list);

        spyList.add("one");
        spyList.add("two");
        //spy() will actually add the "one" and "two" in the spyList

        verify(spyList).add("one");
        verify(spyList).add("two");
        assertEquals("one",spyList.get(0));
        assertEquals(2,spyList.size());

        when(spyList.size()).thenReturn(100);
        //here we are forcing spyList to return 100 when .size() is called
        assertEquals(100,spyList.size());
    }

    @Spy
    List<String> spyList = new ArrayList<>();

    @Test
    public void whenUseSpyAnnotation_thenSpyIsInjectedCorrectly() {
        spyList.add("one");
        assertEquals(1,spyList.size());
        assertEquals("one",spyList.get(0));

        doReturn("two").when(spyList).get(0);
        //here we are forcing spyList to return 100 when .size() is called
        assertEquals("two",spyList.get(0));
    }

    @Test
    public void whenNotUseCaptorAnnotation_thenCorrect() {
        ArgumentCaptor<String> argumentCaptor= ArgumentCaptor.forClass(String.class);
        mockList.add("one");
        verify(mockList,atLeast(1)).add("one");

        verify(mockList).add(argumentCaptor.capture());
        //argumentCaptor will capture the argument passed on .add() method of mockList
        //here passed argument is "one"
        assertEquals("one",argumentCaptor.getValue());
        //argumentCaptor.getValue() will return the passed "one" argument
        //assertEquals() will check "one" with argumentCaptor.getValue()
    }

    @Mock
    List<Integer> mockListInteger;

    @Captor
    ArgumentCaptor<Integer> argumentCaptor;

    @Test
    public void whenUseCaptorAnnotation_thenTheSam() {
        mockListInteger.add(1);
        verify(mockListInteger).add(argumentCaptor.capture());

        assertEquals(1,argumentCaptor.getValue());
    }

    @Test
    public final void answerTest(){
        List<String> list = new ArrayList<>();
        list.add("seven");
        when(mockList.add(anyString())).thenAnswer(invocation -> {
            String num = invocation.getArgument(0);
            return list.contains(num);
        });
        assertTrue(mockList.add("seven"));
        assertFalse(mockList.add("five"));
    }
}