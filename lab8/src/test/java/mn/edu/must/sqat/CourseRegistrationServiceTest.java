
package mn.edu.must.sqat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.*;

@ExtendWith(MockitoExtension.class)
class CourseRegistrationServiceTest {

    @Mock
    StudentRepository repository;

    @Mock
    EmailSender emailSender;

    CourseRegistrationService service;

    @BeforeEach
    void setUp() {
        service = new CourseRegistrationService(repository, emailSender);
    }

    // A1: Идэвхтэй оюутан амжилттай бүртгүүлэх
    @Test
    void activeStudent_whenValid_returnsOK() {
        Student student = new Student(
                "S001", "student@must.edu.mn", true, List.of()
        );

        when(repository.findStudent("S001")).thenReturn(student);
        when(repository.courseExists("CS101")).thenReturn(true);
        when(repository.prerequisites("CS101")).thenReturn(List.of());

        String result = service.register("S001", "CS101");

        assertEquals("OK", result);
        verify(repository).saveRegistration("S001", "CS101");
        verify(emailSender).send(
                eq("student@must.edu.mn"),
                anyString(),
                contains("CS101")
        );
    }

    // A2: Байхгүй оюутан
    @Test
    void missingStudent_whenRegistering_returnsError() {
        when(repository.findStudent("S999")).thenReturn(null);

        String result = service.register("S999", "CS101");

        assertEquals("ERROR_NO_STUDENT", result);
        verify(repository, never()).saveRegistration(anyString(), anyString());
        verify(emailSender, never()).send(
                anyString(), anyString(), anyString()
        );
    }

    // A3: Идэвхгүй оюутан
    @Test
    void inactiveStudent_whenRegistering_returnsError() {
        Student student = new Student(
                "S002", "inactive@must.edu.mn", false, List.of()
        );

        when(repository.findStudent("S002")).thenReturn(student);

        String result = service.register("S002", "CS101");

        assertEquals("ERROR_INACTIVE_STUDENT", result);
        verify(repository, never()).saveRegistration(anyString(), anyString());
        verifyNoInteractions(emailSender);
    }

    // A4: Өмнөх холбоо хичээл дутуу
    @Test
    void missingPrerequisites_whenRegistering_returnsError() {
        Student student = new Student(
                "S003", "student@must.edu.mn", true, List.of()
        );

        when(repository.findStudent("S003")).thenReturn(student);
        when(repository.courseExists("CS201")).thenReturn(true);
        when(repository.prerequisites("CS201"))
                .thenReturn(List.of("CS101"));

        String result = service.register("S003", "CS201");

        assertEquals("ERROR_PREREQUISITES", result);
        verify(repository, never()).saveRegistration(anyString(), anyString());
        verifyNoInteractions(emailSender);
    }

    // A5: И-мэйл илгээхэд exception гарах
    @Test
    void emailFailure_whenRegistering_stillReturnsOK() {
        Student student = new Student(
                "S004", "student@must.edu.mn", true, List.of()
        );

        when(repository.findStudent("S004")).thenReturn(student);
        when(repository.courseExists("CS101")).thenReturn(true);
        when(repository.prerequisites("CS101")).thenReturn(List.of());

        doThrow(new RuntimeException("SMTP error"))
                .when(emailSender)
                .send(anyString(), anyString(), anyString());

        String result = service.register("S004", "CS101");

        assertEquals("OK", result);
        verify(repository).saveRegistration("S004", "CS101");
    }

    // A6: null оролтыг шалгах
    @Test
    void nullInput_whenRegistering_throwsException() {
        assertThrows(IllegalArgumentException.class,
                () -> service.register(null, "CS101"));

        assertThrows(IllegalArgumentException.class,
                () -> service.register("S001", null));

        verifyNoInteractions(repository, emailSender);
    }

    // A7: И-мэйлийн body-д хичээлийн код орсон эсэх
    @Test
    void successfulRegistration_whenEmailSent_containsCourseCode() {
        Student student = new Student(
                "S005", "student@must.edu.mn", true, List.of()
        );

        when(repository.findStudent("S005")).thenReturn(student);
        when(repository.courseExists("CS101")).thenReturn(true);
        when(repository.prerequisites("CS101")).thenReturn(List.of());

        String result = service.register("S005", "CS101");

        assertEquals("OK", result);

        ArgumentCaptor<String> bodyCaptor =
                ArgumentCaptor.forClass(String.class);

        verify(emailSender).send(
                eq("student@must.edu.mn"),
                anyString(),
                bodyCaptor.capture()
        );

        assertTrue(bodyCaptor.getValue().contains("CS101"));
    }

    // A8: Гурван алдааны нөхцөлийг параметржүүлж шалгах
    @ParameterizedTest
    @CsvSource({
            "MISSING, ERROR_NO_STUDENT",
            "INACTIVE, ERROR_INACTIVE_STUDENT",
            "NO_COURSE, ERROR_NO_COURSE"
    })
    void invalidRegistration_whenDifferentConditions_returnsExpectedError(
            String scenario, String expected) {

        if ("MISSING".equals(scenario)) {
            when(repository.findStudent("S006")).thenReturn(null);
        } else if ("INACTIVE".equals(scenario)) {
            Student student = new Student(
                    "S006", "student@must.edu.mn", false, List.of()
            );
            when(repository.findStudent("S006")).thenReturn(student);
        } else if ("NO_COURSE".equals(scenario)) {
            Student student = new Student(
                    "S006", "student@must.edu.mn", true, List.of()
            );
            when(repository.findStudent("S006")).thenReturn(student);
            when(repository.courseExists("CS101")).thenReturn(false);
        }

        String result = service.register("S006", "CS101");

        assertEquals(expected, result);
        verify(repository, never()).saveRegistration(anyString(), anyString());
        verifyNoInteractions(emailSender);
    }
}
