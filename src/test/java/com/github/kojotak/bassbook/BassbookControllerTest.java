package com.github.kojotak.bassbook;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.springframework.test.web.ModelAndViewAssert.assertModelAttributeAvailable;

@ExtendWith(MockitoExtension.class)
class BassbookControllerTest {

    @Mock
    private BassbookDatabase database;

    @InjectMocks
    private BassbookController controller;

    @Test
    public void homeContainsAuthors() {
        var mv = controller.home();

        assertModelAttributeAvailable(mv, "authors");
        Mockito.verify(database).getAuthors();
    }
}