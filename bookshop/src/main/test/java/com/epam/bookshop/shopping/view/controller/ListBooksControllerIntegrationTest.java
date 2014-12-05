package com.epam.bookshop.shopping.view.controller;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.Matchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.epam.bookshop.book.domain.BookFormat;
import com.epam.bookshop.book.repository.dao.BookDao;
import com.epam.bookshop.book.repository.domain.BookEntity;
import com.epam.bookshop.book.service.BookSearchService;

@Test
@WebAppConfiguration
@ContextHierarchy({@ContextConfiguration("classpath:spring/application-context.xml"), @ContextConfiguration("classpath:spring/mvc-context.xml"),})
public class ListBooksControllerIntegrationTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private WebApplicationContext webappContext;

    @InjectMocks
    @Autowired
    private BookSearchService bookSearchService;

    @Mock
    private BookDao bookDao;

    private MockMvc mockMvc;

    @BeforeMethod
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webappContext).build();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testListBooksShouldReturnAllBooksFromDb() throws Exception {
        //GIVEN in setup
        final List<BookEntity> bookEntityList = Arrays.asList(createBookEntity(0, "Title1"), createBookEntity(1, "Title2"));
        BDDMockito.given(bookDao.findByTitleIgnoreCaseLike(Mockito.anyString())).willReturn(bookEntityList);
        //WHEN
        final ResultActions performedRequest = mockMvc.perform(MockMvcRequestBuilders.get(ListBooksController.REQUEST_MAPPING));
        //THEN
        performedRequest.andExpect(MockMvcResultMatchers.status().isOk());
        performedRequest.andExpect(MockMvcResultMatchers.view().name("shopping"));
        performedRequest.andExpect(MockMvcResultMatchers.model().attribute("listBooksModel",
                Matchers.hasProperty("books", Matchers.equalTo(bookEntityList))));
    }

    private BookEntity createBookEntity(int id, String title) {
        final BookEntity bookEntity = new BookEntity();
        bookEntity.setAuthor("Author");
        bookEntity.setId(Long.valueOf(id));
        bookEntity.setFormat(BookFormat.ELECTRONIC.toString());
        bookEntity.setSynopsis("Synopsis");
        bookEntity.setTitle(title);
        return bookEntity;
    }

}
